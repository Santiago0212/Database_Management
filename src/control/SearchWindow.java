package control;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.AVLTree;
import model.BRTree;
import model.Person;

public class SearchWindow <K extends Comparable<K>,V>implements Initializable{
	
   

    @FXML
    private TextField codeTF;

    @FXML
    private TextField completeNameTF;

    @FXML
    private TableView<Person> dataTable;

    @FXML
    private TextField lastNameTF;

    @FXML
    private TableColumn<Person, String> nameC;
    
    @FXML
    private TableColumn<Person, String> lastNameC;
    
    @FXML
    private TableColumn<Person, String> codeC;
    
    @FXML
    private TableColumn<Person, String> dateC;

    @FXML
    private TableColumn<Person, Double> heightC;
    
    @FXML
    private TableColumn<Person, String> nationalityC;
    
    @FXML
    private TableColumn<Person, String> sexC;
    
    @FXML
    private TextField nameTF;

    @FXML
    private Button searchNameBTN;
    
    @FXML
    private Button lastNameBTN;
    
    @FXML
    private Button completeNameBTN;
    
    @FXML
    private Button codeBTN;
    
    
    private AVLTree<K,V> data;
    
    
    private ObservableList obs;;
    
    public SearchWindow(AVLTree<K,V> data) {
    	this.data = data;
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		obs = FXCollections.observableArrayList();
		
    	
		nameC.setCellValueFactory(new PropertyValueFactory<Person,String>("name"));
		lastNameC.setCellValueFactory(new PropertyValueFactory<Person,String>("lastname"));
		codeC.setCellValueFactory(new PropertyValueFactory<Person,String>("code"));
		sexC.setCellValueFactory(new PropertyValueFactory<Person,String>("sex"));
		dateC.setCellValueFactory(new PropertyValueFactory<Person,String>("dateString"));	
		nationalityC.setCellValueFactory(new PropertyValueFactory<Person,String>("nationality"));
		heightC.setCellValueFactory(new PropertyValueFactory<Person,Double>("height"));
		
		dataTable.setItems(obs);
	}
	
    @FXML
    void searchName(ActionEvent event) {
    	dataTable.getItems().clear();
    	
    	data.filt(1);
    	
    	ArrayList<Person> people = new ArrayList<>();
    	
    	String name = nameTF.getText().toUpperCase();
    	
    	Character initial = name.charAt(0);
    	
    	BRTree<K,V> namesTree = (BRTree<K, V>) data.triggerSearch((K) initial).getValue();
    	
    	people = (ArrayList<Person>) namesTree.searchAllNames(name);
    	
    	obs.addAll(people);
    	
    	dataTable.refresh();		
    }
    
    @FXML
    void searchLastName(ActionEvent event) {
    	dataTable.getItems().clear();
    	
    	data.filt(2);
    	
    	ArrayList<Person> people = new ArrayList<>();
    	
    	String lastName = lastNameTF.getText().toUpperCase();
    	
    	Character initial = lastName.charAt(0);
    	
    	BRTree<K,V> lastNamesTree = (BRTree<K, V>) data.triggerSearch((K) initial).getValue();
    	
    	people = (ArrayList<Person>) lastNamesTree.searchAllLastNames(lastName);
    	
    	obs.addAll(people);
    	
    	dataTable.refresh();
    }
    
    @FXML
    void searchCode(ActionEvent event) {
    	dataTable.getItems().clear();
    	
    	data.filt(3);
    	
    	ArrayList<Person> people = new ArrayList<>();
    	
    	String code = codeTF.getText().toUpperCase();
    	
    	Character initial = BRTree.changeToWords(code).charAt(0);
    
    	BRTree<K,V> lastNamesTree = (BRTree<K, V>) data.triggerSearch((K) initial).getValue();
    	
    	people = (ArrayList<Person>) lastNamesTree.searchAllCodes(code);
    	
    	obs.addAll(people);
    	
    	dataTable.refresh();
    }

    @FXML
    void searchCompleteName(ActionEvent event) {
    	dataTable.getItems().clear();
    	
    	data.filt(1);
    	
    	ArrayList<Person> people = new ArrayList<>();
    	
    	String completeName = completeNameTF.getText().toUpperCase();
    	
    	String[] completeNameArr = completeName.split(" ");
    	
    	String name = completeNameArr[0];
    	String lastName = completeNameArr[1];
    	
    	Character initial = name.charAt(0);
    	
    	BRTree<K,V> namesTree = (BRTree<K, V>) data.triggerSearch((K) initial).getValue();
    	
    	people = (ArrayList<Person>) namesTree.searchAllNames(name);
    	
    	for(Person p : people) {
    		if(p.getLastname().compareTo(lastName)==0) {
    			obs.add(p);
    		}
    	}
    	
    	dataTable.refresh();
    }
    

}