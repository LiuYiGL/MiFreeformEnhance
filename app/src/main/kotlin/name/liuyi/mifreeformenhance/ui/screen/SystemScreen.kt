package name.liuyi.mifreeformenhance.ui.screen

import android.content.pm.PackageManager
import android.util.MiuiMultiWindowAdapter
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
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
import top.yukonga.miuix.kmp.basic.BasicComponent
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.LazyColumn
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.basic.rememberTopAppBarState
import top.yukonga.miuix.kmp.extra.SuperDialog
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.utils.MiuixPopupUtils.Companion.dismissDialog
import top.yukonga.miuix.kmp.utils.overScrollVertical

@Composable
fun SystemScreen(navController: NavHostController = rememberNavController()) {
    val topAppBarScrollBehavior = MiuixScrollBehavior(rememberTopAppBarState())
    var label by remember { mutableStateOf("") }
    val packageManager: PackageManager? = LocalContext.current.packageManager
    val configuration = LocalConfiguration.current
    val prefs = LocalSharedPreferences.current

    DisposableEffect(configuration) {
        packageManager?.getPackageInfo(AndroidPackageName, 0)?.applicationInfo?.let {
            label = packageManager.getApplicationLabel(it).toString()
        }
        onDispose { }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = label,
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
            Card(modifier = Modifier.padding(12.dp)) {
                SuperSwitch(
                    title = "移除分屏黑名单限制",
                    checked = prefs.getBoolean("remove_split_screen_blacklist", false),
                    onCheckedChange = {
                        prefs.edit { putBoolean("remove_split_screen_blacklist", it) }
                    }
                )
                SuperSwitch(
                    title = "移除小窗黑名单限制",
                    checked = prefs.getBoolean("remove_freeform_blacklist", false),
                    onCheckedChange = {
                        prefs.edit { putBoolean("remove_freeform_blacklist", it) }
                    },
                    rightActions = {
                        val show = remember { mutableStateOf(false) }
                        SuperDialog(
                            modifier = Modifier.statusBarsPadding(),
                            title = "小窗黑名单",
                            show = show,
                            onDismissRequest = { dismissDialog(show) }) {
                            Column {
                                LazyColumn(modifier = Modifier.height(540.dp)) {
                                    items(MiuiMultiWindowAdapter.getFreeformBlackList()) { packageName ->
                                        var label by remember { mutableStateOf("") }
                                        LaunchedEffect(packageName) {
                                            runCatching {
                                                packageManager?.getPackageInfo(packageName, 0)?.applicationInfo?.let {
                                                    label = packageManager.getApplicationLabel(it).toString()
                                                }
                                            }
                                        }
                                        SelectionContainer {
                                            BasicComponent(title = label.ifEmpty { packageName }, summary = packageName)
                                        }
                                    }
                                }
                                TextButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                    text = "关闭",
                                    onClick = { dismissDialog(show) })
                            }
                        }
                        SmallTitle(
                            text = "查看",
                            modifier = Modifier.clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }) {
                                show.value = true
                            },
                        )
                    }
                )
                SuperSwitch(
                    title = "移除小窗数量限制",
                    checked = prefs.getBoolean("remove_freeform_quantity_limit", false),
                    onCheckedChange = {
                        prefs.edit { putBoolean("remove_freeform_quantity_limit", it) }
                    }
                )
            }
            Card(modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp)) {
                SuperSwitch(
                    title = "贴边小窗保持前台运行",
                    checked = prefs.getBoolean("edge_freeform_keep_foreground", false),
                    onCheckedChange = {
                        prefs.edit { putBoolean("edge_freeform_keep_foreground", it) }
                    }
                )
            }
        }
    }
}

@Preview(device = "spec:width=1080px,height=2400px,dpi=440", apiLevel = 35, locale = "zh-rCN")
@Composable
fun SystemScreenPreview() {
    AppTheme {
        CompositionLocalProvider(
            LocalSharedPreferences provides RemotePreferences()
        ) {
            SystemScreen()
        }
    }
}