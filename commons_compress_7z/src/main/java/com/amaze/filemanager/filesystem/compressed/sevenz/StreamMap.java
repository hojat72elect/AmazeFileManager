

package com.amaze.filemanager.filesystem.compressed.sevenz;

/// Map between folders, files and streams.
class StreamMap {
  /// The first Archive.packStream index of each folder.
  int[] folderFirstPackStreamIndex;
  /// Offset to beginning of this pack stream's data, relative to the beginning of the first pack
  // stream.
  long[] packStreamOffsets;
  /// Index of first file for each folder.
  int[] folderFirstFileIndex;
  /// Index of folder for each file.
  int[] fileFolderIndex;

  @Override
  public String toString() {
    return "StreamMap with indices of "
        + folderFirstPackStreamIndex.length
        + " folders, offsets of "
        + packStreamOffsets.length
        + " packed streams,"
        + " first files of "
        + folderFirstFileIndex.length
        + " folders and"
        + " folder indices for "
        + fileFolderIndex.length
        + " files";
  }
}
