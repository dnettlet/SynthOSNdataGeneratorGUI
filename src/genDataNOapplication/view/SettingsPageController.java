package genDataNOapplication.view;

import java.io.File;

import genDataNOapplication.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class SettingsPageController {
	
	//Reference to the main application
	private Main main;
	
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
	
	
	public void handleInFile1BrowseButton() { handleBrowseButton("inputFile1"); }
	public void handleInFile2BrowseButton() { handleBrowseButton("inputFile2"); }
	public void handleOutFileBrowseButton() { handleBrowseButton("outFile"); }
	public void handleOutgFileBrowseButton() { handleBrowseButton("outgFile"); }
	public void handleOut1FileBrowseButton() { handleBrowseButton("out1File"); }
	public void handleOut2FileBrowseButton() { handleBrowseButton("out2File"); }

	
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
            String filename = file.getName();
            
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

}
