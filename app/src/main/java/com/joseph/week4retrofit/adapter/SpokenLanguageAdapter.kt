package com.joseph.week4retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.joseph.week4retrofit.R
import com.joseph.week4retrofit.model.Genre
import com.joseph.week4retrofit.model.SpokenLanguage


class SpokenLanguageAdapter(private val dataSet: List<SpokenLanguage>) :
        RecyclerView.Adapter<SpokenLanguageAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sLanguageButton: Button

        init {
            // Define click listener for the ViewHolder's View.
            sLanguageButton = view.findViewById(R.id.SpokenLanguage_button)
        }
        fun setData(dataSet: List<SpokenLanguage>, position: Int){
            sLanguageButton.text = dataSet[position].name
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_spokenlanguage, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.setData(dataSet,position)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
