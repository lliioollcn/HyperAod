package cn.lliiooll.hyperaod.utils

object Tools {
    fun hasRoot(): Boolean {

        try {
            Runtime.getRuntime().exec("su")
            return true
        } catch (_: Throwable) {

        }
        return false
    }

    fun killSystemUI() {
        if (hasRoot()) {
            Runtime.getRuntime().exec("su").outputStream.apply {
                write("killall com.android.systemui".toByteArray())
                flush()
                close()
            }
        }
    }
}