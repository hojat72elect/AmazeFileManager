package com.amaze.filemanager.filesystem.compressed.sevenz

data class StartHeader(
    @JvmField
    var nextHeaderOffset: Long = 0L,
    @JvmField
    var nextHeaderSize: Long = 0L,
    @JvmField
    var nextHeaderCrc: Long = 0L,
)