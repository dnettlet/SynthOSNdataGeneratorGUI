package genDataNOapplication.view;

import genDataNOapplication.GenDataNO;
import genDataNOapplication.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HomePageController {
	
	//Reference to the main application
	private Main main;
	
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
	
	@FXML
	private void handleStartApplication() {
		//System.out.println("Hola");
		GenDataNO.main();
		
		Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Run Completed");
    	alert.setHeaderText("The program has finished running");
    	alert.setContentText("Execution complete. To see the results check the output files located in the directory /resources/files. "
    			+ "\n You can run it gain by pressing the \"Start Application\" Button.");
    	alert.showAndWait();
	}

}
