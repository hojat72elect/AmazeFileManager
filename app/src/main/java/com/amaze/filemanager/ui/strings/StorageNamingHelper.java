

package com.amaze.filemanager.ui.strings;

import java.io.File;

import com.amaze.filemanager.R;
import com.amaze.filemanager.fileoperations.filesystem.StorageNaming;

import android.content.Context;

import androidx.annotation.NonNull;

public final class StorageNamingHelper {
  private StorageNamingHelper() {}

  @NonNull
  public static String getNameForDeviceDescription(
      @NonNull Context context,
      @NonNull File file,
      @StorageNaming.DeviceDescription int deviceDescription) {
    switch (deviceDescription) {
      case StorageNaming.STORAGE_INTERNAL:
        return context.getString(R.string.storage_internal);
      case StorageNaming.STORAGE_SD_CARD:
        return context.getString(R.string.storage_sd_card);
      case StorageNaming.ROOT:
        return context.getString(R.string.root_directory);
      case StorageNaming.NOT_KNOWN:
      default:
        return file.getName();
    }
  }
}
