package genDataNOapplication;
	
import java.io.IOException;

import genDataNOapplication.view.HomePageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
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
	
	
	private void showHomePage() {
		try {
			//Load person overview
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
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
