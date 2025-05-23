package cn.lliiooll.hyperaod.hook.systemui

import cn.lliiooll.hyperaod.hook.aod.AodViewHooker
import cn.lliiooll.hyperaod.hook.aod.DozePluginHooker
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog

object AodHooker : YukiBaseHooker() {
    override fun onHook() {
        val service = "com.android.systemui.doze.DozeService".toClass()
        service
            .method { name("onPluginConnected") }
            .hook().before {
                val pluginLoader = args(0)
                    .any()!!
                    .javaClass
                    .classLoader!!
                //YLog.debug("Attached plugin classloader ")
                // 加载hook
                loadHooker(DozePluginHooker(pluginLoader))
                loadHooker(AodViewHooker(pluginLoader))
            }
    }
}