package cn.lliiooll.hyperaod.ui.aod.text

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Shader
import android.view.animation.LinearInterpolator
import android.widget.TextView
import cn.lliiooll.hyperaod.color

class RainbowTextView : TextView {

    private var anim: Boolean = false
    private var animator: ValueAnimator = ValueAnimator()
    var textColor1 = "#FF0000"// 歌词颜色1
    var textColor2 = "#FF7F00"// 歌词颜色2
    private var rainbowColors =
        intArrayOf(textColor1.color().toInt(), textColor2.color().toInt(), textColor1.color().toInt())// 设置彩虹色
    private var textViewWidth = 0
    private var matrix = Matrix()
    private var shader: LinearGradient? = null
    private var translate = 0

    constructor(context: Context?, textColor1: String, textColor2: String) : super(context) {
        this.textColor2 = textColor2
        this.textColor1 = textColor1
        refreshColors()
    }

    fun startAnimation(dur: Long) {
        this.anim = true
        refreshColors()
        matrix = Matrix()
        animator = ValueAnimator.ofFloat(0f, 1f)
        animator.repeatCount = ValueAnimator.INFINITE
        animator.duration = dur
        animator.interpolator = LinearInterpolator()
        animator.addUpdateListener {
            translate += 10
            matrix.setTranslate(translate.toFloat(), 0f)
            shader?.setLocalMatrix(matrix)
            invalidate()
        }
    }

    private fun refreshColors() {
        val colors = arrayListOf<Int>()
        var c0 = textColor1.color().toInt()
        var c1 = textColor2.color().toInt()
        if (c0 > c1) {
            val c2 = c0
            c0 = c1
            c1 = c2
        }
        for (color in c0..c1 step 1) {
            colors.add(color)
        }
        rainbowColors = colors.shuffled().toIntArray()
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (textViewWidth == 0) {
            textViewWidth = measuredWidth
            if (textViewWidth > 0) {
                val seamlessPositions = FloatArray(rainbowColors.size)
                for (i in seamlessPositions.indices) {
                    seamlessPositions[i] = i.toFloat() / (rainbowColors.size - 1)
                }
                shader = LinearGradient(
                    0f,
                    0f,
                    textViewWidth.toFloat(),
                    0f,
                    rainbowColors,
                    null,
                    Shader.TileMode.REPEAT
                )// 初始化渐变
                paint.shader = shader
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (anim)
            animator.start()
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        if (anim)
            animator.cancel()
    }
}