package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.AVLTree;
import model.BRTree;
import model.Person;

public class SearchWindow <K extends Comparable<K>,V>implements Initializable{
	
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
    
    
    private AVLTree<K,V> nameData;
    
    public SearchWindow(AVLTree<K,V> nameData) {
    	this.nameData = nameData;
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
    @FXML
    void searchName(ActionEvent event) {
    	nameData.filt(1);
    	ArrayList<Person> people = new ArrayList<>();
    	
    	String name = nameTF.getText();
    	
    	Character initial = name.charAt(0);
    	
    	BRTree<K,V> namesTree = (BRTree<K, V>) nameData.triggerSearch((K) initial).getValue();
    	
    	people = (ArrayList<Person>) namesTree.searchAll(name);
    	
    	/*System.out.println(people.isEmpty());
    	
    	for(Person p : people) {
    		System.out.println(p.getName()+" "+p.getLastname());
    	}*/
    	
    	
    	
    	
    	
    }
    
    

}