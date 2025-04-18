package name.liuyi.mifreeformenhance.ui.screen

import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import name.liuyi.mifreeformenhance.provider.RemotePreferences
import name.liuyi.mifreeformenhance.ui.AppTheme
import name.liuyi.mifreeformenhance.ui.LocalSharedPreferences
import name.liuyi.mifreeformenhance.ui.component.NavigationPopIcon
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.basic.rememberTopAppBarState
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.utils.overScrollVertical

@Composable
fun MiuiHomeScreen(navController: NavHostController = rememberNavController()) {
    val topAppBarScrollBehavior = MiuixScrollBehavior(rememberTopAppBarState())
    val prefs = LocalSharedPreferences.current
    val packageManager: PackageManager? = LocalContext.current.packageManager
    var miuiHomeLabel by remember { mutableStateOf<String>("") }

    val configuration = LocalConfiguration.current
    DisposableEffect(configuration) {
        packageManager?.getPackageInfo(MiuiHomePackageName, 0)?.applicationInfo?.let {
            miuiHomeLabel = packageManager.getApplicationLabel(it).toString()
        }
        onDispose { }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = miuiHomeLabel,
                scrollBehavior = topAppBarScrollBehavior,
                navigationIcon = { NavigationPopIcon(navController) },
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .overScrollVertical()
                .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
                .verticalScroll(rememberScrollState())
        ) {
            Card(
                modifier = Modifier.padding(12.dp)
            ) {
                SuperSwitch(
                    title = "小窗捷径",
                    summary = "在快捷菜单中添加小窗捷径",
                    checked = prefs.getBoolean("home_freeform_shortcut", false),
                    onCheckedChange = {
                        prefs.edit { putBoolean("home_freeform_shortcut", it) }
                    }
                )
            }
        }
    }
}

@Preview(device = "spec:width=1080px,height=2400px,dpi=440", apiLevel = 35, locale = "zh-rCN")
@Composable
fun MiuiHomeScreenPreview() {
    AppTheme {
        CompositionLocalProvider(
            LocalSharedPreferences provides RemotePreferences()
        ) {
            MiuiHomeScreen()
        }
    }
}