package genDataNOapplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import genDataNOapplication.configuration.ConfigurationModel;
import javafx.concurrent.Task;
 

public class Controller extends Task<Object> {
	
	//Reference to the main application
	private Main main;
	
	protected ConfigurationModel configuration;

	@Override
	protected Object call() throws Exception {
		
		GenDataNO.main(configuration);
		
		return null;
	}
	
	public void setConfiguration(ConfigurationModel configuration) { this.configuration = configuration; }
	
	//Is called by the main application to give a reference back to itself.
	public void setMainApp(Main main) {	this.main = main; }
 
    
 
}
