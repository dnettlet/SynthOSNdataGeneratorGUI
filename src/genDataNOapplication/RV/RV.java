// Copyright (C) 2018  David F. Nettleton (david.nettleton@upf.edu)
// License: GNU GENERAL PUBLIC LICENSE v3.0   See LICENSE for the full license.

package genDataNOapplication.RV;

import java.io.*;
import java.lang.*;
//import java.math.*;
import java.util.Collections;
import java.util.Vector;
import java.lang.Math;
import java.lang.Object;
import java.io.Serializable;
import java.util.Random;
import java.util.List;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.*;

import genDataNOapplication.Dijkstra.AssignSeeds2;
import genDataNOapplication.Dijkstra.FindMedoid;
import genDataNOapplication.User.User;
import genDataNOapplication.community.Community;
import genDataNOapplication.configuration.ConfigurationModel;
import genDataNOapplication.dataFile.dataFile;
import genDataNOapplication.doCalcs.doCalcs;

public class RV{
	
	public ConfigurationModel configuration;
	
static RV RVp;
final static int  NUMVARS = 4;
int user2;
public static Hashtable Users = new Hashtable();
public static Vector vecKeys = new Vector();

public static Hashtable Communities = new Hashtable();

//public static Vector Bridges  = new Vector (); // Top bridges in current graph
//public static Vector Hubs     = new Vector (); // Top hubs in current graph

//public static Vector Communities     = new Vector (); // Communities in current graph

public static Vector seeds        = new Vector (); // used in 'rassigndatageneralizedsensattr4 and dijkstra
public static Vector seeds3       = new Vector (); // used in 'rassigndatageneralizedsensattr4 and dijkstra
public static Vector seedsbesttonow = new Vector (); // saves the best result obtained
public static Vector invalids = new Vector (); // list of nodes which do not comply with the distance requirement

static Vector vgroupsf    = new Vector (); 
static Vector vgroups     = new Vector ();

public static Vector medoidids     = new Vector ();
public static Vector medoidgids     = new Vector ();
public static Vector medoiddists     = new Vector ();

public static double max_avgL=0, min_avgL=999999, max_stdevL=0, min_stdevL=999999, max_degreeL=0, min_degreeL=0;
public static double max_edgesL=0, min_edgesL=999999, max_ccL=0, min_ccL=999999; 

/* used as parameters to be estimated for distance function */

//weights derived from whole karate
//private static double alpha=1.123773125, beta=-0.23265777, gamma=1.4047586635, delta=0.264251769, epsilon=0.628574154;

//calibrated weights hep-th 19 10
//private static double alpha=2.036198827, beta=2.899113849, gamma=-0.512900449, delta=0.363877493, epsilon=0.863483746;

//calibrated weights enron 19 10
private static double alpha=2.818975514, beta=0.735939979, gamma=1.957545111, delta=0.003374712, epsilon=-0.288028499;

//calibrated weights wikivote 19 10
//private static double alpha=3.067287079, beta=-0.255742136, gamma=1.332133616, delta=0.013077081, epsilon=-0.116390063;

public double RVp(ConfigurationModel configuration) 
{
	this.configuration = configuration;
	
    int NUMVARS = 4;
    
    user2=0;
    
	int i=0,j=0,k=0, yaesta=0;
	
	double ret=0;
	
	int vec[];
	double corr = 0;
	double[] v1 = {1,2,3,4};
	double[] v2 = {1,2,3,4};
	
	//***********************************TEST DATASETS************************************
 	
	//String nomFixterIn1    = "./resources/files/amazongraph.csv";  
	//String nomFixterIn2    = "./resources/files/amazoncommunities.csv";  
	//String nomFixterOut    = "./resources/files/amazon_out.csv"; 
	//String nomFixterOutg   = "./resources/files/amazon_outg.csv"; 
	//String nomFixterOut1   = "./resources/files/amazon_out1.csv"; 
	//String nomFixterOut2   = "./resources/files/amazon_out2.csv";
	
	//String nomFixterIn1    = "./resources/files/youtubegraph.csv";  
	//String nomFixterIn2    = "./resources/files/youtubecommunities.csv";  
	//String nomFixterOut    = "./resources/files/youtube_out.csv"; 
	//String nomFixterOutg   = "./resources/files/youtube_outg.csv"; 
	//String nomFixterOut1   = "./resources/files/youtube_out1.csv"; 
	//String nomFixterOut2   = "./resources/files/youtube_out2.csv"; 
	
	//String nomFixterIn1    = "./resources/files/livejournalgraph.csv";  
	//String nomFixterIn2    = "./resources/files/livejournalcommunities.csv";  
	//String nomFixterOut    = "./resources/files/livejournal_out.csv"; 
	//String nomFixterOutg   = "./resources/files/livejournal_outg.csv"; 
	//String nomFixterOut1   = "./resources/files/livejournal_out1.csv"; 
	//String nomFixterOut2   = "./resources/files/livejournal_out2.csv";
	
	//String nomFixterIn1    = "./resources/files/1kby30k.csv";  
	//String nomFixterIn2    = "./resources/files/1kby30kmodauthb.csv";  
	//String nomFixterOut    = "./resources/files/1kby30k_out.csv"; 
	//String nomFixterOutg   = "./resources/files/1kby30k_outg.csv";
	//String nomFixterOut1   = "./resources/files/1kby30k_out1.csv"; 
	//String nomFixterOut2   = "./resources/files/1kby30k_out2.csv";
	
	//String nomFixterIn1    = "./resources/files/rmatgraph.csv";  
	//String nomFixterIn2    = "./resources/files/rmatcommunities.csv";  
	//String nomFixterOut    = "./resources/files/rmat_out.csv"; 
	//String nomFixterOutg   = "./resources/files/rmat_outg.csv";
	//String nomFixterOut1   = "./resources/files/rmat_out1.csv"; 
	//String nomFixterOut2   = "./resources/files/rmat_out2.csv";
	
	String nomFixterIn1    = configuration.getInputFile1(); 
	String nomFixterIn2    = configuration.getInputFile2();
	String nomFixterOut    = configuration.getOutFile();
	String nomFixterOutg   = configuration.getOutgFile();
	String nomFixterOut1   = configuration.getOut1File();
	String nomFixterOut2   = configuration.getOut2File();
     
  	//  *** Obtener los datos del fichero de input ****/
    
  	ret = leer_datos_de_casos(nomFixterIn1, nomFixterIn2, nomFixterOut, nomFixterOutg, nomFixterOut1, nomFixterOut2, user2, NUMVARS);
     
    //corr = Correlation.Correlationp(v1, v2);
    //System.out.println("corr= "+corr);
  	
return ret;
} //fin de RVp


/********************** Output Calculations in File ***********************/
public static int writeData(BufferedWriter output, BufferedWriter outputg){

	int THRESHOLD=9999999;
	String str="";
	User nw,nw2; double written=0;
	int user1=0,user2=0,i=0, friends_ok=0;

	
	String age       = "";
	Double weight    = 0.0;
	String gender    = "";
	String residence = "";
	
	String religion = "";
	String maritalstatus = "";
	String profession = "";
	String politicalorientation = "";
	String sexualorientation = "";
	
	
	String like1 = "", like2 = "",like3 = "";
	String numfriends = "";
	
	int mod=0, modf=0, COM=5;
	

	
	try{
		output.write("user;age;gender;residence;religion;maritalstatus;profession;politicalorientation;sexualorientation;numfriends;like1;like2;like3;classvalue;auth;mod");
		output.newLine();
		outputg.write("user;userf;linkweight");
		outputg.newLine();
		
		Enumeration en1 = RV.Users.keys();
		
	while (en1.hasMoreElements()){
		nw = (User)RV.Users.get((Integer)en1.nextElement());
		user1 = nw.getUser();
		//System.out.println("write out a line. user1 = "+user1);
		
		age       = nw.getAge();
		gender    = nw.getGender();
		residence = nw.getResidence();
		
		religion = nw.getReligion();
		maritalstatus = nw.getMaritalStatus();
		profession = nw.getProfession();
		politicalorientation = nw.getPoliticalOrientation();
		sexualorientation = nw.getSexualOrientation();
		
		//float auth = nw.getAUTH(); 
		//mod  = nw.getMOD();
		
		double ndeg = nw.getNormDegree();
		String ndegs = String.format("%f", ndeg);
		String ndegs2 = ndegs.replaceAll(",", ".");

		mod  = nw.getCommunity(0); // do this for the moment so as not to change the structure
		
		
		like1 = nw.getLike(1);
		like2 = nw.getLike(2);
		like3 = nw.getLike(3);
		

		String classvalue = "";
		
		if ((religion.equals("Muslim"))
		|| (politicalorientation.equals("Left") || politicalorientation.equals("Far Left")))
		//&& (sexualorientation != "Heterosexual"))
			classvalue = "YES";
		else
			classvalue = "NO";
		
		
		// now calculate the % of neighbors who have the same religion as the current user
		double numFriendsR=0;
		for (i = 0; i < nw.friends.size(); i++)
		{
			user2=nw.friends.get(i);
			User nf = (User)RV.Users.get(user2);
	    	String religionf = nf.getReligion();
	    	if (religion.equals(religionf))
	    		++numFriendsR;
		}
		numFriendsR = (double)((double)numFriendsR / (double)nw.friends.size());
		
		if (numFriendsR < 0.35)
		    numfriends = "LOW";
		else if ((0.35 < numFriendsR) && (numFriendsR < 0.60))
			numfriends = "MEDIUM";
		else
			numfriends = "HIGH";
	
		
		if (age != null)
		{
			if (!age.equals(""))
			{
			//System.out.println("age: "+age+"x");
			//System.out.println("write out a line. user1 = "+user1);
			//output.write(user1+";"+age+";"+gender+";"+residence+";"+like1+";"+like2+";"+like3+";"+friendsize);
			output.write(user1+";"+age+";"+gender+";"+residence+";"+religion+";"+maritalstatus+";"+profession+";"+politicalorientation+";"+sexualorientation+";"+numfriends+";"+like1+";"+like2+";"+like3+";"+classvalue+";"+ndegs2+";"+mod);
			output.newLine();output.flush();
			}
		}

		if (nw.friends.size() < THRESHOLD)  // ***degree threshold
		{
		if (nw.friends.size() > 0)
		{
			for (i = 0, friends_ok=0; i < nw.friends.size(); i++){
				user2=nw.friends.get(i);

				nw2 = (User)RV.Users.get(user2);
				if (nw2.friends.size() < THRESHOLD) // ***degree threshold
					++friends_ok;
			} //efor
			
			if (friends_ok > 0)
			for (i = 0; i < nw.friends.size(); i++){
				user2=nw.friends.get(i);
				nw2 = (User)RV.Users.get(user2);
				weight = nw.getWeight(i);
				/*if (weight != null)
				{
					outputg.write(user1+","+user2+","+weight);
					System.out.println("user1:"+user1+" user2:"+user2+" weight:"+weight);
				}*/

				if (nw2.friends.size() < THRESHOLD) // ***degree threshold
				{
					modf  = nw2.getMOD();
					//if ((modf==COM) && (mod==COM))
					if (1==1)
					{
						outputg.write(user1+";"+user2+";"+weight);
						outputg.newLine();outputg.flush();
					}
				}
			} //efor
		}
		else if (nw.friends.size() == 1)
		{
			try{
			user2=nw.friends.get(0);
			nw2 = (User)RV.Users.get(user2);
			weight = nw.getWeight(0);
			
			if (nw2 != null)
				if (nw2.friends.size() > 1 && nw2.friends.size() < THRESHOLD);
				{
					modf  = nw2.getMOD();
					//if ((modf==COM) && (mod==COM)) 
					if (1==1)
					{
						outputg.write(user1+";"+user2+";"+weight);
						outputg.newLine();outputg.flush();
					}
				}
			}
			catch(RuntimeException rte){
				rte.printStackTrace();
				System.out.println("\nRun time exception has been caught");
				//return 1;
			}
		}
		else if (nw.friends.size() < 1)
		{
			++written;
			System.out.println("this node's got 0 neighbors: "+user1+" total of these: "+written);
		}
		} //efi <= THRESHOLD
		
	} //ewhile
	} //etry
	catch(IOException ioe){
		ioe.printStackTrace();
		System.out.println("\nsomething went wrong writing the output file");
		return 1;
	}
	return 0;
}

public static int writeData2(BufferedWriter output1, BufferedWriter output2){

	String str="";
	User nw; Community co;
	int user1=0,com1=0,i=0;
	try{
	Enumeration en1 = Users.keys();
	while (en1.hasMoreElements()){
		nw = (User)Users.get((Integer)en1.nextElement());
		user1 = nw.getUser();
		int numcommunitiesforuser = nw.communities.size();
		output1.write(user1+";"+numcommunitiesforuser);
		output1.newLine();output1.flush();
	} //ewhile
	System.out.println();System.out.println();
	Enumeration en2 = Communities.keys();
	while (en2.hasMoreElements()){
		co = (Community)Communities.get((Integer)en2.nextElement());
		com1 = co.getCommunity();
		int numusersincommunity = co.vertexids.size();
		output2.write(com1+";"+numusersincommunity);
		output2.newLine();output2.flush();
	} //ewhile
	} //etry
	catch(Exception e){
		e.printStackTrace();
		System.out.println("\nsomething went wrong writing the output file");
		return 1;
	}
	return 0;
}

/********************** Get Max Degree ***********************/
public static int getMaxDegree(){
	Enumeration en1 = Users.keys();
	
	String str=""; int degree_user2=0;
	int maxDegree=0, degree=0;
	User nw,nw2;
	int user1=0,user2=0,i=0;
	
	while (en1.hasMoreElements()){
		nw = (User)Users.get((Integer)en1.nextElement());
		user1 = nw.getUser();
		degree = nw.friends.size();
		if (degree > maxDegree)
			maxDegree = degree;
	} //ewhile
	return maxDegree;
}

//******************************************************************** */
//Función que lee los datos de casos del fichero "nomFixter" y los     */
//mete en la estructura 'datosD'									   */
//******************************************************************** */
public static double leer_datos_de_casos(String nomFixterIn1, String nomFixterIn2, 
		String nomFixterOut, String nomFixterOutg, String nomFixterOut1, String nomFixterOut2, 
		int user2, int num_values)
{
String ubicFixter = "";
String nomUbicFixterIn1 = ubicFixter+nomFixterIn1;
String nomUbicFixterIn2 = ubicFixter+nomFixterIn2;
String nomUbicFixterOut = ubicFixter+nomFixterOut;
String nomUbicFixterOutg = ubicFixter+nomFixterOutg;
String nomUbicFixterOut1 = ubicFixter+nomFixterOut1;
String nomUbicFixterOut2 = ubicFixter+nomFixterOut2;
String delim = ";";
String isNull = "";
String temp;
Object[] data=null;
int i, j, k;
//num_values es el número de variables, num_cases es el número de casos
data  = new Object[4+1];
String valVar;

String[] s1; String s2;

int len=0, kount=0;
char oldChar='.';
char newChar=',';

long ii=0;
int u1; int u2; double t1 ; double t2;
String delimiter=";";

Integer oneUser=0;
Integer userActual=0;

int maxid=0;

	try{
		FileReader inputFile1 = new FileReader(nomUbicFixterIn1);
		BufferedReader input1= new BufferedReader(inputFile1);
		
		FileReader inputFile2 = new FileReader(nomUbicFixterIn2);
		BufferedReader input2= new BufferedReader(inputFile2);

		dataFile df1= new dataFile();
		dataFile df2= new dataFile();
		dataFile df3= new dataFile();
		dataFile df4= new dataFile();
		
		FileWriter outputFile = new FileWriter(nomUbicFixterOut);
		BufferedWriter output = new BufferedWriter(outputFile);
		
		FileWriter outputFileg = new FileWriter(nomUbicFixterOutg);
		BufferedWriter outputg = new BufferedWriter(outputFileg);
		
		FileWriter outputFile1 = new FileWriter(nomUbicFixterOut1);
		BufferedWriter output1 = new BufferedWriter(outputFile1);
		
		FileWriter outputFile2 = new FileWriter(nomUbicFixterOut2);
		BufferedWriter output2 = new BufferedWriter(outputFile2);
		
		if (inputFile1 == null)
		{
			System.out.println("\nCant open the input file!\n");
			return 1;
		}

		int linecount=0, validlines=0;
		//for (i=0;i<num_cases;i++)
		i=0;
		int user1_old, user3_old, user1, user3, user4, ts1, ts2, alreadythere;
		int num_friends_user1=0, amigos_en_comun=0;
		String stemp;
		user1_old = user1 = 1;
		int weight = 0;
		
		//System.out.println("\n****GET ALL neighbors FOR ALL USERS*****\n");
		
	    String oneToken;
		temp= input1.readLine();
		int kk=0;
		int lines=0;
		int linesread=0;
		num_friends_user1=0;
		if (temp==null) // end of the file....
			;
		else
		{
			while (temp !=null && linesread<1500000) // for all edges
			{
				

			// Process linea sencer amb RegEx per a extreure els variables 
			// d'un cas.  
			//df1.processarLinea(temp, delim, data, 2); 
			
			StringTokenizer tokens = new StringTokenizer(temp);

			
			if (tokens.hasMoreTokens())
				stemp = tokens.nextToken(delim);
			else
				stemp = null;
			
			user1_old=user1;
			if (stemp != null)
				user1 = Integer.parseInt(stemp);
				
			//stemp = (String)data[0];

			//user1 = Integer.parseInt(stemp);

			
			while (stemp !=null && linesread<1500000) // for this line read user1 and user2
			{
				++linesread;
				//stemp = (String)data[1];
				//user2 = Integer.parseInt(stemp);
				
				if (tokens.hasMoreTokens())
					stemp = tokens.nextToken(delim);
				else
					stemp = null;
				
				if (stemp != null)
				{
					user2 = Integer.parseInt(stemp);
				
				
					if (user1 > maxid)
						maxid = user1;
					if (user2 > maxid)
						maxid = user2;
					++kk;


					if (kk>1000)
					{
						kk=0;
						System.out.println("lines: "+lines+" linesread: "+linesread);
						System.out.flush();
					}
				
					if ((Users.containsKey(user1)) == false){
						User nw = new User(user1);
						//nw.afegirFriend(user1); // first time, him/herself
						nw.afegirFriend(user2); // his/her friend
						nw.loadWeight(0.0);
						Users.put(user1, nw);
						++lines;
					} else {
						User nw = (User)Users.get(user1);
						// check this friend is not already there
						alreadythere = 0;
						if (nw.friends.contains((Integer)user2))
							alreadythere = 1;
						//for (j = 0; j < nw.friends.size(); j++)
						//if (nw.friends.get(j) == user2)
						//alreadythere = 1;
						if (alreadythere != 1) // if not there, insert it
						{
							nw.afegirFriend(user2);
							nw.loadWeight(0.0);
							Users.remove(nw.getUser());
							Users.put(nw.getUser(),nw);
						}
					} //eelse
					// al reves
					if ((Users.containsKey(user2)) == false){
						User nw = new User(user2);
						//nw.afegirFriend(user2); // first time, him/herself
						nw.afegirFriend(user1); // his/her friend
						nw.loadWeight(0.0);
						Users.put(user2, nw);
						++lines;
					} else {
						User nw = (User)Users.get(user2);
						// check this friend is not already there
						alreadythere = 0;
						if (nw.friends.contains((Integer)user1))
							alreadythere = 1;
						//for (j = 0; j < nw.friends.size(); j++)
						//if (nw.friends.get(j) == user1)
						//alreadythere = 1;
						if (alreadythere != 1) // if not there, insert it
						{
							nw.afegirFriend(user1);
							nw.loadWeight(0.0);
							Users.remove(nw.getUser());
							Users.put(nw.getUser(),nw);
						}
					} // eelse
					// fin al reves
				
					//System.out.print(" friend "+user1+" = "+friends_user1[num_friends_user1]+"\n");
					++num_friends_user1;
					
					temp= input1.readLine();
					
					if(temp!= null)
						tokens = new StringTokenizer(temp);
					
					if (tokens.hasMoreTokens())
						stemp = tokens.nextToken(delim);
					else
						stemp = null;
					
					user1_old=user1;
					if (stemp != null)
						user1 = Integer.parseInt(stemp);

					
					//df1.processarLinea(temp, delim, data, 2); 
					//stemp = (String)data[0];
					//user1_old=user1;
					//user1 = Integer.parseInt(stemp);
				} //eelse
			} // ewhile. for this line
			} // ewhile for all lines
		} //eelse
	
			System.out.println("\n****Number of users inserted into hashtable :"+lines);
			System.out.println("\n****maxid :"+maxid);
			
			//writeCalcs(output,lines);
			//seeCalcs();
			//if (1==1)
				//return 0;
			
			
			int unchosenlimit=0, max_degree=0;
			
			int numnodes = Users.size();

	
			unchosenlimit=0;
			System.out.println("\n****read community file****");
			/***************************************************************************************************/
			/* Now read in the community file with the user id and community id                                */
			/* for each node and update the user and community structures with these values.                                */
			/***************************************************************************************************/
			float auth=0.0f;
			int mod=0, degree=0;
			int maxdegree=0;
			temp= input2.readLine();
			if (temp==null) // end of the file....
				;
			else
			{
				// Process linea sencer amb RegEx per a extreure els variables 
				// d'un cas.  
				df2.processarLinea(temp, delim, data, 2); 
					
				stemp = (String)data[0];
				user1_old=user1;
				user1 = Integer.parseInt(stemp);
				kk=0; num_friends_user1=0; //maxid=0;
				
				while ( kk>=0 ) // for all users
				{
					stemp = (String)data[1];
					mod = Integer.parseInt(stemp);
					if (Users.get(user1)!=null)
					{
						// Write community into Community class data structure with corresponding node
						if ((Communities.containsKey(mod)) == false){
							Community co = new Community(mod);
							co.loadvertexids((Integer)user1);
							co.loadvertexidvalues((Double)0.0);
							Communities.put(mod, co);
						}
						else // Aggregate node to existing community
						{
						   Community co = (Community)Communities.get(mod);
						   co.loadvertexids((Integer)user1);
						   co.loadvertexidvalues((Double)0.0);
						   Communities.remove(co.getCommunity());
						   Communities.put(co.getCommunity(),co);
						}
					}
			
	
					//System.out.println("node: "+user1+" mod: "+stemp);
					
					if ((Users.containsKey(user1)) != false){
						User nw = (User)Users.get(user1);
						
						++kk;
					
						degree = nw.friends.size();
						if (degree > max_degree)
							max_degree = degree;
				
						nw.loadCommunity(mod);

						Users.remove(nw.getUser());
						Users.put(nw.getUser(),nw);
						//System.out.println("node: "+user1+" brce: "+brce+ "mod: "+mod);
					}	
					
					temp= input2.readLine();
					if (temp==null) // end of the file....
						kk=-10;
					else
					{
						df2.processarLinea(temp, delim, data, 2); 
						stemp = (String)data[0];
						user1_old=user1;
						user1 = Integer.parseInt(stemp);
					}
					//} // eif user1 is there
				} // ewhile. ok, got all friends for all users...
			} // eif not end of file at start
			/***** OK, now got communities and community for each node********/	
			
			System.out.println("users: "+Users.size()+" communities: "+Communities.size());
		
			//writeData2(output1, output2);
			
			//if (1==1) return 0.0;
			
			/*******************************************************************************************************/			

			int i2=0,j2=0;
			double resd=0;
			
			double okd = doCalcs.doCalcsq(Users); // CALCULATE stats
			System.out.println("OK, done the stats");

			//writeData2(output1, output2);
			
			//if (1==1) return 0;
			
			int seedsize = 110; // 110 seeds for 1K synth file, 5K seeds for amazon, 12k seeds for youtube and livejournal
			
			System.out.println("\nNUMBER OF SEEDS TO BE ASSIGNED:"+seedsize);
			
			//System.out.println("seedsize: "+seedsize+" sumalldegrees: "+sumalldegrees+" numnodes: "+numnodes+" maxid: "+maxid);
			
			//double kanon = 2.0; //
			
			// STEP 1. ASSIGN COMMUNITIES AND MEDOIDS TO COMMUNITIES
			FindMedoid fm = new FindMedoid();
			
			Enumeration enc = RV.Communities.keys();
			Community co; int com=0;
			while ( enc.hasMoreElements() )
			{
				co = (Community)RV.Communities.get((Integer)enc.nextElement());
				com = co.getCommunity();
				fm.FindMedoid(co, com);
			}
			
			
			//writeData2();
			
			//if (1==1) return 0.0;
			
			//STEP 2. ASSIGN THE SEEDS
			System.out.println("\n****STEP 1. ASSIGN THE SEEDS*****\n");
			AssignSeeds2 as2 = new AssignSeeds2();
			//seedsize = 30; 
			as2.AssignSeeds2(Users, maxid, seedsize);
			System.out.println("\nNUMBER OF SEEDS2 ASSIGNED:"+RV.seeds.size());
			
			//if (1==1) return 0.0;
			
	  		enc = RV.Communities.keys();
    		while(enc.hasMoreElements())
    		{
        		co = (Community)RV.Communities.get((Integer)enc.nextElement());
        		int co1 = co.getCommunity();
        		co.numberofseedassigned = 0;
    			RV.Communities.remove(co.getCommunity());
    			RV.Communities.put(co.getCommunity(),co);
    		}
    		
			//if (1==1) return 0.0;
			
			//assign the seeds to the communities
			for(i=0;i<RV.seeds.size();i++) // for each seed
			{
				int sid = (Integer)RV.seeds.get(i);
				User nwc = (User)RV.Users.get(sid);
				//int comseed = nwc.getMOD();
				for(j=0;j<nwc.communities.size();j++) // for each community to which this seed belongs
				{
					int comseed = (Integer) nwc.communities.elementAt((Integer)j);
					Community cwc = (Community)RV.Communities.get(comseed);
					int comcom = cwc.getCommunity();
					//System.out.println("comseed: "+comseed+" comcom: "+comcom+" sid: "+sid);
					if (comseed == comcom)
					{
						//System.out.println("hola..........");
						cwc.seedsc.add(sid);
						++cwc.numberofseedassigned;
						Communities.remove(cwc.getCommunity());
						Communities.put(cwc.getCommunity(),cwc);
					}
				} // efor 
			} // efor
			
    		
			//if (1==1) return 0;	
			
			//STEP 2. ASSIGN THE DATA
			System.out.println("\n****STEP 2. ASSIGN THE DATA*****\n");
			int ok = rAssignDataGeneralizedSensAttr4.rAssignDataGeneralizedSensAttr4(numnodes);

			
			// STEP 3. WRITE OUT THE DATA INTO A FILE, ONE ROW PER NODE (1) and ONE ROW PER LINK WITH CORRESPONDING EDGE WEIGHT(2).
			System.out.println("\n****STEP 3. WRITE OUT THE DATA INTO FOUR FILES*****\n");
			writeData(output, outputg);
			writeData2(output1, output2);
	
			System.out.println("\n****END OF PROCESSING*****\n");
			inputFile1.close();
			outputFile.close();
			output.close();
			input1.close();

		//} //end of if for first line of input file read

		
	}
	catch(IOException ioe){
		ioe.printStackTrace();
		System.out.println("\nHa ocurrido un problema al llegir el fixter");
		return 1;
	}
	return 0;
} //fin de funcion 'leer_datos_de_casos'

} // end of public class RVp