package genDataNOapplication.view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.util.Pair;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import genDataNOapplication.Main;
import genDataNOapplication.Utils.Utils;
import genDataNOapplication.model.AttributeModel;
import genDataNOapplication.model.ConfigurationModel;
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
		loadStatistics();
	}
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadStatistics() {
		
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
        profileChart.setTitle("Profile Frequency");
		chartsSection.add(profileChart, 0, 0);
		
		//Charts for User Attributes
		int row = 0;
		int col = 1;
		for(AttributeModel currentAttr : configuration.getUserAttrributesList()) {
			List<Pair<String, Double>> paramList = currentAttr.getParameterList();
	        final CategoryAxis xAxis = new CategoryAxis();
	        final NumberAxis yAxis = new NumberAxis();
	        final BarChart<String,Number> ageChart = 
	                new BarChart<String,Number>(xAxis,yAxis);
	            ageChart.setTitle(currentAttr.getName());
	            xAxis.setLabel("Parameters");       
	            yAxis.setLabel("Frequency");
	            XYChart.Series series1 = new XYChart.Series();
	            series1.setName("Expected");
	            for(Pair<String, Double> currentParam : paramList) {
	            	series1.getData().add(new XYChart.Data<String, Double>(currentParam.getKey(), currentParam.getValue()));
	            }
	            
	            XYChart.Series series2 = new XYChart.Series();
	            series2.setName("Expected");
	            for(Pair<String, Double> currentParam : paramList) {
	            	series2.getData().add(new XYChart.Data<String, Double>(currentParam.getKey(), currentParam.getValue()));
	            }
	            chartsSection.add(ageChart, col, row);
	            //chartsSection.getChildren().add(ageChart);
	            ageChart.getData().addAll(series1, series2);
	            col++;
	            if(col > 2) {
	            	col = 0;
	            	row++;
	            }
		}
		//List<Pair<String, Double>> paramList = configuration.getUserAttrributesList().get(0).getParameterList();		
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
