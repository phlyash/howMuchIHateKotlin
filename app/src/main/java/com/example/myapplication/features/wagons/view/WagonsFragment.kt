package com.example.myapplication.features.wagons.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class WagonsFragment : Fragment() {
    private var searchJob: Job? = null
    private val vm: WagonsVM by viewModel()
    private lateinit var adapter: WagonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_wagons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.menu_top)
        (activity as? AppCompatActivity)?.setSupportActionBar(toolbar)
        setHasOptionsMenu(true)

        adapter = WagonAdapter(requireFragmentManager())

        val recyclerView = requireView().findViewById<RecyclerView>(R.id.wagon_recycler)
        recyclerView.layoutManager = LinearLayoutManager(requireView().context)
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)
        loadItems()
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

    companion object {
        @JvmStatic
        fun newInstance() = WagonsFragment()
    }

    public fun loadItems(query: String = "") {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            vm.getWagons(query).collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
}