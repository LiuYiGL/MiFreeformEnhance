package name.liuyi.mifreeformenhance.ui.screen

import android.content.pm.PackageManager
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import name.liuyi.mifreeformenhance.ui.LocalSharedPreferences
import name.liuyi.mifreeformenhance.ui.component.NavigationPopIcon
import name.liuyi.mifreeformenhance.ui.component.RebootAppAction
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
fun SystemUIScreen(navController: NavHostController = rememberNavController()) {

    val topAppBarScrollBehavior = MiuixScrollBehavior(rememberTopAppBarState())
    var label by remember { mutableStateOf("") }
    val pm: PackageManager? = LocalContext.current.packageManager
    val prefs = LocalSharedPreferences.current
    val allowNotificationSlide = remember { mutableStateListOf<String>() }

    LaunchedEffect(LocalConfiguration.current) {
        pm?.getPackageInfo(SystemUIPackageName, 0)?.applicationInfo?.let {
            label = pm.getApplicationLabel(it).toString()
        }
        if (pm != null) {
            val resources = pm.getResourcesForApplication(SystemUIPackageName)
            resources.getIdentifier("config_allowNotificationSlide", "array", SystemUIPackageName).let { id ->
                resources.getStringArray(id)
            }.also { allowNotificationSlide.addAll(it) }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = label,
                scrollBehavior = topAppBarScrollBehavior,
                navigationIcon = { NavigationPopIcon(navController) },
                actions = { RebootAppAction(SystemUIPackageName) }
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
                    title = "移除通知下拉限制",
                    checked = prefs.getBoolean("remove_freeform_notification_whitelist", false),
                    onCheckedChange = {
                        prefs.edit { putBoolean("remove_freeform_notification_whitelist", it) }
                    },
                    rightActions = {
                        if (allowNotificationSlide.isNotEmpty()) {
                            val show = remember { mutableStateOf(false) }
                            SmallTitle(
                                text = "查看",
                                modifier = Modifier.clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }) {
                                    show.value = true
                                },
                            )
                            SuperDialog(
                                modifier = Modifier.statusBarsPadding(),
                                title = "默认允许下拉应用",
                                show = show,
                                onDismissRequest = { dismissDialog(show) }) {
                                Column {
                                    LazyColumn(modifier = Modifier.height(540.dp)) {
                                        items(allowNotificationSlide) { packageName ->
                                            val title = packageName.runCatching {
                                                pm?.getPackageInfo(packageName, 0)?.applicationInfo?.let {
                                                    pm.getApplicationLabel(it).toString()
                                                }
                                            }.getOrNull() ?: packageName
                                            SelectionContainer {
                                                BasicComponent(title = title, summary = packageName)
                                            }
                                        }
                                    }
                                    TextButton(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(top = 8.dp),
                                        text = "关闭",
                                        onClick = { dismissDialog(show) },
                                    )
                                }
                            }
                        }
                    }
                )
                SuperSwitch(
                    title = "禁止云控",
                    checked = prefs.getBoolean("forbid_freeform_notification_whitelist_cloud", false),
                    onCheckedChange = {
                        prefs.edit { putBoolean("forbid_freeform_notification_whitelist_cloud", it) }
                    },
                )
            }
            Card(
                modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp)
            ) {
                SuperSwitch(
                    title = "隐藏顶栏",
                    checked = prefs.getBoolean("hide_top_caption", false),
                    onCheckedChange = {
                        prefs.edit { putBoolean("hide_top_caption", it) }
                    },
                )
                AnimatedVisibility(!prefs.getBoolean("hide_top_caption", false)) {
                    Column {
                        SuperSwitch(
                            title = "隐藏顶栏小圆点",
                            checked = prefs.getBoolean("hide_top_dots", false),
                            onCheckedChange = {
                                prefs.edit { putBoolean("hide_top_dots", it) }
                            },
                        )
                    }
                }
                SuperSwitch(
                    title = "隐藏底栏",
                    checked = prefs.getBoolean("hide_bottom_caption", false),
                    onCheckedChange = {
                        prefs.edit { putBoolean("hide_bottom_caption", it) }
                    },
                )
                AnimatedVisibility(!prefs.getBoolean("hide_bottom_caption", false)) {
                    Column {
                        SuperSwitch(
                            title = "隐藏底栏小白条",
                            checked = prefs.getBoolean("hide_bottom_bar", false),
                            onCheckedChange = {
                                prefs.edit { putBoolean("hide_bottom_bar", it) }
                            },
                        )
                    }
                }
            }
        }
    }
}