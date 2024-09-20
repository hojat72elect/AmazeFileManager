package com.amaze.filemanager.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ComputerParcelable(val addr: String, val name: String) : Parcelable {
    override fun toString(): String = "$name [$addr]"

    override fun hashCode(): Int = addr.hashCode()

    override fun equals(other: Any?): Boolean =
        other is ComputerParcelable && other.addr == this.addr
}
