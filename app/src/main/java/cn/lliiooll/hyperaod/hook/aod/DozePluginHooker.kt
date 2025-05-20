package cn.lliiooll.hyperaod.hook.aod

import android.annotation.SuppressLint
import android.content.Context
import com.hchen.superlyricapi.SuperLyricTool
import com.highcapable.yukihookapi.hook.factory.field
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog

class DozePluginHooker(pluginClassLoader: ClassLoader) : BaseAodHooker(pluginClassLoader) {
    @SuppressLint("ResourceType")
    override fun onHook() {
        val dozeServicePluginImplClazz = "com.miui.aod.doze.DozeServicePluginImpl".toClass(pluginClassLoader)
        dozeServicePluginImplClazz
            .method { name("onDreamingStarted") }
            .hook()
            .after {
                // 插件连接成功后注册歌词接收器
                val ctx = instance.javaClass.field { name("sysuiContext") }.get(instance).any()
                if (ctx != null) {
                    SuperLyricTool.registerSuperLyric(ctx as Context, SuperLyricStub)
                    YLog.debug("register superLyric success")
                }
            }
        dozeServicePluginImplClazz
            .method { name("onDreamingStopped") }
            .hook()
            .after {
                // 插件断开前注销歌词接收器
                val ctx = instance.javaClass.field { name("sysuiContext") }.get(instance).any()
                if (ctx != null) {
                    SuperLyricTool.unregisterSuperLyric(ctx as Context, SuperLyricStub)
                    YLog.debug("unregister superLyric success")
                }
            }

    }
}