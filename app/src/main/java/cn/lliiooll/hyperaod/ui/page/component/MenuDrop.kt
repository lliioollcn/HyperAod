package cn.lliiooll.hyperaod.ui.page.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.lliiooll.hyperaod.R

object MenuDrop {
    @Composable
    fun view(
        name: String,
        selected: String = "无",
        modifier: Modifier = Modifier,
        content: MutableMap<@Composable () -> Unit, () -> Unit> = hashMapOf()
    ) {
        var expand by remember { mutableStateOf(false) }
        MenuBase.view(name, modifier = modifier, clickable = {
            expand = !expand
        }, view = {
            Text(selected)
            Image(
                painter = painterResource(R.drawable.ic_arrow), contentDescription = "menu_arrow",
                modifier = Modifier
                    .padding(10.dp, 0.dp)
                    .size(20.dp)
                    .rotate(90f)
            )
            DropdownMenu(expand, onDismissRequest = { expand = false }) {
                content.forEach { item ->
                    DropdownMenuItem(text = item.key, onClick = {
                        item.value()
                        expand = false
                    })
                }
            }
        })
    }
}

@Composable
@Preview(showBackground = true)
private fun MenuDropPreview() {
    MenuDrop.view("test")
}