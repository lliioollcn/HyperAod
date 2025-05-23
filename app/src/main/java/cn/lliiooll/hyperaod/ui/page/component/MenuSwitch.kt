package cn.lliiooll.hyperaod.ui.page.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Switch
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object MenuSwitch {
    @Composable
    fun view(name: String, enable: Boolean, modifier: Modifier = Modifier, onCheckedChange: (Boolean) -> Unit = {}) {
        var status by remember { mutableStateOf(enable) }
        MenuBase.view(name, modifier = modifier, clickable = { status = !status }, view = {
            Switch(
                status, onCheckedChange = {
                    status = it
                    onCheckedChange(it)
                }, modifier = Modifier
                    .padding(0.dp, 0.dp, 30.dp, 0.dp)
                    .size(20.dp)
            )
        })
    }
}

@Composable
@Preview(showBackground = true)
private fun MenuSwitchPreview() {
    MenuSwitch.view("开关", true)
}