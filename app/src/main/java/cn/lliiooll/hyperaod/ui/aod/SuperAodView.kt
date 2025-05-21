package cn.lliiooll.hyperaod.ui.aod

import android.content.Context
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.RelativeLayout
import androidx.core.view.setMargins
import cn.lliiooll.hyperaod.hook.aod.AodViewHooker
import cn.lliiooll.hyperaod.hook.aod.SuperLyricStub
import cn.lliiooll.hyperaod.ui.aod.lyric.SuperLyricView
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.xposed.prefs.YukiHookPrefsBridge

class SuperAodView : RelativeLayout {

    private var maml: Any
    private var host: Any
    private var hooker: AodViewHooker
    private var prefs: YukiHookPrefsBridge

    constructor(maml: ViewGroup, hooker: AodViewHooker, host: Any) : super(maml.context) {
        this.prefs = hooker.prefs
        this.hooker = hooker
        this.host = host
        this.maml = maml
        YLog.debug("Try init SuperAodView")
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        // 歌词
        val lyric = prefs.getBoolean("lyric_enable", false)
        YLog.debug("Lyric status: $lyric")
        if (lyric) {
            // 初始化歌词控件
            YLog.debug("Init SuperLyricView")
            val lyricView = SuperLyricView(context)
            // 获取歌词配置
            lyricView.iconEnable = prefs.getBoolean("lyric_icon", false)// 歌词图标开关
            YLog.debug("Init SuperLyricView iconEnable: ${lyricView.iconEnable}")
            lyricView.iconSize = prefs.getFloat("lyric_icon_size", 25f)// 歌词图标大小
            YLog.debug("Init SuperLyricView iconSize: ${lyricView.iconSize}")
            lyricView.textSize = prefs.getFloat("lyric_text_size", 25f)// 歌词大小
            YLog.debug("Init SuperLyricView textSize: ${lyricView.textSize}")
            lyricView.textColor1 = prefs.getString("lyric_text_color1", "#FF0000")// 歌词颜色1
            YLog.debug("Init SuperLyricView textColor1: ${lyricView.textColor1}")
            lyricView.textColor2 = prefs.getString("lyric_text_color2", "#FF7F00")// 歌词颜色2
            YLog.debug("Init SuperLyricView textColor2: ${lyricView.textColor2}")
            lyricView.textLocation = prefs.getInt("lyric_location", 0)// 歌词位置
            YLog.debug("Init SuperLyricView textLocation: ${lyricView.textLocation}")
            lyricView.textEnterAnim = prefs.getInt("lyric_enter_anim", 0)// 歌词进入动画
            YLog.debug("Init SuperLyricView textEnterAnim: ${lyricView.textEnterAnim}")
            lyricView.textExitAnim = prefs.getInt("lyric_exit_anim", 0)// 歌词退出动画
            YLog.debug("Init SuperLyricView textExitAnim: ${lyricView.textExitAnim}")
            lyricView.textPadding = prefs.getFloat("lyric_padding", 0f)// 歌词边距
            YLog.debug("Init SuperLyricView textPadding: ${lyricView.textPadding}")
            lyricView.rainbowEnable = prefs.getBoolean("lyric_rainbow", false)// 彩虹歌词开关
            YLog.debug("Init SuperLyricView rainbowEnable: ${lyricView.rainbowEnable}")
            lyricView.textAnimDuration = prefs.getFloat("lyric_anim_duration", 800f)// 歌词动画时长
            YLog.debug("Init SuperLyricView textAnimDuration: ${lyricView.textAnimDuration}")
            lyricView.rainbowDuration = prefs.getFloat("lyric_rainbow_duration", 800f)// 歌词渐变速度
            YLog.debug("Init SuperLyricView rainbowDuration: ${lyricView.textAnimDuration}")
            lyricView.init()
            YLog.debug("Init SuperLyricView all")
            // 添加歌词控件
            // 设置歌词控件位置
            val lyricLayoutParams = LayoutParams(MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            YLog.debug("Try add SuperLyricView")
            lyricLayoutParams.addRule(ALIGN_PARENT_BOTTOM)// 居于底部
            lyricLayoutParams.setMargins(lyricView.textPadding.toInt())// 设置边距
            lyricView.layoutParams = lyricLayoutParams
            addView(lyricView)
            YLog.debug("Add SuperLyricView success")
            //hooker.fresh(maml)
            //lyricView.setText("我 是 一 个 很 长 很 长 很 长 很 长 很 长 很 长 的 文 本")
            // 开始监听歌词
            SuperLyricStub.init {
                YLog.debug("SuperLyricView wakeup 1000ms")
                hooker.fresh(maml)
                lyricView.setText(it.lyric)
            }
        }
    }


}