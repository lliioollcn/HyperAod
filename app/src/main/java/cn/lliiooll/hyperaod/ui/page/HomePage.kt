package cn.lliiooll.hyperaod.ui.page

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.lliiooll.hyperaod.ui.page.component.MenuSelect
import cn.lliiooll.hyperaod.ui.page.component.MenuSwitch
import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.hook.factory.prefs

object HomePage : BasePage() {

    @Composable
    override fun view(navController: NavHostController) {
        val prefs = LocalContext.current.prefs()
        Column(modifier = Modifier.fillMaxSize()) {
            // 顶部标题栏
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = "息屏增强", fontSize = 25.sp)

            }
            // 模块激活状态
            val active by remember { mutableStateOf(YukiHookAPI.Status.isModuleActive) }

            // 设置
            MenuSwitch.view("主开关", prefs.getBoolean("enable", false)) {
                prefs.edit { putBoolean("enable", it) }
            }

            MenuSelect.view("歌词设置") {
                navController.navigate("lyric")
            }
            // TODO: 一言
            /*
            MenuSelect.view("一言设置") {
                navController.navigate("hiktokoto")
            }

             */

            MenuSelect.view("其他设置") {
                navController.navigate("others")
            }

        }
    }

}

@Composable
@Preview(showBackground = true)
private fun HomePagePreview() {
    HomePage.view(rememberNavController())
}