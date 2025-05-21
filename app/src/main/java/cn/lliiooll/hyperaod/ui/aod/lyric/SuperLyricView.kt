package cn.lliiooll.hyperaod.ui.aod.lyric

import android.content.Context
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import android.widget.*
import cn.lliiooll.hyperaod.color
import cn.lliiooll.hyperaod.ui.aod.text.RainbowTextView
import com.highcapable.yukihookapi.hook.log.YLog

class SuperLyricView : RelativeLayout, ViewSwitcher.ViewFactory {


    private lateinit var lyricSwitcher: TextSwitcher// 歌词切换
    private lateinit var lyricIcon: ImageView// 歌词图标
    var iconEnable: Boolean = false// 图标开关
    var iconSize: Float = 25f// 图标大小
    var textLocation = 0// 歌词位置
    var textPadding = 0f// 歌词边距

    var textSize: Float = 25f// 歌词大小
    var textColor1 = "#FF0000"// 歌词颜色1
    var textColor2 = "#FF7F00"// 歌词颜色2
    var textAnimDuration = 800f// 歌词动画时长
    var rainbowDuration = 800f// 歌词渐变速度
    var textEnterAnim = 0// 歌词进入动画
    var textExitAnim = 0// 歌词退出动画
    var rainbowEnable = false// 彩虹歌词开关

    val rule = mutableMapOf<Int, Int>().apply {
        this[0] = Gravity.CENTER
        this[1] = Gravity.START
        this[2] = Gravity.END
    }

    // 进入动画
    val animsEnter = mutableMapOf<Int, Animation>().apply {
        // 上划
        this[0] = TranslateAnimation(
            Animation.ABSOLUTE, 0f,
            Animation.ABSOLUTE, 0f,
            Animation.RELATIVE_TO_PARENT, 1f,
            Animation.ABSOLUTE, 0f
        ).apply { duration = textAnimDuration.toLong() }
        // 下划
        this[1] = TranslateAnimation(
            Animation.ABSOLUTE, 0f,
            Animation.ABSOLUTE, 0f,
            Animation.RELATIVE_TO_PARENT, -1f,
            Animation.ABSOLUTE, 0f
        ).apply { duration = textAnimDuration.toLong() }
        // 左划
        this[2] = TranslateAnimation(
            Animation.ABSOLUTE, 0f,
            Animation.RELATIVE_TO_PARENT, -1f,
            Animation.ABSOLUTE, 0f,
            Animation.ABSOLUTE, 0f
        ).apply { duration = textAnimDuration.toLong() }
        // 右划
        this[3] = TranslateAnimation(
            Animation.ABSOLUTE, 0f,
            Animation.RELATIVE_TO_PARENT, 1f,
            Animation.ABSOLUTE, 0f,
            Animation.ABSOLUTE, 0f
        ).apply { duration = textAnimDuration.toLong() }
        // 出现
        this[4] = AlphaAnimation(0f, 1f).apply {
            duration = textAnimDuration.toLong()
        }
    }

    // 退出动画
    val animsExit = mutableMapOf<Int, Animation>().apply {
        // 上划
        this[0] = TranslateAnimation(
            Animation.ABSOLUTE, 0f,
            Animation.ABSOLUTE, 0f,
            Animation.ABSOLUTE, 0f,
            Animation.RELATIVE_TO_PARENT, -1f
        ).apply { duration = textAnimDuration.toLong() }
        // 下划
        this[1] = TranslateAnimation(
            Animation.ABSOLUTE, 0f,
            Animation.ABSOLUTE, 0f,
            Animation.ABSOLUTE, 0f,
            Animation.RELATIVE_TO_PARENT, 1f
        ).apply { duration = textAnimDuration.toLong() }
        // 左划
        this[2] = TranslateAnimation(
            Animation.RELATIVE_TO_PARENT, 1f,
            Animation.ABSOLUTE, 0f,
            Animation.ABSOLUTE, 0f,
            Animation.ABSOLUTE, 0f
        ).apply { duration = textAnimDuration.toLong() }
        // 右划
        this[3] = TranslateAnimation(
            Animation.RELATIVE_TO_PARENT, -1f,
            Animation.ABSOLUTE, 0f,
            Animation.ABSOLUTE, 0f,
            Animation.ABSOLUTE, 0f
        ).apply { duration = textAnimDuration.toLong() }
        // 消失
        this[4] = AlphaAnimation(1f, 0f).apply {
            duration = textAnimDuration.toLong()
        }
    }

    constructor(context: Context?) : super(context) {
        // 初始化切换器
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        lyricSwitcher = TextSwitcher(context!!)
        addView(lyricSwitcher)
    }


    fun init() {
        // 初始化切换器
        lyricSwitcher.setFactory(this)
        // 设置进入动画
        lyricSwitcher.inAnimation = animsEnter[textEnterAnim]
        // 设置退出动画
        lyricSwitcher.outAnimation = animsExit[textExitAnim]
        // 设置切换器位置
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        //layoutParams.addRule(rule[textLocation]!!)
        lyricSwitcher.layoutParams = layoutParams
    }

    override fun makeView(): View {
        // 初始化文字
        val lyricView = RainbowTextView(context)
        // 设置文字大小
        lyricView.textSize = textSize
        // 设置文字颜色
        lyricView.setTextColor(textColor1.color().toInt())
        // 设置文字单行
        lyricView.setSingleLine()
        lyricView.maxLines = 1
        // 设置过渡文字
        lyricView.ellipsize = TextUtils.TruncateAt.MARQUEE
        // 设置跑马灯速度
        lyricView.marqueeRepeatLimit = 1
        // 设置文字居中
        lyricView.gravity = rule[textLocation]!!
        //lyricView.textAlignment = View.TEXT_ALIGNMENT_CENTER
        // 延迟200ms后跑马灯
        lyricView.isSelected = false
        // 设置动态渐变
        if (rainbowEnable) {
            //lyricView.startAnimation(rainbowDuration.toLong())
        }
        return lyricView
    }

    fun setText(text: String) {
        if (text.isBlank()) return
        YLog.debug("SuperLyricView switcher to $text")
        post {
            lyricSwitcher.setText(text)
        }

    }

}