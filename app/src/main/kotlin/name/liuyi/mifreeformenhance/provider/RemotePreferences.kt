package name.liuyi.mifreeformenhance.provider

import android.annotation.SuppressLint
import android.content.ContentResolver
import android.content.Context
import android.content.SharedPreferences
import android.content.UriMatcher
import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import androidx.collection.ArrayMap
import androidx.compose.runtime.mutableStateMapOf
import androidx.core.net.toUri
import name.liuyi.mifreeformenhance.common.XLogManager

@Suppress("UNCHECKED_CAST")
class RemotePreferences(initMap: Map<String, Any?> = HashMap()) : SharedPreferences, XLogManager.LogScope {

    private val mMap = mutableStateMapOf<String, Any?>().also { it.putAll(initMap) }
    private val mListeners = ArrayList<SharedPreferences.OnSharedPreferenceChangeListener>()
    private var mContentResolver: ContentResolver? = null
    private var mContentObserver: ContentObserver? = null
    private var mBase: SharedPreferences? = null

    val base: SharedPreferences?
        get() = mBase

    constructor(base: SharedPreferences) : this(base.all) {
        mBase = base
    }

    override fun getAll(): Map<String, *>? {
        return mMap
    }

    override fun getString(key: String?, defValue: String?): String? {
        return mMap[key] as String? ?: defValue
    }

    override fun getStringSet(
        key: String?,
        defValues: Set<String?>?
    ): Set<String?>? {
        return mMap[key] as MutableSet<String>? ?: defValues
    }

    override fun getInt(key: String?, defValue: Int): Int {
        return mMap[key] as Int? ?: defValue
    }

    override fun getLong(key: String?, defValue: Long): Long {
        return mMap[key] as Long? ?: defValue
    }

    override fun getFloat(key: String?, defValue: Float): Float {
        return mMap[key] as Float? ?: defValue
    }

    override fun getBoolean(key: String?, defValue: Boolean): Boolean {
        return mMap[key] as Boolean? ?: defValue
    }

    override fun contains(key: String?): Boolean {
        return mMap.containsKey(key)
    }

    inner class Editor(val base: SharedPreferences.Editor? = null) : SharedPreferences.Editor {

        private var mClear = false
        private val mModified = ArrayMap<String, Any?>()

        override fun putString(key: String?, value: String?): SharedPreferences.Editor? {
            base?.putString(key, value)
            mModified[key] = value
            return this
        }

        override fun putStringSet(
            key: String?,
            values: Set<String?>?
        ): SharedPreferences.Editor? {
            base?.putStringSet(key, values)
            mModified[key] = if (values == null) null else HashSet(values)
            return this
        }

        override fun putInt(key: String?, value: Int): SharedPreferences.Editor? {
            base?.putInt(key, value)
            mModified[key] = value
            return this
        }

        override fun putLong(key: String?, value: Long): SharedPreferences.Editor? {
            base?.putLong(key, value)
            mModified[key] = value
            return this
        }

        override fun putFloat(key: String?, value: Float): SharedPreferences.Editor? {
            base?.putFloat(key, value)
            mModified[key] = value
            return this
        }

        override fun putBoolean(key: String?, value: Boolean): SharedPreferences.Editor? {
            base?.putBoolean(key, value)
            mModified[key] = value
            return this
        }

        override fun remove(key: String?): SharedPreferences.Editor? {
            base?.remove(key)
            mModified.put(key, this)
            return this
        }

        override fun clear(): SharedPreferences.Editor? {
            base?.clear()
            mClear = true
            return this
        }

        override fun commit(): Boolean {
            base?.commit()
            apply()
            return true
        }

        override fun apply() {
            base?.apply()
            if (mContentResolver == null) {
                w("RemotePreferences contentResolver is null")
                return
            }

            val uris = mutableListOf<Uri>()
            if (mClear) {
                uris.add("content://$AUTHORITY/clear".toUri())
            }
            for ((key, value) in mModified) {
                when (value) {
                    is Editor -> uris.add("content://${AUTHORITY}/remove/$key".toUri())
                    is String -> uris.add("content://${AUTHORITY}/string/$key".toUri())
                    is Int -> uris.add("content://${AUTHORITY}/int/$key/$value".toUri())
                    is Long -> uris.add("content://${AUTHORITY}/long/$key/$value".toUri())
                    is Float -> uris.add("content://${AUTHORITY}/float/$key/$value".toUri())
                    is Set<*> -> uris.add("content://${AUTHORITY}/string_set/$key/${value.joinToString("/")}".toUri())
                    is Boolean -> uris.add("content://${AUTHORITY}/boolean/$key/$value".toUri())
                }
            }
            d("RemotePreferences notifyChange $uris")
            mContentResolver!!.notifyChange(uris, null, ContentResolver.NOTIFY_UPDATE)
        }
    }

