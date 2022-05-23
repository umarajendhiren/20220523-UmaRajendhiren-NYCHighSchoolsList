package com.androidapps.nychighschoolslists.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.androidapps.nychighschoolslists.viewmodel.MainViewModel
import com.androidapps.nychighschoolslists.R
import com.androidapps.nychighschoolslists.model.SchoolListResponseItem

/*Adapter for recyclerView to bind view with data*/
class SchoolListAdapter(

    val schoolList: List<SchoolListResponseItem>, val viewModel: MainViewModel
) : RecyclerView.Adapter<SchoolListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvSchooolName: TextView
        val tvCity: TextView
        val tvTotalScore: TextView
        var cardView: CardView

        init {
            tvSchooolName = view.findViewById(R.id.tv_school_name)
            tvCity = view.findViewById(R.id.tv_city)
            tvTotalScore = view.findViewById(R.id.tv_total_score)
            cardView = view.findViewById(R.id.cardView)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recyclerview_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvSchooolName.text = schoolList[position].school_name
        viewHolder.tvCity.text = schoolList[position].city + ", " + schoolList[position].state_code

        viewHolder.tvTotalScore.text =
            "Total Average SAT Score : ${schoolList[position].total_sat_score.toString()}"

        viewHolder.cardView.setOnClickListener(View.OnClickListener {
            viewModel.onClickSchoolName(
                schoolList[position]
            )
        })

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = schoolList.size

}

