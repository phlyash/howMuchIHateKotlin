package com.example.myapplication.features.trains.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toolbar
import androidx.fragment.app.FragmentManager
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.features.trains.models.Train
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrainAdapter(val fragmentManager: FragmentManager) : PagingDataAdapter<Train, TrainAdapter.TrainHolder>(TrainDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.train_adapter, parent,false)
        return TrainHolder(view)
    }

    override fun onBindViewHolder(holder: TrainHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class TrainHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.train_name)
        val trainTypeText: TextView = itemView.findViewById(R.id.train_type)
        val locomotiveText: TextView = itemView.findViewById(R.id.locomotive)
        val routeText: TextView = itemView.findViewById(R.id.route)
        val imageView: ImageView = itemView.findViewById(R.id.image)

        fun bind(train: Train) {
            nameText.text = "Train name: ${train.name}"
            trainTypeText.text = "Train type: ${train.train_type_name}"
            locomotiveText.text = "Locomotive: ${train.locomotive_name}"
            routeText.text = "route: ${train.route}"

            when (train.train_type) {
                1 -> imageView.setImageResource(R.drawable.train_cargo)
                2 -> imageView.setImageResource(R.drawable.train_people)
                3 -> imageView.setImageResource(R.drawable.train_mail)
                else -> imageView.setImageResource(R.drawable.rzd1)
            }

            itemView.setOnClickListener {
                fragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, TrainEditFragment.newInstance(train.id))
                    val idk = itemView.findViewById<Toolbar>(R.id.menu_top)
                    commit()
                }
            }
        }
    }

    companion object {
        private val TrainDiffCallback = object : DiffUtil.ItemCallback<Train>() {
            override fun areItemsTheSame(oldItem: Train, newItem: Train): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Train, newItem: Train): Boolean =
                oldItem == newItem
        }
    }
}
