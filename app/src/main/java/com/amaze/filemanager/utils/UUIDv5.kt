package com.amaze.filemanager.utils

import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.UUID

/**
 * UUIDv5 implementation, referenced from
 * https://gist.github.com/icedraco/00118b4d3c91d96d8c58e837a448f1b8
 */
object UUIDv5 {
    // Constants defined in RFC4122 https://www.ietf.org/rfc/rfc4122.txt
    @JvmStatic
    val DNS: UUID = UUID.fromString("6ba7b810-9dad-11d1-80b4-00c04fd430c8")

    @JvmStatic
    val URL: UUID = UUID.fromString("6ba7b811-9dad-11d1-80b4-00c04fd430c8")

    @JvmStatic
    val OID: UUID = UUID.fromString("6ba7b812-9dad-11d1-80b4-00c04fd430c8")

    @JvmStatic
    val X500: UUID = UUID.fromString("6ba7b814-9dad-11d1-80b4-00c04fd430c8")

    /**
     * Generate an UUIDv5 UUID from given namespace UUID and name.
     *
     * [namespaceUUID] must be one of [DNS], [URL], [OID], [X500].
     */
    @JvmStatic
    @Suppress("TooGenericExceptionThrown")
    fun fromString(
        namespaceUUID: UUID,
        name: String,
    ): UUID {
        val md: MessageDigest
        try {
            md = MessageDigest.getInstance("SHA-1")
        } catch (ex: NoSuchAlgorithmException) {
            throw Exception("SHA-1 not supported", ex)
        }

        md.update(toBytes(namespaceUUID))
        md.update(name.toByteArray())
        val bytes = md.digest()
        // clear version; set to version 5
        bytes[6] = ((bytes[6].toInt() and 0x0F) or 0x50).toByte()
        // clear variant; set to IETF variant
        bytes[8] = ((bytes[8].toInt() and 0x3F) or 0x80).toByte()
        return fromBytes(bytes)
    }

    private fun fromBytes(data: ByteArray): UUID {
        // Based on the private UUID(bytes[]) constructor
        assert(data.size >= 16)
        var msb = 0L
        var lsb = 0L
        for (i in 0..7)
            msb = msb shl 8 or (data[i].toLong() and 0xff)
        for (i in 8..15)
            lsb = lsb shl 8 or (data[i].toLong() and 0xff)
        return UUID(msb, lsb)
    }

    private fun toBytes(uuid: UUID): ByteArray {
        // inverted logic of fromBytes()
        val out = ByteArray(16)
        val msb = uuid.mostSignificantBits
        val lsb = uuid.leastSignificantBits
        for (i in 0..7)
            out[i] = (msb shr (7 - i) * 8 and 0xff).toByte()
        for (i in 8..15)
            out[i] = (lsb shr (15 - i) * 8 and 0xff).toByte()
        return out
    }
}
