

package com.amaze.filemanager.database.models.explorer;

import com.amaze.filemanager.database.ExplorerDatabase;
import com.amaze.filemanager.database.models.StringWrapper;
import com.amaze.filemanager.database.typeconverters.EncryptedStringTypeConverter;
import com.amaze.filemanager.database.typeconverters.OpenModeTypeConverter;
import com.amaze.filemanager.fileoperations.filesystem.OpenMode;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

/** Created by vishal on 18/4/17. */
@Entity(tableName = ExplorerDatabase.TABLE_CLOUD_PERSIST)
public class CloudEntry {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = ExplorerDatabase.COLUMN_CLOUD_ID)
  private int _id;

  @ColumnInfo(name = ExplorerDatabase.COLUMN_CLOUD_SERVICE)
  @TypeConverters(OpenModeTypeConverter.class)
  private OpenMode serviceType;

  @ColumnInfo(name = ExplorerDatabase.COLUMN_CLOUD_PERSIST)
  @TypeConverters(EncryptedStringTypeConverter.class)
  private StringWrapper persistData;

  public CloudEntry() {}

  public CloudEntry(OpenMode serviceType, String persistData) {
    this.serviceType = serviceType;
    this.persistData = new StringWrapper(persistData);
  }

  public void setId(int _id) {
    this._id = _id;
  }

  public int getId() {
    return this._id;
  }

  public void setPersistData(StringWrapper persistData) {
    this.persistData = persistData;
  }

  public StringWrapper getPersistData() {
    return this.persistData;
  }

  /** Set the service type Support values from {@link OpenMode} */
  public void setServiceType(OpenMode openMode) {
    this.serviceType = openMode;
  }

  /** Returns ordinal value of service from {@link OpenMode} */
  public OpenMode getServiceType() {
    return this.serviceType;
  }
}
