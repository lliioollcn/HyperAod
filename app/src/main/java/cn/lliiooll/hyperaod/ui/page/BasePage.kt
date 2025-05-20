package cn.lliiooll.hyperaod.ui.page

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

abstract class BasePage {

    @Composable
    abstract fun view(navController: NavHostController)
}