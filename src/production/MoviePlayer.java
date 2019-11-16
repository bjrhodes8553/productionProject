package production;
/**
 * *********************************************************
 * File : MoviePlayer.Java
 * Author: Breanna Rhodes
 * Class : COP 3003
 * Purpose : This class extends Product and implements MultimediaControl.
 * **********************************************************
 */



public class MoviePlayer extends Product implements MultimediaControl {
  Screen newScreen;
  MonitorType monitorType;

  /**
   * METHOD NAME: MoviePlayer
   * Purpose: Constructor for the MoviePlayer class. Takes in two string arugments, a Screen
   * and a Monitor type.
   */

  public MoviePlayer(String name, String manufacturer, Screen newScreen, MonitorType monitorType) {
    super(name, manufacturer, ItemType.VISUAL);
    this.newScreen = newScreen;
    this.monitorType = monitorType;
  }

  public void play() {
    System.out.println("Playing movie");
  }

  public void stop() {
    System.out.println("Stopping movie");
  }

  public void next() {
    System.out.println("Next movie");
  }

  public void previous() {
    System.out.println("Previous movie");
  }

  public String toString() {
    System.out.print(super.toString());
    return (newScreen.toString() + "\nMonitor Type: " + monitorType.toString());
  }
}
