package cn.lliiooll.hyperaod.ui.page.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cn.lliiooll.hyperaod.utils.color

object MenuColorEdit {
    @Composable
    fun view(name: String, def: String = "", modifier: Modifier = Modifier, valueChange: (String) -> Unit) {

        var text by remember { mutableStateOf(def) }
        MenuBase.view(name, modifier = modifier, view = {
            Row(
                modifier = Modifier
                    .height(30.dp)
                    .width(45.dp)
                    .padding(end = 5.dp)
                    .background(color(text), RoundedCornerShape(3.dp))
                    .border(1.dp, Color.Black, RoundedCornerShape(3.dp)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

            }
            Row(
                modifier = Modifier
                    .height(30.dp)
                    .width(200.dp)
                    .padding(end = 10.dp)
                    .background(Color.LightGray, RoundedCornerShape(3.dp)), horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = text,
                    onValueChange = {
                        if (it.startsWith("#")) {
                            text = if (it.length <= 9) it else it.substring(0, 9)
                        } else {
                            text = if (it.length <= 8) it else it.substring(0, 8)
                        }
                        valueChange.invoke(text)
                    },

                    modifier = Modifier
                        .padding(9.dp, 6.dp)
                        .fillMaxSize(),
                    singleLine = true,

                    )

            }

        })
    }

    private fun color(text: String): Color {
        return Color(text.color())
    }
}

@Composable
@Preview(showBackground = true)
private fun MenuColorEditPreview() {
    MenuColorEdit.view("test", "#239fe8") {

    }
}