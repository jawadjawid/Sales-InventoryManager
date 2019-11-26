package com.Application.Model.database.helper;

import com.Application.Model.database.DatabaseDriver;
import java.sql.Connection;


public class DatabaseDriverHelper extends DatabaseDriver {

  protected static Connection connectOrCreateDataBase() {
    return DatabaseDriver.connectOrCreateDataBase();
  }

}
