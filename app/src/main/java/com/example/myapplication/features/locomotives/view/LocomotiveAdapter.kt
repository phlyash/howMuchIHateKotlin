package com.example.myapplication.features.locomotives.view

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
import com.example.myapplication.features.locomotives.models.Locomotive

class LocomotiveAdapter(val fragmentManager: FragmentManager) : PagingDataAdapter<Locomotive, LocomotiveAdapter.LocomotiveHolder>(LocomotiveDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocomotiveHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.locomotive_adapter, parent,false)
        return LocomotiveHolder(view)
    }

    override fun onBindViewHolder(holder: LocomotiveHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    inner class LocomotiveHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameText: TextView = itemView.findViewById(R.id.locomotive_name)
        val locomotiveTypeText: TextView = itemView.findViewById(R.id.locomotive_type)
        val imageView: ImageView = itemView.findViewById(R.id.image)

        fun bind(locomotive: Locomotive) {
            nameText.text = "Locomotive name: ${locomotive.name}"
            locomotiveTypeText.text = "Locomotive type: ${locomotive.loco_type}"

            when(locomotive.loco_type) {
                1, 2 -> imageView.setImageResource(R.drawable.loco_old)
                9 -> imageView.setImageResource(R.drawable.loco_cool)
                10 -> imageView.setImageResource(R.drawable.loco_new)
                else -> imageView.setImageResource(R.drawable.loco_rzd)
            }

            itemView.setOnClickListener {
                fragmentManager.beginTransaction().apply {
                    replace(R.id.flFragment, LocomotiveEditFragment.newInstance(locomotive.id))
                    commit()
                }
            }
        }
    }

    companion object {
        private val LocomotiveDiffCallback = object : DiffUtil.ItemCallback<Locomotive>() {
            override fun areItemsTheSame(oldItem: Locomotive, newItem: Locomotive): Boolean =
                oldItem.id == newItem.id

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Locomotive, newItem: Locomotive): Boolean =
                oldItem == newItem
        }
    }
}
