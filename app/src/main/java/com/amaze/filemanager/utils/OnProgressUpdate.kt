package com.amaze.filemanager.utils

/**
 * General inteface for updating data before it's finished loading
 *
 * @author Emmanuel on 13/5/2017, at 22:45.
 */
interface OnProgressUpdate<T> {
    @Suppress("UndocumentedPublicFunction")
    fun onUpdate(data: T)
}
