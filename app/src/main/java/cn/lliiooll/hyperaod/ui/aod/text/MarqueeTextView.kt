package cn.lliiooll.hyperaod.ui.aod.text

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.ViewTreeObserver.OnPreDrawListener
import android.view.animation.LinearInterpolator
import android.widget.FrameLayout
import android.widget.TextView


open class MarqueeTextView(context: Context?) : TextView(context) {

    private var animator: ValueAnimator = ValueAnimator()
    private var isMeasured = false
    private var textWidth = 0f
    private var viewWidth = 0f


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        viewWidth = w.toFloat()
        startScrollIfNeeded()

    }

    override fun onTextChanged(text: CharSequence?, start: Int, lengthBefore: Int, lengthAfter: Int) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
        isMeasured = false
        startScrollIfNeeded()
    }

    private fun startScrollIfNeeded() {
        if (width == 0 || text.length == 0) return
        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
        // 测量文本实际宽度
        viewTreeObserver.addOnPreDrawListener(object : OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                if (isMeasured) return true
                textWidth = paint.measureText(text.toString())
                isMeasured = true
                viewTreeObserver.removeOnPreDrawListener(this)

                if (textWidth > viewWidth) {
                    layoutParams.width = textWidth.toInt()
                    requestLayout()
                    // 延迟500ms
                    startScrollAnimation()
                } else {
                    layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, WRAP_CONTENT)
                }

                return true
            }
        })
    }

    private fun startScrollAnimation() {
        if (animator.isRunning) {
            animator.cancel()
        }

        val startX = 0f
        val endX = textWidth - viewWidth
        translationX = 0f
        animator = ValueAnimator.ofFloat(startX, endX)
        animator.setDuration((endX * 10).toLong()) // 根据滚动距离调整速度
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener { animation: ValueAnimator ->
            val translateX = animation.animatedValue as Float
            translationX = -translateX
        }

        animator.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                // 动画结束后停在末尾
                translationX = -endX
            }
        })
        animator.start()
    }


}