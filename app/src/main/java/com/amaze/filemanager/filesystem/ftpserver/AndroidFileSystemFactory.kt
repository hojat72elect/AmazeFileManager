

package com.amaze.filemanager.filesystem.ftpserver

import android.content.Context
import android.os.Build.VERSION_CODES.KITKAT
import androidx.annotation.RequiresApi
import com.amaze.filemanager.asynchronous.services.ftp.FtpService
import org.apache.ftpserver.ftplet.FileSystemFactory
import org.apache.ftpserver.ftplet.FileSystemView
import org.apache.ftpserver.ftplet.User

@RequiresApi(KITKAT)
class AndroidFileSystemFactory(private val context: Context) : FileSystemFactory {
    override fun createFileSystemView(user: User?): FileSystemView =
        AndroidFtpFileSystemView(context, user?.homeDirectory ?: FtpService.defaultPath(context))
}
