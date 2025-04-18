package name.liuyi.mifreeformenhance.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.icons.useful.Back
import top.yukonga.miuix.kmp.theme.MiuixTheme

@Composable
fun NavigationPopIcon(navController: NavHostController) {
    if (navController.previousBackStackEntry != null) {
        Image(
            modifier = Modifier
                .padding(start = 32.dp)
                .size(26.dp, 26.dp)
                .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
                    navController.popBackStack()
                },
            imageVector = MiuixIcons.Useful.Back,
            contentDescription = null,
            colorFilter = ColorFilter.tint(MiuixTheme.colorScheme.onSurfaceSecondary)
        )
    }
}