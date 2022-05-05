package control;

import java.awt.Button;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.AVLTree;
import model.BRTree;

public class SearchWindow implements Initializable{
	
    @FXML
    private TableColumn<?, ?> codeC;

    @FXML
    private TextField codeTF;

    @FXML
    private TextField completeNameTF;

    @FXML
    private TableView<?> dataTable;

    @FXML
    private TableColumn<?, ?> lastNameC;

    @FXML
    private TextField lastNameTF;

    @FXML
    private TableColumn<?, ?> nameC;

    @FXML
    private TextField nameTF;

    @FXML
    private Button searchNameBTN;
    
    
    private AVLTree<String, BRTree> nameData;
    
    public SearchWindow(AVLTree nameData) {
    	this.nameData = nameData;
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
    @FXML
    void searchName(ActionEvent event) {
    	
    	String
    	
    	String name = nameTF.getText();
    	
    	String initial = name.substring(0,1);
    	
    	BRTree nameTree = nameData.search(initial).getValue();
    	
    }
    
    public void f
    
    

}