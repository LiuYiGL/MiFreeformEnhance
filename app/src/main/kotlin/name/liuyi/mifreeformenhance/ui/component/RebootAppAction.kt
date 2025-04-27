package name.liuyi.mifreeformenhance.ui.component

import android.Manifest
import android.app.ActivityManager
import android.content.pm.PackageManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import top.yukonga.miuix.kmp.basic.ButtonDefaults
import top.yukonga.miuix.kmp.basic.TextButton
import top.yukonga.miuix.kmp.extra.SuperDialog
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.icons.useful.Reboot
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.MiuixPopupUtils.Companion.dismissDialog

@Composable
fun RebootAppAction(packageName: String) {
    var show by remember { mutableStateOf(false) }
    var label by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val pm: PackageManager? = context.packageManager

    LaunchedEffect(Unit) {
        label = pm?.runCatching { getPackageInfo(packageName, 0) }?.getOrNull()?.applicationInfo?.let {
            pm.getApplicationLabel(it).toString()
        }
        val granted = context.runCatching {
            checkSelfPermission(Manifest.permission.KILL_BACKGROUND_PROCESSES)
        }.getOrNull() == PackageManager.PERMISSION_GRANTED
        show = label != null && granted
    }

    if (show) {
        val show = remember { mutableStateOf(false) }
        SuperDialog(
            show = show, title = "确定重启 $label 吗？",
            onDismissRequest = {
                dismissDialog(show)
            },
        ) {
            Row {
                TextButton(modifier = Modifier.weight(1f), text = "取消", onClick = {
                    dismissDialog(show)
                })
                Spacer(modifier = Modifier.size(12.dp))
                TextButton(
                    modifier = Modifier.weight(1f), text = "确定",
                    onClick = {
                        dismissDialog(show)
                        crashApplication(packageName)
                    },
                    colors = ButtonDefaults.textButtonColorsPrimary(),
                )
            }
        }
        Image(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .size(26.dp)
                .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
                    show.value = true
                },
            imageVector = MiuixIcons.Useful.Reboot,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MiuixTheme.colorScheme.onSurfaceSecondary)
        )
    }
}

private fun crashApplication(packageName: String) {
    ActivityManager.getService().crashApplicationWithType(-1, -1, packageName, -1, "crashApplication", true, -100)
}