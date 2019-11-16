package production;
/**
 * *********************************************************
 * File : ItemType.Java Author: Breanna
 * Rhodes Class : COP 3003 Purpose : This enum is used to populate a choice box to determine what
 * type of product is being added to the database.
 * **********************************************************
 */


public enum ItemType {
  // The enums are declared, and have a String value stored in them.
  AUDIO("AU"),
  VISUAL("VI"),
  AUDIO_MOBILE("AM"),
  VISUAL_MOBILE("VM");

  public String code;

  ItemType(String code) {
    this.code = code;
  }
}
