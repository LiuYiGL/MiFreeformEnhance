package name.liuyi.mifreeformenhance.common

import android.os.UserHandle
import com.elvishew.xlog.LogConfiguration
import com.elvishew.xlog.XLog

object XLogManager {

    const val TAG = "MiFreeformEnhance"

    var userId: Int = UserHandle.myUserId()

    fun init() {
        val configuration = LogConfiguration.Builder()
            .tag(TAG)
            .build()
        XLog.init(configuration)
    }

    interface LogScope {
        val tag: String get() = this.javaClass.simpleName

        fun d(message: Any?) {
            XLog.d("[$userId][$tag]$message")
        }

        fun d(message: Any?, e: Throwable?) {
            XLog.d("[$userId][$tag]$message", e)
        }

        fun i(message: Any?) {
            XLog.i("[$userId][$tag]$message")
        }

        fun i(message: Any?, e: Throwable?) {
            XLog.i("[$userId][$tag]$message", e)
        }

        fun w(message: Any?) {
            XLog.w("[$userId][$tag]$message")
        }

        fun w(message: Any?, e: Throwable?) {
            XLog.w("[$userId][$tag]$message", e)
        }

        fun e(message: Any?) {
            XLog.e("[$userId][$tag]$message")
        }

        fun e(message: Any?, e: Throwable?) {
            XLog.e("[$userId][$tag]$message", e)
        }
    }
}