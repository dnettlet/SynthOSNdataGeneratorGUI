package genDataNOapplication;

import genDataNOapplication.model.ConfigurationModel;
import javafx.concurrent.Task;
 

public class Controller extends Task<Object> {
	
	protected ConfigurationModel configuration;

	@Override
	protected Object call() throws Exception {
		
		double ret = GenDataNO.main(configuration);
		
		return ret;
	}
	
	public void setConfiguration(ConfigurationModel configuration) { this.configuration = configuration; }
	
 
    
 
}
