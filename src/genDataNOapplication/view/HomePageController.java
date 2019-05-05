package genDataNOapplication.view;

import java.io.File;

import genDataNOapplication.Controller;
import genDataNOapplication.Main;
import genDataNOapplication.Utils.FileUtils;
import genDataNOapplication.model.ConfigurationModel;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;

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
	
	//Progress Indicator
	@FXML
	ProgressIndicator progressIndicator;
	
	private Controller programExecution;
	
	
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
		startApplication(configuration);
	}
	
	//Stops the execution and re enables the buttons
	@FXML
	private void handleCancelButton() {
		startButton.setDisable(false);
		programExecution.cancel(true);
		progressIndicator.setVisible(false);
		cancelButton.setVisible(false);
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
	
	//Given a ConfigurationModel, starts the program with this configuration. 
	public void startApplication(ConfigurationModel configuration) {
		try{
			startButton.setDisable(true);
			loadFromFileButton.setDisable(true);
			changeSettingsButton.setDisable(true);
		
			cancelButton.setVisible(true);
			progressIndicator.setVisible(true);
			progressIndicator.setProgress(-1);
			progressIndicator.progressProperty().unbind();
			
	
			
			programExecution = new Controller();
			programExecution.setMainApp(main);
			programExecution.setConfiguration(configuration);
			
			// When completed tasks
						programExecution.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, 
				                new EventHandler<WorkerStateEvent>() {
				
				                    @Override
				                    public void handle(WorkerStateEvent t) {
				                    	if(!programExecution.isCancelled()) {
				                    	progressIndicator.setVisible(false);
				                		Alert alert = new Alert(AlertType.INFORMATION);
				                    	alert.setTitle("Run Completed");
				                    	alert.setHeaderText("The program has finished running");
				                    	alert.setContentText("Execution complete. To see the results check the output files located in the directory /resources/files. "
				                    			+ "\n You can run it gain by pressing the \"Start Application\" Button.");
				                    	alert.showAndWait();
				                    	
				                    	startButton.setDisable(false);
				            			loadFromFileButton.setDisable(false);
				            			changeSettingsButton.setDisable(false);
				                    	cancelButton.setVisible(false);
				                    	}
				                        
				                    }
				                });
						
						programExecution.addEventHandler(WorkerStateEvent.WORKER_STATE_CANCELLED, 
				                new EventHandler<WorkerStateEvent>() {
				
				                    @Override
				                    public void handle(WorkerStateEvent t) {
				                    	System.out.println("An error occurred");
				                        
				                    }
				                });

				        // Start the Task.
				        new Thread(programExecution).start();
		}catch(Throwable t) {
			System.out.println("Un error");
		}
	}
	

}
