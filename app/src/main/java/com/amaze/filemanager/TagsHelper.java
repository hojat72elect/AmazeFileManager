

package com.amaze.filemanager;

public final class TagsHelper {
  private TagsHelper() {}

  public static String getTag(Class<?> clazz) {
    String className = clazz.getSimpleName();
    return className.substring(0, Math.min(23, className.length()));
  }
}
