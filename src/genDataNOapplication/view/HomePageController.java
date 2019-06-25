package genDataNOapplication.view;

import java.io.File;

import genDataNOapplication.Main;
import genDataNOapplication.Utils.FileUtils;
import genDataNOapplication.model.ConfigurationModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.scene.control.Button;

//Controller class for the Home Page
public class HomePageController {
	
	
	//Reference to the main application
	private Main main;
	
	//Buttons
	@FXML
	Button startButton;
	@FXML
	Button cancelButton;
	@FXML
	Button changeSettingsButton;
	@FXML
	Button loadFromFileButton;
	
	
	//Class constructor
	public HomePageController() {
		
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
	
	//Sets the configuration parameters to default and launches the application
	@FXML
	private void handleStartApplicationButton() {
		ConfigurationModel configuration = new ConfigurationModel();
		main.setConfiguration(configuration);
		main.runCustomSettings();
	}
	
	//Goes to the settings screen
	@FXML
	private void handleChangeSettingsButton() {
		main.showSettingsPage();
	}
	
	//Opens the select file dialog and imports the configuration from file
	@FXML
	private void handleLoadFromFileButton() {
		
		//Let the user choose a file from the file chooser screen
        FileChooser fileChooser = new FileChooser();
        File initialDirectory = new File("./config");
        fileChooser.setInitialDirectory(initialDirectory);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Archivo de origen XML (.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());
        ConfigurationModel configuration = new ConfigurationModel();
        
        //Executes the import and shows a message depending if the import was successfull or not
		configuration = FileUtils.loadConfig(file, configuration);
		if(configuration == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Importing Configuration");
			alert.setHeaderText("An error occurred while importing the configuration from a file.");
			alert.setContentText("The configuration couldn't be imported. Check that the file " + file.getName()
						+ " exists and is not corrupted. The default configuration will be restored, please try again.");
			alert.showAndWait();
			
		}else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Import Successfull");
			alert.setHeaderText("The configuration has been successfully imported");
			alert.setContentText("You imported the configuration from file " + file.getName() + " . Click Start Application to run with the imported"
					 + "settings or click change settings to edit those settings.");
			alert.showAndWait();
			main.setConfiguration(configuration);
		}
	}	
}
