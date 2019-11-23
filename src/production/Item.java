package production;

/**
 * *********************************************************
 * File : Item.Java Author: Breanna Rhodes
 * Class : COP 3003 Purpose : This enum with force all classes to implement the functions within it.
 * **********************************************************
 */


public interface Item {

  // Getter Methods:
  public int getId();

  public String getManufacturer();

  public String getName();

  // Setter Methods:
  public void setManufacturer(String manufacturer);

  public void setName(String name);
}
