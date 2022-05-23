package com.androidapps.nychighschoolslists.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidapps.nychighschoolslists.viewmodel.MainViewModel
import com.androidapps.nychighschoolslists.R
import com.androidapps.nychighschoolslists.adapter.SchoolListAdapter

import dagger.hilt.android.AndroidEntryPoint


/*App features:
* The app displays a list of schools with a total average SAT score.
* If the user clicks on the school name, it will take the user to ScoreDetailsActivity.
* ScoreDetailsActivity displays detailed scores, admission requirements, and contact information for the school.
* If the user has a network connection, the app will make an asynchronous API request to get a list of schools and a list of scores.
* Once the JSON response is received, the app will update the local database for offline support and display the list of schools in the recycler view.
* If the user doesn't have a network connection, the app won't make network requests and instead it will display a list of schools from the local ROOM database.
* Users can sort schools by school name or by total score.*/



@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var schoolAdapter: SchoolListAdapter
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.result_recycler_view)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        viewModel.isDatabaseUpdated.observe(this, Observer { isDbUpdated ->
            if (isDbUpdated)
                viewModel.getSchoolDetailsFromLocalDatabase()
        })
        viewModel.liveSchoolsWithScore.observe(this, Observer { schoolListItem ->
            if (schoolListItem.isNotEmpty()) {
                schoolAdapter = SchoolListAdapter(schoolListItem, viewModel)
                recyclerView.adapter = schoolAdapter


            }
        })

        viewModel.selectedSchool.observe(this, Observer {

            val intent = Intent(this, ScoreDetailsActivity::class.java).apply {
                putExtra("SelectedSchool", it)
            }
            startActivity(intent)
        })
    }

    /*
    * If the user opens the app from the recent app tray, onStart() will be invoked, not onCreate().
    * So this is the best place to check the network connection and respond accordingly.
    *  */

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onStart() {
        super.onStart()
        updateSchoolList()

    }


    fun updateSchoolList() {
        var isNetworkConnected = viewModel.isNetworkAvailable()
        if (!isNetworkConnected) {

            viewModel.getSchoolDetailsFromLocalDatabase()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.Sort_by_name)
            viewModel.sortBySchoolName()
        else
            viewModel.sortByScore()
        return super.onOptionsItemSelected(item)
    }


}