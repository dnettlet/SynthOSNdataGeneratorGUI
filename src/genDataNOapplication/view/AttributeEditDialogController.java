package genDataNOapplication.view;

import java.util.ArrayList;
import java.util.List;

import genDataNOapplication.configuration.AttributeModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class AttributeEditDialogController {
	
	AttributeModel attribute;
	List<Pair<String, Integer>> parameterList;
	private Stage dialogStage;
	private boolean okClicked = false;
	private int paramCount = 0;
	
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
	
	@FXML
	GridPane parametersSection;
	
	public AttributeEditDialogController() {

	}
	
    @FXML
    private void initialize() {
    	nameTextField.setText("Prova Name");
    	descriptionTextArea.setText("Parameter Description. Nulla vitae elit libero, a pharetra augue. Aenean lacinia bibendum nulla sed consectetur.");
    	parameterList = new ArrayList<Pair<String, Integer>>();
    	
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
    
    @FXML
    private void handleAddButton() {
    	TextField paramName = new TextField();
    	paramName.setText("Introduce Parameter Name");
    	parametersSection.add(paramName, 0, paramCount + 2);
    	Spinner<Integer> paramValue = new Spinner<Integer>();
		paramValue.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
		paramValue.getValueFactory().setValue(10);
		parametersSection.add(paramValue, 1, paramCount + 2);
		Button deleteParamButton = new Button();
		deleteParamButton.setText("Delete");
		String deleteButtonID = "deleteParamButton" + String.valueOf(paramCount);
		deleteParamButton.setId(deleteButtonID);
		parametersSection.add(deleteParamButton, 2, paramCount + 2);
		paramCount++;
    }
    
    /**
     * Called when the user clicks ok.
     */
    @SuppressWarnings("unchecked")
	@FXML
    private void handleOk() {
        if (isInputValid()) {
        	attribute.setName(nameTextField.getText());
        	attribute.setDescription(descriptionTextArea.getText());
        	List<Node> childrens = parametersSection.getChildren();
    		TextField paramName = null;
    		Spinner<Integer> paramValue = null;
        	for(Node currentNode : childrens) {
	    		if(currentNode.getId() != null) {
	    			continue;
	    		}
	    		if(currentNode.getClass().toString().equals("class javafx.scene.control.TextField")) {
	    			paramName = (TextField) currentNode;
	    			continue;
	    		}else {
	    			paramValue = (Spinner<Integer>) currentNode;
	    		}
	    		Pair<String, Integer> parameter = new Pair<String, Integer>(paramName.getText(), paramValue.getValue());
	    		parameterList.add(parameter);
        	}
        	System.out.println(parameterList);
        	attribute.setParameterList(parameterList);
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
