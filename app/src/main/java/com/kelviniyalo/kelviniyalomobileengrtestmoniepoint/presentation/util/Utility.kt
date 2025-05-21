package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util

import android.content.Context
import android.transition.TransitionInflater
import android.view.View
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.R

object Utility {
    fun View.startMoveUpAnimation(context: Context) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.move_up)
        startAnimation(animation)
    }

    fun Fragment.manageElementTransition() {
        TransitionInflater.from(requireContext()).inflateTransition(android.R.transition.move)
            .apply {
                duration = 500L
                sharedElementEnterTransition = this
                sharedElementReturnTransition = this
            }

    }

}