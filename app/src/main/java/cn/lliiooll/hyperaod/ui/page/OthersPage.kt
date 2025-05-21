package cn.lliiooll.hyperaod.ui.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cn.lliiooll.hyperaod.ui.page.component.MenuSwitch
import com.highcapable.yukihookapi.hook.factory.prefs

object OthersPage : BasePage() {


    @Composable
    override fun view(navController: NavHostController) {

        val prefs = LocalContext.current.prefs()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            MenuSwitch.view("GIF无限循环", prefs.getBoolean("others.infgif", false)) {
                prefs.edit { putBoolean("others.infgif", it) }
            }

        }
    }

}

@Composable
@Preview(showBackground = true)
private fun OthersPagePreview() {
    OthersPage.view(rememberNavController())
}