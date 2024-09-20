package com.amaze.filemanager.filesystem.compressed.sevenz;

import java.util.BitSet;

/// Properties for non-empty files.
class SubStreamsInfo {
    /// Unpacked size of each unpacked stream.
    long[] unpackSizes;
    /// Whether CRC is present for each unpacked stream.
    BitSet hasCrc;
    /// CRCs of unpacked streams, if present.
    long[] crcs;
}
