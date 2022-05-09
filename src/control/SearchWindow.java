package control;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import main.Main;
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
    
    @FXML
    private Button volverBTN;
    
    
    private AVLTree<K,V> data;
    
    private Person stClicked;
    
    private ObservableList<Person> obs;;
    
    @FXML
    private Button modificarBTN;

    @FXML
    private Button borrarBTN;


    @FXML
    void borrar(ActionEvent event) {
    	Character initial = 0;
    	if(data.filt==1) {
    		String name = stClicked.getName();
        	
        	initial = name.charAt(0);
        	
    	}else if(data.filt==2) {
    		String lastName =  stClicked.getLastname();
    	
    		initial = lastName.charAt(0);
       	 
    	}else {
    		String code =  stClicked.getLastname();
        	
    		initial = BRTree.changeToWords(code).charAt(0);
    	}
    	
    	BRTree<K,V> namesTree = (BRTree<K, V>) data.triggerSearch((K) initial).getValue();
    	
    	namesTree.delete(stClicked);
    	
    	dataTable.refresh();
    	
    	
    	Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("Persona eliminada");
        alert.setTitle("Info");
        alert.setContentText("Persona eliminada con exito, actualiza la busqueda por favor.");
        alert.showAndWait();
    	
    }

    @FXML
    void modificar(ActionEvent event) throws IOException {
    	if(stClicked!=null) {
    		FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/Modify.fxml"));
    		loader.setController(new Modify<K, V>(stClicked,this.data));
    		Parent parent = (Parent) loader.load();
    		Stage stage = new Stage();
    		Scene scene = new Scene(parent);
    		stage.setTitle("AVL Tree Search");
    		stage.setScene(scene);
    		stage.show();
    	}
    }
    
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
		
		dataTable.setOnMouseClicked(event -> {
			stClicked = dataTable.getSelectionModel().getSelectedItem();
		});
	}
	
    @FXML
    void searchName(ActionEvent event) {
    	dataTable.getItems().clear();
    	
    	if(data.filt!=1)
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
    	
    	if(data.filt!=2)
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
    	
    	if(data.filt!=3)
    	data.filt(3);
    	
    	ArrayList<Person> people = new ArrayList<>();
    	
    	String code = codeTF.getText();
    	
    	Character initial = BRTree.changeToWords(code).charAt(0);
    
    	BRTree<K,V> lastNamesTree = (BRTree<K, V>) data.triggerSearch((K) initial).getValue();
    	
    	people = (ArrayList<Person>) lastNamesTree.searchAllCodes(code);
    	
    	obs.addAll(people);
    	
    	dataTable.refresh();
    }

    @FXML
    void searchCompleteName(ActionEvent event) {
    	dataTable.getItems().clear();
    	
    	if(data.filt!=1)
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
    
    @FXML
    void volver(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/PrincipalMenu.fxml"));
		loader.setController(new PrincipalMenu<K, V>(this.data));
		Parent parent = (Parent) loader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setTitle("AVL Tree Search");
		stage.setScene(scene);
		stage.show();
		
		Stage stage1 = (Stage) volverBTN.getScene().getWindow();
	    stage1.close();
    }
    

}