package name.liuyi.mifreeformenhance.ui.component

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.IPowerManager
import android.os.ServiceManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.icons.useful.Reboot
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun RebootAction() {

    var show by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        show = context.runCatching {
            checkSelfPermission(Manifest.permission.REBOOT)
        }.getOrNull() == PackageManager.PERMISSION_GRANTED
    }

    if (show) {
        Image(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .size(26.dp)
                .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
                    reboot()
                },
            imageVector = MiuixIcons.Useful.Reboot,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MiuixTheme.colorScheme.onSurfaceSecondary)
        )
    }
}

private fun reboot(): Result<Unit> = runCatching {
    IPowerManager.Stub.asInterface(ServiceManager.getService(Context.POWER_SERVICE)).reboot(true, null, false)
}