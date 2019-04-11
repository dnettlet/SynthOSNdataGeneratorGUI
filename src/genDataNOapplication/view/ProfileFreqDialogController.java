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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

public class ProfileFreqDialogController {
	
	private Stage dialogStage;
	private boolean okClicked;
	
	private int[] frequencies;	
	
	//Sliders
	@FXML
	Slider sliderP0;
	@FXML
	Slider sliderP1;
	@FXML
	Slider sliderP2;
	@FXML
	Slider sliderP3;
	@FXML
	Slider sliderP4;
	@FXML
	Slider sliderP5;
	@FXML
	Slider sliderP6;
	@FXML
	Slider sliderP7;
	@FXML
	Slider sliderP8;
	@FXML
	Slider sliderP9;
	
	//Labels
	@FXML
	Label labelP0;
	@FXML
	Label labelP1;
	@FXML
	Label labelP2;
	@FXML
	Label labelP3;
	@FXML
	Label labelP4;
	@FXML
	Label labelP5;
	@FXML
	Label labelP6;
	@FXML
	Label labelP7;
	@FXML
	Label labelP8;
	@FXML
	Label labelP9;
	
	//Buttons
	@FXML
	Button cancelButton;
	@FXML
	Button saveButton;
	
	

	//Class constctor
	public ProfileFreqDialogController() {

	}
	//Initializes the controller class. This method is automatically called
    // after the fxml file has been loaded.
    @FXML
    private void initialize() {
    	okClicked = false;
    	frequencies = new int[10];
    	sliderP0.setMin(0);    	sliderP0.setMax(500);    	
        sliderP0.valueProperty().addListener((observable, oldvalue, newvalue) ->
                {   int i = newvalue.intValue();
                    labelP0.setText(Integer.toString(i)); } );
        sliderP0.setValue(216);
        
    	sliderP1.setMin(0);    	sliderP1.setMax(500);    	
        sliderP1.valueProperty().addListener((observable, oldvalue, newvalue) ->
                {   int i = newvalue.intValue();
                    labelP1.setText(Integer.toString(i)); } );
        sliderP1.setValue(172);
        
    	sliderP2.setMin(0);    	sliderP2.setMax(500);    	
        sliderP2.valueProperty().addListener((observable, oldvalue, newvalue) ->
                {   int i = newvalue.intValue();
                    labelP2.setText(Integer.toString(i)); } );
        sliderP2.setValue(211);
        
    	sliderP3.setMin(0);    	sliderP3.setMax(500);    	
        sliderP3.valueProperty().addListener((observable, oldvalue, newvalue) ->
                {   int i = newvalue.intValue();
                    labelP3.setText(Integer.toString(i)); } );
        sliderP3.setValue(97);
        
    	sliderP4.setMin(0);    	sliderP4.setMax(500);    	
        sliderP4.valueProperty().addListener((observable, oldvalue, newvalue) ->
                {   int i = newvalue.intValue();
                    labelP4.setText(Integer.toString(i)); } );
        sliderP4.setValue(81);
        
    	sliderP5.setMin(0);    	sliderP5.setMax(500);    	
        sliderP5.valueProperty().addListener((observable, oldvalue, newvalue) ->
                {   int i = newvalue.intValue();
                    labelP5.setText(Integer.toString(i)); } );
        sliderP5.setValue(157);
        
    	sliderP6.setMin(0);    	sliderP6.setMax(500);    	
        sliderP6.valueProperty().addListener((observable, oldvalue, newvalue) ->
                {   int i = newvalue.intValue();
                    labelP6.setText(Integer.toString(i)); } );
        sliderP6.setValue(24);
        
    	sliderP7.setMin(0);    	sliderP7.setMax(500);    	
        sliderP7.valueProperty().addListener((observable, oldvalue, newvalue) ->
                {   int i = newvalue.intValue();
                    labelP7.setText(Integer.toString(i)); } );
        sliderP7.setValue(5);
        
    	sliderP8.setMin(0);    	sliderP8.setMax(500);    	
        sliderP8.valueProperty().addListener((observable, oldvalue, newvalue) ->
                {   int i = newvalue.intValue();
                    labelP8.setText(Integer.toString(i)); } );
        sliderP8.setValue(24);
        
    	sliderP9.setMin(0);    	sliderP9.setMax(500);    	
        sliderP9.valueProperty().addListener((observable, oldvalue, newvalue) ->
                {   int i = newvalue.intValue();
                    labelP9.setText(Integer.toString(i)); } );
        sliderP9.setValue(9);

        

    	
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
        	return frequencies;
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
		frequencies[0] = (int) sliderP0.getValue();
		frequencies[1] = (int) sliderP1.getValue();
		frequencies[2] = (int) sliderP2.getValue();
		frequencies[3] = (int) sliderP3.getValue();
		frequencies[4] = (int) sliderP4.getValue();
		frequencies[5] = (int) sliderP5.getValue();
		frequencies[6] = (int) sliderP6.getValue();
		frequencies[7] = (int) sliderP7.getValue();
		frequencies[8] = (int) sliderP8.getValue();
		frequencies[9] = (int) sliderP9.getValue();
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
