package genDataNOapplication.model;

import java.util.ArrayList;
import java.util.List;

import genDataNOapplication.model.AttributeModel;
import javafx.util.Pair;

//Class that stores all the customizable settings for the program execution
public class ConfigurationModel {
	
	//Input and output file paths
	private String inputFile1;
	private String inputFile2;
	private String outFile;
	private String outgFile;
	private String out1File;
	private String out2File;
	
	//Number of communities
	private int numCommunities;
	
	private int seedSize;
	private int randomness;
	
	//List of User Attributes
	private List<AttributeModel> userAttrributesList;
	
	//List of Profiles
	private List<ProfileModel> profileList;
	
	//Class constructor
	public ConfigurationModel() {
		
		this.inputFile1 = "./resources/files/karate.csv";
		this.inputFile2 = "./resources/files/karatemodauth.csv";
		this.outFile = "./resources/files/karate_out.csv";
		this.outgFile = "./resources/files/karate_outg.csv";
		this.out1File = "./resources/files/karate_out1.csv"; 
		this.out2File = "./resources/files/karate_out2.csv";
		
		userAttrributesList = new ArrayList<AttributeModel>();
		profileList = new ArrayList<ProfileModel>();
		
		numCommunities = 10;
		seedSize = 110;
		randomness = 0;
		
		/*By default, a user has the following attributes:
		 * 
		 * Residence, Age, gender, religion, marital status, profession, 
		 * political orientation and sexual orientation.
		 * 
		 */
		
		AttributeModel age = new AttributeModel();
		age.setName("Age");
		age.setDescription("The age range in Years");
		List<Pair<String, Double>> AgeparameterList = new ArrayList<Pair<String, Double>>();
		AgeparameterList.add(new Pair<String, Double>("18-25", 0.25)); //0
		AgeparameterList.add(new Pair<String, Double>("26-35", 0.25)); //1
		AgeparameterList.add(new Pair<String, Double>("36-45", 0.17)); //2
		AgeparameterList.add(new Pair<String, Double>("46-55", 0.09)); //3
		AgeparameterList.add(new Pair<String, Double>("56-65", 0.08)); //4
		AgeparameterList.add(new Pair<String, Double>("66-75", 0.08)); //5
		AgeparameterList.add(new Pair<String, Double>("76-85", 0.08)); //6
		age.setParameterList(AgeparameterList);
		userAttrributesList.add(age);
		
		AttributeModel gender = new AttributeModel();
		gender.setName("Gender");
		gender.setDescription("The gender. Male or Female.");
		List<Pair<String, Double>> GenderparameterList = new ArrayList<Pair<String, Double>>();
		GenderparameterList.add(new Pair<String, Double>("male", 0.5));
		GenderparameterList.add(new Pair<String, Double>("female", 0.5));
		gender.setParameterList(GenderparameterList);
		userAttrributesList.add(gender);
		
		AttributeModel residence = new AttributeModel();
		residence.setName("Residence");
		residence.setDescription("The City or place where the user lives");
		List<Pair<String, Double>> ResidenceparameterList = new ArrayList<Pair<String, Double>>();
		ResidenceparameterList.add(new Pair<String, Double>("Palo Alto", 0.16));
		ResidenceparameterList.add(new Pair<String, Double>("Santa Barbara", 0.16));
		ResidenceparameterList.add(new Pair<String, Double>("Winthrop", 0.17));
		ResidenceparameterList.add(new Pair<String, Double>("Boston", 0.17));
		ResidenceparameterList.add(new Pair<String, Double>("Cambridge", 0.17));
		ResidenceparameterList.add(new Pair<String, Double>("San Jose", 0.17));
		residence.setParameterList(ResidenceparameterList);
		userAttrributesList.add(residence);
		
		AttributeModel religion = new AttributeModel();
		religion.setName("Religion");
		religion.setDescription("The religion a user follows. It can also be the case that"
				+ " the user doesn't follow any religion at all");
		List<Pair<String, Double>> ReligionparameterList = new ArrayList<Pair<String, Double>>();
		ReligionparameterList.add(new Pair<String, Double>("Buddhist", 0.068));
		ReligionparameterList.add(new Pair<String, Double>("Christian", 0.304));
		ReligionparameterList.add(new Pair<String, Double>("Hindu", 0.138));
		ReligionparameterList.add(new Pair<String, Double>("Jewish", 0.02));
		ReligionparameterList.add(new Pair<String, Double>("Muslim", 0.257));
		ReligionparameterList.add(new Pair<String, Double>("Sikh", 0.03));
		ReligionparameterList.add(new Pair<String, Double>("Traditional Spirituality", 0.01));
		ReligionparameterList.add(new Pair<String, Double>("Other religions", 0.110));
		ReligionparameterList.add(new Pair<String, Double>("No religious affiliation", 0.117));
		religion.setParameterList(ReligionparameterList);
		userAttrributesList.add(religion);
		
		AttributeModel maritalstatus = new AttributeModel();
		maritalstatus.setName("Marital Status");
		maritalstatus.setDescription("A person can be single, in a relationship, married, etc.");
		List<Pair<String, Double>> maritalstatusparameterList = new ArrayList<Pair<String, Double>>();
		maritalstatusparameterList.add(new Pair<String, Double>("Single", 0.3));
		maritalstatusparameterList.add(new Pair<String, Double>("Married", 0.5));
		maritalstatusparameterList.add(new Pair<String, Double>("Divorced", 0.1));
		maritalstatusparameterList.add(new Pair<String, Double>("Widowed", 0.06));
		maritalstatus.setParameterList(maritalstatusparameterList);
		//userAttrributesList.add(maritalstatus);
		
		AttributeModel profession = new AttributeModel();
		profession.setName("Profession");	
		profession.setDescription("The Job a person is currently employed on");
		List<Pair<String, Double>> professionparameterList = new ArrayList<Pair<String, Double>>();
		professionparameterList.add(new Pair<String, Double>("Manager", 0.12));
		professionparameterList.add(new Pair<String, Double>("Professional", 0.17));
		professionparameterList.add(new Pair<String, Double>("Service", 0.14));
		professionparameterList.add(new Pair<String, Double>("Sales and office", 0.18));
		professionparameterList.add(new Pair<String, Double>("Natural resources construction and maintenance", 0.07));
		professionparameterList.add(new Pair<String, Double>("Production transportation and material moving", 0.09));
		professionparameterList.add(new Pair<String, Double>("Student", 0.23));
		profession.setParameterList(professionparameterList);
		//userAttrributesList.add(profession);
		
		AttributeModel politicalOrientation = new AttributeModel();
		politicalOrientation.setName("Political Orientation");
		politicalOrientation.setDescription("The political orientation is the type of ideology a person has.");
		List<Pair<String, Double>> politicalOrientationparameterList = new ArrayList<Pair<String, Double>>();
		politicalOrientationparameterList.add(new Pair<String, Double>("Far Left", 0.09));
		politicalOrientationparameterList.add(new Pair<String, Double>("Left", 0.35));
		politicalOrientationparameterList.add(new Pair<String, Double>("Center Left", 0.18));
		politicalOrientationparameterList.add(new Pair<String, Double>("Center", 0.18));
		politicalOrientationparameterList.add(new Pair<String, Double>("Center Right", 0.10));
		politicalOrientationparameterList.add(new Pair<String, Double>("Right", 0.08));
		politicalOrientationparameterList.add(new Pair<String, Double>("Far Right", 0.01));
		politicalOrientation.setParameterList(politicalOrientationparameterList);
		//userAttrributesList.add(politicalOrientation);
		
		AttributeModel sexualOrientation = new AttributeModel();
		sexualOrientation.setName("Sexual Orientation");
		sexualOrientation.setDescription("The sexual orientation of a person");
		List<Pair<String, Double>> sexualOrientationparameterList = new ArrayList<Pair<String, Double>>();
		sexualOrientationparameterList.add(new Pair<String, Double>("Asexual", 0.01));
		sexualOrientationparameterList.add(new Pair<String, Double>("Bisexual", 0.02));
		sexualOrientationparameterList.add(new Pair<String, Double>("Heterosexual", 0.95));
		sexualOrientationparameterList.add(new Pair<String, Double>("Homosexual", 0.02));
		sexualOrientation.setParameterList(sexualOrientationparameterList);
		//userAttrributesList.add(sexualOrientation);
		
		
		
		
	
	}
	
