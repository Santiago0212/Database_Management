package control;

import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.AVLTree;

public class SearchWindow implements Initializable{
	
    @FXML
    private TextField codeTF;

    @FXML
    private TextField completeNameTF;

    @FXML
    private TextField lastNameTF;

    @FXML
    private TextField nameTF;
    
    
    private AVLTree nameData;
    
    public SearchWindow(AVLTree nameData) {
    	this.nameData = nameData;
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
    
    

}