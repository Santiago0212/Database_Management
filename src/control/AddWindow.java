package control;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class AddWindow implements Initializable{

    @FXML
    private Button doneBTN;

    @FXML
    private TextField nameTF;

    @FXML
    private TextField lastaNameTF;

    @FXML
    private TextField heightTF;

    @FXML
    private ChoiceBox<?> sexCB;

    @FXML
    private DatePicker dateBirthDP;

    @FXML
    private ChoiceBox<?> nationalityCB;

    @FXML
    void done(ActionEvent event) {

    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
