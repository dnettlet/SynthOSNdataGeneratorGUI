package genDataNOapplication.view;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import genDataNOapplication.Main;
import genDataNOapplication.Utils.FileUtils;
import genDataNOapplication.model.AttributeModel;
import genDataNOapplication.model.ConfigurationModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//Class that controlls the behaviour of the Communities Settings Page
public class CommunitiesSettingsController {
	
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
	Button communityAssignmentButton;
	@FXML
	Button profileFrequencyButton;
	@FXML
	Button nextButton;
	
	//Spinners
	@FXML
	Spinner<Integer> numCommunitiesSpinner;

	
	//Class constructor
	public CommunitiesSettingsController() {
		
	}
	
	//Initializes the controller class. This method is automatically called
    // after the fxml file has been loaded.
	@FXML
	private void initialize() {
		numCommunitiesSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10));
		numCommunitiesSpinner.getValueFactory().setValue(10);
	}	
	
	//Is called by the main application to give a reference back to itself.
	public void setMainApp(Main main) {
		this.main = main;
	}
	
	//Is called to set a specific configuration
	public void setConfiguration(ConfigurationModel configuration) {
		this.configuration = configuration;
		numCommunitiesSpinner.getValueFactory().setValue(configuration.getNumCommunities());

	}
	
	@FXML
	public void handleCommunityAssignmentButton() {
		int[] commAssaign = main.showCommunityAssaignmentDialog();
		if(commAssaign != null) {
			configuration.setProfileCommunityAssaignment(commAssaign);
		}
	}
	
	@FXML
	public void handleProfileFrequencyButton() {
		int[] frequencies = main.showProfileFreqDialog();
		if(frequencies != null) {
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

	}
	
	//Restores the page values to the default ones
	@FXML
	public void handleResetButton() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Reset Default");
		alert.setHeaderText("Reset parameters to default");
		alert.setContentText("Are you sure you want to reset all settings parameters to the default configuration?");
		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
		Image icon = new Image("file:./resources/icons/confirmation_icon.png");
		stage.getIcons().add(icon);		
		alert.setGraphic(new ImageView(icon));
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			configuration.setNumCommunities(10);
			configuration.setSeedSize(110);

		}
	}
	
	@FXML
	public void handleInputFilesButtonTab() {
		configuration.setNumCommunities(numCommunitiesSpinner.getValue());
		main.setConfiguration(configuration);
		main.showSettingsPage();
	}
	
	@FXML
	public void handleAdvancedButtonTab() {
		configuration.setNumCommunities(numCommunitiesSpinner.getValue());
		main.setConfiguration(configuration);
		main.showAdvancedSettingsPage();
	}
	
	@FXML
	public void handleUserParametersButtonTab() {
		configuration.setNumCommunities(numCommunitiesSpinner.getValue());
		main.setConfiguration(configuration);
		main.showUserAttributesPage();
	}
	
	@FXML
	public void handleProfilesButtonTab() {
		configuration.setNumCommunities(numCommunitiesSpinner.getValue());
		main.setConfiguration(configuration);
		main.showProfilesPage();
	}
	
	@FXML
	public void handleOutputFilesButtonTab() {
		configuration.setNumCommunities(numCommunitiesSpinner.getValue());
		main.setConfiguration(configuration);
		main.showOutputFileSettingsPage();
	}
	
	@FXML
	public void handleRunButtonTab() {
		configuration.setNumCommunities(numCommunitiesSpinner.getValue());
		main.setConfiguration(configuration);
		main.showRunPage();
	}
	
	
}
