package name.liuyi.mifreeformenhance.ui.screen

import android.content.pm.PackageManager
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import name.liuyi.mifreeformenhance.R
import name.liuyi.mifreeformenhance.provider.RemotePreferences
import name.liuyi.mifreeformenhance.provider.isWorldReadable
import name.liuyi.mifreeformenhance.ui.AppTheme
import name.liuyi.mifreeformenhance.ui.LocalSharedPreferences
import name.liuyi.mifreeformenhance.ui.component.NavigationPopIcon
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.LazyColumn
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.basic.rememberTopAppBarState
import top.yukonga.miuix.kmp.extra.SuperArrow
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.overScrollVertical

const val MiuiHomePackageName: String = "com.miui.home"
const val AndroidPackageName: String = "android"

@Composable
fun HomeScreen(navController: NavHostController = rememberNavController()) {
    val topAppBarScrollBehavior = MiuixScrollBehavior(rememberTopAppBarState())
    val prefs = LocalSharedPreferences.current
    val packageManager: PackageManager? = LocalContext.current.packageManager
    var miuiHomeLabel by remember { mutableStateOf<String>("") }
    var systemLabel by remember { mutableStateOf<String>("") }

    val configuration = LocalConfiguration.current
    DisposableEffect(configuration) {
        packageManager?.getPackageInfo(MiuiHomePackageName, 0)?.applicationInfo?.let {
            miuiHomeLabel = packageManager.getApplicationLabel(it).toString()
        }
        packageManager?.getPackageInfo(AndroidPackageName, 0)?.applicationInfo?.let {
            systemLabel = packageManager.getApplicationLabel(it).toString()
        }
        onDispose { }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = stringResource(R.string.app_name),
                scrollBehavior = topAppBarScrollBehavior,
                navigationIcon = {
                    NavigationPopIcon(navController)
                })
        }
    ) { padding ->
        LazyColumn { }
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .overScrollVertical()
                .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection)
                .verticalScroll(rememberScrollState())
        ) {
            Card(modifier = Modifier.padding(12.dp)) {
                BasicComponent(
                    title = stringResource(R.string.xposed_state),
                    summary = prefs.getStringSet("xposed_scope", setOf())?.takeIf { it.isNotEmpty() }
                        ?.joinToString("\n"),
                    rightActions = {
                        Text(
                            modifier = Modifier.widthIn(max = 130.dp),
                            text = stringResource(if (prefs.isWorldReadable()) R.string.enabled else R.string.disabled),
                            fontSize = MiuixTheme.textStyles.body1.fontSize,
                            color = MiuixTheme.colorScheme.onSurfaceVariantActions,
                            textAlign = TextAlign.End,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                )
            }
            SmallTitle(stringResource(R.string.scope))
            Card(modifier = Modifier.padding(12.dp, 0.dp, 12.dp, 12.dp)) {
                SuperArrow(
                    title = systemLabel,
                    summary = AndroidPackageName,
                    onClick = { navController.navigate(AndroidPackageName) }
                )
                SuperArrow(
                    title = miuiHomeLabel,
                    summary = MiuiHomePackageName,
                    onClick = { navController.navigate(MiuiHomePackageName) }
                )
            }
        }
    }
}

@Preview(device = "spec:width=1080px,height=2400px,dpi=440", apiLevel = 35, locale = "zh-rCN")
@Composable
fun HomeScreenPreview() {
    CompositionLocalProvider(
        LocalSharedPreferences provides RemotePreferences(
            mutableMapOf(
                "xposed_scope" to setOf("com.miui.home", "com.miui.home.launcher", "com.miui.home.launcher.settings")
            )
        )
    ) {
        AppTheme {
            HomeScreen()
        }
    }
}