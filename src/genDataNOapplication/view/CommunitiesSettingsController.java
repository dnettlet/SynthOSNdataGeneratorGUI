package genDataNOapplication.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import genDataNOapplication.Main;
import genDataNOapplication.model.ConfigurationModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.Pair;
import javafx.scene.control.Alert.AlertType;
//Class that controlls the behaviour of the Communities Settings Page
public class CommunitiesSettingsController {
	
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
	Button communityAssignmentButton;
	@FXML
	Button profileFrequencyButton;
	@FXML
	Button helpButton;
	
	//Spinners
	@FXML
	Spinner<Integer> numCommunitiesSpinner;
	@FXML
	Spinner<Integer> seedSizeSpinner;
	
	//Class constructor
	public CommunitiesSettingsController() {
		
	}
	
	//Initializes the controller class. This method is automatically called
    // after the fxml file has been loaded.
	@FXML
	private void initialize() {
		numCommunitiesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
		numCommunitiesSpinner.getValueFactory().setValue(10);
		seedSizeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 9999));
		seedSizeSpinner.getValueFactory().setValue(110);
		
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
	public void handleCommunityAssignmentButton() {
		configuration.setProfileCommunityAssaignment(main.showCommunityAssaignmentDialog());
	}
	
	@FXML
	public void handleProfileFrequencyButton() {
		int[] frequencies = main.showProfileFreqDialog();
		int count = 0;
		List<Pair<List<Integer>, Integer>> profileList = configuration.getProfileList();
		List<Pair<List<Integer>, Integer>> updatedProfileList = new ArrayList<Pair<List<Integer>, Integer>>();
		for(Pair<List<Integer>, Integer> currentProfile : profileList) {
			List<Integer> paramList = currentProfile.getKey();
			Pair<List<Integer>, Integer> updatedProfile = new Pair<List<Integer>, Integer>(paramList, frequencies[count]);
			updatedProfileList.add(updatedProfile);
			count++;
			
		}
		configuration.setProfileList(updatedProfileList);
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
			configuration.setNumCommunities(10);
			configuration.setSeedSize(110);
			seedSizeSpinner.getValueFactory().setValue(110);
		}
	}
	
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
	public void handleFilesButtonTab() {
		//configuration.setNumCommunities(numCommunitiesSpinner.getValue());
		//configuration.setSeedSize(seedSizeSpinner.getValue());
		//main.setConfiguration(configuration);
		main.showSettingsPage();
	}
	
	@FXML
	public void handleAdvancedButtonTab() {
		main.showAdvancedSettingsPage();
	}
	
	@FXML
	public void handleUserParametersButtonTab() {
		main.showUserAttributesPage();
	}

	
	

}
