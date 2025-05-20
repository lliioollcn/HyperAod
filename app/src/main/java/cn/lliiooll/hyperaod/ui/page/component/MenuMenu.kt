package cn.lliiooll.hyperaod.ui.page.component

import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

object MenuMenu {
    @Composable
    fun view(name: String, modifier: Modifier = Modifier, clickable: () -> Unit = {}) {

        MenuBase.view(name, row = false, modifier = modifier, view = {
            Checkbox(true, { clickable() })
        })
    }
}

@Composable
@Preview(showBackground = true)
private fun MenuMenuPreview() {
    MenuMenu.view("test") {

    }
}