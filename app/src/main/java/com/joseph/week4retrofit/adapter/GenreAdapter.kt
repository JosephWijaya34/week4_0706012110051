package com.joseph.week4retrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.joseph.week4retrofit.R
import com.joseph.week4retrofit.model.Genre


class GenreAdapter(private val dataSet: List<Genre>) :
        RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val genreButton: Button

        init {
            // Define click listener for the ViewHolder's View.
            genreButton = view.findViewById(R.id.genre_button)
        }
        fun setData(dataSet: List<Genre>, position: Int){
            genreButton.text = dataSet[position].name
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_genre, viewGroup, false)

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
