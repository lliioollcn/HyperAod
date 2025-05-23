package cn.lliiooll.hyperaod.hook

import cn.lliiooll.hyperaod.BuildConfig
import cn.lliiooll.hyperaod.hook.systemui.AodHooker
import com.highcapable.yukihookapi.annotation.xposed.InjectYukiHookWithXposed
import com.highcapable.yukihookapi.hook.factory.configs
import com.highcapable.yukihookapi.hook.factory.encase
import com.highcapable.yukihookapi.hook.xposed.proxy.IYukiHookXposedInit

@InjectYukiHookWithXposed
class HookEntry : IYukiHookXposedInit {

    override fun onInit() = configs {
        debugLog {
            tag = "HyperAod"
            isEnable = BuildConfig.DEBUG
        }
        isDebug = BuildConfig.DEBUG
    }

    override fun onHook() = encase {
        loadApp("com.android.systemui") {
            loadHooker(AodHooker)

        }
        /*
        loadApp("com.netease.cloudmusic") {
            //setMediaMetadata() called with: title = [
            "gn0.h".toClass()
                .method {
                    name("Q")
                }.hook().after {
                    YLog.info("App lyric: ${args(0).string()}")

                }
        }

         */
    }
}