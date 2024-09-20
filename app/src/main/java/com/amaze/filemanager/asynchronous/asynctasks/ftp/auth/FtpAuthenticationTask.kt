package com.amaze.filemanager.asynchronous.asynctasks.ftp.auth

import androidx.annotation.MainThread
import com.amaze.filemanager.R
import com.amaze.filemanager.application.AppConfig
import com.amaze.filemanager.asynchronous.asynctasks.Task
import com.amaze.filemanager.filesystem.ftp.NetCopyClientConnectionPool.FTP_URI_PREFIX
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import org.apache.commons.net.ftp.FTPClient
import org.json.JSONObject

class FtpAuthenticationTask(
    private val protocol: String,
    private val host: String,
    private val port: Int,
    private val certInfo: JSONObject?,
    private val username: String,
    private val password: String?,
    private val explicitTls: Boolean = false,
) : Task<FTPClient, FtpAuthenticationTaskCallable> {
    override fun getTask(): FtpAuthenticationTaskCallable {
        return if (protocol == FTP_URI_PREFIX) {
            FtpAuthenticationTaskCallable(
                host,
                port,
                username,
                password ?: "",
            )
        } else {
            FtpsAuthenticationTaskCallable(
                host,
                port,
                certInfo!!,
                username,
                password ?: "",
                explicitTls,
            )
        }
    }

    @MainThread
    override fun onError(error: Throwable) {
        if (error is SocketException || error is SocketTimeoutException || error is ConnectException
        ) {
            AppConfig.toast(
                AppConfig.getInstance(),
                AppConfig.getInstance()
                    .resources
                    .getString(
                        R.string.ssh_connect_failed,
                        host,
                        port,
                        error.localizedMessage ?: error.message,
                    ),
            )
        }
    }

    @MainThread
    override fun onFinish(value: FTPClient) {
        android.util.Log.d("TEST", value.toString())
    }
}
