package production;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.text.html.ImageView;

public class EmployeeController {
@FXML private AnchorPane Anchor_employee;


  @FXML
  private TextField textfield_firstName;

  @FXML
  private TextField textfield_lastName;

  @FXML
  private TextField textfield_password;

  @FXML
  private TextArea textarea_employee;

  @FXML
  private Button btn_createEmp;

  @FXML private Button btn_back;

  String firstName;
  String lastName;
  String password;
  String fullName;

  /**
   * Method that is initialized as soon as the screen is entered.
   */
  public void initialize(){

  }

  /**
   * This method is used when the create employee button is pressed. When the button is pushed,
   * the employees information is used to generate a username and email address. The password
   * is also checked to make sure it is strong enough.
   * @param event
   */
  @FXML
  void createEmployee(MouseEvent event) {
    //This label will display at the top of the anchorpane if the user enters a bad password.
    Label oops = new Label(  "Oops! Your password needs an uppercase, lowercase and a special character!");


    //Clear the text area before new information is entered into it.
    textarea_employee.clear();
    firstName = textfield_firstName.getText();
    lastName = textfield_lastName.getText();
    password = textfield_password.getText();
    fullName = firstName+ " "+ lastName;
    //Create an Employee object based on user's input.
    Employee employee = new Employee(fullName, password);
    //Create a string that holds the employee's password after it goes through the Employee methods.
    String checkPass = employee.getPassword();

    //If password is equal to 'pw' then it is too weak, and new password needs to be made.
    if(checkPass.equals("pw")){

      //If the password made by the user isn't strong enough it will prompt the user to remake it.
      Anchor_employee.getChildren().add(oops);
      textfield_password.clear();
      //If it is strong enough, it will update the user with their info in the text area.
    }else{
     //Call initialize again so the anchorpane resets.
      Anchor_employee.getChildren().remove(oops);
      textarea_employee.appendText(employee.toString());
      textfield_firstName.clear();
      textfield_lastName.clear();
      textfield_password.clear();
    }

  }
  //Brings you back to the production screen.
@FXML
  void backToProduction(MouseEvent event){
    Main.createProductionScene(event, "ProductionScreen.fxml");
  }


}

