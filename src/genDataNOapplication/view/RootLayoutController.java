package genDataNOapplication.view;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class RootLayoutController {
	
    @FXML
    private void handleExit() {
        System.exit(0);
    }
	
    @FXML
    private void handleAbout() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("About the program");
    	alert.setHeaderText("About the Synthetic Data Generator");
    	alert.setContentText("A synthetic data generator for Online Social Networks Graphs developed by David Nettleton. "
    			+ "\n GUI developed by Marc Canal as a TFG.");
    	alert.showAndWait();
    }
    
    @FXML
    private void handleLicense() {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("License");
    	alert.setHeaderText("GNU General Public License v3.0");
    	alert.setContentText("This program is distributed under the GNU General Public License v3.0" + 
    			"\n Permissions of this strong copyleft license are conditioned on making available complete source code of licensed works and modifications," + 
    			"which include larger works using a licensed work, under the same license." +
    			"\n Copyright and license notices must be preserved. Contributors provide an express grant of patent rights.");
    	alert.showAndWait();
    }
    
    @FXML
    private void handleDocumentation() {
    	File file = new File("./README and USER MANUAL.pdf");
        try {
			Desktop.getDesktop().open(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }



}
    
    
