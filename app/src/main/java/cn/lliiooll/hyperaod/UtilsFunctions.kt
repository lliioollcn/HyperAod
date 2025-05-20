package cn.lliiooll.hyperaod

class UtilsFunctions {
}

fun String.color(): Long {
    val regex = "[0-9a-fA-F]+".toRegex().find(this)
    if (regex != null) {
        var color = regex.value
        if (color.length > 8) color = color.substring(0, 8)
        while (color.length < 8) {
            color = "f$color"
        }
        try {
            return color.toLong(16)
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }
    return 0xffffff
}

