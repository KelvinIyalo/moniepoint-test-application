package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.shipment

import android.content.res.ColorStateList
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.R
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.TableItem
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.databinding.FragmentShipmentBinding
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util.ShipmentStatus
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util.UiState
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util.Utility.startMoveUpAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShipmentFragment : Fragment(R.layout.fragment_shipment) {
    private lateinit var binding: FragmentShipmentBinding
    private var isScrollingDown = true
    private val viewModel: ShipmentViewModel by activityViewModels()
    private lateinit var transactionsAdapter: ShippingAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShipmentBinding.bind(view)
        TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move).apply {
            sharedElementEnterTransition = this
            sharedElementReturnTransition = this
        }

        with(binding) {
            backBtn.setOnClickListener { findNavController().popBackStack() }
        }

        initClickListeners()
        initTransactionsRecyclerViewAdapter()


        viewModel.transactions.observe(viewLifecycleOwner) { uiState ->
            transactionsAdapter.submitList(uiState.shipmentDetails)
            updateSelectedTab(uiState.shipmentStatus!!)
        }
    }

    private fun updateSelectedTab(
        shipmentStatus: ShipmentStatus
    ) {
        val barSelectedColor = R.color.button_color
        val barUnselectedColor = R.color.purple_700
        val countUnselectedColor = resources.getColor(R.color.gray)
        val selectedTextColor = resources.getColor(R.color.white)
        val table = mapOf(
            ShipmentStatus.ALL to TableItem(binding.allLiner, binding.all, binding.allCount),
            ShipmentStatus.COMPLETED to TableItem(
                binding.completedLiner,
                binding.completed,
                binding.completedCount
            ),
            ShipmentStatus.IN_PROGRESS to TableItem(
                binding.inProgressLiner,
                binding.inProgress,
                binding.inProgressCount
            ),
            ShipmentStatus.CANCELLED to TableItem(
                binding.canceledLiner,
                binding.canceled,
                binding.canceledCount
            ),
            ShipmentStatus.PENDING_ORDER to TableItem(
                binding.pendingLiner,
                binding.pending,
                binding.pendingCount
            ),
        )


        table.forEach { (status, tableItem) ->
            tableItem.bar.setBackgroundResource(if (shipmentStatus == status) barSelectedColor else barUnselectedColor)
            tableItem.count.backgroundTintList =
                ColorStateList.valueOf(if (shipmentStatus == status) resources.getColor(R.color.button_color) else countUnselectedColor)
            tableItem.text.setTextColor(if (shipmentStatus == status) selectedTextColor else countUnselectedColor)
        }

    }

    private fun initTransactionsRecyclerViewAdapter() {
        transactionsAdapter = ShippingAdapter(
            isScrollingDown = isScrollingDown
        )
        binding.shipmentRecyclerview.apply {
            adapter = transactionsAdapter
            addOnScrollListener(object :RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    isScrollingDown = dy > 0
                }
            })
        }
    }

    private fun initClickListeners() {
        viewModel.retrieveAllShippingCount().observe(requireActivity(), Observer { response ->
            when (response) {

                is UiState.Success -> {

                    val statusCounts = mapOf(
                        "All" to response.data!!.size,
                        "Completed" to response.data.count { it.status.contentEquals("Completed") },
                        "Pending" to response.data.count { it.status.contentEquals("Pending") },
                        "In-Progress" to response.data.count { it.status.contentEquals("In-Progress") },
                        "Canceled" to response.data.count { it.status.contentEquals("Canceled") }
                    )


                    with(binding) {

                        val textViewMap = mapOf(
                            "All" to allCount,
                            "Completed" to completedCount,
                            "Pending" to pendingCount,
                            "In-Progress" to inProgressCount,
                            "Canceled" to canceledCount
                        )
                        statusCounts.forEach { (status, count) ->
                            textViewMap[status]?.apply {
                                isVisible = count > 0
                                text = count.toString()
                            }
                        }

                    }


                }

                else -> {}
            }
        })


        with(binding) {
            val filterButtons = listOf(allLayout, completedLayout, pendingLayout, canceledLayout, inProgressLayout)
            val statuses = listOf(ShipmentStatus.ALL, ShipmentStatus.COMPLETED, ShipmentStatus.PENDING_ORDER, ShipmentStatus.CANCELLED, ShipmentStatus.IN_PROGRESS)

            filterButtons.forEachIndexed { index, layout ->
                layout.setOnClickListener {
                    viewModel.filterByStatus(statuses[index])
                }
            }
        }


    }

}

