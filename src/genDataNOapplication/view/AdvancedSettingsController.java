package genDataNOapplication.view;

import java.util.Optional;

import genDataNOapplication.Main;
import genDataNOapplication.configuration.ConfigurationModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

public class AdvancedSettingsController {
	
	//Reference to the main application
	private Main main;
	
	//Configuration
	protected ConfigurationModel configuration;
	
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
	Button distanceThresholdButton;
	
	//Radio buttons
	@FXML
	RadioButton lowRandomness;
	@FXML
	RadioButton mediumRandomness;
	@FXML
	RadioButton highRandomness;
	ToggleGroup randomness = new ToggleGroup();
	
	
	//Class constructor
	public AdvancedSettingsController() {
		
	}
	
	//Initializes the controller class. This method is automatically called
    // after the fxml file has been loaded.
	@FXML
	private void initialize() {
		lowRandomness.setToggleGroup(randomness);
		lowRandomness.setSelected(true);
		mediumRandomness.setToggleGroup(randomness);
		highRandomness.setToggleGroup(randomness);
		
		randomness.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
	           @Override
	           public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
	               // Has selection.
	               if (randomness.getSelectedToggle() != null) {
	                   RadioButton button = (RadioButton) randomness.getSelectedToggle();
	                   //System.out.println("Button: " + button.getText());
	                   switch(button.getText()) {
	                   case "Low":
	                	   configuration.setRandomness(0);
	                	   break;
	                   case "Medium":
	                	   configuration.setRandomness(2);
	                	   break;
	                   case "High":
	                	   configuration.setRandomness(4);
	                	   break;
	                   }
	               }
	           }
	       });
		
	}
	
	//Is called by the main application to give a reference back to itself.
	public void setMainApp(Main main) {
		this.main = main;
	}
	
	//Is called to set a specific configuration
	public void setConfiguration(ConfigurationModel configuration) {
		this.configuration = configuration;
	}
	
	@FXML
	public void handleSaveButton() {
		main.setConfiguration(configuration);
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
			configuration.setRandomness(0);
			lowRandomness.setSelected(true);
		}
	}
	

	
	@FXML
	public void handleFilesButtonTab() {
		main.showSettingsPage();
	}
	
	@FXML
	public void handleCommunitiesButtonTab() {
		main.showCommunitiesSettingsPage();
	}
	
	

}

