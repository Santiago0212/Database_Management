package control;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.AVLNode;
import model.AVLTree;
import model.BRTree;
import model.Person;
import model.Sex;

public class Modify <K extends Comparable<K>,V>implements Initializable{

    @FXML
    private Button doneBTN;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField lastaNameTF;

    @FXML
    private TextField heightTF;

    @FXML
    private ChoiceBox<String> sexCB;

    @FXML
    private DatePicker dateBirthDP;

    @FXML
    private ChoiceBox<String> nationalityCB;
    
    private Person stClicked;
    
    private AVLTree<K, V> nameData;
    
    private int code;
    
    private Character initial;

    public Modify(Person stClicked,AVLTree<K, V> nameData) {
    	this.stClicked=stClicked;
    	this.nameData = nameData;
	}

	@FXML
    void done(ActionEvent event) {
		String name=nameTF.getText().toUpperCase();
    	String lastName=lastaNameTF.getText().toUpperCase();
    	String sexString=sexCB.getValue();
    	LocalDate dateBirht=dateBirthDP.getValue();
    	String heightString=heightTF.getText();
    	String nationality=nationalityCB.getValue();
    	
    	double height=Double.parseDouble(heightString);
    	
    	Sex sex = null;
		if(sexString.equalsIgnoreCase("BOY")) {
			sex = Sex.MALE;
		} else if(sexString.equalsIgnoreCase("GIRL")) {
			sex = Sex.FEMALE;
		}
		
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate localDate = LocalDate.of(2016, 8, 19);
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
    	
    	
    	
    	
    	//BRTree<Integer, Person> addingTree = (BRTree<Integer, Person>) nameData.triggerSearch((K) initial).getValue();
    	
    	
    	//Person person=addingTree.search(code).getValue();
    	stClicked.setName(name);
    	stClicked.setLastname(lastName);
    	stClicked.setHeight(height);
    	stClicked.setDate(date);
    	stClicked.setNationality(nationality);
    	stClicked.setSex(sex);
    	
    	
    	
    	//Person person = new Person(code+"", name, lastName, sex, date, height, nationality);
    	
    	

		Stage stage1 = (Stage) doneBTN.getScene().getWindow();
	    stage1.close();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		String[ ] paises = new String[] {"Canada","Estados Unidos","Mexico","Belice","Costa rica","El Salvador",
	    		"Guatemala","Honduras","Nicaragua","Panamá","Argentina","Bolivia","Brasil","Chile","Colombia","Ecuador",
	    		"Paraguay","Peru","Surinam","Trinidad y Tobago","Uruguay","Venezuela","Antigua y Barbuda","Bahamas","Barbados",
	    		"Cuba","Dominica","Granada","Guyana","Haiti","Jamaica","República Dominicana","San Cristóbal y Nieves","San Vicente y las Granadinas","Santa Lucia"};
		
		sexCB.getItems().add("BOY");
		sexCB.getItems().add("GIRL");
		
		String pais="";
		for(int i=0;i<paises.length;i++) {
			pais=paises[i];
			nationalityCB.getItems().add(pais);
		}
		
		nameTF.setText(stClicked.getName());
		initial = stClicked.getName().charAt(0);
		
		
		
		lastaNameTF.setText(stClicked.getLastname());
		heightTF.setText(stClicked.getHeight()+"");
		
		
		String sexString="";
		if(stClicked.getSex().equals(Sex.MALE)) {
			sexString="BOY";
		}else if(stClicked.getSex().equals(Sex.FEMALE)) {
			sexString="GIRL";
		}
		sexCB.setValue(sexString);
		
		LocalDate localdate = stClicked.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		dateBirthDP.setValue(localdate);

		nationalityCB.setValue(stClicked.getNationality());
		
		code=Integer.parseInt(stClicked.getCode());

		
	}

}