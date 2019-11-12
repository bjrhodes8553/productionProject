package production;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * Controller class controls all aspects of the application. It is used to give function to each
 * element of the application, such as buttons.
 */
@SuppressWarnings("ALL")
public class Controller {

  final String jcbdDriver = "org.h2.Driver";
  final String dbUrl = "jdbc:h2:C:/Users/feesh/OneDrive/intelliJCOP/productionProject/res";
  // login credentials to get into the database.
  final String user = "";
  final String pass = "";
  // Initializing the connection and prepared statement for later use.
  Connection conn = null;
  Statement stmt = null;

  // ************************************************************************************************
  // FXML VARIABLES
  /**
   * These are the components of the scenebuilder application. Each component that is going to be
   * used as a function in the application is declared in the controller. After the component is
   * declared, the function will follow
   */
  @FXML private Button btn_add_product;

  @FXML private Button Btn_remove;

  @FXML private ChoiceBox<ItemType> choiceB_type;

  @FXML private ComboBox<Integer> combo_quantity;

  @FXML private ListView listView_eProducts;

  @FXML private Button recordProductionBtn;

  @FXML private TextArea textArea_productLog;

  @FXML private TextField txtField_manufacturer;

  @FXML private TextField TxtField_Product;

  @FXML private TableColumn<?, ?> viewName;

  @FXML private TableColumn<?, ?> viewManu;

  @FXML private TableColumn<?, ?> viewType;

  @FXML private TableView<Product> viewProducts;


  // ************************************************************************************************
  // METHODS:

  /**
   * METHOD NAME: Initalize PURPOSE: This method will fill the choice box with the enum values from
   * ItemType. The if/else statement will iterate through the values of the enum and store them into
   * the choice box. The statement also checks to box to see if it is currently empty. If it is
   * empty, it will fill the choice box, if it is not empty, then it will display the choice box.
   * This is also so that the box does not fill with the values over and over again.
   */
  public void initialize() {

    // If/else statement to populate the choicebox for itemtype.
    if (choiceB_type.getItems().isEmpty()) {
      choiceB_type.getItems().addAll(ItemType.values());
    } else {
      choiceB_type.show();
    }

    // Similar statement is made to populate the combobox.
    if (combo_quantity.getItems().isEmpty()) {
      combo_quantity.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    } else {
      System.out.println(" ");
    }
    // call methods that need to be called when the program starts
    getName();
    //testMultimedia();
    populateTableView();
}
   public void populateTableView(){
     // Initilize array list for products
     ArrayList<Product> aListProducts = new ArrayList();
      String sql = "SELECT * FROM PRODUCT";
      aListProducts.clear();
      viewProducts.getItems().clear();
    try {
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      /*
       A while loop is made to display the information line by line in an organized manner. After
       the data is displayed, the query is closed using stmt.close(). The connection is also
       closed using a similar function, conn.close().
      */
      while (rs.next()) {
        final String pName = rs.getString("NAME");
        final String pManu = rs.getString("MANUFACTURER");
        final String pType = rs.getString("TYPE");
        ItemType type;
        switch (pType) {
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
        Product dbProduct = new Widget(pName, pManu, type);
        aListProducts.add(dbProduct);
        ObservableList<Product> listProducts = FXCollections.observableArrayList(aListProducts);
        viewProducts.setItems(listProducts);
      }
      // Close the connection
      stmt.close();
      conn.close();
    }catch(ClassNotFoundException e){
     System.out.println("Class not found when selecting items from PRODUCTION database.");
    }catch (SQLException e){
      System.out.println("SQL Exception when selecting items from PRODUCTION database");
    }
    } // End of catch.


  /** METHOD NAME: testMultimedia() PURPOSE: Demonstrates functionality in the user interface. */
  public static void testMultimedia() {
    AudioPlayer newAudioProduct =
        new AudioPlayer(
            "DP-X1A", "Onkyo", "DSD/FLAC/ALAC/WAV/AIFF/MQA/Ogg-Vorbis/MP3/AAC", "M3U/PLS/WPL");
    Screen newScreen = new Screen("720x480", 40, 22);
    MoviePlayer newMovieProduct =
        new MoviePlayer("DBPOWER MK101", "OracleProduction", newScreen, MonitorType.LCD);
    ArrayList<MultimediaControl> productList = new ArrayList<MultimediaControl>();
    productList.add(newAudioProduct);
    productList.add(newMovieProduct);
    for (MultimediaControl p : productList) {
      System.out.println(p);
      p.play();
      p.stop();
      p.next();
      p.previous();
    }
  }

  /*
   * METHOD NAME: recordProduction
   * Purpose: This method will connect to the database and select the
   * column name column and store the values in the name column into the
   * record production listview.
   */
  public void getName() {

    try {
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      stmt = conn.createStatement();
      String sql = "SELECT NAME FROM PRODUCT";
      ResultSet rs = stmt.executeQuery(sql);
      /*
       A while loop is made to display the information line by line in an organized manner. After
       the data is displayed, the query is closed using stmt.close(). The connection is also
       closed using a similar function, conn.close().
      */

      while (rs.next()) {
        final String pName = rs.getString("NAME");
        if (listView_eProducts.getItems().contains(pName)) {
          System.out.println("");
        } else {
          listView_eProducts.getItems().addAll(pName);
        }
      }
      // Close the connection
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } // End of catch.
  }

  public void record_production(MouseEvent mouseEvent) {
    add_productLog();
    System.out.println("product added");
  }

  /**
   * METHOD NAME: add_productLog. PURPOSE: Set the table view to editable, so that it can have items
   * added to it. Then the columns are made that reflect the database. The columns are then added to
   * the table view.
   */
  public void add_productLog() {
    ObservableList<String>selectedItem;
    selectedItem = listView_eProducts.getSelectionModel().getSelectedItems();
    String prodName = selectedItem.get(0);


    try {
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM PRODUCT WHERE NAME= '"+prodName+"'";
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
        int quantity = combo_quantity.getValue();
        ProductionRecord addProduction = new ProductionRecord(1, quantity, pManufacturer.substring(0,3)+"0000", new Date());
        DatabaseAccessor.add_production_record(addProduction);
        ProductionRecord displayProduction = new ProductionRecord(pName, quantity, pManufacturer.substring(0,3)+"0000", new Date());
        textArea_productLog.appendText(displayProduction.toStringWithName());
      }
      // Close the connection
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } // End of catch.

    }