    inner class Observer(handler: Handler) : ContentObserver(handler) {

        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH).apply {
            addURI(AUTHORITY, "boolean/*/*", 1)
            addURI(AUTHORITY, "int/*/*", 2)
            addURI(AUTHORITY, "long/*/*", 3)
            addURI(AUTHORITY, "float/*/*", 4)
            addURI(AUTHORITY, "string/*", 5)
            addURI(AUTHORITY, "string_set/*", 6)
            addURI(AUTHORITY, "remove/*", 7)
            addURI(AUTHORITY, "clear", 8)
        }

        override fun onChange(selfChange: Boolean, uris: Collection<Uri?>, flags: Int) {
            d("RemotePreferences.Observer onChange: selfChange=$selfChange, uris=$uris, flags=$flags")
            val updateKeys = HashSet<String>()
            var keysCleared = false
            for (uri in uris) {
                if (uri == null) continue
                runCatching {
                    val segments = uri.pathSegments
                    when (uriMatcher.match(uri)) {
                        1 -> mMap[segments[1]] = segments[2].toBoolean().also { updateKeys.add(segments[1]) }
                        2 -> mMap[segments[1]] = segments[2].toInt().also { updateKeys.add(segments[1]) }
                        3 -> mMap[segments[1]] = segments[2].toLong().also { updateKeys.add(segments[1]) }
                        4 -> mMap[segments[1]] = segments[2].toFloat().also { updateKeys.add(segments[1]) }
                        5 -> mMap[segments[1]] = segments[2].also { updateKeys.add(segments[1]) }
                        6 -> mMap[segments[1]] = HashSet(segments.subList(2, segments.size)).also {
                            updateKeys.add(segments[1])
                        }

                        7 -> mMap.remove(segments[1]).also { updateKeys.add(segments[1]) }
                        8 -> mMap.clear().also { keysCleared = true }
                    }
                }.onFailure {
                    e("RemotePreferences.Observer onChange: $it", it)
                }
            }
            if (keysCleared) {
                for (listener in mListeners) {
                    listener.runCatching {
                        onSharedPreferenceChanged(this@RemotePreferences, null)
                    }.onFailure {
                        if (listener is XLogManager.LogScope) {
                            listener.e("RemotePreferences.Observer onChange: $it", it)
                        } else {
                            e("RemotePreferences.Observer onChange: $it", it)
                        }
                    }
                }
            }
            for (updateKey in updateKeys) {
                for (listener in mListeners) {
                    listener.runCatching {
                        onSharedPreferenceChanged(this@RemotePreferences, updateKey)
                    }.onFailure {
                        if (listener is XLogManager.LogScope) {
                            listener.e("RemotePreferences.Observer onChange: $it", it)
                        } else {
                            e("RemotePreferences.Observer onChange: $it", it)
                        }
                    }
                }
            }
        }
    }

    override fun edit(): SharedPreferences.Editor? {
        return Editor(mBase?.edit())
    }

    override fun registerOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        if (listener != null) mListeners.add(listener)
    }

    override fun unregisterOnSharedPreferenceChangeListener(listener: SharedPreferences.OnSharedPreferenceChangeListener?) {
        mListeners.remove(listener)
    }

    fun attachContentResolver(contentResolver: ContentResolver) {
        if (mContentResolver != null && mContentObserver != null) {
            mContentResolver!!.unregisterContentObserver(mContentObserver!!)
        }
        mContentResolver = contentResolver
        mContentObserver = Observer(Handler.getMain())
        contentResolver.registerContentObserver("content://$AUTHORITY".toUri(), true, mContentObserver!!)
    }

}

@SuppressLint("WorldReadableFiles")
fun SharedPreferences.isWorldReadable(): Boolean {
    return when (this) {
        is RemotePreferences -> this.base?.isWorldReadable() == true
        else -> javaClass.name == "android.app.SharedPreferencesImpl"
                && javaClass.getDeclaredField("mMode")
            .also { it.isAccessible = true }
            .get(this) as Int and Context.MODE_WORLD_READABLE == Context.MODE_WORLD_READABLE
    }
}