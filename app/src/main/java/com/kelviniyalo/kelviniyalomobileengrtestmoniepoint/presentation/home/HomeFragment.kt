package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.R
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.databinding.FragmentHomeBinding
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util.Utility.startMoveUpAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        with(binding) {
            searchEt.setOnClickListener {
                val extras = FragmentNavigatorExtras(
                    binding.toolbarSection to "search_toolbar",
                    binding.searchEt to "search_search"
                )

                val direction = R.id.action_navigation_home_to_searchFragment
                findNavController().navigate(
                    direction,
                    null,
                    null,
                    extras
                )
            }

            navView.menu.findItem(R.id.navigation_home).isChecked = true
            navView.setOnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.navigation_calculate -> {
                        val extras = FragmentNavigatorExtras(
                            binding.toolbarSection to "toolbar_calculate",
                            binding.searchEt to "title_calculate"
                        )

                        val direction = R.id.navigation_calculate
                        findNavController().navigate(
                            direction,
                            null,
                            null,
                            extras
                        )
                        true
                    }

                    R.id.navigation_shipment -> {
                        val extras = FragmentNavigatorExtras(
                            binding.toolbarSection to "toolbar_shipment",
                            binding.searchEt to "title_shipment"
                        )

                        val direction = R.id.navigation_shipment
                        findNavController().navigate(
                            direction,
                            null,
                            null,
                            extras
                        )
                        true
                    }
                    // Add cases for other BottomNavigationView items if needed
                    else -> false
                }
            }

            binding.availableVehicles.startMoveUpAnimation(requireContext())
            binding.shipmentDetails.startMoveUpAnimation(requireContext())
            binding.trackingLabel.startMoveUpAnimation(requireContext())
        }
    }

    override fun onResume() {
        super.onResume()
        binding.navView.menu.findItem(R.id.navigation_home).isChecked = true
    }

}