package com.example.animationsample

import android.animation.AnimatorSet
import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.view.children

class AnimationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val animation = AnimatorSet()

    init {
        initAnimation()
    }

    private fun initAnimation() {
        val block = MovableBlock(context).apply {
            layoutParams = LayoutParams(16.dp(), 16.dp()).apply {
                addRule(CENTER_IN_PARENT)
            }
        }
        addView(block)

        animation.playSequentially(
            block.moveVertical(0.25f).setDuration(1000),
            block.moveVertical(-0.5f).setDuration(1000),
            block.moveVertical(0.25f).setDuration(1000)
        )
    }

    private fun Int.dp() = (context.resources.displayMetrics.density * this).toInt()

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        children.forEach {
            if (it is MovableBlock) {
                it.setContainerSize(measuredWidth, measuredHeight)
            }
        }
    }

    fun start() {
        animation.start()
    }
}