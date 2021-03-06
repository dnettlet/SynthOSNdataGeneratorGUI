package genDataNOapplication.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import genDataNOapplication.Controller;
import genDataNOapplication.Main;
import genDataNOapplication.model.ConfigurationModel;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class RunPageController {
	
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
		Button outputFilesButtonTab;
		@FXML
		Button advancedButtonTab;
		@FXML
		Button backButton;
		@FXML
		Button runButton;
		@FXML
		Button cancelButton;
		@FXML
		Button homePageButton;
		@FXML
		Button statisticsButton;
		@FXML
		Button openButton;
		
	//Progress bar
		@FXML
		ProgressBar progressIndicator;
		
	private Controller programExecution;
	
	
	public RunPageController() {
		
	}
	
	@FXML
	public void initialize() {
		
	}
	
	//Is called by the main application to give a reference back to itself.
	public void setMainApp(Main main) {
		this.main = main;
	}
	
	//Is called to set a specific configuration
	public void setConfiguration(ConfigurationModel configuration) {
		this.configuration = configuration;

	}
	
	//Stops the execution and re enables the buttons
	@FXML
	private void handleCancelButton() {
		runButton.setDisable(false);
		programExecution.cancel(true);
		progressIndicator.setVisible(false);
		cancelButton.setVisible(false);
	}
	
	@FXML
	public void handleStartApplicationButton() {
		main.setConfiguration(configuration);
		startApplication();
	}
	
	@FXML
	public void handleOpenButton() {
		try {
		File file = new File(configuration.getOutFile());
		String parent = file.getParent();
		File outDirectory = new File(parent);
		Desktop.getDesktop().open((outDirectory));
		} catch (IOException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Given a ConfigurationModel, starts the program with this configuration.
	@FXML
	public void startApplication() {
		try{
			runButton.setDisable(true);
			backButton.setDisable(true);
			inputFilesButtonTab.setDisable(true);
			userParametersButtonTab.setDisable(true);
			profilesButtonTab.setDisable(true);
			communitiesButtonTab.setDisable(true);
			outputFilesButtonTab.setDisable(true);
			advancedButtonTab.setDisable(true);
		
			cancelButton.setVisible(true);
			homePageButton.setVisible(false);
			statisticsButton.setVisible(false);
			openButton.setVisible(false);
			progressIndicator.setVisible(true);
			progressIndicator.setProgress(-1);
			progressIndicator.progressProperty().unbind();
			
	
			
			programExecution = new Controller();
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
				                		Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
				                		Image icon = new Image("file:./resources/icons/info_icon.png");
				                		stage.getIcons().add(icon);		
				                		alert.setGraphic(new ImageView(icon));
				                    	alert.showAndWait();
				                    	
				                    	runButton.setDisable(false);
				            			inputFilesButtonTab.setDisable(false);
				            			userParametersButtonTab.setDisable(false);
				            			profilesButtonTab.setDisable(false);
				            			communitiesButtonTab.setDisable(false);
				            			outputFilesButtonTab.setDisable(false);
				            			advancedButtonTab.setDisable(false);
				                    	cancelButton.setVisible(false);
				                    	statisticsButton.setVisible(true);
				                    	homePageButton.setVisible(true);
				                    	openButton.setVisible(true);
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
	
	@FXML
	public void handleMainPageButton() {
		main.showHomePage();
	}
	
	@FXML
	public void handleInputFilesButtonTab() {
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
	
	@FXML
	public void handleCommunitiesButtonTab() {
		main.showCommunitiesSettingsPage();
	}
	
	@FXML
	public void handleProfilesButtonTab() {
		main.showProfilesPage();
	}
	
	@FXML
	public void handleOutputFilesButtonTab() {
		main.showOutputFileSettingsPage();
	}
	
	@FXML
	public void handleStatisticsButton() {
		main.showStatisticsPage();
	}
	
	

}
