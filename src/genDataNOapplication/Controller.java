package genDataNOapplication;

import genDataNOapplication.model.ConfigurationModel;
import javafx.concurrent.Task;
 

public class Controller extends Task<Object> {
	
	//Reference to the main application
	private Main main;
	
	protected ConfigurationModel configuration;

	@Override
	protected Object call() throws Exception {
		
		double ret = GenDataNO.main(configuration);
		
		return ret;
	}
	
	public void setConfiguration(ConfigurationModel configuration) { this.configuration = configuration; }
	
	//Is called by the main application to give a reference back to itself.
	public void setMainApp(Main main) {	this.main = main; }
 
    
 
}
