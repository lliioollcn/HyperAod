package cn.lliiooll.hyperaod.ui.page.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.lliiooll.hyperaod.R

object MenuSelect {
    @Composable
    fun view(name: String, modifier: Modifier = Modifier, clickable: () -> Unit) {
        MenuBase.view(name, modifier = modifier, clickable = clickable, view = {
            Image(
                painter = painterResource(R.drawable.ic_arrow), contentDescription = "menu_arrow",
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .size(20.dp)

            )
        })
    }
}


@Composable
@Preview(showBackground = true)
private fun MenuSelectPreview() {
    MenuSelect.view("test") {}
}