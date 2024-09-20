

package com.amaze.filemanager.database;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amaze.filemanager.database.models.explorer.CloudEntry;
import com.amaze.filemanager.fileoperations.exceptions.CloudPluginException;
import com.amaze.filemanager.fileoperations.filesystem.OpenMode;
import com.amaze.filemanager.ui.fragments.CloudSheetFragment;

import android.content.Context;

import androidx.annotation.NonNull;

import io.reactivex.schedulers.Schedulers;

/** Created by vishal on 18/4/17. */
public class CloudHandler {

  public static final String CLOUD_PREFIX_BOX = "box:/";
  public static final String CLOUD_PREFIX_DROPBOX = "dropbox:/";
  public static final String CLOUD_PREFIX_GOOGLE_DRIVE = "gdrive:/";
  public static final String CLOUD_PREFIX_ONE_DRIVE = "onedrive:/";

  public static final String CLOUD_NAME_GOOGLE_DRIVE = "Google Drive™";
  public static final String CLOUD_NAME_DROPBOX = "Dropbox";
  public static final String CLOUD_NAME_ONE_DRIVE = "One Drive";
  public static final String CLOUD_NAME_BOX = "Box";
  private final Logger LOG = LoggerFactory.getLogger(CloudHandler.class);

  private final ExplorerDatabase database;
  private final Context context;

  public CloudHandler(@NonNull Context context, @NonNull ExplorerDatabase explorerDatabase) {
    this.context = context;
    this.database = explorerDatabase;
  }

  public void addEntry(CloudEntry cloudEntry) throws CloudPluginException {

    if (!CloudSheetFragment.isCloudProviderAvailable(context)) throw new CloudPluginException();

    database.cloudEntryDao().insert(cloudEntry).subscribeOn(Schedulers.io()).subscribe();
  }

  public void clear(OpenMode serviceType) {
    database
        .cloudEntryDao()
        .findByServiceType(serviceType.ordinal())
        .subscribeOn(Schedulers.io())
        .subscribe(
            cloudEntry ->
                database
                    .cloudEntryDao()
                    .delete(cloudEntry)
                    .subscribeOn(Schedulers.io())
                    .subscribe(),
            throwable -> LOG.warn("failed to delete cloud connection", throwable));
  }

  public void clearAllCloudConnections() {
    database.cloudEntryDao().clear().subscribeOn(Schedulers.io()).blockingGet();
  }

  public void updateEntry(OpenMode serviceType, CloudEntry newCloudEntry)
      throws CloudPluginException {

    if (!CloudSheetFragment.isCloudProviderAvailable(context)) throw new CloudPluginException();

    database.cloudEntryDao().update(newCloudEntry).subscribeOn(Schedulers.io()).subscribe();
  }

  public CloudEntry findEntry(OpenMode serviceType) throws CloudPluginException {

    if (!CloudSheetFragment.isCloudProviderAvailable(context)) throw new CloudPluginException();

    try {
      return database
          .cloudEntryDao()
          .findByServiceType(serviceType.ordinal())
          .subscribeOn(Schedulers.io())
          .blockingGet();
    } catch (Exception e) {
      // catch error to handle Single#onError for blockingGet
      LOG.error(getClass().getSimpleName(), e);
      return null;
    }
  }

  public List<CloudEntry> getAllEntries() throws CloudPluginException {

    if (!CloudSheetFragment.isCloudProviderAvailable(context)) throw new CloudPluginException();
    return database.cloudEntryDao().list().subscribeOn(Schedulers.io()).blockingGet();
  }
}
