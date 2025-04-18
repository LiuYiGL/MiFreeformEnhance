package name.liuyi.mifreeformenhance.ui

import android.content.SharedPreferences
import androidx.compose.runtime.staticCompositionLocalOf

val LocalSharedPreferences = staticCompositionLocalOf<SharedPreferences> {
    error("CompositionLocal LocalSharedPreferences not present")
}
