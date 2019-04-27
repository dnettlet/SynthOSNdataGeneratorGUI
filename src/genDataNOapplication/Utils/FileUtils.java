package genDataNOapplication.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import genDataNOapplication.model.AttributeModel;
import genDataNOapplication.model.ConfigurationModel;
import javafx.util.Pair;

import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class FileUtils {

   public static ConfigurationModel loadConfig(File file, ConfigurationModel configuration) {
	   
	   try {
		   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(file);
	         doc.getDocumentElement().normalize();
	         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

    		 Element eElement = (Element) doc.getElementsByTagName("inputFiles").item(0);
    		 System.out.println("InputFile1: " + eElement.getElementsByTagName("inputFile1").item(0).getTextContent());
    		 configuration.setInputFile1(eElement.getElementsByTagName("inputFile1").item(0).getTextContent());
    		 System.out.println("InputFile2: " + eElement.getElementsByTagName("inputFile2").item(0).getTextContent());
    		 configuration.setInputFile2(eElement.getElementsByTagName("inputFile2").item(0).getTextContent());

        	 Element outFilesElem = (Element) doc.getElementsByTagName("outputFiles").item(0);
	         System.out.println("OutFile: " + outFilesElem.getElementsByTagName("outFile").item(0).getTextContent());
	         configuration.setOutFile(outFilesElem.getElementsByTagName("outFile").item(0).getTextContent());
	         System.out.println("OutgFile: " + outFilesElem.getElementsByTagName("outgFile").item(0).getTextContent());
	         configuration.setOutgFile(outFilesElem.getElementsByTagName("outgFile").item(0).getTextContent());
	         System.out.println("Out1File: " + outFilesElem.getElementsByTagName("out1File").item(0).getTextContent());
	         configuration.setOut1File(outFilesElem.getElementsByTagName("out1File").item(0).getTextContent());
	         System.out.println("Out2File: " + outFilesElem.getElementsByTagName("out2File").item(0).getTextContent());
	         configuration.setOut2File(outFilesElem.getElementsByTagName("out2File").item(0).getTextContent());

        	 Element varElem = (Element) doc.getElementsByTagName("vars").item(0);
        	 System.out.println("Numcommunities: " + varElem.getElementsByTagName("numCommunities").item(0).getTextContent());
        	 configuration.setNumCommunities(Integer.parseInt(varElem.getElementsByTagName("numCommunities").item(0).getTextContent()));
        	 System.out.println("SeedSize: " + varElem.getElementsByTagName("seedSize").item(0).getTextContent());
        	 configuration.setSeedSize(Integer.parseInt(varElem.getElementsByTagName("seedSize").item(0).getTextContent()));
        	 System.out.println("Randomness: " + varElem.getElementsByTagName("randomness").item(0).getTextContent());
        	 configuration.setRandomness(Integer.parseInt(varElem.getElementsByTagName("randomness").item(0).getTextContent()));
	         
        	 
        	 List<AttributeModel> attributeList = new ArrayList<AttributeModel>();
        	 NodeList attributeNList = doc.getElementsByTagName("attribute");
        	 for(int i = 0; i < attributeNList.getLength(); i++) {
        		 Node attributeNode = attributeNList.item(i);
        		 if(attributeNode.getNodeType() == Node.ELEMENT_NODE) {
            		 Element attributeElem = (Element) attributeNode;
            		 AttributeModel attribute = new AttributeModel();
            		 System.out.println("Attribute Name: " + attributeElem.getAttribute("name"));
            		 attribute.setName(attributeElem.getAttribute("name"));
            		 System.out.println("Attribute Description: " + attributeElem.getElementsByTagName("description").item(0).getTextContent());
            		 attribute.setDescription(attributeElem.getElementsByTagName("description").item(0).getTextContent());
            		 
            		 List<Pair<String, Double>> parameterList = new ArrayList<Pair<String, Double>>();
            		 NodeList paramNList = attributeElem.getElementsByTagName("param");
            		 System.out.println("Number of params: " + paramNList.getLength());
            		 for(int j = 0; j < paramNList.getLength(); j++) {
            			 Element paramElem = (Element) paramNList.item(j);
            			 System.out.println("Value: " + paramElem.getElementsByTagName("value").item(0).getTextContent());
            			 String value = paramElem.getElementsByTagName("value").item(0).getTextContent();
            			 System.out.println("Frequency: " + paramElem.getElementsByTagName("frequency").item(0).getTextContent());
            			 Double frequency = Double.parseDouble(paramElem.getElementsByTagName("frequency").item(0).getTextContent());
            			 Pair<String, Double> param = new Pair<String, Double>(value, frequency);
            			 parameterList.add(param);
            		 }
            		 attribute.setParameterList(parameterList);
            		 attributeList.add(attribute);
        		 }
        	 }
        	 configuration.setAttributeList(attributeList);
        	 
        	 List<Pair<List<Integer>, Integer>> profileList = new ArrayList<Pair<List<Integer>, Integer>>();
        	 NodeList profileNList = doc.getElementsByTagName("profile");
        	 for(int i = 0; i < profileNList.getLength(); i++) {
        		 Node profileNode = profileNList.item(i);
        		 if(profileNode.getNodeType() == Node.ELEMENT_NODE) {
        			 Element profileElem = (Element) profileNode;
        			 System.out.println("Profile ID: " + profileElem.getAttribute("id"));
        			 System.out.println("Params: " + profileElem.getElementsByTagName("params").item(0).getTextContent());
        			 String[] paramArray = profileElem.getElementsByTagName("params").item(0).getTextContent().split(",");
        			 List<Integer> profileParamList = new ArrayList<Integer>();
        			 for(int j = 0; j < paramArray.length; j++) {
        				 profileParamList.add(Integer.parseInt(paramArray[j]));
        			 }
        			 System.out.println("Frequency: " + profileElem.getElementsByTagName("frequency").item(0).getTextContent());
        			 int frequency = Integer.parseInt(profileElem.getElementsByTagName("frequency").item(0).getTextContent());
        			 Pair<List<Integer>, Integer> profile = new Pair<List<Integer>, Integer>(profileParamList, frequency);
        			 profileList.add(profile);
        			 System.out.println("Community: " + profileElem.getElementsByTagName("community").item(0).getTextContent());
        			 int profileCommunityAssaign[] = configuration.getProfileCommunityAssaignment();
        			 int community = Integer.parseInt(profileElem.getElementsByTagName("community").item(0).getTextContent());
        			 int profileID = Integer.parseInt(profileElem.getAttribute("id"));
        			 profileCommunityAssaign[community] = profileID;
        			 configuration.setProfileCommunityAssaignment(profileCommunityAssaign);
        		 }
        	 }
        	 
        	 
        	 return configuration;
	         
	   } catch (Exception e) {
	         e.printStackTrace();
	         return null;
	      }
   }
}
