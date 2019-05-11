package genDataNOapplication.view;

import java.io.File;
import java.util.Optional;

import genDataNOapplication.Main;
import genDataNOapplication.model.ConfigurationModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

//Controller class for the Settings Page
public class InputFileSettingsController {
	
	//Reference to the main application
	private Main main;
	
	//Configuration
	protected ConfigurationModel configuration;
	
	//Buttons
	@FXML
	Button inputFilesButtonTab;
	@FXML
	Button userParametersButtonTab;
	@FXML
	Button profilesButtonTab;
	@FXML
	Button communitiesButtonTab;
	@FXML
	Button advancedButtonTab;
	@FXML
	Button runButtonTab;
	@FXML
	Button outputFilesButtonTab;
	@FXML
	Button browseInFile1Button;
	@FXML
	Button browseInFile2Button;
	@FXML
	Button helpButton;
	@FXML
	Button resetButton;
	@FXML
	Button backButton;
	@FXML
	Button nextButton;
	
	//TextFields
	@FXML
	TextField inputFile1Name;
	@FXML
	TextField inputFile2Name;
	
	//Class constructor
	public InputFileSettingsController() {
		
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
		inputFile1Name.setText(configuration.getInputFile1());
		inputFile2Name.setText(configuration.getInputFile2());
	}
	
	//Browse button Handlers
	public void handleInFile1BrowseButton() { handleBrowseButton("inputFile1"); }
	public void handleInFile2BrowseButton() { handleBrowseButton("inputFile2"); }

	//When browse button pressed, a file chooser is opened and when a file is selected its path is writen in the textfield
	private void handleBrowseButton(String field) {
		
        FileChooser fileChooser = new FileChooser();
        
        File initialDirectory = new File("./resources/Input_files");
        
        fileChooser.setInitialDirectory(initialDirectory);

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Archivo de valores separados por comas de Microsoft Excel (.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null) {
            System.out.println(file.getName());
            String filePath = file.getPath();
            //String[] parts = filePath.split("(?<=\\\\)");
           // String filename = parts[parts.length - 1];
            String filename = filePath;
            
            switch(field) {
            case "inputFile1":
            	inputFile1Name.setText(filename);
            	break;
            case "inputFile2":
            	inputFile2Name.setText(filename);
            	break;
            }
        }
	}
	
	private void save() {	
		if(!inputFile1Name.getText().isEmpty()) { configuration.setInputFile1(inputFile1Name.getText());	}
		if(!inputFile2Name.getText().isEmpty()) { configuration.setInputFile2(inputFile2Name.getText()); }
		main.setConfiguration(configuration);
	}
	
	//Resets the default configuration. Asks user confirmation.
	@FXML
	public void handleResetButton() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Reset Default");
		alert.setHeaderText("Reset parameters to default");
		alert.setContentText("Are you sure you want to reset all settings parameters to the default configuration?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			inputFile1Name.clear();
			inputFile2Name.clear();
		}

	}
	
	@FXML
	public void handleHelpButton() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Help");
		alert.setHeaderText("Input Files");
		alert.setContentText("File 1 contain a graph, defined as edges, one per row, in the format id1 id2, i1 id3,"
				+ " .. etc. where id is a node id and ‘id1 id2’ indicates there is an edge (link) between these two nodes. \n \n"
				+ "File 2 contains the community labels for each of the users, that is, each user has an associated community "
				+ "label in the format id mod, where id is the user and mod is the community id (usually, 1,2,3, etc.).");

		alert.showAndWait();
	}
	
	//Handles back button. If something has been modified asks for user confirmation.
	@FXML
	public void handleBackButton() {
		save();
		main.showHomePage();
	}
	
	@FXML
	public void handleCommunitiesButtonTab() {
		save();
		main.showCommunitiesSettingsPage();
	}
	
	@FXML
	public void handleUserParametersButtonTab() {
		save();
		main.showUserAttributesPage();
	}
	
	@FXML
	public void handleProfilesButtonTab() {
		save();
		main.showProfilesPage();
	}
	
	@FXML
	public void handleAdvancedButtonTab() {
		save();
		main.showAdvancedSettingsPage();
	}
	
	@FXML
	public void handleOutputFilesButtonTab() {
		save();
		main.showOutputFileSettingsPage();
	}
	
	@FXML
	public void handleRunButtonTab() {
		save();
		main.showRunPage();
	}

	

}
