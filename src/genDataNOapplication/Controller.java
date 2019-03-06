package genDataNOapplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import genDataNOapplication.configuration.ConfigurationModel;
import javafx.concurrent.Task;
 

public class Controller extends Task<Object> {
	
	protected ConfigurationModel configuration;

	@Override
	protected Object call() throws Exception {
		
		configuration = new ConfigurationModel();
		
		GenDataNO.main();
		
		return null;
	}
 
    
 
}
