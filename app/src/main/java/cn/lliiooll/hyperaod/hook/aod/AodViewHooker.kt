package cn.lliiooll.hyperaod.hook.aod

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.core.view.setPadding
import cn.lliiooll.hyperaod.ui.aod.SuperAodView
import com.highcapable.yukihookapi.hook.factory.field
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog

class AodViewHooker(pluginClassLoader: ClassLoader) : BaseAodHooker(pluginClassLoader) {

    val handler = Handler(Looper.getMainLooper())

    @SuppressLint("ResourceType")
    override fun onHook() {
        val hooker = this
        "com.miui.aod.AODView".toClass(pluginClassLoader).method { name("makeNormalPanel") }.hook {
            after {
                // 用于让系统清醒一下更新界面
                val mHost = instance.javaClass.field { name("mHost") }.get(instance).any()
                val mTableModeContainer = instance.javaClass.field { name("mTableModeContainer") }.get(instance).any()!!
                val mClockContent =
                    mTableModeContainer.javaClass.field { name("mClockContent") }.get(mTableModeContainer).any()

                // 用于寻找息屏界面
                val aodView = mClockContent as ViewGroup
                val parent = aodView.children.first() as ViewGroup
                //val parent = mClockContent as ViewGroup
                // 初始化自定义息屏内容
                val superAodView = SuperAodView(parent, hooker, mHost!!)
                superAodView.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    gravity = Gravity.CENTER
                }

                parent.addView(superAodView)
                YLog.debug("Add SuperAodView success")
            }
        }


    }


    fun wakeUp(host: Any?, l: Long) {
        if (host != null) {
            host.javaClass.method { name("requestDrawWakelock") }.give()?.invoke(host, l, "lyric wakeup")
        }
    }

    fun fresh(host: Any?) {
        if (host != null) {
            val task = host.javaClass.field { name("mPauseTask") }.get(host).any()!!
            host.javaClass.method { name("fresh") }.give()?.invoke(host)
            val inf = prefs.getBoolean("others.infgif", false)
            YLog.debug("Inf : $inf")
            task.javaClass.method { name("execute") }.give()?.invoke(task, 5000L)
            if (inf) {
                task.javaClass.method { name("execute") }.give()?.invoke(task, 60000L)
            }
        }
    }

    private fun look(view: ViewGroup): ViewGroup {
        for (v in view.children) {
            YLog.debug("Looking for ${v.javaClass.name}")
            if (v.javaClass.name == "com.miui.maml.component.MamlView") return v as ViewGroup
            if (v is ViewGroup) {
                look(v)
            }
        }
        return view
    }
}