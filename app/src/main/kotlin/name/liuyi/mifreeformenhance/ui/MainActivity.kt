package name.liuyi.mifreeformenhance.ui

import android.content.SharedPreferences
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import name.liuyi.mifreeformenhance.BuildConfig
import name.liuyi.mifreeformenhance.common.XLogManager
import name.liuyi.mifreeformenhance.provider.RemotePreferences
import name.liuyi.mifreeformenhance.ui.screen.HomeScreen
import name.liuyi.mifreeformenhance.ui.screen.MiuiHomeScreen
import name.liuyi.mifreeformenhance.ui.screen.SystemScreen
import name.liuyi.mifreeformenhance.ui.screen.SystemUIScreen

class MainActivity : ComponentActivity(), XLogManager.LogScope {

    private lateinit var mPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        d("onCreate")
        window.decorView.windowInsetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )

        mPrefs = runCatching {
            getSharedPreferences("${BuildConfig.APPLICATION_ID}_preferences", MODE_WORLD_READABLE)
        }.getOrElse {
            getSharedPreferences("${BuildConfig.APPLICATION_ID}_preferences", MODE_PRIVATE)
        }.let { RemotePreferences(it).apply { attachContentResolver(contentResolver) } }

        setContent {
            AppTheme {
                CompositionLocalProvider(
                    LocalSharedPreferences provides mPrefs
                ) {
                    val navController = rememberNavController()
                    // 进入动画：从右移动到左边，同时左边变黑
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        enterTransition = {
                            slideInHorizontally { it }
                        },
                        exitTransition = {
                            slideOutHorizontally { -it }
                        },
                        popEnterTransition = {
                            slideInHorizontally { -it }
                        },
                        popExitTransition = {
                            slideOutHorizontally { it }
                        }
                    ) {
                        composable("home") {
                            HomeScreen(navController)
                        }
                        composable("com.miui.home") {
                            MiuiHomeScreen(navController)
                        }
                        composable("android") {
                            SystemScreen(navController)
                        }
                        composable("com.android.systemui") {
                            SystemUIScreen(navController)
                        }
                    }
                }
            }
        }
    }
}