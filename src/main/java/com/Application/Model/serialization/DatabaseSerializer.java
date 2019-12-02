package com.Application.Model.serialization;

import android.content.Context;

import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.database.helper.DatabaseDriverHelper;
import com.Application.Model.exceptions.DatabaseInsertException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;

public class DatabaseSerializer {

  private static String deserializeDirectory;
  private static String serializeDirectory;

  public static DatabaseBackup deserialize() {
    try {
      FileInputStream fileIn = new FileInputStream(deserializeDirectory + "database_copy.ser");
      ObjectInputStream in = new ObjectInputStream(fileIn);
      DatabaseBackup databasebackup = (DatabaseBackup) in.readObject();
      in.close();
      fileIn.close();
      return databasebackup;
    } catch (IOException i) {
      i.printStackTrace();
      return null;
    } catch (ClassNotFoundException c) {
      System.out.println("Class not found");
      c.printStackTrace();
      return null;
    }
  }

  public static String serialize(DatabaseBackup databasebackup) {
    try {
      FileOutputStream fileOut = new FileOutputStream(serializeDirectory + "database_copy.ser");
      ObjectOutputStream out = new ObjectOutputStream(fileOut);
      out.writeObject(databasebackup);
      out.close();
      fileOut.close();
      System.out.println("Serialized data is saved as database_copy.ser");
    } catch (IOException i) {
      i.printStackTrace();
    }
    return databasebackup.toString();
  }

  public static void deserializeDatabase(Context context, String directory) throws SQLException, DatabaseInsertException {
    deserializeDirectory = directory;
    DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(context);
    context.deleteDatabase("inventorymgmt.db");
    DatabaseBackup backupversion = DatabaseSerializer.deserialize();
    DatabaseBackupSetDown.SetDownEverything(backupversion, mydb);
  }

  public static void serializeDatabase(Context context, String directory) throws SQLException, DatabaseInsertException {
    serializeDirectory = directory;
    DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(context);
    DatabaseBackup databaseBackup = new DatabaseBackup();
    DatabaseBackupSetUp.SetUpEverything(databaseBackup,mydb);
    DatabaseSerializer.serialize(databaseBackup);

  }
}

