package com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.presentation.util

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import com.kelviniyalo.kelviniyalomobileengrtestmoniepoint.R

object Utility {
    fun View.startMoveUpAnimation(context: Context) {
        val animation = AnimationUtils.loadAnimation(context, R.anim.move_up)
        startAnimation(animation)
    }

}