	//Setters and getters
	public String getInputFile1() { return inputFile1; }
	public void setInputFile1(String inputFile1) { this.inputFile1 = inputFile1; }
	
	public String getInputFile2() { return inputFile2; }
	public void setInputFile2(String inputFile2) { this.inputFile2 = inputFile2; }
	
	public String getOutFile() { return outFile; }
	public void setOutFile(String outFile) { this.outFile = outFile; }
	
	public String getOutgFile() { return outgFile; }
	public void setOutgFile(String outgFile) { this.outgFile = outgFile; }
	
	public String getOut1File() { return out1File; }
	public void setOut1File(String out1File) { this.out1File = out1File; }
	
	public String getOut2File() { return out2File; }
	public void setOut2File(String out2File) { this.out2File = out2File; }
	
	public int getNumCommunities() { return numCommunities; }
	public void setNumCommunities(int numCommunities) { this.numCommunities = numCommunities; }
	
	public int getSeedSize() { return seedSize; }
	public void setSeedSize(int seedSize) { this.seedSize = seedSize; }
	
	public int getRandomness() { return randomness; }
	public void setRandomness(int randomness) { this.randomness = randomness; }
	
	public List<AttributeModel> getUserAttrributesList() { return userAttrributesList; }
	
	//Add and remove user attributes
	public void setAttributeList(List<AttributeModel> attributeList) {this.userAttrributesList = attributeList; }  
	
	public void removeUserAttribute(String attributeName) {
		for(AttributeModel attribute : userAttrributesList) {
			if(attribute.getName().equals(attributeName)) {
				userAttrributesList.remove(attribute);
			}
			else {
				System.out.println("The attribute " + attributeName + " doesn't exist");
			}
		}
	}

}
