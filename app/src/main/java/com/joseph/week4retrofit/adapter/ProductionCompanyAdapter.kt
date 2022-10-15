package com.joseph.week4retrofit.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.joseph.week4retrofit.R
import com.joseph.week4retrofit.helper.Const
import com.joseph.week4retrofit.model.ProductionCompany


class ProductionCompanyAdapter(private val dataSet: List<ProductionCompany>) :
        RecyclerView.Adapter<ProductionCompanyAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imagePC: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            imagePC = view.findViewById(R.id.imageView_PC)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item

        val view = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.card_productioncompany, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        Glide.with(viewHolder.itemView.context)
            .load(Const.IMG_URL + dataSet[position].logo_path)
            .into(viewHolder.imagePC)
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
