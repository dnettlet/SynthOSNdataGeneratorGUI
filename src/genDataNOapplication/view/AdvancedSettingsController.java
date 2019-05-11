package genDataNOapplication.view;

import java.util.Optional;

import genDataNOapplication.Main;
import genDataNOapplication.model.ConfigurationModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
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
	Button InputFilesButtonTab;
	@FXML
	Button userParametersButtonTab;
	@FXML
	Button profilesButtonTab;
	@FXML
	Button communitiesButtonTab;
	@FXML
	Button outputFilesButtonTab;
	@FXML
	Button advancedButtonTab;
	@FXML
	Button runButtonTab;
	@FXML
	Button resetButton;
	@FXML
	Button backButton;
	@FXML
	Button distanceThresholdButton;
	@FXML
	Button saveRunButton;
	@FXML
	Button helpButton;
	@FXML
	Button nextButton;
	
	//Radio buttons
	@FXML
	RadioButton lowRandomness;
	@FXML
	RadioButton mediumRandomness;
	@FXML
	RadioButton highRandomness;
	ToggleGroup randomness = new ToggleGroup();
	
	//Spinners
	@FXML
	Spinner<Integer> seedSizeSpinner;
	
	
	//Class constructor
	public AdvancedSettingsController() {
		
	}
	
	//Initializes the controller class. This method is automatically called
    // after the fxml file has been loaded.
	@FXML
	private void initialize() {
		seedSizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9999));
		seedSizeSpinner.getValueFactory().setValue(110);
		
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
		seedSizeSpinner.getValueFactory().setValue(configuration.getSeedSize());
	}
	
	@FXML
	public void handleSaveRunButton() {
		main.setConfiguration(configuration);
		main.runCustomSettings();
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
			seedSizeSpinner.getValueFactory().setValue(110);
		}
	}
	
	//Button that promps a help popup
	@FXML
	public void handleHelpButton() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help");
		alert.setHeaderText("Seed Size");
		alert.setContentText("The seedsize is graph dependent and take into account that the more seeds," + 
							 "the more time it will take to locate them. The values are orientative." +
							 "\n 110 seeds for 1K synth file, 5K seeds for amazon, 12k seeds for youtube and livejournal." +
							 "\n For more information read the User Manual (Menu -> Help -> Documentation)");
		alert.showAndWait();
	}
	

	
	@FXML
	public void handleInputFilesButtonTab() {
		main.setConfiguration(configuration);
		configuration.setSeedSize(seedSizeSpinner.getValue());
		main.showSettingsPage();
	}
	
	@FXML
	public void handleProfilesButtonTab() {
		main.setConfiguration(configuration);
		configuration.setSeedSize(seedSizeSpinner.getValue());
		main.showProfilesPage();
	}
	
	@FXML
	public void handleCommunitiesButtonTab() {
		main.setConfiguration(configuration);
		configuration.setSeedSize(seedSizeSpinner.getValue());
		main.showCommunitiesSettingsPage();
	}
	
	@FXML
	public void handleUserParametersButtonTab() {
		main.setConfiguration(configuration);
		configuration.setSeedSize(seedSizeSpinner.getValue());
		main.showUserAttributesPage();
	}
	
	@FXML
	public void handleOutputFilesButtonTab() {
		main.setConfiguration(configuration);
		configuration.setSeedSize(seedSizeSpinner.getValue());
		main.showOutputFileSettingsPage();
	}
	
	@FXML
	public void handleRunButtonTab() {
		main.setConfiguration(configuration);
		configuration.setSeedSize(seedSizeSpinner.getValue());
		main.showRunPage();
	}
	
	

}

