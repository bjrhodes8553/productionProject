package production;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class of the JavaFX program. It is required to run a program in JavaFX Start function will
 * start the JavaFX application. The stage of the application is set in the parameter. The name of
 * the application stage is primaryStage. It uses functions such as set title and set scene to
 * create the stage. The final function (.show) is used to display the stage to the user.
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("ProductionScreen.fxml"));
    primaryStage.setTitle("Production Task: ");
    primaryStage.setScene(new Scene(root, 800, 600));
    primaryStage.show();
  }

  /** Launches the application. */
  public static void main(String[] args) {
    launch(args);

    // Screen newScreen = new Screen("720x480", 40, 22);
    // String testS = newScreen.toString();
    // System.out.println(testS);

    // test constructor used when creating production records from user interface
    // Integer numProduced = 3; // this will come from the combobox in the UI

    /* //issue 4
   for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
          ProductionRecord pr = new ProductionRecord(0);
          System.out.println(pr.toString());
        }

        // test constructor used when creating production records from reading database
        ProductionRecord pr = new ProductionRecord(0, 3, "1", new Date());
        System.out.println(pr.toString());

        // testing accessors and mutators
        pr.setProductionNum(1);
        System.out.println(pr.getProductionNum());

        pr.setProductID(4);
        System.out.println(pr.getProductID());

        pr.setSerialNum("2");
        System.out.println(pr.getSerialNum());

        pr.setProdDate(new Date());
        System.out.println(pr.getProdDate());

        for (ItemType it : ItemType.values()) {
          System.out.println(it);
        }

        //implement an interface without creating a class
        MyInterface mii = new MyInterface() {
          @Override
          public void printIt(String text)
    */
    // issue 5
    // Product productProduced = new Widget("iPod", "Apple", ItemType.AUDIO);

    // test constructor used when creating production records from user interface
    // int numProduced = 3;  // this will come from the combobox in the UI
    // int itemCount = 0;

    // for(int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++)
    // {
    // ProductionRecord pr = new ProductionRecord(productProduced, itemCount++);
    // using the iterator as the product id for testing
    // System.out.println(pr.toString());
    // }
  }

}
 /* public static void genericDemo() {
     // Create arrays of Integer, Double and Character
     Integer[] intArray = { 1, 2, 3, 4, 5 };
     Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
     Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };

     System.out.println("Array integerArray contains:");
     printArray(intArray);   // pass an Integer array

     System.out.println("\nArray doubleArray contains:");
     printArray(doubleArray);   // pass a Double array

     System.out.println("\nArray characterArray contains:");
     printArray(charArray);   // pass a Character array
   }

   public static void printArray(Integer[] intArray){
     System.out.println(Arrays.toString(intArray));

 }
   public static void printArray(Double[] doubleArray){
     System.out.println(Arrays.toString(doubleArray));
   }
   public static void printArray(Character[] charArray){
     System.out.println(Arrays.toString(charArray));

   }*/
