package com.example.animationsample

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.View

class MovableBlock(context: Context) : View(context) {
    private var containerWidth: Int = 0
    private var containerHeight: Int = 0

    init {
        id = generateViewId()
        setBackgroundColor(Color.RED)
    }

    fun setContainerSize(width: Int, height: Int) {
        containerWidth = width
        containerHeight = height
    }

    fun moveVertical(percentage: Float) = AnimatorSet().apply {
        val view = this@MovableBlock
        val animator = ObjectAnimator.ofFloat(view, TRANSLATION_Y, view.translationY)
        play(animator)
        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                Log.w("BLOCK", "Move vertical start: container = ${containerHeight}, current translation = ${view.translationY}")
                animator.setFloatValues(
                    view.translationY,
                    view.translationY + percentage * containerHeight
                )
            }

            override fun onAnimationEnd(animation: Animator) {
                Log.w("BLOCK", "Move vertical end: container = ${containerHeight}, current translation = ${view.translationY}")
            }
        })
    }
}