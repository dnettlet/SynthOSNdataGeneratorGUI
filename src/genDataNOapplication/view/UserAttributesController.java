package genDataNOapplication.view;

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
			attributeCard.resize(318, 363);
			attributeCard.setMinHeight(363);
			attributesSection.add(attributeCard, 0, 0);			
			BorderPane cardBorderPane = new BorderPane();
			attributeCard.setContent(cardBorderPane);
			cardBorderPane.resize(attributeCard.getWidth(), attributeCard.getHeight());
			
			HBox hbox = new HBox();
			cardBorderPane.setTop(hbox);
			hbox.setSpacing(30);
			Text description = new Text();
			description.setText(attribute.getDescription());
			description.setWrappingWidth(201);
			hbox.getChildren().add(description);
			VBox vbox = new VBox();
			hbox.getChildren().add(vbox);
			Button editButton = new Button();
			editButton.setText("Edit");
			Button saveButton = new Button();
			saveButton.setText("Save");
			saveButton.setDefaultButton(true);
			Button deleteButton = new Button();
			deleteButton.setText("Delete");
			deleteButton.setCancelButton(true);
			vbox.getChildren().addAll(editButton, saveButton, deleteButton);
			
			
			ScrollPane cardScrollPane = new ScrollPane();
			cardScrollPane.resize(cardScrollPane.getWidth(), cardScrollPane.getHeight());
			System.out.println(cardBorderPane.getWidth() + " , " + cardBorderPane.getHeight());
			cardBorderPane.setCenter(cardScrollPane);
			GridPane parametersGridPane = new GridPane();
			parametersGridPane.resize(cardBorderPane.getWidth(), cardBorderPane.getHeight());
			parametersGridPane.setHgap(15);
			parametersGridPane.setVgap(10);
			parametersGridPane.setPadding(new Insets(0, 10, 0, 10));
			
			int paramCount = 0;
			for(Pair<String, Integer> parameter : attribute.getParameterList()) {
				TextField paramName = new TextField();
				paramName.setText(parameter.getKey());
				parametersGridPane.add(paramName, paramCount, 0);
				
				Spinner<Integer> paramValue = new Spinner<Integer>();
				paramValue.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100));
				paramValue.getValueFactory().setValue(parameter.getValue());
				parametersGridPane.add(paramValue, paramCount, 1);
				
				paramCount++;
			}
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
