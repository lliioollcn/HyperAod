package cn.lliiooll.hyperaod.ui.page

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.lliiooll.hyperaod.ui.page.component.MenuSelect
import cn.lliiooll.hyperaod.ui.page.component.MenuSwitch
import cn.lliiooll.hyperaod.utils.Tools
import com.highcapable.yukihookapi.YukiHookAPI
import com.highcapable.yukihookapi.hook.factory.prefs

object HomePage : BasePage() {

    @Composable
    override fun view(navController: NavHostController) {
        val ctx = LocalContext.current
        val prefs = ctx.prefs()

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
            var openDialog by remember { mutableStateOf(false) }

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
            MenuSelect.view("关于模块") {
                navController.navigate("about")
            }

            MenuSelect.view("重启系统界面") {
                openDialog = true
            }

            when (openDialog) {
                true -> {
                    AlertDialog(
                        icon = {
                            Icon(Icons.Default.Info, contentDescription = "Example Icon")
                        },
                        text = {
                            Text(text = "确认重启系统界面?")
                        },
                        onDismissRequest = {
                            openDialog = false
                        },
                        confirmButton = {
                            TextButton(
                                onClick = {
                                    when (Tools.hasRoot()) {
                                        true -> {
                                            Tools.killSystemUI()
                                        }

                                        false -> {
                                            Toast.makeText(ctx, "未获取到Root权限", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                    openDialog = false
                                }
                            ) {
                                Text("确认")
                            }
                        },
                        dismissButton = {
                            TextButton(
                                onClick = {
                                    openDialog = false
                                }
                            ) {
                                Text("取消")
                            }
                        }
                    )
                }

                false -> {}
            }


        }
    }

}

@Composable
@Preview(showBackground = true)
private fun HomePagePreview() {
    HomePage.view(rememberNavController())
}