  /**
   * METHOD NAME: display_products. PURPOSE: This method is going to call the DatabaseAccessor's
   * method displayProducts, where the database will be connected and the information inside of the
   * database will be put into the table view whent he populate table button is hit.
   */
  @FXML
  void display_products(MouseEvent event) {
    DatabaseAccessor.displayProducts();
  } // End of display_products.

  /*
   * METHOD NAME: remove_product
   * PURPOSE: This method will remove a selected item from the database based on the product ID.
   */
  @FXML
  void remove_product(MouseEvent event) {
    DatabaseAccessor.removeProduct();
  }

  /**
   * METHOD NAME: add_product PURPOSE: This is the function that will add products to the database.
   * 10 products have already been entered using this function. The button labeled "add product"
   * adds a product to the database. When the button is clicked by the mouse, the information is
   * entered.
   */
  @FXML
  void add_product(MouseEvent event) {
    // When using a textfield, the method getText() is used to get the text entered by user.
    final String add_product_name = TxtField_Product.getText();
    final String add_manufacturer = txtField_manufacturer.getText();
    // When using a choice box, the method getValue() is used to get the users selection.
    final ItemType get_type = choiceB_type.getValue();
    // Cast the type to a string so the database can accept it smoothly.
    final String type_string = get_type.toString();
    Product W = new Widget(add_product_name, add_manufacturer, get_type);
    // Call the DatabaseAccessor class to insert information into the database entered by user.
    DatabaseAccessor.insertProduct(W);
    // Call initilize again, so the screen can refresh with updated information.
    initialize();

    // Print statement confirming the users information that is being added to the database.
    System.out.println(
        "\nInformation that has been added to the database succesfully:"
            + "\nName: "
            + add_product_name
            + "\nManufacturer: "
            + add_manufacturer
            + "\nType: "
            + type_string);
  }
}

