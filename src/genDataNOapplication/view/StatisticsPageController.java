package genDataNOapplication.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

import genDataNOapplication.Main;
import genDataNOapplication.RV.RV;
import genDataNOapplication.User.User;
import genDataNOapplication.Utils.Utils;
import genDataNOapplication.model.AttributeModel;
import genDataNOapplication.model.ConfigurationModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;


public class StatisticsPageController {
	//Reference to the main application
	private Main main;
		
	//Configuration
	protected ConfigurationModel configuration;
	double total;
	
	//Buttons
	@FXML
	Button backButton;
	@FXML
	Button outFileButton;
	@FXML 
	Button outgFileButton;
	@FXML
	Button out1FileButton;
	@FXML
	Button out2FileButton;
	
	//GridPane
	@FXML
	GridPane chartsSection;
	
	//ChoiceBox
	@FXML
	ChoiceBox<String> whatToDisplay;
	
	public StatisticsPageController() {
		
	}
	
	@FXML
	public void initialize() {
	}
	
	//Is called by the main application to give a reference back to itself.
	public void setMainApp(Main main) {
		this.main = main;
	}
	
	//Is called to set a specific configuration
	public void setConfiguration(ConfigurationModel configuration) {
		this.configuration = configuration;
		total = 0;
		setup();
	}
	
