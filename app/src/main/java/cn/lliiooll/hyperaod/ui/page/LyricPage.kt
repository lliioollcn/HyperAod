package cn.lliiooll.hyperaod.ui.page

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.lliiooll.hyperaod.ui.page.component.*
import com.highcapable.yukihookapi.hook.factory.prefs

object LyricPage : BasePage() {

    val animMap = mutableMapOf<String, Int>().apply {
        this["上划"] = 0
        this["下划"] = 1
        this["左划"] = 2
        this["右划"] = 3
        this["消失"] = 4
        this["出现"] = 5
    }
    val locMap = mutableMapOf<String, Int>().apply {
        this["居中"] = 0
        this["靠左"] = 1
        this["靠右"] = 2
    }

    @Composable
    override fun view(navController: NavHostController) {

        val prefs = LocalContext.current.prefs()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // 提示
            SuperLyricTips.view()
            // 设置歌词图标
            var lyricEnable by remember { mutableStateOf(prefs.getBoolean("lyric_enable", false)) }
            var rainbowEnable by remember { mutableStateOf(prefs.getBoolean("lyric_rainbow", false)) }
            var iconEnable by remember { mutableStateOf(prefs.getBoolean("lyric_icon", false)) }
            MenuSwitch.view("歌词开关", lyricEnable) {
                lyricEnable = it
                prefs.edit { putBoolean("lyric_enable", it) }
            }
            // TODO: 全项展开
            MenuSwitch.view("歌词图标", iconEnable) {
                iconEnable = it
                prefs.edit { putBoolean("lyric_icon", it) }
            }

            AnimatedVisibility(
                visible = iconEnable,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                // 设置歌词图标大小
                MenuSlide.view("图标大小", prefs.getFloat("lyric_icon_size", 25f), range = 10f..50f) {
                    prefs.edit { putFloat("lyric_icon_size", it) }
                }
            }

            // 设置文字大小
            MenuSlide.view("文字大小", prefs.getFloat("lyric_text_size", 25f), range = 1f..50f) {
                prefs.edit { putFloat("lyric_text_size", it) }
            }
            // 设置彩虹文字
            MenuSwitch.view("动态渐变", rainbowEnable) {
                rainbowEnable = it
                prefs.edit { putBoolean("lyric_rainbow", it) }
            }
            AnimatedVisibility(
                visible = rainbowEnable,
                enter = expandVertically(),
                exit = shrinkVertically()
            ) {
                // 设置渐变速度
                MenuSlide.view("渐变速度", prefs.getFloat("lyric_rainbow_duration", 200f), range = 0f..10000f) {
                    prefs.edit { putFloat("lyric_rainbow_duration", it) }
                }
            }
            // 设置文字颜色
            MenuColorEdit.view("渐变颜色1", prefs.getString("lyric_text_color1", "#FF0000")) {
                prefs.edit { putString("lyric_text_color1", it) }
            }
            // 设置文字颜色
            MenuColorEdit.view("渐变颜色2", prefs.getString("lyric_text_color2", "#FF7F00")) {
                prefs.edit { putString("lyric_text_color2", it) }
            }

            var lyricLocation by remember { mutableStateOf(prefs.getInt("lyric_location", 0)) }
            var enterAnim by remember { mutableStateOf(prefs.getInt("lyric_enter_anim", 0)) }
            var exitAnim by remember { mutableStateOf(prefs.getInt("lyric_exit_anim", 0)) }
            // 歌词位置
            drop("文字位置", lyricLocation, locMap) {
                lyricLocation = it
                prefs.edit { putInt("lyric_location", it) }
            }
            // 设置文字边距
            MenuSlide.view("文字边距", prefs.getFloat("lyric_padding", 0f), range = 0f..500f) {
                prefs.edit { putFloat("lyric_padding", it) }
            }
            // 歌词进入动画
            drop("歌词进入动画", enterAnim, animMap.filter { it.value != 4 }.toMutableMap()) {
                enterAnim = it
                prefs.edit { putInt("lyric_enter_anim", it) }
            }
            // 歌词退出动画
            drop("歌词退出动画", exitAnim, animMap.filter { it.value != 5 }.toMutableMap()) {
                exitAnim = it
                prefs.edit { putInt("lyric_exit_anim", it) }
            }

            // 设置动画时长
            MenuSlide.view("动画时长", prefs.getFloat("lyric_anim_duration", 800f), range = 0f..10000f) {
                prefs.edit { putFloat("lyric_anim_duration", it) }
            }

        }
    }

    @Composable
    fun drop(name: String, selected: Int, map: MutableMap<String, Int>, action: (value: Int) -> Unit) {
        MenuDrop.view(
            name, map.entries.associate { (k, v) -> v to k }[selected]!!,
            content = hashMapOf<@Composable () -> Unit, () -> Unit>().apply {
                map.forEach {
                    this[{ Text(it.key) }] = { action(it.value) }
                }
            },
        )
    }

}


@Composable
@Preview(showBackground = true)
private fun LyricPagePreview() {
    LyricPage.view(rememberNavController())
}