package com.androidapps.nychighschoolslists

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter

/*This data binding adapter will be used in xml file */

@BindingAdapter("app:goneUnless")

fun goneUnless(view: TextView, viewValue: String?) {
    if (viewValue == null) {
        view.visibility = View.GONE
    } else {
        if (view.text.equals("Requirements:"))
            view.visibility = View.VISIBLE
        else {
            view.visibility = View.VISIBLE
            view.text = viewValue
        }
    }

}

