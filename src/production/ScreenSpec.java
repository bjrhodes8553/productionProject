/**
 * *********************************************************
 * File : ScreenSpec.Java
 * Author: Breanna Rhodes
 * Class : COP 3003
 * Purpose : This interface defines three methods pertaining to the Screen class.
 * **********************************************************
 */

package production;


public interface ScreenSpec{

  public String getResolution();
  public int getRefreshRate();
  public int getResponseTime();
  public String toString();
}