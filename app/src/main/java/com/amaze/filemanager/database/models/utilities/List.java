

package com.amaze.filemanager.database.models.utilities;

import com.amaze.filemanager.database.UtilitiesDatabase;

import androidx.room.Entity;

/**
 * {@link Entity} representation of <code>list</code> table in utilities.db.
 *
 * @see UtilitiesDatabase
 */
@Entity(tableName = UtilitiesDatabase.TABLE_LIST)
public class List extends OperationData {

  public List(String path) {
    super(path);
  }
}
