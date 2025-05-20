package cn.lliiooll.hyperaod.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cn.lliiooll.hyperaod.ui.page.HomePage
import cn.lliiooll.hyperaod.ui.page.LyricPage
import cn.lliiooll.hyperaod.ui.theme.HyperAodTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HyperAodTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainPage(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainPage(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    Column(modifier = modifier.fillMaxSize()) {
        NavHost(navController = navController, startDestination = "home") {
            composable("home") {
                HomePage.view(navController)
            }
            composable("lyric") {
                LyricPage.view(navController)
            }
            composable("hiktokoto") {
                //HomePage.view(navController)
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HyperAodTheme {
        MainPage()
    }
}