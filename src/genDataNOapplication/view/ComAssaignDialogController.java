package genDataNOapplication.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class ComAssaignDialogController {
	
	private Stage dialogStage;
	private boolean okClicked = false;
	
	private ObservableList<String> choiceList;
	private int[] userChoices;
	
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
	public ComAssaignDialogController() {

	}
	//Initializes the controller class. This method is automatically called
    // after the fxml file has been loaded.
    @FXML
    private void initialize() {

    }
    
    public void setup(int[] profileCommAssaign) {
    	choiceList = FXCollections.observableArrayList();
    	userChoices = new int[10];
    	choiceList.removeAll(choiceList);
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
    	choiceList.addAll(p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
    	com0.getItems().addAll(choiceList);
    	com0.getSelectionModel().select(profileCommAssaign[0]);
    	com1.getItems().addAll(choiceList);
    	com1.getSelectionModel().select(profileCommAssaign[1]);
    	com2.getItems().addAll(choiceList);
    	com2.getSelectionModel().select(profileCommAssaign[2]);
    	com3.getItems().addAll(choiceList);
    	com3.getSelectionModel().select(profileCommAssaign[3]);
    	com4.getItems().addAll(choiceList);
    	com4.getSelectionModel().select(profileCommAssaign[4]);
    	com5.getItems().addAll(choiceList);
    	com5.getSelectionModel().select(profileCommAssaign[5]);
    	com6.getItems().addAll(choiceList);
    	com6.getSelectionModel().select(profileCommAssaign[6]);
    	com7.getItems().addAll(choiceList);
    	com7.getSelectionModel().select(profileCommAssaign[7]);
    	com8.getItems().addAll(choiceList);
    	com8.getSelectionModel().select(profileCommAssaign[8]);
    	com9.getItems().addAll(choiceList);
    	com9.getSelectionModel().select(profileCommAssaign[9]);
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
    public int[] isOkClicked() {
        if(okClicked) {
        	return userChoices;
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
		userChoices[0] =  Integer.parseInt(com0.getValue().substring(com0.getValue().length() - 1));
		userChoices[1] =  Integer.parseInt(com1.getValue().substring(com1.getValue().length() - 1));
		userChoices[2] =  Integer.parseInt(com2.getValue().substring(com2.getValue().length() - 1));
		userChoices[3] =  Integer.parseInt(com3.getValue().substring(com3.getValue().length() - 1));
		userChoices[4] =  Integer.parseInt(com4.getValue().substring(com4.getValue().length() - 1));
		userChoices[5] =  Integer.parseInt(com5.getValue().substring(com5.getValue().length() - 1));
		userChoices[6] =  Integer.parseInt(com6.getValue().substring(com6.getValue().length() - 1));
		userChoices[7] =  Integer.parseInt(com7.getValue().substring(com7.getValue().length() - 1));
		userChoices[8] =  Integer.parseInt(com8.getValue().substring(com8.getValue().length() - 1));
		userChoices[9] =  Integer.parseInt(com9.getValue().substring(com9.getValue().length() - 1));
		okClicked = true;
		dialogStage.close();

    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

}
