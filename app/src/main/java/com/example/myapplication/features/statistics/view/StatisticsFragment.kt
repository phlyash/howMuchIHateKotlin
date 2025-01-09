package com.example.myapplication.features.statistics.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class StatisticsFragment : Fragment() {
    private val vm: StatisticsVM by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vm.fetchStatistics()

        val totalWagons = requireView().findViewById<TextView>(R.id.total_wagons)
        val mostPopularWagon = requireView().findViewById<TextView>(R.id.popular_wagon_type)
        val mostPopularLocomotive = requireView().findViewById<TextView>(R.id.popular_locomotive)
        val mostPopularRoute = requireView().findViewById<TextView>(R.id.popular_route)

        vm.statistics.observe(viewLifecycleOwner) {
            totalWagons.text = "most popular wagon: ${it.total_wagons}"
            mostPopularWagon.text = "most popular wagon type: ${it.most_popular_wagon_type}"
            mostPopularLocomotive.text = "most popular locomotive: ${it.most_popular_locomotive}"
            mostPopularRoute.setText("most popular route: ${it.most_popular_route}")
        }
    }

        companion object {
        @JvmStatic
        fun newInstance() =
            StatisticsFragment()
    }
}