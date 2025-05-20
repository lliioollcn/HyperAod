package cn.lliiooll.hyperaod.ui.page.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.lliiooll.hyperaod.R

object SuperLyricTips {
    @Composable
    fun view(modifier: Modifier = Modifier) {
        val uri = LocalUriHandler.current

        Row(
            modifier = modifier
                .padding(25.dp, 10.dp)
                .fillMaxWidth()
                .background(
                    color = Color(0xFFFF9210),
                    shape = RoundedCornerShape(10.dp)
                )
                .clickable {
                    uri.openUri("https://github.com/HChenX/SuperLyric/releases")
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.ic_tips),
                contentDescription = "superlyric_tips_icon",
                modifier = Modifier
                    .padding(10.dp)
                    .padding(10.dp, 0.dp, 0.dp, 0.dp)
                    .size(35.dp)
            )
            Column(
                modifier = Modifier
                    .padding(0.dp, 30.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "要使用息屏歌词功能，你需要先安装SuperLyric"
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun SuperLyricTipsPreview() {
    SuperLyricTips.view()
}