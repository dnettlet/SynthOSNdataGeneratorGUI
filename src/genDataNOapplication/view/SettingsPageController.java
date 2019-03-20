package genDataNOapplication.view;

import java.io.File;
import java.util.Optional;

import genDataNOapplication.Main;
import genDataNOapplication.configuration.ConfigurationModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

//Controller class for the Settings Page
public class SettingsPageController {
	
	//Reference to the main application
	private Main main;
	
	//Configuration
	protected ConfigurationModel configuration;
	
	//Buttons
	@FXML
	Button browseInFile1Button;
	@FXML
	Button browseInFile2Button;
	@FXML
	Button browseOutFileButton;
	@FXML
	Button brwoseOutgFileButton;
	@FXML
	Button browseOut1FileButton;
	@FXML 
	Button browseOut2FileButton;
	@FXML
	Button saveRunButton;
	@FXML
	Button resetButton;
	@FXML
	Button backButton;
	@FXML
	Button advancedSettingsButton;
	
	//TextFields
	@FXML
	TextField inputFile1Name;
	@FXML
	TextField inputFile2Name;
	@FXML
	TextField outFileName;
	@FXML
	TextField outgFileName;
	@FXML
	TextField out1FileName;
	@FXML
	TextField out2FileName;
	
	//Class constructor
	public SettingsPageController() {
		
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
	
	//Browse button Handlers
	public void handleInFile1BrowseButton() { handleBrowseButton("inputFile1"); }
	public void handleInFile2BrowseButton() { handleBrowseButton("inputFile2"); }
	public void handleOutFileBrowseButton() { handleBrowseButton("outFile"); }
	public void handleOutgFileBrowseButton() { handleBrowseButton("outgFile"); }
	public void handleOut1FileBrowseButton() { handleBrowseButton("out1File"); }
	public void handleOut2FileBrowseButton() { handleBrowseButton("out2File"); }

	//When browse button pressed, a file chooser is opened and when a file is selected its path is writen in the textfield
	private void handleBrowseButton(String field) {
		
        FileChooser fileChooser = new FileChooser();
        
        File initialDirectory = new File("./resources");
        
        fileChooser.setInitialDirectory(initialDirectory);

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Archivo de valores separados por comas de Microsoft Excel (.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null) {
            System.out.println(file.getName());
            String filename = file.getPath();
            
            switch(field) {
            case "inputFile1":
            	inputFile1Name.setText(filename);
            	break;
            case "inputFile2":
            	inputFile2Name.setText(filename);
            	break;
            case "outFile":
            	outFileName.setText(filename);
            	break;
            case "outgFile":
            	outgFileName.setText(filename);
            	break;
            case "out1File":
            	out1FileName.setText(filename);
            	break;
            case "out2File":
            	out2FileName.setText(filename);
            	break;
            }
            
            
        }

	}
	
	//Saves the settings to a instance of ConfigurationModel. Then runs the program 
	@FXML
	public void handleSaveRunButton() {
		//Save
		save();
		
		//Run
		main.runCustomSettings();
	}
	
	private void save() {	
		if(!inputFile1Name.getText().isEmpty()) { configuration.setInputFile1(inputFile1Name.getText());	}
		if(!inputFile2Name.getText().isEmpty()) { configuration.setInputFile2(inputFile2Name.getText()); }
		if(!outFileName.getText().isEmpty()) { configuration.setOutFile(outFileName.getText()); }
		if(!outgFileName.getText().isEmpty()) { configuration.setOutgFile(outgFileName.getText()); }
		if(!out1FileName.getText().isEmpty()) { configuration.setOut1File(out1FileName.getText()); }
		if(!out2FileName.getText().isEmpty()) { configuration.setOut2File(out2FileName.getText()); }
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
			outFileName.clear();
			outgFileName.clear();
			out1FileName.clear();
			out2FileName.clear();
		}

	}
	
	//Handles back button. If something has been modified asks for user confirmation.
	@FXML
	public void handleBackButton() {
		if((inputFile1Name.getText().isEmpty() && inputFile2Name.getText().isEmpty() && outFileName.getText().isEmpty()) &&
		outgFileName.getText().isEmpty() && out1FileName.getText().isEmpty() && out2FileName.getText().isEmpty()) {
			main.showHomePage();
		}else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Return Home");
			alert.setHeaderText("Return to Home Screen");
			alert.setContentText("Are you sure you want to return to the previous page? \n All the changes will be lost.");
			
			Optional<ButtonType> result = alert.showAndWait();
			if(result.get() == ButtonType.OK) {
				main.showHomePage();
			}
		}
		
	}
	
	@FXML
	public void handleAdvancedSettingsButton() {
		save();
		main.showCommunitiesSettingsPage();
	}
	

}
