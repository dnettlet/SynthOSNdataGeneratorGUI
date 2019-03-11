package genDataNOapplication.configuration;

//Class that stores all the customizable settings for the program execution
public class ConfigurationModel {
	
	//Input and output file paths
	private String inputFile1;
	private String inputFile2;
	private String outFile;
	private String outgFile;
	private String out1File;
	private String out2File;
	
	//Class constructor
	public ConfigurationModel() {
		
		this.inputFile1 = "./resources/files/karate.csv";
		this.inputFile2 = "./resources/files/karatemodauth.csv";
		this.outFile = "./resources/files/karate_out.csv";
		this.outgFile = "./resources/files/karate_outg.csv";
		this.out1File = "./resources/files/karate_out1.csv"; 
		this.out2File = "./resources/files/karate_out2.csv";
		
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
	

}
