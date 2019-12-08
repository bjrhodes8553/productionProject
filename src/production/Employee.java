package production;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Employee {
  private String name;
  private String password;
  private String Username;
  private String Email;


  private static final Pattern[] passwordRegex = new Pattern[3];
  static{
    passwordRegex[0] = Pattern.compile(".*[A-Z].*");
    passwordRegex[1] = Pattern.compile(".*[a-b].*");
    passwordRegex[2] = Pattern.compile(".*[`~!@#$%^&*()\\-_=+\\\\|\\[{\\]};:'\",<.>/?].*");
  }

  final static String secondWordRegex = "(\\s[A-Za-z]+)";
  final static Pattern secondWordPattern = Pattern.compile(secondWordRegex, Pattern.MULTILINE);
  static Matcher secondWordMatcher;


  final static String firstWordRegex = "([A-Za-z]+\\s)";
  final static Pattern firstWordPattern = Pattern.compile(firstWordRegex, Pattern.MULTILINE);
  static Matcher wordMatcher;



  public Employee(String name, String password){
    checkName(name);
    this.name = name;
    ValidPassword(password);
  }


  public void checkName(String name){

    if(name.contains(" ")){
      setUsername(name);
      setEmail(name);
    }
    else{
      Username = "default";
      Email = "user@oracleacademy.Test";
    }
  }

  /**
   * This method takes the user's entered password and does a Regex search to make sure it has
   * at least one lowercase letter, one uppercase letter and one special character.
   * If it does not, then the password it set to pw.
   * @param password
   * @return
   */

  public String ValidPassword(String password) {
    boolean passwordMatch = true;
    this.password = password;

    //If the password doesn't have all the elements in pattern, it is set to 'pw'.
    for(Pattern passwordPattern : passwordRegex){
      if(!passwordPattern.matcher(password).matches()){
        this.password = "pw";
      }
    }
    return password;
    }

  /**
   * This method takes in the user's name and creates a username by taking the first letter
   * of the users first name and the last name, and putting the username in lowercase letters.
   * This is done using a Regex that finds a string with a space at the beginning and a word after.
   * The string is then manipulated with a string builder object, that is casted back into a string.
   * @param name
   */
  public void setUsername(String name) {
    String test = "";
    Matcher wordMatcher = secondWordPattern.matcher(name);
    while (wordMatcher.find()) {
      //Username = wordMatcher.group(0);
      test = wordMatcher.group(0);
    }
    StringBuilder sb = new StringBuilder(test);
    String last = sb.deleteCharAt(0).toString();
    this.Username = name.substring(0,1).toLowerCase()+last.toLowerCase();
    }

  /**
   * This method will take in the user's name. It will then create an email address with the user's
   * first name, a period, and a last name all in lower case letters. This is done using regex.
   * @param name
   */
  public void setEmail(String name){
    String first = "";
    String last = " ";

    //Set up matcher for Regex Search.
    Matcher firstWordMatcher = firstWordPattern.matcher(name);
    while (firstWordMatcher.find()) {
      // Username = wordMatcher.group(0);
      first = firstWordMatcher.group(0);
    }//End of while.

    //Create StringBuilder object to manipulate the String of the user's name.
    StringBuilder firstNameBuilder = new StringBuilder(first);
    //Want to remove the last letter of the first name because it is a whitespace.
    int index = first.length()-1;
    String makeFirst = firstNameBuilder.deleteCharAt(index).toString();

    //Set up matcher for the Regex search.
    Matcher wordMatcher = secondWordPattern.matcher(name);
    while (wordMatcher.find()) {
      //Username = wordMatcher.group(0);
      last = wordMatcher.group(0);
    }
    //Create a string builder to manipulate the user's last name.
    StringBuilder lastNameBuilder = new StringBuilder(last);
    String make = lastNameBuilder.deleteCharAt(0).toString();

    //Set user's email equal to the appended regex results.
    this.Email = makeFirst.toLowerCase()+"."+ make.toLowerCase()+ "@oracleacademy.Test";
  }
  public void setName(String name){
    this.name = name;
  }
  public void setPassword(String password){
    this.password = ValidPassword(password);
  }
  public String toString(){
    return("EMPLOYEE DETAILS:"
        +"\n---------------------------"
        + "\nNAME: "+ name+
        "\nUSERNAME: "+Username+
        "\nEMAIL: "+Email+
        "\nPASSWORD: "+ password);
  }

  public String getPassword(){
    return password;
  }

}
