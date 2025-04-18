package name.liuyi.mifreeformenhance.provider

import android.annotation.SuppressLint
import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.SharedPreferences
import android.content.UriMatcher
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import name.liuyi.mifreeformenhance.BuildConfig
import name.liuyi.mifreeformenhance.common.XLogManager

const val AUTHORITY = "${BuildConfig.APPLICATION_ID}.provider.sharedprefs"

private val Matcher by lazy {
    UriMatcher(UriMatcher.NO_MATCH).apply {
        addURI(AUTHORITY, "boolean/*/*", 1)
        addURI(AUTHORITY, "int/*/*", 2)
        addURI(AUTHORITY, "long/*/*", 3)
        addURI(AUTHORITY, "float/*/*", 4)
        addURI(AUTHORITY, "string/*", 5)
        addURI(AUTHORITY, "string_set/*", 6)
    }
}

class SharedPrefsProvider : ContentProvider(), XLogManager.LogScope {

    private lateinit var prefs: SharedPreferences

    @SuppressLint("WorldReadableFiles")
    override fun onCreate(): Boolean {
        d("onCreate")
        return runCatching {
            prefs = context!!.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_WORLD_READABLE)
        }.isSuccess
    }

    override fun query(
        uri: Uri,
        projection: Array<out String?>?,
        selection: String?,
        selectionArgs: Array<out String?>?,
        sortOrder: String?
    ): Cursor? {
        val segments = uri.pathSegments
        val cursor = MatrixCursor(arrayOf("data"))
        return when (Matcher.match(uri)) {
            1 -> cursor.apply { newRow().add(prefs.getBoolean(segments[1], segments[2].toBoolean())) }
            2 -> cursor.apply { newRow().add(prefs.getInt(segments[1], segments[2].toInt())) }
            3 -> cursor.apply { newRow().add(prefs.getLong(segments[1], segments[2].toLong())) }
            4 -> cursor.apply { newRow().add(prefs.getFloat(segments[1], segments[2].toFloat())) }
            5 -> cursor.apply { newRow().add(prefs.getString(segments[1], segments.getOrNull(2))) }
            6 -> cursor.apply { prefs.getStringSet(segments[1], null)?.forEach { newRow().add(it) } }
            else -> null
        }
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(
        uri: Uri,
        selection: String?,
        selectionArgs: Array<out String?>?
    ): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String?>?
    ): Int {
        return 0
    }
}