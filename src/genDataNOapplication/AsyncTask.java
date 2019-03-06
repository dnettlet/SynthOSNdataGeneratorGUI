package genDataNOapplication;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
 
import javafx.concurrent.Task;
 

public class AsyncTask extends Task<Object> {

	@Override
	protected Object call() throws Exception {
		
		GenDataNO.main();
		
		return null;
	}
 
    
 
}
