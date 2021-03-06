package genDataNOapplication.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private List<Pair<List<Integer>, Integer>> profileList;
	
	//Each Profile Assigned to one community
	int profileCommunityAssaign[] = new int[10];
	
	//Class constructor
	public ConfigurationModel() {
		
		this.inputFile1 = "./resources/Input_files/inputFile1_karate.csv";
		this.inputFile2 = "./resources/Input_files/inputFile2_karatemodauth.csv";
		this.outFile = "./resources/Output_files/karate_out.csv";
		this.outgFile = "./resources/Output_files/karate_outg.csv";
		this.out1File = "./resources/Output_files/karate_out1.csv"; 
		this.out2File = "./resources/Output_files/karate_out2.csv";
		
		userAttrributesList = new ArrayList<AttributeModel>();
		profileList = new ArrayList<Pair<List<Integer>, Integer>>();
		
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
		ReligionparameterList.add(new Pair<String, Double>("Buddhist", 0.1));
		ReligionparameterList.add(new Pair<String, Double>("Christian", 0.30));
		ReligionparameterList.add(new Pair<String, Double>("Hindu", 0.13));
		ReligionparameterList.add(new Pair<String, Double>("Jewish", 0.1));
		ReligionparameterList.add(new Pair<String, Double>("Muslim", 0.25));
		ReligionparameterList.add(new Pair<String, Double>("Sikh", 0.0));
		ReligionparameterList.add(new Pair<String, Double>("Traditional Spirituality", 0.0));
		ReligionparameterList.add(new Pair<String, Double>("Other religions", 0.10));
		ReligionparameterList.add(new Pair<String, Double>("No religious affiliation", 0.02));
		religion.setParameterList(ReligionparameterList);
		userAttrributesList.add(religion);
		
		AttributeModel maritalstatus = new AttributeModel();
		maritalstatus.setName("Marital Status");
		maritalstatus.setDescription("A person can be single, in a relationship, married, etc.");
		List<Pair<String, Double>> maritalstatusparameterList = new ArrayList<Pair<String, Double>>();
		maritalstatusparameterList.add(new Pair<String, Double>("Single", 0.3));
		maritalstatusparameterList.add(new Pair<String, Double>("Married", 0.5));
		maritalstatusparameterList.add(new Pair<String, Double>("Divorced", 0.1));
		maritalstatusparameterList.add(new Pair<String, Double>("Widowed", 0.1));
		maritalstatus.setParameterList(maritalstatusparameterList);
		userAttrributesList.add(maritalstatus);
		
		AttributeModel profession = new AttributeModel();
		profession.setName("Profession");	
		profession.setDescription("The Job a person is currently employed on");
		List<Pair<String, Double>> professionparameterList = new ArrayList<Pair<String, Double>>();
		professionparameterList.add(new Pair<String, Double>("Manager", 0.12));
		professionparameterList.add(new Pair<String, Double>("Professional", 0.17));
		professionparameterList.add(new Pair<String, Double>("Service", 0.14));
		professionparameterList.add(new Pair<String, Double>("Sales and office", 0.18));
		professionparameterList.add(new Pair<String, Double>("Natural resources construction", 0.07));
		professionparameterList.add(new Pair<String, Double>("Production transportation", 0.09));
		professionparameterList.add(new Pair<String, Double>("Student", 0.23));
		profession.setParameterList(professionparameterList);
		userAttrributesList.add(profession);
		
		AttributeModel politicalOrientation = new AttributeModel();
		politicalOrientation.setName("Political Orientation");
		politicalOrientation.setDescription("The political orientation is the type of ideology a person has.");
		List<Pair<String, Double>> politicalOrientationparameterList = new ArrayList<Pair<String, Double>>();
		politicalOrientationparameterList.add(new Pair<String, Double>("Far Left", 0.09));
		politicalOrientationparameterList.add(new Pair<String, Double>("Left", 0.35));
		politicalOrientationparameterList.add(new Pair<String, Double>("Center Left", 0.18));
		politicalOrientationparameterList.add(new Pair<String, Double>("Center", 0.18));
		politicalOrientationparameterList.add(new Pair<String, Double>("Center Right", 0.10));
		politicalOrientationparameterList.add(new Pair<String, Double>("Right", 0.09));
		politicalOrientationparameterList.add(new Pair<String, Double>("Far Right", 0.01));
		politicalOrientation.setParameterList(politicalOrientationparameterList);
		userAttrributesList.add(politicalOrientation);
		
		AttributeModel sexualOrientation = new AttributeModel();
		sexualOrientation.setName("Sexual Orientation");
		sexualOrientation.setDescription("The sexual orientation of a person");
		List<Pair<String, Double>> sexualOrientationparameterList = new ArrayList<Pair<String, Double>>();
		sexualOrientationparameterList.add(new Pair<String, Double>("Asexual", 0.01));
		sexualOrientationparameterList.add(new Pair<String, Double>("Bisexual", 0.02));
		sexualOrientationparameterList.add(new Pair<String, Double>("Heterosexual", 0.95));
		sexualOrientationparameterList.add(new Pair<String, Double>("Homosexual", 0.02));
		sexualOrientation.setParameterList(sexualOrientationparameterList);
		userAttrributesList.add(sexualOrientation);
		

		List<Integer> profileParams0 = new ArrayList<Integer>();
		profileParams0.addAll(Arrays.asList(2, 0, 3, 8, 1, 3, 3, 2));
		Pair<List<Integer>, Integer> profile0 = new Pair<List<Integer>, Integer>(profileParams0, 216);
		profileList.add(profile0);
		
		List<Integer> profileParams1 = new ArrayList<Integer>();
		profileParams1.addAll(Arrays.asList(1, 1, 4, 0, 2, 3, 3, 2));
		Pair<List<Integer>, Integer> profile1 = new Pair<List<Integer>, Integer>(profileParams1, 172);
		profileList.add(profile1);
		
		List<Integer> profileParams2 = new ArrayList<Integer>();
		profileParams2.addAll(Arrays.asList(0, 0, 0, 1, 0, 6, 2, 2));
		Pair<List<Integer>, Integer> profile2 = new Pair<List<Integer>, Integer>(profileParams2, 211);
		profileList.add(profile2);
		
		List<Integer> profileParams3 = new ArrayList<Integer>();
		profileParams3.addAll(Arrays.asList(0, 1, 2, 4, 0, 1, 4, 2));
		Pair<List<Integer>, Integer> profile3 = new Pair<List<Integer>, Integer>(profileParams3, 97);
		profileList.add(profile3);

		List<Integer> profileParams4 = new ArrayList<Integer>();
		profileParams4.addAll(Arrays.asList(4, 0, 1, 2, 3, 4, 5, 2));
		Pair<List<Integer>, Integer> profile4 = new Pair<List<Integer>, Integer>(profileParams4, 81);
		profileList.add(profile4);

		List<Integer> profileParams5 = new ArrayList<Integer>();
		profileParams5.addAll(Arrays.asList(5, 1, 5, 3, 1, 5, 0, 2));
		Pair<List<Integer>, Integer> profile5 = new Pair<List<Integer>, Integer>(profileParams5, 157);
		profileList.add(profile5);

		List<Integer> profileParams6 = new ArrayList<Integer>();
		profileParams6.addAll(Arrays.asList(0, 1, 2, 1, 0, 1, 4, 2));
		Pair<List<Integer>, Integer> profile6 = new Pair<List<Integer>, Integer>(profileParams6, 24);
		profileList.add(profile6);
		
		List<Integer> profileParams7 = new ArrayList<Integer>();
		profileParams7.addAll(Arrays.asList(0, 1, 2, 3, 0, 1, 4, 2));
		Pair<List<Integer>, Integer> profile7 = new Pair<List<Integer>, Integer>(profileParams7, 5);
		profileList.add(profile7);
		
		List<Integer> profileParams8 = new ArrayList<Integer>();
		profileParams8.addAll(Arrays.asList(4, 0, 1, 2, 3, 4, 1, 2));
		Pair<List<Integer>, Integer> profile8 = new Pair<List<Integer>, Integer>(profileParams8, 28);
		profileList.add(profile8);

		List<Integer> profileParams9 = new ArrayList<Integer>();
		profileParams9.addAll(Arrays.asList(4, 0, 1, 2, 3, 4, 0, 2));
		Pair<List<Integer>, Integer> profile9 = new Pair<List<Integer>, Integer>(profileParams9, 9);
		profileList.add(profile9);
		
		profileCommunityAssaign[0] = 0; // assign profile 0 to community 0
		profileCommunityAssaign[1] = 2; // assign profile 2 to community 1
		profileCommunityAssaign[2] = 1; // assign profile 1 to community 2
		profileCommunityAssaign[3] = 4; // assign profile 4 to community 3
		profileCommunityAssaign[4] = 5; // assign profile 5 to community 4
		profileCommunityAssaign[5] = 3; // assign profile 3 to community 5
		profileCommunityAssaign[6] = 7; // assign profile 7 to community 6
		profileCommunityAssaign[7] = 9; // assign profile 9 to community 7
		profileCommunityAssaign[8] = 6; // assign profile 6 to community 8
		profileCommunityAssaign[9] = 8; // assign profile 8 to community 9

		
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
	
	public List<Pair<List<Integer>, Integer>> getProfileList() { return profileList; }
	public void setProfileList(List<Pair<List<Integer>, Integer>> profileList) { this.profileList = profileList; }
	
	public int[] getProfileCommunityAssaignment() { return profileCommunityAssaign; }
	public void setProfileCommunityAssaignment(int[] profileCommunityAssaign) { this.profileCommunityAssaign = profileCommunityAssaign; }
	
	//Add user attributes
	public void setAttributeList(List<AttributeModel> attributeList) {this.userAttrributesList = attributeList; }  
	

}
