package production;
/**
 * *********************************************************
 * File : Product.Java
 * Author: Breanna Rhodes
 * Class : COP 3003
 * Purpose : This abtract class implements Item. It is used in AudioPlayer
 * and MoviePlayer. An instance of Product is not applicable.
 * **********************************************************
 */

public abstract class Product implements Item {
  int id;
  ItemType type;
  String manufacturer;
  String name;

  // This constructor takes the name and manufactuer as an argument.
  public Product(String name, String manufacturer) {
    this.name = name;
    this.manufacturer = manufacturer;
  }
  // This constructor takes in name, manufactuer and type as an argument.

  Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  // This method cast the information into a string.
  public String toString() {
    return ("Name: " + name + "\n" + "Manufacturer: " + manufacturer + "\n" + "Type: " + type);
  }

  // Getter methods:
  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public ItemType getType() {
    return type;
  }

  // Setter methods
  public void setName(String name) {
    this.name = name;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public void setType(ItemType type) {
    this.type = type;
  }
}
