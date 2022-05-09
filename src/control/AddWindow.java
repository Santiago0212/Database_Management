package control;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;
import model.AVLTree;
import model.BRTree;
import model.Person;
import model.Sex;

public class AddWindow <K extends Comparable<K>,V>implements Initializable{

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
    
    private AVLTree<K, V> nameData;
    
    public AddWindow(AVLTree<K, V> nameData) {
    	this.nameData = nameData;
    }

    @FXML
    void done(ActionEvent event) throws IOException {
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
    	
    	int code=Main.getCodeAux();
    
    	
    	
    	
    	try {
    		Person person = new Person(code+"", name, lastName, sex, date, height, nationality);
    		Main.addPerson(code, person);
    	}catch(Exception e){
    		Alert alert = new Alert(Alert.AlertType.ERROR);
    	    alert.setHeaderText(null);
    	    alert.setTitle("Error");
    	    alert.setContentText("No se puede generar sin rellanar todos los datos.");
    	    alert.showAndWait();
    	}finally {
    		Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Listo");
            alert.setTitle("Info");
            alert.setContentText("Persona agregada exitosamente.");
            alert.showAndWait();
    	}
    	Main.setCodeAux(code+1);
    	
    	cerrar();
    }
    
    public void cerrar() throws IOException {
    	FXMLLoader loader = new FXMLLoader(Main.class.getResource("../ui/PrincipalMenu.fxml"));
		loader.setController(new PrincipalMenu<K, V>(this.nameData));
		Parent parent = (Parent) loader.load();
		Stage stage = new Stage();
		Scene scene = new Scene(parent);
		stage.setTitle("AVL Tree Search");
		stage.setScene(scene);
		stage.show();
		
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
	}

}
