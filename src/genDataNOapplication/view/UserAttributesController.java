package genDataNOapplication.view;

import java.awt.Label;
import java.util.ArrayList;
import java.util.List;

import genDataNOapplication.Main;
import genDataNOapplication.configuration.AttributeModel;
import genDataNOapplication.configuration.ConfigurationModel;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

public class UserAttributesController {
	
	//Reference to the main application
	private Main main;
	
	//Configuration
	protected ConfigurationModel configuration;
	
	private List<AttributeModel> list;
	private ObservableList<AttributeModel> attributeList;
	
	//Buttons
	@FXML
	Button filesButtonTab;
	@FXML
	Button userParametersButtonTab;
	@FXML
	Button communitiesButtonTab;
	@FXML
	Button advancedButtonTab;
	@FXML
	Button backButton;
	@FXML
	Button addButton;
	
	//Anchor pane
	@FXML
	GridPane attributesSection;

	//Class Constructor
	public UserAttributesController() {
		
	}
	
	//Initializes the controller class. This method is automatically called
    // after the fxml file has been loaded.
	@FXML
	private void initialize() {
		list = new ArrayList<AttributeModel>();
		attributeList = FXCollections.observableList(list);
		attributeList.addListener(new ListChangeListener<Object>() {

			@SuppressWarnings("rawtypes")
			@Override
	        public void onChanged(ListChangeListener.Change change) {
	            System.out.println("Ocurrio un cambio! ");
	            reloadAttributesSection();
	        }
	    });
		
	}
	

	//Is called by the main application to give a reference back to itself.
	public void setMainApp(Main main) {
		this.main = main;
	}
	
	//Is called to set a specific configuration
	public void setConfiguration(ConfigurationModel configuration) {
		this.configuration = configuration;
	}
	
	@FXML
	public void handleAddButton() {
	    AttributeModel attribute = new AttributeModel();
	    AttributeModel modifiedAttribute = main.showAttributeEditDialog(attribute);
	    if (modifiedAttribute  != null) {
	        System.out.println("Name: " + modifiedAttribute.getName() + "\n Description: " + modifiedAttribute.getDescription());
	        attributeList.add(modifiedAttribute);
	    }
	}
	

	protected void reloadAttributesSection() {
		attributesSection.getChildren().clear();
		for(AttributeModel attribute : attributeList) {
			
			TitledPane attributeCard = new TitledPane();
			attributeCard.setText(attribute.getName());
			attributeCard.setCollapsible(false);
			attributesSection.add(attributeCard, 0, 0);
			attributeCard.resize(318, 363);
			attributeCard.setMinHeight(363);
			BorderPane cardBorderPane = new BorderPane();
			attributeCard.setContent(cardBorderPane);
			cardBorderPane.resize(attributeCard.getWidth(), attributeCard.getHeight());
			ScrollPane cardScrollPane = new ScrollPane();
			cardScrollPane.resize(cardBorderPane.getWidth(), cardBorderPane.getHeight());
			cardBorderPane.setCenter(cardScrollPane);
			GridPane parametersGridPane = new GridPane();
			parametersGridPane.resize(cardBorderPane.getWidth(), cardBorderPane.getHeight());
			parametersGridPane.setHgap(10);
			parametersGridPane.setVgap(10);
			parametersGridPane.setPadding(new Insets(0, 10, 0, 10));
			
			int paramCount = 0;
			for(Pair<String, Integer> parameter : attribute.getParameterList()) {
				TextField paramName = new TextField();
				paramName.setText(parameter.getKey());
				parametersGridPane.add(paramName, paramCount, 0);
				
				Spinner<Integer> paramValue = new Spinner();
				paramValue.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
				paramValue.getValueFactory().setValue(parameter.getValue());
				parametersGridPane.add(paramValue, paramCount, 1);
				
				paramCount++;
			}
			

			
			/*ScrollPane cardScrollPane = new ScrollPane();
			GridPane parametersGridPane = new GridPane();
			Text description = new Text();
			description.setText(attribute.getDescription());
			//parametersGridPane.add(description, 0, 0);
			cardScrollPane.setContent(parametersGridPane);
			cardBorderPane.setCenter(cardScrollPane);
			cardBorderPane.setTop(description);
			attributeCard.setContent(cardBorderPane);
			AnchorPane attributeAnchorPane = new AnchorPane(attributeCard);
			attributeCard.resize(attributeAnchorPane.getWidth(), attributeAnchorPane.getHeight());
			cardBorderPane.resize(attributeCard.getWidth(),attributeCard.getHeight());
			cardScrollPane.resize(cardBorderPane.getWidth(), cardBorderPane.getHeight());
			description.resize(cardBorderPane.getWidth(), cardBorderPane.getHeight());*/
			
			
			
			
			
			/*TitledPane attributeCard = new TitledPane();
			attributeCard.setText(attribute.getName());
			attributeCard.setCollapsible(false);
			AnchorPane anchorPane = new AnchorPane();
			attributeCard.setContent(anchorPane);
			
			ScrollPane scrollPane = new ScrollPane();
			attributeCard.setContent(scrollPane);
			scrollPane.resize(attributeCard.getWidth(), attributeCard.getHeight());
			BorderPane borderPane = new BorderPane();
			scrollPane.setContent(borderPane);
			borderPane.resize(scrollPane.getWidth(), scrollPane.getHeight());
			Text description = new Text();
			description.setText(attribute.getDescription());
			borderPane.setTop(description);*/
			
			
			//attributesSection.add(attributeAnchorPane, 0, 0);
		}
		
	}
	
	
	@FXML
	public void handleFilesButtonTab() {
		//configuration.setNumCommunities(numCommunitiesSpinner.getValue());
		//configuration.setSeedSize(seedSizeSpinner.getValue());
		//main.setConfiguration(configuration);
		main.showSettingsPage();
	}
	
	@FXML
	public void handleAdvancedButtonTab() {
		main.showAdvancedSettingsPage();
	}
	
	@FXML
	public void handleCommunitiesButtonTab() {
		main.showCommunitiesSettingsPage();
	}
	
	

}
