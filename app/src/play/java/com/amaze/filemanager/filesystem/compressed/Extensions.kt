

package com.amaze.filemanager.filesystem.compressed

import android.os.Build.VERSION.SDK_INT
import android.os.Build.VERSION_CODES.M
import com.github.junrar.Archive

/**
 * Extension function to patch [Archive.isPasswordProtected] which uses API that is not available
 * for Android 6.0 or lower.
 *
 * @see [Archive.isPasswordProtected]
 * @see [java.util.stream.Stream]
 * @return true if archive is password protected
 */
fun Archive.isPasswordProtectedCompat(): Boolean {
    return if (SDK_INT > M) {
        this.isPasswordProtected
    } else {
        fileHeaders.any { obj ->
            obj.isEncrypted
        }
    }
}
