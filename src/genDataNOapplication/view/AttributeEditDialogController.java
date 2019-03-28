package genDataNOapplication.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import genDataNOapplication.Utils.Utils;
import genDataNOapplication.configuration.AttributeModel;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class AttributeEditDialogController {
	
	AttributeModel attribute;
	List<Pair<String, Integer>> parameterList;
	List<String> attributeNames;
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
    	attributeNames = new ArrayList<String>();
    	
    }
    
    /**
     * Sets the stage of this dialog.
     * 
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setAttributeNames(List<String> attributeNames) {
    	this.attributeNames = attributeNames;
    }
    
    /**
     * Sets the person to be edited in the dialog.
     * 
     * @param person
     */
    public void setAttribute(AttributeModel attribute) {
        this.attribute = attribute;

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
		paramValue.getValueFactory().setValue(50);
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
        if (isInputValid()) {
        	attribute.setParameterList(parameterList);
            okClicked = true;
            dialogStage.close();
        }else {
        	parameterList.clear();
        }
    }
    
    /**
     * Called when the user clicks cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
	@FXML
	public void handleResetButton() {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Reset Default");
		alert.setHeaderText("Reset parameters to default");
		alert.setContentText("Are you sure you want to reset all settings parameters to the default configuration?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			nameTextField.setText("");
			descriptionTextArea.setText("");
			parameterList.clear();
			for(int i = (paramCount*3) + 4; i > 4; i--) {

				parametersSection.getChildren().remove(i-1);

			}
			paramCount = 0;

		}
	}
    /**
     * Validates the user input in the text fields.
     * 
     * @return true if the input is valid
     */
    private boolean isInputValid() {
    	boolean error = false;
    	String errorHeader = "";
    	String errorMessage = "";
    	if(nameTextField.getText().equals("")) {
    		errorHeader = "Error with the Attribute Name";
    		errorMessage = "The Attribute must have a name!";
    		error = true;
    	}
    	for(String attributeName : attributeNames) {
    		if(nameTextField.getText().equals(attributeName)) {
    			errorHeader = "Error with the Attribute Name";
    			errorMessage = "There is already an attribute with this name.";
    			error = true;
    		}
    	}
    	if(paramCount > 0) {
        	int sum = 0;
        	for(Pair<String, Integer> parameter : parameterList) {
        		sum += parameter.getValue();
        	}
        	if(sum != 100) {
        		errorHeader = "Error with parameter Values";
        		errorMessage = "Please make sure the sum of the values of the different parameters is 100."
        				+ "\n Remember that the parameter values are the % of assignation, so it should sum 100.";
        		error = true;
        	}
    	}else {
    		errorHeader = "There are no Attribute Parameters!";
    		errorMessage = "You can not create a User attribute without parameters."
    				+ "\n Please create some parameters for this attribute by clicking the Add Parameter button";
    		error = true;
    	}

    	if(error) {
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setTitle("Error with attribute");
    		alert.setHeaderText(errorHeader);
    		alert.setContentText(errorMessage);
    		alert.showAndWait();
    		return false;
    	}
    	return true;
    }
    
    private void openAttribute(AttributeModel attribute) {
    	this.attribute = attribute;
    	this.parameterList = attribute.getParameterList();
    	paramCount = parameterList.size();
    	nameTextField.setText(attribute.getName());
    	descriptionTextArea.setText(attribute.getDescription());
    	for(Pair<String, Integer> parameter : parameterList) {
        	TextField paramName = new TextField();
        	paramName.setText(parameter.getKey());
        	parametersSection.add(paramName, 0, paramCount + 2);
        	Spinner<Integer> paramValue = new Spinner<Integer>();
    		paramValue.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
    		paramValue.getValueFactory().setValue(parameter.getValue());
    		parametersSection.add(paramValue, 1, paramCount + 2);
    		Button deleteParamButton = new Button();
    		deleteParamButton.setText("Delete");
    		String deleteButtonID = "deleteParamButton" + String.valueOf(paramCount);
    		deleteParamButton.setId(deleteButtonID);
    		parametersSection.add(deleteParamButton, 2, paramCount + 2);
    	}
    	
    }
	
    
	
	
	
}
