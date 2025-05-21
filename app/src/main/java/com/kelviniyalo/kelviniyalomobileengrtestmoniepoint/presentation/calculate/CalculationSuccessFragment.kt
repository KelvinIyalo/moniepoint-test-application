package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.calculate

import android.animation.ValueAnimator
import android.os.Bundle
import android.os.Handler
import android.transition.TransitionInflater
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.R
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.databinding.FragmentCalculationSuccessBinding
import java.text.NumberFormat
import java.util.Locale


class CalculationSuccessFragment : Fragment(R.layout.fragment_calculation_success) {
    private lateinit var binding: FragmentCalculationSuccessBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCalculationSuccessBinding.bind(view)
        val animation =
            TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
        val animationMoveUp = AnimationUtils.loadAnimation(requireContext(), R.anim.move_up)
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
        amountCountEffect()
        with(binding) {
            backBtn.setOnClickListener { animateAndNavigation() }
            backBtn.startAnimation(animationMoveUp)
            estimateMessage.startAnimation(animationMoveUp)
            amount.startAnimation(animationMoveUp)
            estimatedAmountLabel.startAnimation(animationMoveUp)
            brandName.startAnimation(animationMoveUp)
        }
    }

    private fun animateAndNavigation() {
        val animationBounce = AnimationUtils.loadAnimation(requireContext(), R.anim.btn_bounce)
        binding.backBtn.startAnimation(animationBounce)
        Handler().postDelayed({
            findNavController().popBackStack(R.id.navigation_home, false)
        }, 400)
    }

    private fun amountCountEffect() {
        binding.amount.text = "0"

        val startValue = 0
        val endValue = 2800 // Set your desired end value

        val animator = ValueAnimator.ofInt(startValue, endValue)
        animator.duration = 3000

        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue as Int

            val formattedValue =
                NumberFormat.getNumberInstance(Locale.getDefault()).format(animatedValue)

            binding.amount.text = StringBuilder("$").append(formattedValue)
        }

        animator.start()
    }

}