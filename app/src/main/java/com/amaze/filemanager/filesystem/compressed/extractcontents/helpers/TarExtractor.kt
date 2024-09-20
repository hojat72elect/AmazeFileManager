package com.amaze.filemanager.filesystem.compressed.extractcontents.helpers

import android.content.Context
import com.amaze.filemanager.fileoperations.utils.UpdatePosition
import java.io.InputStream
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream

class TarExtractor(
    context: Context,
    filePath: String,
    outputPath: String,
    listener: OnUpdate,
    updatePosition: UpdatePosition,
) : AbstractCommonsArchiveExtractor(
    context,
    filePath,
    outputPath,
    listener,
    updatePosition,
) {
    override fun createFrom(inputStream: InputStream): TarArchiveInputStream =
        runCatching {
            TarArchiveInputStream(inputStream)
        }.getOrElse {
            throw BadArchiveNotice(it)
        }
}
