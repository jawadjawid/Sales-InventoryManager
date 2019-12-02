package com.Application.Model.serialization;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import com.b07.database.helper.DatabaseDriverHelper;
import com.b07.exceptions.DatabaseInsertException;

public class DatabaseSerializer {
  public static DatabaseBackup deserialize() {
    try {
      FileInputStream fileIn = new FileInputStream("database_copy.ser");
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
      FileOutputStream fileOut = new FileOutputStream("database_copy.ser");
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

  public static void deserializeDatabase() throws SQLException, DatabaseInsertException {
    DatabaseDriverHelper.initializeDatabase();
    DatabaseBackup backeupversion = DatabaseSerializer.deserialize();
    DatabaseBackupSetDown.SetDownEverything(backeupversion);
  }

  public static void serializeDatabase(Context context) throws SQLException, DatabaseInsertException {
    DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper();
    DatabaseBackup databasebackup = new DatabaseBackup();
    DatabaseBackupSetUp.SetUpEverything(databasebackup);
    DatabaseSerializer.serialize(databasebackup);
    DatabaseDriverHelper.clearDatabase();
  }
}

