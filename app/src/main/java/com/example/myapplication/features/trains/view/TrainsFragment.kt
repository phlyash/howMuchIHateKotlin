package com.example.myapplication.features.trains.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.google.android.material.appbar.AppBarLayout
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrainsFragment : Fragment() {
    private var searchJob: Job? = null
    private val vm: TrainsVM by viewModel()
    private lateinit var adapter: TrainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_trains, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.menu_top)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        adapter = TrainAdapter(requireFragmentManager())

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.train_recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireView().context)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        loadItems()
    }

    companion object {
        @JvmStatic
        fun newInstance() = TrainsFragment()
    }

    public fun loadItems(query: String = "") {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            vm.getTrains(query).collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_top, menu)

        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.queryHint = "Search items"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { loadItems(it) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { loadItems(it) }
                return true
            }
        })
    }
}