package cn.lliiooll.hyperaod.ui.page.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object MenuBase {
    @Composable
    fun view(
        name: String,
        row: Boolean = true,
        modifier: Modifier = Modifier,
        view: @Composable () -> Unit = {},
        clickable: () -> Unit = {}
    ) {
        if (row) {
            Row(
                modifier = modifier
                    .padding(25.dp, 10.dp)
                    .fillMaxWidth()
                    .height(45.dp)
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { clickable() },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = name, modifier = Modifier
                        .weight(1f)
                        .padding(15.dp, 0.dp)
                )
                view()
            }
        } else {
            Column(
                modifier = modifier
                    .padding(25.dp, 10.dp)
                    .fillMaxWidth()
                    .background(
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(10.dp)
                    )
                    .clickable { clickable() },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = name, modifier = Modifier
                        .padding(0.dp, 15.dp)
                )
                view()
            }
        }

    }
}