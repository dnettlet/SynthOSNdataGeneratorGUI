package genDataNOapplication.view;

import genDataNOapplication.Main;
import genDataNOapplication.configuration.AttributeModel;
import genDataNOapplication.configuration.ConfigurationModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class UserAttributesController {
	
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
	Button backButton;
	@FXML
	Button addButton;
	
	//Anchor pane
	@FXML
	GridPane attributesSection;

	//Class Constructor
	public UserAttributesController() {
		
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
	
	//Is called to set a specific configuration
	public void setConfiguration(ConfigurationModel configuration) {
		this.configuration = configuration;
	}
	
	@FXML
	public void handleAddButton() {
	    AttributeModel attribute = new AttributeModel();
	    AttributeModel modifiedAttribute = main.showAttributeEditDialog(attribute);
	    if (modifiedAttribute  != null) {
	        System.out.println("Name: " + modifiedAttribute.getName() + "\n Description: " + modifiedAttribute.getDescription());
	    }
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
	public void handleCommunitiesButtonTab() {
		main.showCommunitiesSettingsPage();
	}
	
	

}
