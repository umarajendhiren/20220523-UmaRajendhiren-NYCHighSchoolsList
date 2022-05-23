package com.androidapps.nychighschoolslists.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.androidapps.nychighschoolslists.R
import com.androidapps.nychighschoolslists.model.SchoolListResponseItem
import com.androidapps.nychighschoolslists.databinding.ActivityScoreDetailsBinding

/*ScoreDetailsActivity displays detailed scores, admission requirements, and contact information for the school
* Used data binding to avoid more boilerplate code (findViewById())*/
class ScoreDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val selectedSchool = intent.getParcelableExtra<SchoolListResponseItem>("SelectedSchool")
        val binding: ActivityScoreDetailsBinding = DataBindingUtil.setContentView(
            this, R.layout.activity_score_details
        )
        binding.model = selectedSchool


    }
}