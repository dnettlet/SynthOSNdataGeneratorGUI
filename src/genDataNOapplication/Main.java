package genDataNOapplication;
	
import java.io.IOException;
import java.util.List;

import genDataNOapplication.model.AttributeModel;
import genDataNOapplication.model.ConfigurationModel;
import genDataNOapplication.view.AdvancedSettingsController;
import genDataNOapplication.view.AttributeEditDialogController;
import genDataNOapplication.view.CommunitiesSettingsController;
import genDataNOapplication.view.ComAssaignDialogController;
import genDataNOapplication.view.HomePageController;
import genDataNOapplication.view.ProfileFreqDialogController;
import genDataNOapplication.view.ProfilesPageController;
import genDataNOapplication.view.RootLayoutController;
import genDataNOapplication.view.FileSettingsController;
import genDataNOapplication.view.UserAttributesController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private ConfigurationModel configuration;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Synthetic Data Generator");
		
		configuration = new ConfigurationModel();
		
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
			
			
	        // Give the controller access to the main app.
	        RootLayoutController controller = loader.getController();
	        controller.setMainApp(this);
			
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
	            loader.setLocation(Main.class.getResource("view/FileSettings.fxml"));
	            AnchorPane settingsPage = (AnchorPane) loader.load();
	            
	            rootLayout.setCenter(settingsPage);

				FileSettingsController controller = loader.getController();
				controller.setMainApp(this);
				controller.setConfiguration(configuration);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	
	
	public void showProfilesPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/ProfilesPage.fxml"));
			Node ProfilesPage = (Node) loader.load();
			
			rootLayout.setCenter(ProfilesPage);
			
			ProfilesPageController controller = loader.getController();
			controller.setMainApp(this);
			controller.setConfiguration(configuration);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showCommunitiesSettingsPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/CommunitiesProfilesSettings.fxml"));
			Node communitiesSettings = (Node) loader.load();
			
			rootLayout.setCenter(communitiesSettings);
			
			CommunitiesSettingsController controller = loader.getController();
			controller.setMainApp(this);
			controller.setConfiguration(configuration);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showAdvancedSettingsPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/AdvancedSettings.fxml"));
			AnchorPane AdvancedSettings = (AnchorPane) loader.load();
			
			rootLayout.setCenter(AdvancedSettings);
			
			AdvancedSettingsController controller = loader.getController();
			controller.setMainApp(this);
			controller.setConfiguration(configuration);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showUserAttributesPage() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/UserAttributes.fxml"));
			Node UserAttributes = (Node) loader.load();
			
			rootLayout.setCenter(UserAttributes);
			
			UserAttributesController controller = loader.getController();
			controller.setMainApp(this);
			controller.setConfiguration(configuration);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public AttributeModel showAttributeEditDialog(AttributeModel attribute, List<String> attributeNames, boolean openExistentAttr) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/AttributeEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Attribute");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        AttributeEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setAttribute(attribute);
	        controller.setAttributeNames(attributeNames);
	        if(openExistentAttr) {
	        	controller.openAttribute(attribute);
	        }

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();
	        
	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	//Runs the program with a set of customized settings
	public void runCustomSettings() {
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
	
	
	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public int[] showCommunityAssaignmentDialog() {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/CommunityAssaignmentDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Community Assaignment");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        ComAssaignDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();
	        
	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	/**
	 * Opens a dialog to edit details for the specified person. If the user
	 * clicks OK, the changes are saved into the provided person object and true
	 * is returned.
	 * 
	 * @param person the person object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public int[] showProfileFreqDialog() {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/ProfileFrequencyDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Profile Frequency");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the person into the controller.
	        ProfileFreqDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();
	        
	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return null;
	    }
	}
	
	public void setConfiguration(ConfigurationModel configuration) {
		this.configuration = configuration;
	}
	
	public ConfigurationModel getConfiguration() {
		return configuration;
	}
	
	//Returns the primary stage of the program
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
