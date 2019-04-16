package genDataNOapplication.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import genDataNOapplication.Main;
import genDataNOapplication.model.AttributeModel;
import genDataNOapplication.model.ConfigurationModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

public class ProfilesPageController {
	
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
	Button profilesButtonTab;
	@FXML
	Button communitiesButtonTab;
	@FXML
	Button advancedButtonTab;
	@FXML
	Button resetButton;
	@FXML
	Button backButton;
	@FXML
	Button nextButton;
	
	//Gridpane
	//@FXML
	//GridPane profilesGridPane;
	
	@FXML
	AnchorPane profilesAnchorPane;
	
	
	//Class constructor
	public ProfilesPageController() {
		
	}
	
	//Initializes the controller class. This method is automatically called
    // after the fxml file has been loaded.
	@FXML
	private void initialize() {
		
	}
	@FXML
	public void loadProfileCards() {
		//int numAttributes = configuration.getUserAttrributesList().size();
		GridPane profilesGridPane = new GridPane();
		profilesGridPane.setHgap(20);
		profilesGridPane.setVgap(25);
		profilesGridPane.setPrefWidth(970);
		int col = 0; int row = 0;
		for(int i = 0; i < 10; i++) {
			String title = "Profile " + i;
			GridPane profileAttr = new GridPane();
			int col1 = 0; int row1 = 0;
			for(AttributeModel attribute : configuration.getUserAttrributesList()) {
				ChoiceBox<String> attributeSelection = new ChoiceBox();
				List<String> options = new ArrayList<String>();
				for(Pair<String, Double> param : attribute.getParameterList()) {
					options.add(param.getKey());
				}
				attributeSelection.getItems().addAll(options);
				if(col1 < 3) {
				profileAttr.add(attributeSelection, row1, col1);
				col1++;
				}else {
					col1 = 0;
					row1++;
					profileAttr.add(attributeSelection, row1, col1);
					col1++;
				}
			}
			TitledPane profileCard = new TitledPane();
			profileCard.setText(title);
			profileCard.setCollapsible(false);
			profileCard.setContent(profileAttr);
			if(col < 2) {
				profilesGridPane.add(profileCard, col, row);
				col++;
			}else {
				col = 0;
				row++;
				profilesGridPane.add(profileCard, col, row);
				col++;
			}
			
		}
		profilesAnchorPane.getChildren().add(profilesGridPane);
	}
	
	//Is called by the main application to give a reference back to itself.
	public void setMainApp(Main main) {
		this.main = main;
	}
	
	//Is called to set a specific configuration
	public void setConfiguration(ConfigurationModel configuration) {
		this.configuration = configuration;
		loadProfileCards();
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
		main.setConfiguration(configuration);
		main.showSettingsPage();
	}
	
	
	@FXML
	public void handleCommunitiesButtonTab() {
		main.setConfiguration(configuration);
		main.showCommunitiesSettingsPage();
	}
	
	@FXML
	public void handleUserParametersButtonTab() {
		main.setConfiguration(configuration);
		main.showUserAttributesPage();
	}
	
	@FXML
	public void handleAdvancedButtonTab() {
		main.setConfiguration(configuration);
		main.showAdvancedSettingsPage();
	}
	
	

}

