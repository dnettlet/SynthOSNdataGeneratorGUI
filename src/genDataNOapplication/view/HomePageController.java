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
	
	@FXML
	private void handleCancelButton() {
		startButton.setDisable(false);
		programExecution.cancel(true);
		progressIndicator.setVisible(false);
		cancelButton.setVisible(false);
	}
	
	@FXML
	private void handleChangeSettingsButton() {
		main.showSettingsPage();
	}
	
	@FXML
	private void handleLoadFromFileButton() {
		System.out.println("Loading File");
		
        FileChooser fileChooser = new FileChooser();
        
        File initialDirectory = new File("./config");
        fileChooser.setInitialDirectory(initialDirectory);

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "Archivo de origen XML (.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(main.getPrimaryStage());
		FileUtils.load(file);
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
