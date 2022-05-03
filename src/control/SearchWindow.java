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

public class SearchWindow implements Initializable{
	
    @FXML
    private TextField codeTF;

    @FXML
    private TextField completeNameTF;

    @FXML
    private TextField lastNameTF;

    @FXML
    private TextField nameTF;
    
    private AutoCompletionBinding<String> autoCompletionBinding;
    private String[] _possibleSuggestionsName = {"Santiago", "Sebastian", "Jhonatan", "Samuel", "Crystal", "Daniela", "Laura", "Natalia", "Juan"};
    private Set<String> possibleSuggestionsName = new HashSet<>(Arrays.asList(_possibleSuggestionsName));
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TextFields.bindAutoCompletion(nameTF, possibleSuggestionsName);
	}
    
    

}