package production;

/*
 * *********************************************************
 * File : DatabaseAccessor.Java Author:
 * Breanna Rhodes
 * Class : COP 3003
 * Purpose : This class handles all of the database connections in
 * the program.
 * **********************************************************
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

public class DatabaseAccessor {
  static final String jcbdDriver = "org.h2.Driver";
  static final String dbUrl = "jdbc:h2:./res";

  // login credentials to get into the database.
  static final String user = "";
  static String pass = "";
  // Initializing the connection and prepared statement for later use.
  static Connection conn = null;
  static Statement stmt = null;
  static Widget wid;

  static int productID;
  static ProductionRecord proRec;
  static String productName;
  static String productManu;
  static String productType;

  /**
   * METHOD NAME: insertProduct. PURPOSE: The purpose of this method is to establish a connection to
   * the database, and insert the information entered by the user into the database. The method uses
   * a prepared statement, and parametrizes the variables so that information from another class can
   * be inserted into the database. This method is called in the controller class when the add
   * product button is pushed.
   */
  public static void insertProduct(Product widgetProduct) {
    /*
     * The database driver is established using a string, and an h2 driver is entered into the
     * arugment. The url can be found in the database properties. It is copied and pasted into the
     * string argument for url.
     */
    String stringType = widgetProduct.getType().toString();
    try {
      // Properties prop = new Properties();
      // prop.load(new FileInputStream("res/properties"));
      // pass = prop.getProperty("password");

      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      // Parametrized variables being inserted into the database, that are called in the header.
      stmt = conn.createStatement();
      String sql =
          "INSERT INTO PRODUCT(NAME, TYPE, MANUFACTURER)"
              + "VALUES('"
              + widgetProduct.getName()
              + "','"
              + stringType
              + "','"
              + widgetProduct.getManufacturer()
              + "');";

      // Executes the prepared statement.
      stmt.executeUpdate(sql);
      // The next two line closes the connection, as well as the statement.
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } // End of catch.
  } // End of insertProduct method.

  /**
   * METHOD NAME: displayProducts. Purpose: This method will connect to the database, and select
   * items inside the database and display them to the user inside of a table view. The method is
   * called in the controller class, when the button populate table is pushed.
   */
  public static void displayProducts() {
    try {
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM PRODUCT";
      ResultSet rs = stmt.executeQuery(sql);
      /*
       A while loop is made to display the information line by line in an organized manner. After
       the data is displayed, the query is closed using stmt.close(). The connection is also
       closed using a similar function, conn.close().
      */
      while (rs.next()) {
        String productType = rs.getString("TYPE");
      }
      // Close the connection
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } // End of catch.
  } // End of displayProduct method.

  public static Widget getProduct(int productID) {

    try {
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM PRODUCT WHERE ID = " + productID + ";";
      ResultSet rs = stmt.executeQuery(sql);
      /*
       A while loop is made to display the information line by line in an organized manner. After
       the data is displayed, the query is closed using stmt.close(). The connection is also
       closed using a similar function, conn.close().
      */
      while (rs.next()) {
        String productName = rs.getString("NAME");
        String productManu = rs.getString("MANUFACTURER");
        String productType = rs.getString("TYPE");

        ItemType type;
        switch (productType) {
          case "AUDIO":
            type = ItemType.AUDIO;
            break;
          case "VISUAL":
            type = ItemType.VISUAL;
            break;
          case "AUDIO_MOBILE":
            type = ItemType.AUDIO_MOBILE;
            break;
          default:
            type = ItemType.VISUAL_MOBILE;
        }
        Widget wid = new Widget(productName, productManu, type);
      }

      // Close the connection
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } // End of catch.
    return wid;
  } // End of displayProduct method.

  public static ProductionRecord getProductionRecord(int ID) {

    try {
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM PRODUCTIONRECORD";
      ResultSet rs = stmt.executeQuery(sql);
      /*
       A while loop is made to display the information line by line in an organized manner. After
       the data is displayed, the query is closed using stmt.close(). The connection is also
       closed using a similar function, conn.close().
      */
      while (rs.next()) {
        int productNum = rs.getInt("PRODUCTION_NUM");
        String serialNum = rs.getString("SERIAL_NUM");
        String dateProduced = rs.getString("DATE_PRODUCED");
        ProductionRecord proRec =
            new ProductionRecord(productNum, ID, serialNum, new Date(dateProduced));
      }
      // Close the connection
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } // End of catch.
    return proRec;
  }
  // End of displayProduct method.

  /**
   * METHOD NAME: removeProduct PURPOSE: This method will remove a selected item from the database.
   */
  public static void removeProduct(String name) {
    try {
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      stmt = conn.createStatement();
      // Right now this method clears the entire database.
      // String sql = "DELETE FROM PRODUCT WHERE ID = '"+id+"'";
      String sql = "DELETE FROM PRODUCT WHERE NAME= '" + name + "'";
      stmt.executeUpdate(sql);
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * METHOD NAME: remove_productionLog PURPOSE: This method clears the entire production log when
   * the 'Remove Log' button is clicked by the user.
   */
  public static void remove_productionLog() {
    try {
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      stmt = conn.createStatement();
      String sql = "DELETE FROM PRODUCTIONRECORD";
      stmt.executeUpdate(sql);
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * METHOD NAME: getProductList PURPOSE:This method will return an array list that will be stored
   * in the table view as an observable list.
   *
   * @return arrayListProducts
   */
  public static ArrayList<AudioPlayer> getProductList() {
    ArrayList<AudioPlayer> arrayListProducts = new ArrayList<AudioPlayer>();
    try {
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM PRODUCT";
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        arrayListProducts.add(
            new AudioPlayer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
      }
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return arrayListProducts;
  }

  /**
   * METHOD NAME: add_production_record PURPOSE: This purpose of this method is to connect to the
   * database, take in the product as an argument and add it to the PRODUCTIONRECORD database.
   */
  public static void add_production_record(ProductionRecord pr) {
    try {
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      // Parametrized variables being inserted into the database, that are called in the header.
      stmt = conn.createStatement();
      String sql =
          "INSERT INTO PRODUCTIONRECORD(PRODUCTION_NUM, PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED)"
              + "VALUES('"
              + pr.getProductionNum()
              + "','"
              + pr.getProductID()
              + "','"
              + pr.getSerialNum()
              + "','"
              + pr.getProdDate().toString()
              + "');";

      // Executes the prepared statement.
      stmt.executeUpdate(sql);
      // The next two line closes the connection, as well as the statement.
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
  } // End of insertProduct method.
} // End of DatabaseAccessor class.
