package com.amaze.filemanager.utils

/** @author Emmanuel on 14/9/2017, at 21:00.
 */
interface OnAsyncTaskFinished<T> {
    @Suppress("UndocumentedPublicFunction")
    fun onAsyncTaskFinished(data: T)
}
