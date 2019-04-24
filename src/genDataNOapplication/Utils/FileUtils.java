package genDataNOapplication.Utils;
import java.io.File;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

public class FileUtils {

   public static void load(File file) {
	   
	   try {
		   DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	         Document doc = dBuilder.parse(file);
	         doc.getDocumentElement().normalize();
	         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

    		 Element eElement = (Element) doc.getElementsByTagName("inputFiles").item(0);
    		 System.out.println("InputFile1: " + eElement.getElementsByTagName("inputFile1").item(0).getTextContent());
    		 System.out.println("InputFile2: " + eElement.getElementsByTagName("inputFile2").item(0).getTextContent());


        	 Element outFilesElem = (Element) doc.getElementsByTagName("outputFiles").item(0);
	         System.out.println("OutFile: " + outFilesElem.getElementsByTagName("outFile").item(0).getTextContent());
	         System.out.println("OutgFile: " + outFilesElem.getElementsByTagName("outgFile").item(0).getTextContent());
	         System.out.println("Out1File: " + outFilesElem.getElementsByTagName("out1File").item(0).getTextContent());
	         System.out.println("Out2File: " + outFilesElem.getElementsByTagName("out2File").item(0).getTextContent());
	         

        	 Element varElem = (Element) doc.getElementsByTagName("vars").item(0);
        	 System.out.println("Numcommunities: " + varElem.getElementsByTagName("numCommunities").item(0).getTextContent());
        	 System.out.println("SeedSize: " + varElem.getElementsByTagName("seedSize").item(0).getTextContent());
        	 System.out.println("Randomness: " + varElem.getElementsByTagName("randomness").item(0).getTextContent());
	         
	         
	   } catch (Exception e) {
	         e.printStackTrace();
	      }

      /*try {
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
         Document doc = dBuilder.parse(file);
         doc.getDocumentElement().normalize();
         System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
         NodeList nList = doc.getElementsByTagName("student");
         System.out.println("----------------------------");
         
         for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
               Element eElement = (Element) nNode;
               System.out.println("Student roll no : " 
                  + eElement.getAttribute("rollno"));
               System.out.println("First Name : " 
                  + eElement
                  .getElementsByTagName("firstname")
                  .item(0)
                  .getTextContent());
               System.out.println("Last Name : " 
                  + eElement
                  .getElementsByTagName("lastname")
                  .item(0)
                  .getTextContent());
               System.out.println("Nick Name : " 
                  + eElement
                  .getElementsByTagName("nickname")
                  .item(0)
                  .getTextContent());
               System.out.println("Marks : " 
                  + eElement
                  .getElementsByTagName("marks")
                  .item(0)
                  .getTextContent());
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }*/
   }
}
