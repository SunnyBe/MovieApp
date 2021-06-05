package com.buchi.fullentry.cars.presentation.cardetail

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.buchi.fullentry.R
import com.buchi.fullentry.cars.adapter.ConditionsAdapter
import com.buchi.fullentry.cars.model.Car
import com.buchi.fullentry.cars.presentation.CarsViewModel
import com.buchi.fullentry.databinding.FragmentCarDetailBinding
import com.buchi.fullentry.utilities.Constants
import com.buchi.fullentry.utilities.formatAmount
import com.buchi.fullentry.utilities.inBrace
import com.buchi.fullentry.utilities.to1dp
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CarDetailFragment: Fragment() {
    lateinit var binding: FragmentCarDetailBinding
    private val viewModel: CarDetailViewModel by viewModels()
    private val activityViewModel: CarsViewModel by activityViewModels()

    private var carDetail: Car? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCarDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.checkoutInclude.purchaseAction.isEnabled = false

        arguments?.apply {
            carDetail = getParcelable(Constants.CAR_ITEM)
            Log.d(javaClass.simpleName, "Bundle carDetail: $carDetail")
            if (carDetail != null) {
                viewModel.setStateEvent(CarDetailStateEvents.FetchCarDetail(carDetail?.id))
                populateDetailView(carDetail)
            } else {
                findNavController().navigateUp()
            }
        }

        binding.toolBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        lifecycleScope.launchWhenStarted {
            viewModel.dataState.mapLatest { ds ->
                activityViewModel.dataStateChanged(ds)
                showLoadingState(ds.loading)
                ds.data?.let { event ->
                    event.getContentIfNotHandled()?.let { loginViewState ->
                        viewModel.processViewState(loginViewState)
                    }
                }
            }.launchIn(lifecycleScope)
        }

        lifecycleScope.launch {
            viewModel.viewState.collectLatest { vs ->
                vs.carDetail?.let { car ->
                    updateDetailView(car)
                }
            }
        }
    }

    private fun showLoadingState(isLoading: Boolean) {
        Toast.makeText(requireContext(), "Is loading: $isLoading", Toast.LENGTH_SHORT).show()
    }
    private fun populateDetailView(car: Car?) {
        with(binding) {
            toolBar.title = car?.title ?: "Detail"
            carTitle.text = car?.title

            with(carDetailInclude) {
                carLocation.text = "${car?.city}"
                carYear.text = car?.year.toString()
                gradeScore.text = car?.gradeScore?.to1dp?.inBrace
                Glide.with(requireContext())
                    .load(car?.imageUrl)
                    .placeholder(R.drawable.baseline_photo_black_24dp)
                    .error(R.drawable.baseline_broken_image_pink_500_24dp)
                    .centerCrop()
                    .fitCenter()
                    .into(imageView)
            }

            with(carConditionsInclude) {

            }

            with(checkoutInclude) {
                purchaseAmount.text = car?.marketplacePrice?.toDouble()?.formatAmount
            }
        }
    }

    private fun updateDetailView(car: Car?) {
        binding.checkoutInclude.purchaseAction.isEnabled = true
        with(binding) {

            with(carDetailInclude) {
                carLocation.text = "${car?.address}, ${car?.city}, ${car?.country}"
                carYear.text = car?.year.toString()
                gradeScore.text = car?.gradeScore?.to1dp?.inBrace
                Glide.with(requireContext())
                    .load(car?.imageUrl)
                    .placeholder(R.drawable.baseline_photo_black_24dp)
                    .error(R.drawable.baseline_broken_image_pink_500_24dp)
                    .centerCrop()
                    .fitCenter()
                    .into(imageView)
            }

            with(carConditionsInclude) {
                populateConditionRecycler(car)
            }

            with(checkoutInclude) {
                purchaseAmount.text = car?.price?.toDouble()?.formatAmount
            }
        }
    }

    private fun populateConditionRecycler(car: Car?) {
        binding.carConditionsInclude.conditionTitle.setText("Car Conditions")
        binding.carConditionsInclude.conditionList.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ConditionsAdapter().apply {
                submitList(car?.damageMedia)
            }
        }
    }
}