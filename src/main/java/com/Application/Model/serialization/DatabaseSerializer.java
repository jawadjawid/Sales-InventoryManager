package com.Application.Model.serialization;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.Application.Model.database.DatabaseDriverAndroid;
import com.Application.Model.database.helper.DatabaseDriverAndroidHelper;
import com.Application.Model.database.helper.DatabaseDriverHelper;
import com.Application.Model.exceptions.DatabaseInsertException;
import com.Application.Model.exceptions.DeserializationUnsuccessfulException;
import com.Application.Model.exceptions.NoLongerAdminException;
import com.Application.Model.store.SalesApplication;
import com.Application.Model.users.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

public class DatabaseSerializer {

  private static String deserializeDirectory;
  private static String serializeDirectory;

  public static DatabaseBackup deserialize(Context context) {
    try {

      FileInputStream fileIn = context.openFileInput("database_copy.ser");
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

  public static String serialize(DatabaseBackup databasebackup, Context context) {
    try {
      //   File root = new File(Environment.getDataDirectory(), serializeDirectory + "database_copy.ser");
      //
      FileOutputStream fileOut = context
          .openFileOutput(serializeDirectory + "database_copy.ser", Context.MODE_PRIVATE);
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

  public static void deserializeDatabase(Context context, User user, String directory)
      throws SQLException, DatabaseInsertException, NoLongerAdminException, DeserializationUnsuccessfulException {
    deserializeDirectory = directory;
    DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(context, "temp.db");

    try {
      DatabaseBackup backupversion = DatabaseSerializer.deserialize(context);
      DatabaseBackupSetDown.SetDownEverything(backupversion, mydb);

      context.deleteDatabase("inventorymgmt.db");
      DatabaseDriverAndroidHelper mydb2 = new DatabaseDriverAndroidHelper(context);
      DatabaseBackup backupversion2 = DatabaseSerializer.deserialize(context);
      DatabaseBackupSetDown.SetDownEverything(backupversion2, mydb2);
    } catch (Exception e) {
      throw new DeserializationUnsuccessfulException();
    }
  }


  public static void serializeDatabase(Context context, String directory)
      throws SQLException, DatabaseInsertException {
    serializeDirectory = directory;
    DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(context);
    DatabaseBackup databaseBackup = new DatabaseBackup();
    DatabaseBackupSetUp.SetUpEverything(databaseBackup, mydb);
    DatabaseSerializer.serialize(databaseBackup, context);

  }
}

