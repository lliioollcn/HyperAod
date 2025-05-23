package cn.lliiooll.hyperaod.ui.page.component

import android.icu.text.DecimalFormat
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly

object MenuSlide {
    @Composable
    fun view(
        name: String,
        num: Float,
        range: ClosedFloatingPointRange<Float> = 0f..10f,
        modifier: Modifier = Modifier,
        onInputChanged: (num: Float) -> Unit = {}
    ) {
        var input by remember { mutableStateOf(num) }
        var edit by remember {
            mutableStateOf(
                DecimalFormat("#.#")
                    .format(input)
            )
        }
        MenuBase.view(name, modifier = modifier, view = {
            BasicTextField(
                value = edit,
                onValueChange = {
                    edit = it
                    if (it.isNotBlank() && it.isDigitsOnly() && range.contains(it.toFloat())) {
                        input = it.toFloat()
                        edit = DecimalFormat("#.#").format(input)
                        onInputChanged(it.toFloat())
                    }
                },
                modifier = Modifier
                    .width(50.dp),
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
            )
            Slider(
                value = input, onValueChange = {
                    input = it
                    edit = DecimalFormat("#.#").format(input)
                    onInputChanged(it)
                }, modifier = Modifier
                    .width(200.dp)
                    .height(20.dp)
                    .padding(end = 10.dp),
                valueRange = range
            )
        })
    }
}

@Composable
@Preview(showBackground = true)
private fun MenuSlidePreview() {
    MenuSlide.view("滑动输入", 3.0f)
}