package genDataNOapplication.view;

import java.util.ArrayList;
import java.util.List;

import genDataNOapplication.model.AttributeModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class CommunityAssaignmentController {
	
	private Stage dialogStage;
	private boolean okClicked = false;
	
	private ObservableList<String> choices;
	
	//Choice boxes
	@FXML
	ChoiceBox<String> com0;
	@FXML
	ChoiceBox<String> com1;
	@FXML
	ChoiceBox<String> com2;
	@FXML
	ChoiceBox<String> com3;
	@FXML
	ChoiceBox<String> com4;
	@FXML
	ChoiceBox<String> com5;
	@FXML
	ChoiceBox<String> com6;
	@FXML
	ChoiceBox<String> com7;
	@FXML
	ChoiceBox<String> com8;
	@FXML
	ChoiceBox<String> com9;
	
	//Buttons
	@FXML
	Button cancelButton;
	@FXML
	Button saveButton;
	
	

	//Class constctor
	public CommunityAssaignmentController() {

	}
	//Initializes the controller class. This method is automatically called
    // after the fxml file has been loaded.
    @FXML
    private void initialize() {
    	choices = FXCollections.observableArrayList();
    	choices.removeAll(choices);
    	String p0 = "Profile 0";
    	String p1 = "Profile 1";
    	String p2 = "Profile 2";
    	String p3 = "Profile 3";
    	String p4 = "Profile 4";
    	String p5 = "Profile 5";
    	String p6 = "Profile 6";
    	String p7 = "Profile 7";
    	String p8 = "Profile 8";
    	String p9 = "Profile 9";
    	choices.addAll(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    	com0.getItems().addAll(choices);
    	com1.getItems().addAll(choices);
    	com2.getItems().addAll(choices);
    	com3.getItems().addAll(choices);
    	com4.getItems().addAll(choices);
    	com5.getItems().addAll(choices);
    	com6.getItems().addAll(choices);
    	com7.getItems().addAll(choices);
    	com8.getItems().addAll(choices);
    	com9.getItems().addAll(choices);
    }
    
    
    //Setters
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
 
    
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public ObservableList<String> isOkClicked() {
        if(okClicked) {
        	return choices;
        }
        else {
        	return null;
        }
    }

    
    /**
     * Called when the user clicks ok.
     */

	@FXML
    private void handleOk() {

    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

}
