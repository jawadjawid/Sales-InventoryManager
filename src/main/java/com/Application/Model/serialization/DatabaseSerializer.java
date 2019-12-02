package com.Application.Model.serialization;

import android.content.Context;
import android.os.Environment;

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
    try{
      File root = new File(Environment.getDataDirectory(), serializeDirectory + "database_copy.ser");
      if (!root.exists()) {
        root.mkdirs();
      }
      FileOutputStream fileOut = new FileOutputStream(root);
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

  public static void deserializeDatabase(Context context, User user, String directory) throws SQLException, DatabaseInsertException, NoLongerAdminException , DeserializationUnsuccessfulException{
    deserializeDirectory = directory;
    DatabaseDriverAndroidHelper mydbBackup = new DatabaseDriverAndroidHelper(context,"backup");
    DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(context);

    try {
      DatabaseBackup backupversion = DatabaseSerializer.deserialize();
      DatabaseBackupSetDown.SetDownEverything(backupversion, mydbBackup);
      context.deleteDatabase("inventorymgmt.db");
      renameDbFile();
    }catch (Exception e){
        deleteDbFile();
        throw new DeserializationUnsuccessfulException();
    }

    if(mydbBackup.getUserRoleIdH(user.getId()) != SalesApplication.ADMIN_ID){
      throw new NoLongerAdminException();
    }

    DatabaseBackup backupversion1 = DatabaseSerializer.deserialize();
    DatabaseBackupSetDown.SetDownEverything(backupversion1, mydb);

  }

  private static void deleteDbFile(){
    String fileName =  "//data//"+"com.example.salesapp"+"//databases//"+"backup.db";

    File myFile = new File(fileName);
    if(myFile.exists())
      myFile.delete();
  }

  private static void renameDbFile(){
    try {
      File backup = Environment.getDataDirectory();
      File data = Environment.getDataDirectory();

      if (backup.canWrite()) {
        String currentDBPath = "//data//"+"com.example.salesapp"+"//databases//"+"backup.db";
        String backupDBPath = "//data//"+"com.example.salesapp"+"//databases//"+"inventorymgmt.db";
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(backup, backupDBPath);

        if (currentDB.exists()) {
          FileChannel src = new FileInputStream(currentDB).getChannel();
          FileChannel dst = new FileOutputStream(backupDB).getChannel();
          dst.transferFrom(src, 0, src.size());
          src.close();
          dst.close();
        }
      }
    } catch (Exception e) {

    }
  }

  public static void serializeDatabase(Context context, String directory) throws SQLException, DatabaseInsertException {
    serializeDirectory = directory;
    DatabaseDriverAndroidHelper mydb = new DatabaseDriverAndroidHelper(context);
    DatabaseBackup databaseBackup = new DatabaseBackup();
    DatabaseBackupSetUp.SetUpEverything(databaseBackup,mydb);
    DatabaseSerializer.serialize(databaseBackup);

  }
}

