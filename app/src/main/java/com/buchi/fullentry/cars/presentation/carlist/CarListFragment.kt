package com.buchi.fullentry.cars.presentation.carlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.buchi.fullentry.R
import com.buchi.fullentry.cars.adapter.CarListAdapter
import com.buchi.fullentry.cars.adapter.CarMakeListAdapter
import com.buchi.fullentry.cars.model.Car
import com.buchi.fullentry.cars.model.CarMake
import com.buchi.fullentry.cars.presentation.CarsViewModel
import com.buchi.fullentry.databinding.FragmentCarListBinding
import com.buchi.fullentry.utilities.Constants
import com.buchi.fullentry.utilities.viewvisibility
import com.google.android.material.snackbar.Snackbar
import com.mig35.carousellayoutmanager.CarouselLayoutManager
import com.mig35.carousellayoutmanager.CarouselZoomPostLayoutListener
import com.mig35.carousellayoutmanager.CenterScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import kotlin.math.floor

@AndroidEntryPoint
class CarListFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {
    lateinit var binding: FragmentCarListBinding
    private val viewModel: CarListViewModel by viewModels()
    private val activityViewModel: CarsViewModel by activityViewModels()
    private val listAdapter: CarListAdapter by lazy {
        CarListAdapter(carListListener)
    }

    private val makeListAdapter: CarMakeListAdapter by lazy {
        CarMakeListAdapter(requireActivity(), carMakeListListener)
    }
    private val carListListener = object : CarListAdapter.CarListListener {
        override fun onItemClicked(item: Car) {
            navigateToDetailPage(item)
        }
    }

    private val carMakeListListener = object : CarMakeListAdapter.CarMakeListListener {
        override fun onItemClicked(item: CarMake) {
            fetchCarByMake(item)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        queryCarList()
        menuSelection()
        mviStatesProcessing()
    }

    override fun onRefresh() {
        viewModel.setStateEvent(CarListStateEvents.FetchCarList)
    }

    // Init view and setup initial values and processes
    private fun initView() {
        binding.carRefreshAction.setOnRefreshListener(this)
        val carouselLayoutManager = CarouselLayoutManager(CarouselLayoutManager.VERTICAL).apply {
            setPostLayoutListener(CarouselZoomPostLayoutListener())
        }
        binding.carList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            addOnScrollListener(CenterScrollListener())
            adapter = listAdapter
        }

        binding.carList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                val itemHeight = recyclerView.height
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val position = recyclerView.computeHorizontalScrollOffset()
                        .toFloat() / itemHeight.toFloat()
                    val itemPosition = floor(position.toDouble()).toInt()
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })

        binding.carToolbarInclude.carMakeList.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = makeListAdapter
        }

        viewModel.setStateEvent(CarListStateEvents.FetchCarList)
    }

    // Process Menu selection
    private fun menuSelection() {
        binding.toolBar.setOnMenuItemClickListener { menu ->
            when (menu.itemId) {
                R.id.refresh -> {
                    viewModel.setStateEvent(CarListStateEvents.FetchCarList)
                }
                R.id.settings -> {
                    Toast.makeText(requireContext(), "Not implemented yet!", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            true
        }

        binding.carToolbarInclude.searchQuery.setOnCloseListener {
            viewModel.setStateEvent(CarListStateEvents.FetchCarList)
            true
        }
    }

    // Query Movie list from the search view
    private fun queryCarList() {
        binding.carToolbarInclude.searchQuery.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (query != null && query.isNotBlank()) {
                    viewModel.setStateEvent(CarListStateEvents.QueryList(query))
                    true
                } else {
                    false
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return if (newText != null && newText.isNotBlank()) {
                    viewModel.setStateEvent(CarListStateEvents.QueryList(newText))
                    true
                } else {
                    false
                }
            }
        })
    }

    // Update recycler view
    private fun updateRecyclerView(cars: List<Car>) {
        listAdapter.submitList(cars)
        viewModel.setStateEvent(CarListStateEvents.Idle)
    }

    private fun navigateToDetailPage(item: Car) {
        val bundle = Bundle().apply {
            putParcelable(Constants.CAR_ITEM, item)
        }
        findNavController().navigate(R.id.action_carListFragment_to_carDetailFragment, bundle)
    }

    private fun fetchCarByMake(item: CarMake) {
        TODO("Not yet implemented")
    }

    private fun mviStatesProcessing() {
        lifecycleScope.launch {
            viewModel.carMake.collect { listResult ->
                Log.d(javaClass.simpleName, "List of car makes: $listResult")
                listResult.data?.let { list ->
                    makeListAdapter.submitList(list.getContentIfNotHandled())
                }
                listResult.error?.let {
                    Toast.makeText(
                        context,
                        "${it.getContentIfNotHandled()?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                binding.carToolbarInclude.makeProgress.viewvisibility(listResult.loading)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.dataState.mapLatest { ds ->
                binding.carRefreshAction.isRefreshing = ds.loading
                activityViewModel.dataStateChanged(ds)
                ds.message?.let {
                    Snackbar.make(binding.root, it.peekContent(), Snackbar.LENGTH_LONG).show()
                }
                ds.data?.let { event ->
                    event.getContentIfNotHandled()?.let { loginViewState ->
                        viewModel.processViewState(loginViewState)
                    }
                }
            }.launchIn(lifecycleScope)

            viewModel.viewState.collectLatest { vs ->
                vs.carList?.let { cars ->
                    Log.d(javaClass.simpleName, cars.toString())
                    binding.carRefreshAction.isRefreshing = false
                    // Only update the current list if there's a new list
                    if (cars.isNotEmpty()) updateRecyclerView(cars)
                }
            }
        }
    }
}