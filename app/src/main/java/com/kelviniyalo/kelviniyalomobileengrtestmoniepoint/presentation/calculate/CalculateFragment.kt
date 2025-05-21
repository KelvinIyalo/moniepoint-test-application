package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.calculate

import android.content.res.ColorStateList
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.R
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.data.model.Categories
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.databinding.CategoryTypeBinding
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.databinding.FragmentCalculateBinding
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util.Utility.manageElementTransition
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util.Utility.startMoveUpAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CalculateFragment : Fragment(R.layout.fragment_calculate) {
    private lateinit var binding: FragmentCalculateBinding
    private val viewModel: CategoriesViewModel by activityViewModels()
    private var isSelected: Boolean = false
    private lateinit var categoriesAdapter: CategoriesAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCalculateBinding.bind(view)
        manageElementTransition()

        with(binding) {
            calculateBtn.setOnClickListener {
                animateAndNavigation()
            }
            backBtn.setOnClickListener { findNavController().popBackStack() }
            calculateBtn.startMoveUpAnimation(requireContext())
            categoriesLabel.startMoveUpAnimation(requireContext())
            quest2Label.startMoveUpAnimation(requireContext())
            questLabel.startMoveUpAnimation(requireContext())
            packagingLabel.startMoveUpAnimation(requireContext())
            sendingPackageCard.startMoveUpAnimation(requireContext())
            destinationLabel.startMoveUpAnimation(requireContext())
            cardParams.startMoveUpAnimation(requireContext())
        }
        initTransactionsRecyclerViewAdapter()

        viewModel.transactions.observe(viewLifecycleOwner) { uiState ->
            categoriesAdapter.submitList(uiState.categories)
        }
    }

    private fun animateAndNavigation() {
        val animationBounce = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_bounce)
        binding.calculateBtn.startAnimation(animationBounce)
        Handler().postDelayed({
            val direction = R.id.action_navigation_calculate_to_calculationSuccessFragment
            val extras = FragmentNavigatorExtras(
                binding.boxIc to "calculate_success"
            )
            findNavController().navigate(direction, null, null, extras)
        }, 400)

    }

    private fun initTransactionsRecyclerViewAdapter() {
        categoriesAdapter = CategoriesAdapter(
            onItemClicked = { position: Int, itemAtPosition: Categories, itemView ->
                isSelected = !isSelected
                if (isSelected) {
                    setViewManagement(itemView)
                } else {
                    resetViewManagement(itemView)
                }
            }
        )
        binding.categoriesRv.adapter = categoriesAdapter
    }

    private fun setViewManagement(itemView: CategoryTypeBinding) {
        with(itemView) {
            val animationBounce = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_expand)
            categoryLayout.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.black))
            itemName.setTextColor(resources.getColor(R.color.white))
            val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.selected_24)
                ?.apply {
                    setBounds(0, 0, intrinsicWidth, intrinsicHeight)
                }
            itemName.setCompoundDrawables(drawable, null, null, null)
            categoryLayout.startAnimation(animationBounce)
        }

    }

    private fun resetViewManagement(itemView: CategoryTypeBinding) {
        with(itemView) {
            categoryLayout.backgroundTintList = null
            categoryLayout.setBackgroundResource(R.drawable.categories_widget)
            itemName.setTextColor(resources.getColor(R.color.black))
            itemName.setCompoundDrawables(null, null, null, null)
        }

    }

}