package name.liuyi.mifreeformenhance

import android.app.Application
import name.liuyi.mifreeformenhance.common.XLogManager

class Module : Application() {

    init {
        XLogManager.init()
    }
}