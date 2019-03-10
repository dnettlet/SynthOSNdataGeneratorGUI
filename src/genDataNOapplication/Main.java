package genDataNOapplication;
	
import java.io.IOException;

import genDataNOapplication.configuration.ConfigurationModel;
import genDataNOapplication.view.HomePageController;
import genDataNOapplication.view.SettingsPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Synthetic Data Generator");
		
		initRootLayout();
		showHomePage();
	}
	
	//Initializes the root layout
	private void initRootLayout() {
		try {
			//Load root layout from fxml file
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			
			//Show the scene containing the root layout
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			primaryStage.show();
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	//When called shows the Home Page of the program
	public void showHomePage() {
		try {
			//Load Home Page
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HomePage.fxml"));
			AnchorPane homePage = (AnchorPane) loader.load();
		
			//Set homePage in the center of root layout
			rootLayout.setCenter(homePage);
			
			//Give the controller access to the main app
			HomePageController controller = loader.getController();
			controller.setMainApp(this);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//When called shows the settings page of the program
	public void showSettingsPage() {
		 try {
			 	
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class.getResource("view/SettingsPage.fxml"));
	            AnchorPane settingsPage = (AnchorPane) loader.load();
	            
	            rootLayout.setCenter(settingsPage);

				SettingsPageController controller = loader.getController();
				controller.setMainApp(this);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	//Runs the program with a set of customized settings
	public void runCustomSettings(ConfigurationModel configuration) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/HomePage.fxml"));
			AnchorPane homePage = (AnchorPane) loader.load();
		
			rootLayout.setCenter(homePage);
			
			HomePageController controller = loader.getController();
			controller.setMainApp(this);
			
			controller.startApplication(configuration);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Returns the primary stage of the program
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
