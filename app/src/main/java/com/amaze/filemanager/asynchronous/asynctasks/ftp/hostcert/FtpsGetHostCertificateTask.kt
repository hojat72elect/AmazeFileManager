package com.amaze.filemanager.asynchronous.asynctasks.ftp.hostcert

import android.content.Context
import com.amaze.filemanager.asynchronous.asynctasks.ftp.AbstractGetHostInfoTask
import java.lang.ref.WeakReference
import org.json.JSONObject

class FtpsGetHostCertificateTask(
    private val host: String,
    private val port: Int,
    private val explicitTls: Boolean = false,
    context: Context,
    callback: (JSONObject) -> Unit,
) : AbstractGetHostInfoTask<JSONObject, FtpsGetHostCertificateTaskCallable>(host, port, callback) {
    val ctx: WeakReference<Context> = WeakReference(context)

    override fun getTask(): FtpsGetHostCertificateTaskCallable =
        FtpsGetHostCertificateTaskCallable(host, port, explicitTls)
}
