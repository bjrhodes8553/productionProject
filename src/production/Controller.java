package production;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;

/**
 * Controller class controlls all aspects of the application. It is used to give function to each
 * element of the application, such as buttons.
 */
@SuppressWarnings("ALL")
public class Controller {

  /**
   * The database driver is established using a string, and an h2 driver is entered into the
   * arugment. The url can be found in the database properties. It is copied and pasted into the
   * string argument for url.
   */
  private static final String jcbdDriver = "org.h2.Driver";

  private static final String dbUrl =
      "jdbc:h2:C:/Users/feesh/OneDrive/intelliJCOP/productionProject/res";
  /**
   * The database credentials for logging into the database. This increases the security of the
   * database. Username and password can be entered into database properties.
   */
  private static final String user = "username";

  private static final String pass = "password";
  /**
   * Declaring the object Connection, imports all of the fields and methods used to establish a
   * conneciton. Declaring the object Statement will allow queries to be passed to the database, so
   * that it can be accessed.
   */
  private Connection conn = null;

  private Statement stmt = null;
  /**
   * These are the components of the scenebuilder application. Each component that is going to be
   * used as a function in the application is declared in the controller. After the component is
   * declared, the function will follow
   */
  @FXML private TextField productNameTxtBox;

  @FXML private TextField maunfacturerTxtBox;
  @FXML private ChoiceBox<?> typeChoicebox;
  @FXML private TableColumn<?, ?> existingProducts;
  @FXML private Button addProductBtn;
  @FXML private Button recordProductionBtn;

  /**
   * Main method of the controller. not completely necessary, but it is being used to establish that
   * a connection has been made.
   */
  public static void main(String[] args) {
    System.out.println("Database Connected");
  } // End of main.

  /**
   * This is going to display the products currently in the database to a table in the application.
   * When the cells are prompted to be editted, they will be filled with the data in the production
   * database.
   */
  @FXML
  void display_products(CellEditEvent<?, ?> event) {
    /*
     Try statement is made to call the driver, and to connect to the database. Using the
     connection object, conn, and statement object stmt, a query is made. The query that is being
     made is accessing the database to obtain the information inside of PRODUCT table. The query
     is made by declaring a string, sql, and inputing the command inside of it. This is also known
     as a prepared statement. ResultSet will send the statement and execute the query.
    */
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
        System.out.println(rs.getString(1));
      } // End of while.
      stmt.close();
      conn.close();
      /*
       Catch is used to catch an exception so the application is not terminated. The
       ClassNotFoundExeception or SQLException are both considered, and if caught, will display
       their stack trace.
      */
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } // End of catch.
  } // End of display_products.

  /**
   * This is the function that will add products to the database. 10 products have already been
   * entered using this function. The button labeled "add product" adds a product to the database.
   * When the button is clicked by the mouse, the information is entered.
   */
  @FXML
  void add_product(MouseEvent event) {
    /*
     Similar to the try statement before, the driver is called, and a connection is established.
     Instead of accessing the database to display the information inside,
     this statement sends a query to add information
     The statement sends a query to the database with information to input into the database.
     The statment execution is updated, and the database then contains the inserted code.
     The statement is then closed, as well as the connection.
    */
    try {
      Class.forName(jcbdDriver);

      conn = DriverManager.getConnection(dbUrl, user, pass);

      stmt = conn.createStatement();

      String sql =
          "INSERT INTO PRODUCT(NAME, TYPE, MANUFACTURER) VALUES('NAME', 'TYPE', 'MANUFACTURER')";

      stmt.executeUpdate(sql);
      stmt.close();
      conn.close();
      /*
       Also similar to the earlier catch, this will catch the exceptions, so the code can still run.
      */
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } // End catch.

    System.out.println("Product Added");
  } // End add_product.

  /**
   * This function will take the button on the application labeled record production, and give it
   * function. The button will take orders from the client of what they would like produced.
   */
  @FXML
  void record_production(MouseEvent event) {
    System.out.println("Your order has been recorded");
  } // End of record_production

  /**
   * This function will take the user input entered into the text box. This will be accomplished by
   * using the getProductName function. Then, this function will set a query to insert a product by
   * name into the database.
   */
  public void input_products(CellEditEvent<?, ?> cellEditEvent) {
    System.out.println("Product name has been entered");
  } // End of input_products.

  /**
   * This function is used to display the different production types into a choice box. The choice
   * box will have options once it is called by context.
   */
  public void display_types(ContextMenuEvent contextMenuEvent) {
    System.out.println("Production types have been updated.");
  } // End of display_names.
  /**
   * This function is used to scan the users input into the text box, and store it. Once it is
   * stored, it will be called by the input_product function so that it can be used in a query.
   */

  public void getProductName(MouseEvent mouseEvent) {
    System.out.println("Product name has been scanned and stored.");
  } // End of getProductName.

  /**
   * This function will be used to scan the manufacturer text box, and store the user input. Once
   * the input is stored, it will be used in a query to store the information into the database.
   */
  public void getManufacturer(MouseEvent mouseEvent) {
    System.out.println("Manufactuer has been scanned and stored.");
  } // End of getManufacturer.

  /**
   * This function will be used by the type choice box. Once a selection has been made using the
   * mouse, that value will be stored into a variable. The variable will then be used in a query to
   * store in the entered information into the database.
   */
  public void getType(MouseEvent mouseEvent) {
    System.out.println("Type has been selected and stored.");
  } // End of getType.
} // End of Controller class.
