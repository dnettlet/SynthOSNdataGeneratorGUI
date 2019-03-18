package genDataNOapplication.view;

import java.util.Optional;

import genDataNOapplication.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class AdvancedSettingsController {
	
	//Reference to the main application
	private Main main;
	
	//Buttons
	@FXML
	Button filesButtonTab;
	@FXML
	Button userParametersButtonTab;
	@FXML
	Button communitiesButtonTab;
	@FXML
	Button advancedButtonTab;
	@FXML
	Button saveButton;
	@FXML
	Button resetButton;
	@FXML
	Button backButton;
	@FXML
	Button communityAssignmentButton;
	
	//TextFields
	@FXML
	TextField numCommunitiesTextField;
	
	//Class constructor
	public AdvancedSettingsController() {
		
	}
	
	//Initializes the controller class. This method is automatically called
    // after the fxml file has been loaded.
	@FXML
	private void initialize() {
		
	}
	
	//Is called by the main application to give a reference back to itself.
	public void setMainApp(Main main) {
		this.main = main;
	}
	
	@FXML
	public void handleSaveButton() {
		main.showSettingsPage();
	}
	
	@FXML
	public void handleResetButton() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Reset Default");
		alert.setHeaderText("Reset parameters to default");
		alert.setContentText("Are you sure you want to reset all settings parameters to the default configuration?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){

		}
	}
	
	@FXML
	public void handleFilesButtonTab() {
		main.showSettingsPage();
	}
	
	

}
