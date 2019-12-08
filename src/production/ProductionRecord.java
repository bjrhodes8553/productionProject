package production;

import java.time.LocalDate;
import java.util.Date;

/*
 * *********************************************************
 * File : ProductionRecord.Java
 * Author: Breanna Rhodes
 * Class : COP 3003
 * Purpose : This class keeps record of the the items in production.
 * **********************************************************
 */

public class ProductionRecord {
  // Initialize the variables used in this class.
  int productionNumber;
  int productID;
  String serialNumber;
  Date dateProduced;
  String name;
  Date date;

  /**
   * METHOD NAME: productProduced PURPOSE:This constructor for the ProductionRecord call that takes
   * two arguments.
   */
  public ProductionRecord(Product productProduced, int i) {
    dateProduced = new Date();
    // the serial number is generated but obtaining the manufacturer, and adding integers.
    serialNumber =
        productProduced.getManufacturer().substring(0, 3)
            + productProduced.getType().code
            + "0000"
            + i;
  }

  // This constructor only takes the product ID as an argument.
  ProductionRecord(int productID) {
    this.productID = productID;
    this.productionNumber = 0;
    this.serialNumber = "0";
    this.dateProduced = new Date();
  }

  // This overloaded constructor takes 4 arguements.
  ProductionRecord(int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  /** METHOD NAME: toString PURPOSE: This method cast the data into a string. */
  public String toString() {
    return "\nProd. Num: "
        + this.productionNumber
        + ","
        + " Product ID: "
        + this.productID
        + ","
        + " Serial Num: "
        + this.serialNumber
        + ","
        + " Date: "
        + this.dateProduced;
  }

  /** METHOD NAME: toStringWithName PURPOSE: This method cast the data into a string. */
  public String toStringWithName() {
    return "\nProd Name: "
        + this.name
        + ","
        + " Product ID: "
        + this.productID
        + ","
        + " Serial Num: "
        + this.serialNumber
        + ","
        + " Date: "
        + this.date;
  }

  ProductionRecord(String name, int productID, String serialNumber, Date date) {
    this.name = name;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.date = date;
  }

  ProductionRecord(int productID, String serialNumber, Date date) {
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.date = date;
  }

  // Getter Methods:
  public int getProductionNum() {
    return productionNumber;
  }

  public int getProductID() {
    return productID;
  }

  public String getSerialNum() {
    return serialNumber;
  }

  public Date getProdDate() {
    return dateProduced;
  }

  // Setter Methods:
  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public void setProdDate(Date dateProduced) {
    this.dateProduced = new Date();
  }
}
