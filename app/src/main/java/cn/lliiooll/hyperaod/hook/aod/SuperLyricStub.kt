package cn.lliiooll.hyperaod.hook.aod

import android.annotation.SuppressLint
import com.hchen.superlyricapi.ISuperLyric
import com.hchen.superlyricapi.SuperLyricData
import com.highcapable.yukihookapi.hook.log.YLog

@SuppressLint("StaticFieldLeak")
object SuperLyricStub : ISuperLyric.Stub() {
    private var receiver: (data: SuperLyricData) -> Unit = { }

    override fun onStop(p0: SuperLyricData?) {
        //YLog.info("Lyric stop")
        if (p0 != null) receiver.invoke(p0)
    }

    @SuppressLint("ResourceType")
    override fun onSuperLyric(p0: SuperLyricData?) {
        val lyric = p0?.lyric
        //YLog.info("Receive lyric $lyric")
        if (p0 != null) receiver.invoke(p0)
    }


    fun init(receiver: (data: SuperLyricData) -> Unit) {
        this.receiver = receiver
    }
}