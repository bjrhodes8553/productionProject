/**
 * *********************************************************
 * File : Screen.Java
 * Author: Breanna Rhodes
 * Class : COP 3003
 * Purpose : This class implements ScreenSpec and defines the screen for the MoviePlayer class.
 * **********************************************************
 */

package production;

public class Screen implements ScreenSpec{

  String resolution;
  int refreshrate;
  int responsetime;

  public String getResolution(){
    return resolution;
  }
  public int getRefreshRate(){
    return refreshrate;
  }
  public int getResponseTime(){
    return responsetime;
  }

  Screen(String resolution, int refreshrate, int responsetime){
    this.resolution = resolution;
    this.refreshrate = refreshrate;
    this.responsetime = responsetime;

  }

  public String toString() {
    return ("\nScreen:"
        + "\nResolution: "
        + resolution
        + "\nRefresh rate: "
        + refreshrate
        + "\nResponse time: "
        + responsetime);
  }

}