package genDataNOapplication.configuration;

import java.util.ArrayList;
import java.util.List;

import javafx.util.Pair;

public class AttributeModel {
	
	private String name;
	private String description;
	List<Pair<String, Integer>> parameterList;
	
	public AttributeModel() {
		name = "Insert a Name";
		description = "Insert a description";
		parameterList = new ArrayList<Pair<String, Integer>>();
	}
	
	//Getters and setters
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	public List<Pair<String, Integer>> getParameterList() { return parameterList; }
	public void setParameterList(List<Pair<String, Integer>> parameterList) { this.parameterList = parameterList; }
	
	//Add and remove parameters
	public void addParameter(Pair<String, Integer> parameter) {
		for(Pair<String, Integer> currentParameter : parameterList) {
			if(currentParameter.getKey().equals(parameter.getKey())){
				System.out.println("ERROR: There is already a parameter with the same Name");
			}
			else {
				parameterList.add(parameter);
			}
		}
	}
	
	public void removeParameter(String parameterName) {
		for(Pair<String, Integer> parameter : parameterList) {
			if(parameter.getKey().equals(parameterName)) {
				parameterList.remove(parameter);
			}
			else {
				System.out.println("The parameter " + parameterName + " doesn't exist");
			}
		}
	}
	
	
}
