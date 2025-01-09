package com.example.myapplication.features.wagons.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.features.wagons.models.Wagon

class WagonAdapter(val fragmentManager: FragmentManager) : PagingDataAdapter<Wagon, WagonAdapter.WagonHolder>(WagonDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WagonHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.wagon_adapter, parent,false)
        return WagonHolder(view)
    }

    override fun onBindViewHolder(holder: WagonHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class WagonHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val wagonTrainText: TextView = itemView.findViewById(R.id.wagon_train)
        val wagonTypeText: TextView = itemView.findViewById(R.id.wagon_type)
        val imageView: ImageView = itemView.findViewById(R.id.image)

        fun bind(wagon: Wagon) {
            wagonTrainText.text = "Train: ${wagon.train_id}"
            wagonTypeText.text = "Wagon type: ${wagon.wagon_type}"

            when (wagon.wagon_type) {
                1 -> imageView.setImageResource(R.drawable.wagon_grain)
                5 -> imageView.setImageResource(R.drawable.wagon_people)
                6 -> imageView.setImageResource(R.drawable.wagon_cattle)
                7, 8 -> imageView.setImageResource(R.drawable.wagon_oil)
                else -> imageView.setImageResource(R.drawable.wagon_rzd)
            }

            itemView.setOnClickListener {
                fragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, WagonEditFragment.newInstance(wagon.id))
                    commit()
                }
            }
        }
    }

    companion object {
        private val WagonDiffCallback = object : DiffUtil.ItemCallback<Wagon>() {
            override fun areItemsTheSame(oldItem: Wagon, newItem: Wagon): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Wagon, newItem: Wagon): Boolean =
            oldItem == newItem
        }
    }
}
