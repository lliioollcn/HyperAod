package cn.lliiooll.hyperaod.ui.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.lliiooll.hyperaod.R

object AboutPage : BasePage() {


    @Composable
    override fun view(navController: NavHostController) {

        val uri = LocalUriHandler.current
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painterResource(R.mipmap.ic_launcher), null,
                    modifier = Modifier
                        .size(100.dp),
                )
                Text(
                    text = stringResource(R.string.app_name),
                    fontSize = 20.sp
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.clickable {
                        uri.openUri("https://github.com/lliioollcn/HyperAod")
                    },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.mipmap.ic_github), null,
                        modifier = Modifier
                            .size(27.dp)
                            .padding(end = 5.dp)
                    )
                    Text(text = "Github")
                }
                Row(
                    modifier = Modifier
                        .clickable {
                            uri.openUri("https://qm.qq.com/q/6VXrmSnAoo")
                        }
                        .padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "交流Q群: 1048253759")
                }

                Row(
                    modifier = Modifier
                        .clickable {
                            uri.openUri("https://github.com/HighCapable/YukiHookAPI")
                        }
                        .padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(R.mipmap.ic_yukihookapi), null,
                        modifier = Modifier
                            .size(27.dp)
                            .padding(end = 5.dp)
                    )
                    Text(text = "模块使用YukiHookAPI构建")
                }

                Row(
                    modifier = Modifier
                        .clickable {
                            uri.openUri("https://github.com/HChenX/SuperLyricApi")
                        }
                        .padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "模块使用SuperLyricApi获取歌词")
                }

                Row(
                    modifier = Modifier
                        .clickable {
                            uri.openUri("https://github.com/LSPosed/LSPosed")
                        }
                        .padding(top = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "模块仅支持LSPosed使用!")
                }

            }

        }
    }

}

@Composable
@Preview(showBackground = true)
private fun AboutPagePreview() {
    AboutPage.view(rememberNavController())
}