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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * Controller class controls all aspects of the application. It is used to give function to each
 * element of the application, such as buttons.
 */
@SuppressWarnings("ALL")
public class Controller {

  // ***********************************************************************************************
  // INITIALIZE DATABASE:
  // Declare the Driver and the URL in the Class so the methods can have access to it.
  final String jcbdDriver = "org.h2.Driver";
  final String dbUrl = "jdbc:h2:./res";
  // login credentials to get into the database.
  final String user = "";
  final String pass = "";
  // Initializing the connection and prepared statement for later use.
  Connection conn = null;
  Statement stmt = null;
  // **********************************************************************************************
  // FXML VARIABLES:
  /*
   * These are the components of the scenebuilder application. Each component that is going to be
   * used as a function in the application is declared in the controller. After the component is
   * declared, the function will follow
   */
  @FXML private Button btnAddProduct;

  @FXML private Button btnEmployee;

  @FXML private Button btnRemove;

  @FXML private Button btnRemoveLog;

  @FXML private Button btnRecordProduct;

  @FXML private ChoiceBox<ItemType> choicebType;

  @FXML private ComboBox<Integer> comboQuantity;

  @FXML private ImageView imageLogo;

  @FXML private ListView listViewEProducts;

  @FXML private TextArea textAreaProductLog;

  @FXML private TextField txtFieldManufacturer;

  @FXML private TextField txtFieldProduct;

  @FXML private TableColumn<?, ?> tableColViewName;

  @FXML private TableColumn<?, ?> tableColViewManu;

  @FXML private TableColumn<?, ?> tableColViewType;

  @FXML private TableView<Product> tableViewProduct;
  // *********************************************************************************************
  // METHODS:

  /**
   * METHOD NAME: Initalize
   * PURPOSE: This method will fill the choice box with the enum values from
   * ItemType. The if/else statement will iterate through the values of the enum and store them into
   * the choice box. The statement also checks to box to see if it is currently empty. If it is
   * empty, it will fill the choice box, if it is not empty, then it will display the choice box.
   * This is also so that the box does not fill with the values over and over again.
   */
  public void initialize() {

    populate_ProductionTextView();


    //Properties prop = new Properties();
    //prop.load(new FileInputStream("res/properties"));
    //String PASS = prop.getProperty("password");




    // If/else statement to populate the choicebox for itemtype.
    if (choicebType.getItems().isEmpty()) {
      // The choicebox will add all of the items in ItemType.values()
      choicebType.getItems().addAll(ItemType.values());
    } else {
      // If the choicebox is not empty, it will just show the values and not add more items.
      choicebType.show();
    }

    // Similar statement is made to populate the combobox.
    if (comboQuantity.getItems().isEmpty()) {
      for (int i = 0; i<101; i++)
      // If there are no values in the combobox, it will generate items in the combobox via for-loop
      comboQuantity.getItems().addAll(i);
    } else {
      // If the combobox already contains the items, then it will just print a blank statement.
      System.out.println(" ");
    }
    // call methods that need to be called when the program starts
    getName();
    // testMultimedia(); -- (commented out until needed.)
    populate_tableview();
  } // End of intialize method.

