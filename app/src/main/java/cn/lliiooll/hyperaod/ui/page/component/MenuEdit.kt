package cn.lliiooll.hyperaod.ui.page.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

object MenuEdit {
    @Composable
    fun view(name: String, def: String = "", modifier: Modifier = Modifier, valueChange: (String) -> Unit) {

        var text by remember { mutableStateOf(def) }
        MenuBase.view(name, modifier = modifier, view = {
            Row(
                modifier = Modifier
                    .height(30.dp)
                    .width(250.dp)
                    .background(Color.LightGray, RoundedCornerShape(3.dp)), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = text,
                    onValueChange = {
                        text = it
                        valueChange.invoke(it)
                    },

                    modifier = Modifier
                        .padding(9.dp, 6.dp)
                        .fillMaxSize(),

                    )
            }

        })
    }
}

@Composable
@Preview(showBackground = true)
private fun MenuEditPreview() {
    MenuEdit.view("test") {

    }
}