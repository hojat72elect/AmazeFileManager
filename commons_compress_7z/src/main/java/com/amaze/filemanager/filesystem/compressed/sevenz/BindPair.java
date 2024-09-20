

package com.amaze.filemanager.filesystem.compressed.sevenz;

class BindPair {
  long inIndex;
  long outIndex;

  @Override
  public String toString() {
    return "BindPair binding input " + inIndex + " to output " + outIndex;
  }
}
