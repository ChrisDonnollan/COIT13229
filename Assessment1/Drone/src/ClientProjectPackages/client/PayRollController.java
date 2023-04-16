/**
 * Created by Mary Tom
* JDK11 and JavaFX 11.0.2
 * This is the Controller class which implements the action events for the
 * Register Employee and view employees Buttons.
 * This class creates the EmployeeClient object and sends the entered data
 * to the server and displays the received data in the TextArea.
 */
package client;

import domain.SalariedEmployee;
import java.io.IOException;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class PayRollController {
    @FXML
    private Label lblLast;

    @FXML
    private Label lblSalary;
    
    @FXML
    private TextField txtFldFirstName;
    @FXML
    private TextField txtFldLastName;
   @FXML
    private TextField txtFldSalary;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Label lblFirstName;

    @FXML
    private Button btnSubmit;
    @FXML
    private TextArea txtAreaResult;

    
    @FXML
    private Button btnViewEmployee;
      
    private LinkedList<SalariedEmployee> employees;
    private LinkedList<SalariedEmployee> searchResult;
   
    String firstname,
            lastName,
            socialSecurityNumber;
     double salary;
            EmployeeClient  clientprocess ;  
    /**
     *
     *
     * @throws IOException
     * 
     * Initializes the controller class.
     */
    
    public void initialize() {
        
        employees = new LinkedList<>();
        searchResult = new LinkedList<>();
        
        try {

           clientprocess = new EmployeeClient ();
    
        
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
        
    } 
    /**
     * This method gets the employee details entered by the user
     * and creates and adds SalariedEmployee object to the  LinkedList.
     * Then invokes the EmployeeClient class method to send the data to the server.
     * @param event 
     */
    
    @FXML
    void AddEmployeeClicked(ActionEvent event) {
         
        firstname = txtFldFirstName.getText();
        lastName = txtFldLastName.getText();
       
        salary = Double.parseDouble(txtFldSalary.getText());

        employees.add(new SalariedEmployee(firstname,lastName,salary));
        //System.out.println (this.employees);
         //send the added employees to the server.
        this.clientprocess.sendEmployees(employees);
    }
     /**
      * 
      * @param event
      * This method invokes the EmployeeClient class method to receive the
      * employees from the server. Then displays it in the TextArea.
      */
     @FXML
    void viewEmployeeButtonClicked(ActionEvent event) {
        
           this.searchResult = this.clientprocess.receiveEmployees();
           System.out.println("receved from file\n"+ this.searchResult);
           txtAreaResult.clear();
           txtAreaResult.setText("");
         //display the values to the TextArea.
              this.searchResult.forEach((e) -> {
                txtAreaResult.appendText(((SalariedEmployee)e).toString());
            });
          }
      // }
    }

    
