/**
 * *********************************************************
 * File : DatabaseAccessor.Java
 * Author: Breanna Rhodes
 * Class : COP 3003
 * Purpose : This class handles all of the database connections in the program.
 * **********************************************************
 */

package production;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseAccessor {

  /**
   * METHOD NAME: insertProduct. PURPOSE: The purpose of this method is to establish a connection to
   * the database, and insert the information entered by the user into the database. The method uses
   * a prepared statement, and parametrizes the variables so that information from another class can
   * be inserted into the database. This method is called in the controller class when the add
   * product button is pushed.
   */
  public static void insertProduct(Product W) {
    /**
     * The database driver is established using a string, and an h2 driver is entered into the
     * arugment. The url can be found in the database properties. It is copied and pasted into the
     * string argument for url.
     */
    final String jcbdDriver = "org.h2.Driver";
    final String dbUrl = "jdbc:h2:C:/Users/feesh/OneDrive/intelliJCOP/productionProject/res";
    // login credentials to get into the database.
    final String user = "";
    final String pass = "";
    // Initializing the connection and prepared statement for later use.
    Connection conn = null;
    Statement stmt = null;
    String sType = W.getType().toString();
    try {
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      // Parametrized variables being inserted into the database, that are called in the header.
      stmt = conn.createStatement();
      String sql = "INSERT INTO PRODUCT(NAME, TYPE, MANUFACTURER)" +
          "VALUES('"
          + W.getName()
          + "','"
          + sType
          + "','"
          + W.getManufacturer() + "');";

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
   * METHOD NAME: displayProducts.
   * Purpose: This method will connect to the database, and select
   * items inside the database and display them to the user inside of a table view. The method is
   * called in the controller class, when the button populate table is pushed.
   */
  public static void displayProducts() {
    // Because the method is static, the driver and URl need to be declared inside of the method.
    final String jcbdDriver = "org.h2.Driver";
    final String dbUrl = "jdbc:h2:C:/Users/feesh/OneDrive/intelliJCOP/productionProject/res";
    // login credentials to get into the database.
    final String user = "";
    final String pass = "";
    // Initializing the connection and prepared statement for later use.
    Connection conn = null;
    Statement stmt = null;
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
        String pName = rs.getString("NAME");
        String pManufacturer = rs.getString("MANUFACTURER");
        String pType = rs.getString("TYPE");
      }
      // Close the connection
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } // End of catch.
  } // End of displayProduct method.



  /*
   * METHOD NAME: removeProduct
   * PURPOSE: This method will remove a selected item from the database.
   */
  public static void removeProduct(String name) {
    // Because the method is static, the driver and URl need to be declared inside of the method.
    final String jcbdDriver = "org.h2.Driver";
    final String dbUrl = "jdbc:h2:C:/Users/feesh/OneDrive/intelliJCOP/productionProject/res";
    // login credentials to get into the database.
    final String user = "";
    final String pass = "";
    // Initializing the connection and prepared statement for later use.
    Connection conn = null;
    Statement stmt = null;
    try {
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      stmt = conn.createStatement();
      // Right now this method clears the entire database.
      //String sql = "DELETE FROM PRODUCT WHERE ID = '"+id+"'";
      String sql = "DELETE FROM PRODUCT WHERE NAME= '"+name+"'";
      stmt.executeUpdate(sql);
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }
    }

  /**
   * METHOD NAME: remove_productionLog
   * PURPOSE: This method clears the entire production log when the 'Remove Log' button is clicked
   * by the user.
   */
  public static void remove_productionLog(){
      // Because the method is static, the driver and URl need to be declared inside of the method.
      final String jcbdDriver = "org.h2.Driver";
      final String dbUrl = "jdbc:h2:C:/Users/feesh/OneDrive/intelliJCOP/productionProject/res";
      // login credentials to get into the database.
      final String user = "";
      final String pass = "";
      // Initializing the connection and prepared statement for later use.
      Connection conn = null;
      Statement stmt = null;

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
   * METHOD NAME: getProductList
   * PURPOSE:This method will return an array list that will be stored in the table view as an
   * observable list.
   * @return arrayListProducts
   */
  public static ArrayList<AudioPlayer> getProductList(){
    ArrayList<AudioPlayer> arrayListProducts = new ArrayList<AudioPlayer>();
    // Because the method is static, the driver and URl need to be declared inside of the method.
    final String jcbdDriver = "org.h2.Driver";
    final String dbUrl = "jdbc:h2:C:/Users/feesh/OneDrive/intelliJCOP/productionProject/res";
    // login credentials to get into the database.
    final String user = "";
    final String pass = "";
    // Initializing the connection and prepared statement for later use.
    Connection conn = null;
    Statement stmt = null;
    try{
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM PRODUCT";
      ResultSet rs = stmt.executeQuery(sql);
      while(rs.next()){
        arrayListProducts.add(new AudioPlayer(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)));
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
   * METHOD NAME: add_production_record
   * PURPOSE: This purpose of this method is to connect to the database, take in the
   * product as an argument and add it to the PRODUCTIONRECORD database.
   * @param pr
   */
    public static void add_production_record(ProductionRecord pr){
      final String jcbdDriver = "org.h2.Driver";
      final String dbUrl = "jdbc:h2:C:/Users/feesh/OneDrive/intelliJCOP/productionProject/res";
      // login credentials to get into the database.
      final String user = "";
      final String pass = "";
      // Initializing the connection and prepared statement for later use.
      Connection conn = null;
      Statement stmt = null;
      try {
        Class.forName(jcbdDriver);
        conn = DriverManager.getConnection(dbUrl, user, pass);
        // Parametrized variables being inserted into the database, that are called in the header.
        stmt = conn.createStatement();
        String sql = "INSERT INTO PRODUCTIONRECORD(PRODUCTION_NUM, PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED)" +
            "VALUES('"
            + pr.getProductionNum()
            + "','"
            + pr.getProductID()
            + "','"
            + pr.getSerialNum()
            + "','"
            + pr.getProdDate().getTime() + "');";

        // Executes the prepared statement.
        stmt.executeUpdate(sql);
        // The next two line closes the connection, as well as the statement.
        stmt.close();
        conn.close();
      } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
      }
    } // End of insertProduct method.


  }// End of DatabaseAccessor class.