	private void setup() {
		File outFile = new File(configuration.getOutFile());
		String name = outFile.getName();
		outFileButton.setText(name);
		File outgFile = new File(configuration.getOutgFile());
		name = outgFile.getName();
		outgFileButton.setText(name);
		File out1File = new File(configuration.getOut1File());
		name = out1File.getName();
		File out2File = new File(configuration.getOut2File());
		name = out2File.getName();
		out2FileButton.setText(name);
		whatToDisplay.getItems().add("Community 0");
		whatToDisplay.getItems().add("Community 1");
		whatToDisplay.getItems().add("Community 2");
		whatToDisplay.getItems().add("Community 3");
		whatToDisplay.getItems().add("Community 4");
		whatToDisplay.getItems().add("Community 5");
		whatToDisplay.getItems().add("Community 6");
		whatToDisplay.getItems().add("Community 7");
		whatToDisplay.getItems().add("Community 8");
		whatToDisplay.getItems().add("Community 9");
		whatToDisplay.getItems().add("Show All");
		whatToDisplay.getSelectionModel().select(10);
        whatToDisplay.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() { 
       	 
            // if the item of the list is changed 
            public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, Number value, Number userValue) 
            { 
            	chartsSection.getChildren().clear();
            	loadStatistics(whatToDisplay.getSelectionModel().selectedIndexProperty().intValue());
            } 
        }); 
		loadStatistics(whatToDisplay.getSelectionModel().selectedIndexProperty().intValue());
	}
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadStatistics(int comNum) {
		System.out.println("Displaying community " + comNum);
		//Chart for Profiles
		List<Pair<List<Integer>, Integer>>  profileList = configuration.getProfileList();
		int count = 0;
		ObservableList<PieChart.Data> profileChartData = FXCollections.observableArrayList();
        for(Pair<List<Integer>, Integer> currentProfile : profileList) {
        	String title = "Profile " + count;
        	profileChartData.add(new PieChart.Data(title, currentProfile.getValue()));
        	count++;
        }
        final PieChart profileChart = new PieChart(profileChartData);
        
        for (PieChart.Data d : profileChart.getData()) {
            total += d.getPieValue();
        }

        profileChart.getData().stream().forEach(data -> {
            Tooltip tooltip = new Tooltip();
            tooltip.setText(100*data.getPieValue()/total + "%");
            Tooltip.install(data.getNode(), tooltip);
            data.pieValueProperty().addListener((observable, oldValue, newValue) -> 
                tooltip.setText(newValue + "%"));
        });

        profileChart.setTitle("Profile Frequency");
		chartsSection.add(profileChart, 0, 0);
		
		//Charts for User Attributes
		int row = 0;
		int col = 1;
		int countAttr = 0;
		for(AttributeModel currentAttr : configuration.getUserAttrributesList()) {
			List<Pair<String, Double>> paramList = getExpectedData(comNum, countAttr);
			List<Pair<String, Double>> realParamList = getRealData(comNum, countAttr);
	        final CategoryAxis xAxis = new CategoryAxis();
	        final NumberAxis yAxis = new NumberAxis();
	        final BarChart<String,Number> attributesChart = 
	                new BarChart<String,Number>(xAxis,yAxis);
	            attributesChart.setTitle(currentAttr.getName());
	            xAxis.setLabel("Parameters");       
	            yAxis.setLabel("Frequency");
	            XYChart.Series series1 = new XYChart.Series();
	            series1.setName("Expected");
	            for(Pair<String, Double> currentParam : paramList) {
	            	series1.getData().add(new XYChart.Data<String, Double>(currentParam.getKey(), currentParam.getValue()));
	            }
	            
	            XYChart.Series series2 = new XYChart.Series();
	            series2.setName("Real");
	            for(Pair<String, Double> currentParam : realParamList) {
	            	series2.getData().add(new XYChart.Data<String, Double>(currentParam.getKey(), currentParam.getValue()));
	            }
	            chartsSection.add(attributesChart, col, row);
	            attributesChart.getData().addAll(series1, series2);
	            col++;
	            if(col > 2) {
	            	col = 0;
	            	row++;
	            }
	       countAttr++;
		}
		//List<Pair<String, Double>> paramList = configuration.getUserAttrributesList().get(0).getParameterList();		
	}
	
	
	private List<Pair<String, Double>> getExpectedData(int comNum, int countAttr){
		List<Pair<String, Double>> expectedParamList = configuration.getUserAttrributesList().get(countAttr).getParameterList();

		if(comNum != 10) {
			int[] profComAssaign = configuration.getProfileCommunityAssaignment();
			int profileID = profComAssaign[comNum];
			Pair<List<Integer>, Integer> profile = configuration.getProfileList().get(profileID);
			int paramID = profile.getKey().get(countAttr);
			Pair<String, Double> param = expectedParamList.get(paramID);
			List<Pair<String, Double>> newParamList = new ArrayList<Pair<String, Double>>();
			
			for(Pair<String, Double> currentParam : expectedParamList) {
				if(currentParam.getKey().equals(param.getKey())) {
					Pair<String, Double> newParam = new Pair<String, Double>(currentParam.getKey(), 1.0);
					newParamList.add(newParam);
				}else {
					Pair<String, Double> newParam = new Pair<String, Double>(currentParam.getKey(), 0.0);
					newParamList.add(newParam);
				}
					
			}
			expectedParamList = newParamList;
			
		}
		
		return expectedParamList;
	}
	@SuppressWarnings("unchecked")
	private List<Pair<String, Double>> getRealData(int comNum, int attributeId){
		List<Pair<String, Double>> realParamList = new ArrayList<Pair<String, Double>>();
		List<Integer> paramFreqList = new ArrayList<Integer>();
		List<String> paramNameList = new ArrayList<String>();
		for(Pair<String, Double> currentParam : configuration.getUserAttrributesList().get(attributeId).getParameterList()) {
			paramNameList.add(currentParam.getKey());
			paramFreqList.add(0);
		}
		Enumeration<?> en1 = RV.Users.keys();
		
		while (en1.hasMoreElements()){
			User nw = (User)RV.Users.get((Integer)en1.nextElement());
			if(comNum != 10) {
			Vector<Integer> communities =  nw.communities;
			if(!communities.contains(comNum))
				continue;
			}
			
			String attribute = new String();
			switch(attributeId) {
			case 0:
				attribute = nw.getAge();
				break;
			case 1:
				attribute = nw.getGender();
				break;
			case 2:
				attribute = nw.getResidence();
				break;
			case 3:
				attribute = nw.getReligion();
				break;
			case 4:
				attribute = nw.getMaritalStatus();
				break;
			case 5:
				attribute = nw.getProfession();
				break;
			case 6:
				attribute = nw.getPoliticalOrientation();
				break;
			case 7:
				attribute = nw.getSexualOrientation();
				break;
			}
				
			int index = 0;
			for(String currentName : paramNameList) {
				if(attribute.equals(currentName)) {
					index = paramNameList.indexOf(currentName);
					int freq = paramFreqList.get(index);
					freq++;
					paramFreqList.set(index, freq);
					break;
				}
			}

		}
		int sum = 0;
		for(String currentName : paramNameList) {
			sum += paramFreqList.get(paramNameList.indexOf(currentName));
		}
		
		for(int value : paramFreqList) {
			double freq = (double) value / sum;
			String name = paramNameList.get(paramFreqList.indexOf(value));
			Pair<String, Double> param = new Pair<String, Double>(name, freq);
			realParamList.add(param);
		}
		return realParamList;
	}
	
	@FXML
	public void handleOutFileButton() {
		File outFile = new File(configuration.getOutFile());
		if(!Utils.openFile(outFile)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error opening the file " + outFile.getPath());

			alert.showAndWait();
		}
	}
	
	@FXML
	public void handleOutgFileButton() {
		File outgFile = new File(configuration.getOutgFile());
		if(!Utils.openFile(outgFile)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error opening the file " + outgFile.getPath());

			alert.showAndWait();
		}
	}
	
	@FXML
	public void handleOut1FileButton() {
		File out1File = new File(configuration.getOut1File());
		if(!Utils.openFile(out1File)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error opening the file " + out1File.getPath());

			alert.showAndWait();
		}
	}
	
	@FXML
	public void handleOut2FileButton() {
		File out2File = new File(configuration.getOut2File());
		if(!Utils.openFile(out2File)) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("Error opening the file " + out2File.getPath());

			alert.showAndWait();
		}
	}
	
	@FXML
	public void handleBackButton() {
		main.showRunPage();
	}
	
	
}