  /**
   * METHOD NAME: populate_tableview()
   * PURPOSE: The purpose of this method is to populate the tableview on the first tab the user
   * comes across. The tableview is generated from the items in the database.
   */
  public void populate_tableview() {
    // Set the cell value factory for the columns of the tableview.
    tableColViewName.setCellValueFactory(new PropertyValueFactory("Name"));
    tableColViewManu.setCellValueFactory(new PropertyValueFactory("Manufacturer"));
    tableColViewType.setCellValueFactory(new PropertyValueFactory("Type"));
    // Create sql statement.
    String sql = "SELECT * FROM PRODUCT";
    // Clear the ArrayList and the ObservableList.
    // Initialize the observable list that will be added to the tableview.
    ObservableList<Product> productOlist = FXCollections.observableArrayList();
    // Initilize array list for products
    ArrayList<Product> productArrayList = new ArrayList<Product>();
    // Clear the ArrayList and the ObservableList.
    productArrayList.clear();
    productOlist.clear();
    // Try statement starts and set up connection for database.
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
        // Initialize type as an ItemType so when a Product is created,it has correct parameters.
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
        } // End of switch statement.
        // Create Product object that will be added to the list.
        Product dbProduct = new Widget(pName, pManu, type);
        productArrayList.add(dbProduct);
        tableViewProduct.setItems(productOlist);
        productOlist.add(dbProduct);
      } // End of while loop.
      // Close the connection
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      System.out.println("Class not found when selecting items from PRODUCTION database.");
    } catch (SQLException e) {
      System.out.println("SQL Exception when selecting items from PRODUCTION database");
    } // End of catch.
  } // End of populate_tableview.

  /**
   *  METHOD NAME: testMultimedia()
   * PURPOSE: Demonstrates functionality in the user interface.
   */
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

  /**
   * METHOD NAME: recordProduction
   * Purpose: This method will connect to the database and select the
   * column name column and store the values in the name column into the
   * record production listview.
   */
  public void getName() {

    // Try statement and connect to the database.
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
        String productName = rs.getString("NAME");
        // If the listview already contains that name, it will print a blank statement.
        if (listViewEProducts.getItems().contains(productName)) {
          // If the listview doesnt have the name, then it will add it to the listview.
        } else {
          listViewEProducts.getItems().addAll(productName);
          // Call initialize again to reset the screen with proper settings.
          initialize();
        }
      } // End of while.
      // Close the connection
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } // End of catch.
  } // End of getName method.

  /**
   * METHOD NAME: record_production PURPOSE: The purpose of this method is to call the
   * add_productLog when the button is clicked, and also to print a statement that informs the user
   * has added a product.
   */
  public void record_production(MouseEvent mouseEvent) {
    add_productLog();
    System.out.println("product added");
  } // End of record_production method.

  /**
   * METHOD NAME: add_productLog. PURPOSE: Set the table view to editable, so that it can have items
   * added to it. Then the columns are made that reflect the database. The columns are then added to
   * the table view.
   */
  public void add_productLog() {
    // Declare ObservableList object that the listview can store its items in.
    ObservableList<String> selectedItem;
    // The observableList will be set to the selected item in the listview.
    selectedItem = listViewEProducts.getSelectionModel().getSelectedItems();
    // A string is set equal to the item value selected.
    String prodName = selectedItem.get(0);

    // Try statement with connection establishment.
    try {
      Class.forName(jcbdDriver);
      conn = DriverManager.getConnection(dbUrl, user, pass);
      stmt = conn.createStatement();
      String sql = "SELECT * FROM PRODUCT WHERE NAME= '" + prodName + "'";
      ResultSet rs = stmt.executeQuery(sql);
      /*
       A while loop is made to display the information line by line in an organized manner. After
       the data is displayed, the query is closed using stmt.close(). The connection is also
       closed using a similar function, conn.close().
      */
      while (rs.next()) {
        int productID = rs.getInt("ID");
        String productName = rs.getString("NAME");
        String productManu = rs.getString("MANUFACTURER");
        String productType = rs.getString("TYPE");
        // Get the value from the comboBox, and store in variable quantity.
        int quantity = comboQuantity.getValue();

        // For loop to create as many items in ProductionRecord as the quantity selected.
        for (int i = 1; i < quantity + 1; i++) {
          // Create a ProductionRecord object that is set to the value by user.
          ProductionRecord addProduction =
              new ProductionRecord(0, i, productManu.substring(0, 3) + productType.substring(0,2) + "0000" + i,
                  new Date());
          ProductionRecord displayProd = new ProductionRecord(prodName, i, productManu.substring(0, 3) + productType.substring(0,2) + "0000" + i, new Date());
          textAreaProductLog.appendText(displayProd.toStringWithName());

          // Call the add_production_record method from the DatabaseAccessor class.
          DatabaseAccessor.add_production_record(addProduction);

        } // End of for.
      } // End of while.
      // Close the connection
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } // End of catch.
  } // End of add_productlog method.

  public void populate_ProductionTextView(){
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
        int productNum = rs.getInt("ID");
        String productName = rs.getString("NAME");
        String productManu = rs.getString("MANUFACTURER");
        String productType = rs.getString("TYPE");

      // textAreaProductLog.appendText("Product Name: "+productName+"Serial: "+DatabaseAccessor.getProductionRecord().getSerialNum()+
        // "Date Produced: "+DatabaseAccessor.getProductionRecord().getProdDate());

        // Create another object that will display the RecordProduction.

        //  ProductionRecord displayProduction = new ProductionRecord(DatabaseAccessor.getProductionRecord());

          // The textarea will display the product name, ProductID, serial number and dateProduced.
          //textAreaProductLog.appendText(displayProduction.toString());
        //displayProduction.toStringWithName();
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
    // Initialize Observablelist.
    ObservableList<Product> list;
    // In the ObservableList, allow the user to select an item from the list, and store value.
    list = tableViewProduct.getSelectionModel().getSelectedItems();
    // Product object is going to be set the value selected from the list.
    Product name = list.get(0);
    // Get the name from the product and pass it as an arugment for the removeProduct method.
    String itemName = name.getName();
    // Call removeProduct method from DatabaseAccessor class.
    DatabaseAccessor.removeProduct(itemName);
    listViewEProducts.getItems().remove(itemName);
    // Re-initialize the screen, and clear the textfields.
    initialize();
    clear_textfields();
  }

  /**
   * METHOD NAME: remove_productionLog PURPOSE: This purpose of this method is to clear the
   * production log in the text area as well as the PRODUCTIONRECORD database.
   */
  @FXML
  void remove_productionLog(MouseEvent event) {
    // Clear the text area.
    textAreaProductLog.clear();
    // Call the remove_productionLog method from the DatabaseAccessor class.
    DatabaseAccessor.remove_productionLog();
  } // end of remove_productionLog.

  /**
   * METHOD NAME: clear_textfields PURPOSE: The purpose of this method is to clear the text fields
   * after information has been stored into the database or if the database is cleared.
   */
  void clear_textfields() {
    // Clear the textfields of manufacturer and product name.
    txtFieldManufacturer.clear();
    txtFieldProduct.clear();
    // Hide the choicebox for type.
    choicebType.hide();
  } // End of clear_textfields.

  /**
   * METHOD NAME: add_product PURPOSE: This is the function that will add products to the database.
   * 10 products have already been entered using this function. The button labeled "add product"
   * adds a product to the database. When the button is clicked by the mouse, the information is
   * entered.
   */
  @FXML
  void add_product(MouseEvent event) {
    // When using a textfield, the method getText() is used to get the text entered by user.
    final String add_product_name = txtFieldProduct.getText();
    final String add_manufacturer = txtFieldManufacturer.getText();
    // When using a choice box, the method getValue() is used to get the users selection.
    final ItemType get_type = choicebType.getValue();
    // Cast the type to a string so the database can accept it smoothly.
    final String type_string = get_type.toString();
    Product productWidget = new Widget(add_product_name, add_manufacturer, get_type);
    // Call the DatabaseAccessor class to insert information into the database entered by user.
    DatabaseAccessor.insertProduct(productWidget);
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
    clear_textfields();
  }

  @FXML
  public void goEmployee(MouseEvent event){
    Main.createEmployeeScene(event, "Employee.fxml");
  }
}
