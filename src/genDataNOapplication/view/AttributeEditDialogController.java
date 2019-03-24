package genDataNOapplication.view;

import genDataNOapplication.configuration.AttributeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AttributeEditDialogController {
	
	AttributeModel attribute;
	private Stage dialogStage;
	private boolean okClicked = false;
	
	@FXML
	TextField nameTextField;
	@FXML
	TextArea descriptionTextArea;
	
	@FXML
	Button addButton;
	@FXML
	Button resetButton;
	@FXML
	Button cancelButton;
	@FXML
	Button saveButton;
	
	public AttributeEditDialogController() {

	}
	
    @FXML
    private void initialize() {
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setAttribute(AttributeModel attribute) {
        this.attribute = attribute;

        /*firstNameField.setText(person.getFirstName());
        lastNameField.setText(person.getLastName());
        streetField.setText(person.getStreet());
        postalCodeField.setText(Integer.toString(person.getPostalCode()));
        cityField.setText(person.getCity());
        birthdayField.setText(DateUtil.format(person.getBirthday()));
        birthdayField.setPromptText("dd.mm.yyyy");*/
    }
    
    /**
     * Returns true if the user clicked OK, false otherwise.
     * 
     * @return
     */
    public AttributeModel isOkClicked() {
        if(okClicked) {
        	return attribute;
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
        if (isInputValid()) {
        	attribute.setName(nameTextField.getText());
        	attribute.setDescription(descriptionTextArea.getText());
            /*person.setFirstName(firstNameField.getText());
            person.setLastName(lastNameField.getText());
            person.setStreet(streetField.getText());
            person.setPostalCode(Integer.parseInt(postalCodeField.getText()));
            person.setCity(cityField.getText());
            person.setBirthday(DateUtil.parse(birthdayField.getText()));*/

            okClicked = true;
            dialogStage.close();
        }
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
    	return true;
    }
	
    
	
	
	
}
