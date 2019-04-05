// Copyright (C) 2018  David F. Nettleton (david.nettleton@upf.edu)
// License: GNU GENERAL PUBLIC LICENSE v3.0   See LICENSE for the full license.

package genDataNOapplication.RV;

import java.io.*;
import java.lang.Math;
import java.lang.Object;
import java.io.Serializable;
import java.util.Random;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;

import genDataNOapplication.User.User;
import genDataNOapplication.community.Community;
import genDataNOapplication.configuration.AttributeModel;
import genDataNOapplication.configuration.ConfigurationModel;
import javafx.util.Pair;

public class rAssignDataGeneralizedSensAttr4{
	
	public static ConfigurationModel configuration;
	
public static double dage(String agev1, String agev2)
{
int i=0, j=0;
 
String age[]  = new String[7]; 
String ag="";
int agix = 0;

age[0]  = "18-25";
age[1]  = "26-35";
age[2]  = "36-45";
age[3]  = "46-55";
age[4]  = "56-65";
age[5]  = "66-75";
age[6]  = "76-85";

int pos1=0, pos2=0;

for(i=0; i<age.length; i++)
{
	if (agev1.equals(age[i]) == true ) // found it
	{
		pos1 = i;
	}
	if (agev2.equals(age[i]) == true ) // found it
	{
		pos2 = i;
	}
}
double distage = Math.abs(pos1 - pos2) / 6.0;

return(distage);

}

public static String assignAge(String ag, Random generator, Random generator2, double dagthresh, String[] age,
		                       int agL1, int agL2,int agL3,int agL4)
{

	double distage=0.0;
	int agix=0;
	String ag2="";
	int ranval = 	 ((int)(generator2.nextInt(100))); // rnd will be between 0 and 9
	
	boolean got = false;
	if (ranval < agL1)
		ag2 = ag; // 60% of the time it'll be the same so just assign it.
	else
		while (!got)
		{				
			agix = 	 ((int)(generator.nextInt(12))); // rnd will be between 0 and limit-1
			ag2 = age[agix];

			distage = dage(ag, ag2);
		
			if ((ranval > agL2) && (ranval < agL3) && (distage == dagthresh))
				got = true;
			else if ((ranval > agL4) && (distage > dagthresh))
				got = true;
		}

    return(ag2);

}

public static double dpol(String polv1, String polv2)
{
int i=0, j=0;
 
String pol[]  = new String[7]; 
String po="";
int poix = 0;

pol[0] = "Far Left";
pol[1] = "Left";
pol[2] = "Center Left";
pol[3] = "Center";
pol[4] = "Center Right";
pol[5] = "Right";
pol[6] = "Far Right";

int pos1=0, pos2=0;

for(i=0; i<pol.length; i++)
{
	if (polv1.equals(pol[i]) == true ) // found it
	{
		pos1 = i;
	}
	if (polv2.equals(pol[i]) == true ) // found it
	{
		pos2 = i;
	}
}
double distpol = Math.abs(pos1 - pos2) / 6.0;

return(distpol);

}

public static String assignPol(String pol, Random generator, Random generator2, double dpolthresh, String[] politicalorientation,
        					   int polL1, int polL2, int polL3, int polL4)
{
	double distpol=0.0; int polix=0;
	String pol2="";
	
	int ranval = 	 ((int)(generator2.nextInt(100))); // rnd will be between 0 and 9
	boolean got = false; 
	if (ranval < polL1)
		pol2 = pol; // 60% of the time it'll be the same so just assign it.
	else
		while (!got)
		{
			polix =  ((int)(generator.nextInt(99))); // rnd will be between 0 and limit-1
			pol2 = politicalorientation[polix];

			distpol = dpol(pol, pol2);
	
			if ((ranval > polL2) && (ranval < polL3) && (distpol == dpolthresh))
				got = true;
			else if ((ranval > polL4) && (distpol > dpolthresh))
				got = true;
			//System.out.println("distres: "+distres);
		}
return(pol2);
}


public static double dseo(String seov1, String seov2)
{

double distseo = 0.0;
if (seov1.equals(seov2) == true ) // just check for equality or not
	distseo=0.0;
else
	distseo=1.0;

return(distseo);
}

public static double dseo2(String seov1, String seov2)
{
	String sexual[] = new String[4]; 
	String sex="";
	sexual[0] = "Asexual";sexual[1] = "Bisexual";sexual[2] = "Heterosexual";sexual[3] = "Homosexual";

	double distseo = 0.0;

	if (seov1.equals(seov2) == true ) // found it
		distseo = 0.0;
	else if (seov1.equals(sexual[2]) == false && seov2.equals(sexual[2]) == false) 
		distseo = 0.5;
	else
		distseo = 1.0;

return(distseo);
}

public static String assignSeo(String seo, Random generator, Random generator2, double dseothresh, 
		   				       String[] sexualorientation,
		   				       int seoL1, int seoL2,int seoL3,int seoL4,
		   				       int seoL1h, int seoL2h,int seoL3h,int seoL4h)
{

	double distseo=0.0; int seoix=0;
	String seo2="";
	int ranval = 	 ((int)(generator2.nextInt(100))); // rnd will be between 0 and 9
	
	boolean got = false;
	if (ranval < seoL1 && seo.equals("Heterosexual"))
		seo2 = seo; // 60% of the time it'll be the same so just assign it.
	else if (ranval < seoL1h && !seo.equals("Heterosexual"))
		seo2 = seo; // 40% of the time it'll be the same so just assign it.
	else
		while (!got)
		{
			seoix =  ((int)(generator.nextInt(100))); // rnd will be between 0 and limit-1
			seo2 = sexualorientation[seoix];

			distseo = dseo2(seo, seo2);
			
			//System.out.println("ranval: "+ranval+" distseo: "+distseo+" seo: "+seo+" seo2: "+seo2);
			
			if (seo.equals("Asexual") || seo.equals("Bisexual") || seo.equals("Homosexual"))
			{
				//System.out.println("P1");
				if ((ranval > seoL2h) && (ranval < seoL3h) && (distseo == dseothresh)) // 30% of time !hetero
					got = true;
				else if ((ranval > seoL4h) && (distseo > dseothresh)) // 30% of time hetero
					got = true;
			}
			else // hetero
			{
				//System.out.println("P2");
				if ((ranval > seoL2) && (ranval < seoL3) && (distseo == 0.0)) // 30% of time hetero
					got = true;
				else if ((ranval > seoL4) && (distseo > 0.0)) // 10% of time ! hetero
					got = true;
			}
		}
return(seo2);
}


public static double dres(String residencev1, String residencev2)
{
int i=0, j=0;

String residence[] = new String[6]; 
String county[]    = new String[6]; 
String state[]     = new String[6]; 
String division[]  = new String[6]; 
String region[]    = new String[6]; 

String res="";
int resix = 0;

residence[0] = "Palo Alto";
residence[1] = "Santa Barbara";
residence[2] = "Winthrop";
residence[3] = "Boston";
residence[4] = "Cambridge";
residence[5] = "San Jose";

county[0] = "Santa Clara";
county[1] = "Santa Barbara";
county[2] = "Suffolk";
county[3] = "Suffolk";
county[4] = "Middlesex";
county[5] = "Santa Clara";

state[0] = "California";
state[1] = "California";
state[2] = "Massachusetts";
state[3] = "Massachusetts";
state[4] = "Massachusetts";
state[5] = "California";

division[0] = "Pacific";
division[1] = "Pacific";
division[2] = "New England";
division[3] = "New England";
division[4] = "New England";
division[5] = "Pacific";

region[0] = "West";
region[1] = "West";
region[2] = "Northeast";
region[3] = "Northeast";
region[4] = "Northeast";
region[5] = "West";

int pos1=0, pos2=0;

double distresidence = 0.0;

for(i=0; i<residence.length; i++)
{
	if (residencev1.equals(residence[i]) == true ) // found it
	{
		pos1 = i;
	}
	if (residencev2.equals(residence[i]) == true ) // found it
	{
		pos2 = i;
	}
}
if (residence[pos1].equals(residence[pos2]))
	distresidence = 0.0;
else if (county[pos1].equals(county[pos2]))
	distresidence = 0.25;
else if (state[pos1].equals(state[pos2]))
	distresidence = 0.50;
else if (division[pos1].equals(division[pos2]))
	distresidence = 0.75;
else if (region[pos1].equals(region[pos2]))
	distresidence = 0.90;
else
	distresidence = 1.00;

return(distresidence);
}

public static String assignRes(String res, Random generator, Random generator2, double dresthresh1, 
							   double dresthresh2, double dresthresh3, String[] residence,
							   int resL1, int resL2,int resL3,int resL4)
{

	double distres=0.0; int resix=0;
	String res2="";
	int ranval = 	 ((int)(generator2.nextInt(100))); // rnd will be between 0 and 9
	boolean got = false;
	if (ranval < resL1)
		res2 = res; // 60% of the time it'll be the same so just assign it.
	else
	while (!got)
	{
		resix =  ((int)(generator.nextInt(6))); // rnd will be between 0 and limit-1
		res2 = residence[resix];
		distres = dres(res, res2);
	
		//System.out.println("ranval: "+ranval+" distres: "+distres);

		if ((ranval >= resL1) && (ranval < resL3) && ((distres == dresthresh1) || (distres == dresthresh2)))
			got = true;
		else if ((ranval >= resL4) && (distres == dresthresh3))
			got = true;						

	}
	return(res2);
}


public static double dgen(String genderv1, String genderv2)
{
int i=0, j=0;

String gender[]    = new String[2]; 

String gen="";

int genix = 0;

gender[0] = "male";
gender[1] = "female";

double distgender = 0.0;
if (genderv1.equals(genderv2) == true ) // just check for equality or not
	distgender=0.0;
else
	distgender=1.0;

return(distgender);
}

public static String assignGen(String gen, Random generator, Random generator2, 
                               int genL1, double dgenthresh, String[] gender, String lk23)

{
    int ranval=0, genix=0; 
    boolean got=false; double distgen=0.0;
	String gen2="";
	
	ranval = 	 ((int)(generator2.nextInt(100))); // rnd will be between 0 and limit-1

	while (!got)
	{
		genix =  ((int)(generator.nextInt(2))); // rnd will be between 0 and limit-1
		gen2 = gender[genix];
		distgen = dgen(gen, gen2);
		
		if (ranval < genL1)
		{
			gen2 = gen; // 33% of the time assign the same
			got = true;
		}
		else 
		{
			got = true; // 66% of the time assign randomly (on average will result in 66% of first gender)
		}
	}
	
	
	genix = 	 ((int)(generator.nextInt(10))); // rnd will be between 0 and limit-1
	
	if (genix < 7 && lk23.equals("soccer club")) //bias 70% time male if third like is soccer
		gen2 = gender[0]; //male
	else if (genix >= 7 && lk23.equals("soccer club"))
		gen2 = gender[1]; //female
	
	
return(gen2);
}


public static double drel(String relv1, String relv2)
{

double distrel = 0.0;
if (relv1.equals(relv2) == true ) // just check for equality or not
	distrel=0.0;
else
	distrel=1.0;

return(distrel);
}

public static double drel2(String religionv1, String religionv2)
{
	double distreligion = 0.0;
	String religion[]    	 = new String[9]; 
	religion[0] = "Buddhist";  religion[1] = "Christian"; 
	religion[2] = "Hindu";     religion[3] = "Jewish";    
	religion[4] = "Muslim";    religion[5] = "Sikh";      
	religion[6] = "Traditional Spirituality";  religion[7] = "Other Religions"; religion[8] = "No religious affiliation";


	if (religionv1.equals(religionv2) == true ) // found it
		distreligion = 0.0;

	// Buddhist, Hindu and Sikh are considered quite similar
	else if ( (religionv1.equals(religion[0]) == true || religionv1.equals(religion[2]) == true || religionv1.equals(religion[5]) == true)
			&&  (religionv2.equals(religion[0]) == true || religionv2.equals(religion[2]) == true || religionv2.equals(religion[5]) == true)
			)
		distreligion = 0.5;
	// Christian and Jewish are considered quite similar
	else if ( (religionv1.equals(religion[1]) == true || religionv1.equals(religion[3]) == true )
			&&  (religionv2.equals(religion[1]) == true || religionv2.equals(religion[3]) == true )
			)
		distreligion = 0.5;
	else
		distreligion = 1.0;


return(distreligion);
}

public static String assignRel(String rel, Random generator, Random generator2, double drelthresh, String[] religion,
		int LimRel1,  int LimRel2, int LimRel3, int LimRel4, int LimRel5, int LimRel6, int LimRel7, int LimRel8)
{
	double distrel=0.0; int relix=0;
	String rel2="";
	int ranval = 	 ((int)(generator2.nextInt(100))); // rnd will be between 0 and 9
	
	//int LimRel1 = 40,  LimRel2 = 41, LimRel3 = 86, LimRel4 = 61, LimRel5 = 71, LimRel6 = 81, LimRel7 = 56, LimRel8 = 70; 
	
	
	int LimRel_X = LimRel1, 
		LimRel_J1 = LimRel2, LimRel_J2 = LimRel3,
		LimRel_C1 = LimRel4, LimRel_C2 = LimRel5,
		LimRel_B1 = LimRel4, LimRel_B2 = LimRel6, LimRel_B3 = LimRel3,
		LimRel_H1 = LimRel4, LimRel_H2 = LimRel6, LimRel_H3 = LimRel3,
		LimRel_S1 = LimRel2, LimRel_S2 = LimRel7, LimRel_S3 = LimRel8,
		LimRel_M = LimRel4;

	
	boolean got = false;

	while (!got)
	{
		relix =  ((int)(generator.nextInt(1000))); // rnd will be between 0 and limit-1
		rel2 = religion[relix];
		
		distrel = drel2(rel, rel2);
		
		//System.out.println("ranval: "+ranval+" distrel: "+distrel+" thresh: "+drelthresh);
		
		if (rel.equals("Jewish"))
		{
			if (ranval < 41 )
			{
				rel2 = rel; // 40% of the time it'll be the same so just assign it.
				got = true;
			}
			else if ((ranval > 40) && (ranval < 86)) // 45% of time
			{
				rel2 = "Christian"; // 45% of the time it'll be Christian
				got = true;
			}
			else if ((ranval > 85) && (distrel > drelthresh)) // 15% of time random
				got = true;
		}
		else if (rel.equals("Christian"))
		{
			if (ranval < 61 )
			{
				rel2 = rel; // 60% of the time it'll be the same so just assign it.
				got = true;
			}
			else if ((ranval > 60) && (ranval < 71)) // 10% of time 
			{
				rel2 = "Jewish"; // 10% of the time it'll be Jewish (not so many of them)
				got = true;
			}
			else if ((ranval > 70) && (distrel > drelthresh)) // 30% of time random
				got = true;
		}
		else if (rel.equals("Buddhist"))
		{
			if (ranval < 61 )
			{
				rel2 = rel; // 60% of the time it'll be the same so just assign it.
				got = true;
			}
			else if ((ranval > 60) && (ranval < 81)) // 20% of time 
			{
				rel2 = "Hindu"; // 20% of the time it'll be Hindu (not so many of them)
				got = true;
			}
			else if ((ranval > 80) && (ranval < 86)) // 5% of time 
			{
				rel2 = "Sikh"; // 5% of the time it'll be Sikh (even less of them)
				got = true;
			}
			else if ((ranval > 85) && (distrel > drelthresh)) // 15% of time random
				got = true;
		}
		else if (rel.equals("Hindu"))
		{
			if (ranval < 61 )
			{
				rel2 = rel; // 60% of the time it'll be the same so just assign it.
				got = true;
			}
			else if ((ranval > 60) && (ranval < 81)) // 20% of time 
			{
				rel2 = "Buddhist"; // 20% of the time it'll be Buddhist (not so many of them)
				got = true;
			}
			else if ((ranval > 80) && (ranval < 86)) // 5% of time 
			{
				rel2 = "Sikh"; // 5% of the time it'll be Sikh (even less of them)
				got = true;
			}
			else if ((ranval > 85) && (distrel > drelthresh)) // 15% of time random
				got = true;
		}
		else if (rel.equals("Sikh"))
		{
			if (ranval < 41 )
			{
				rel2 = rel; // 40% of the time it'll be the same so just assign it.
				got = true;
			}
			else if ((ranval > 40) && (ranval < 56)) // 15% of time 
			{
				rel2 = "Buddhist"; // 15% of the time it'll be Buddhist
				got = true;
			}
			else if ((ranval > 55) && (ranval < 70)) // 5% of time 
			{
				rel2 = "Hindu"; // 15% of the time it'll be Hindu
				got = true;
			}
			else if ((ranval > 71) && (distrel > drelthresh)) // 15% of time random
				got = true;
		}
		else if (rel.equals("Muslim"))
		{
			if (ranval < 61 )
			{
				rel2 = rel; // 60% of the time it'll be the same so just assign it.
				got = true;
			}
			else if ((ranval > 60) && (distrel > drelthresh)) // 40% of time random
				got = true;
		}
		else
		{
			if ((ranval < 40) && (distrel == 0.0))
			{
				got = true;
			}
			else if ((ranval >= 40) && (distrel > 0.0))
				got = true;
		}

	}

return(rel2);

}


public static double dmar(String marv1, String marv2)
{

double distmar = 0.0;
if (marv1.equals(marv2) == true ) // just check for equality or not
	distmar=0.0;
else
	distmar=1.0;

return(distmar);
}

public static double dmar2(String maritalv1, String maritalv2)
{
	String marital[]       	 = new String[4]; 
	String mar="";
	marital[0] = "Single";   marital[1] = "Married";  marital[2] = "Divorced"; marital[3] = "Widowed";

	double distmarital = 0.0;

	if (maritalv1.equals(maritalv2) == true ) // found it
		distmarital = 0.0;
	
	// married, divorced and widowed are considered quite similar
	else if (maritalv1.equals(maritalv2) == false &&  maritalv1.equals(marital[0]) == false &&
	maritalv2.equals(marital[0]) == false) 
		distmarital = 0.5;
	else
		distmarital = 1.0;

return(distmarital);
}


public static String assignMar(String mar, Random generator, Random generator2, double dagthresh, String[] maritalstatus,
							   int LimMar1, int LimMar2, int LimMar3, int LimMar4, int LimMar5)
{
	double distmar=0.0; int marix = 0;
	String mar2="";
	int ranval = 	 ((int)(generator2.nextInt(100))); // rnd will be between 0 and 9
	
	//int LimMar1=51, LimMar2=81, LimMar3=31, LimMar4=61, LimMar5=91;
	
	int LimMar_SM1 = LimMar1, LimMar_SM2 = LimMar2, 
		LimMar_D1  = LimMar3, LimMar_D2  = LimMar4, LimMar_D3 = LimMar5, 
		LimMar_W1  = LimMar3, LimMar_W2  = LimMar4, LimMar_W3 = LimMar5;
	
	boolean got = false;
	
	while (!got)
	{
		marix =  ((int)(generator.nextInt(96))); // rnd will be between 0 and limit-1
		mar2 = maritalstatus[marix];

		distmar = dmar2(mar, mar2);
		
		//System.out.println("ranval: "+ranval+" distseo: "+distseo+" seo: "+seo+" seo2: "+seo2);
		
		if (mar.equals("Single") || mar.equals("Married"))
		{
			if ((ranval < LimMar_SM1)) // 50% of time same
			{ 
				mar2=mar;
				got = true;
		    }
			else if ((ranval > (LimMar_SM1-1)) && (ranval < LimMar_SM2)) // 30% of time same
			{
				if (mar.equals("Single")) 
				{ 
					mar2="Married";
					got = true;
			    }	
				else if (mar.equals("Married")) 
				{ 
					mar2="Single";
					got = true;
			    }
			}
			else if ((ranval > (LimMar_SM2-1)) && (!mar2.equals("Single") && !mar2.equals("Married")))
			{
				got = true;
			}	
		} // end of case when single or married
		
		else if (mar.equals("Divorced"))
		{
			if ((ranval < LimMar_D1)) // 30% of time same
			{ 
				mar2=mar;
				got = true;
		    }
			else if ((ranval > (LimMar_D1-1)) && (ranval < LimMar_D2)) // 30% of time same
			{
				mar2="Single";
				got = true;
			}	
			else if ((ranval > (LimMar_D2-1)) && (ranval < LimMar_D3)) // 30% of time same
			{
				mar2="Married";
				got = true;
			}
			else if ((ranval > (LimMar_D3-1)))
			{
				mar2="Widowed";
				got = true;
			}	
		 } // end of case when divorced
		
		else if (mar.equals("Widowed"))
		{
			if ((ranval < LimMar_W1)) // 30% of time same
			{ 
				mar2=mar;
				got = true;
		    }
			else if ((ranval > (LimMar_W1-1)) && (ranval < LimMar_W2)) // 30% of time same
			{
				mar2="Single";
				got = true;
			}	
			else if ((ranval > (LimMar_W2-1)) && (ranval < LimMar_W3)) // 30% of time same
			{
				mar2="Married";
				got = true;
			}
			else if ((ranval > (LimMar_W3-1)))
			{
				mar2="Divorced";
				got = true;
			}	
		 } // end of case when widowed

	}

return(mar2);

}

public static double dprof(String profv1, String profv2)
{

double distprof = 0.0;
if (profv1.equals(profv2) == true ) // just check for equality or not
	distprof=0.0;
else
	distprof=1.0;

return(distprof);
}

public static double dprof2(String professionv1, String professionv2)
{
	double distprof = 0.0;
	String profession[]    = new String[10]; 

	profession[0] = "Manager";     		profession[1] = "Professional";  
	profession[2] = "Service";          profession[3] = "Sales and office"; 
	profession[4] = "Natural resources construction and maintenance"; 
	profession[5] = "Production transportation and material moving"; 
	profession[6] = "Student"; 

	if (professionv1.equals(professionv2) == true ) // found it
		distprof = 0.0;

	// Manager and professional are considered quite similar
	else if ( (professionv1.equals(profession[0]) == true || professionv1.equals(profession[1]) == true)
	&&  (professionv2.equals(profession[0]) == true || professionv2.equals(profession[1]) == true)
	)
		distprof = 0.5;
	//Service and sales are considered quite similar
	else if ( (professionv1.equals(profession[2]) == true || professionv1.equals(profession[3]) == true)
	&&  (professionv2.equals(profession[2]) == true || professionv2.equals(profession[3]) == true)
	)
		distprof = 0.5;
	//natural and prod are considered quite similar
	else if ( (professionv1.equals(profession[4]) == true || professionv1.equals(profession[5]) == true)
	&&  (professionv2.equals(profession[4]) == true || professionv2.equals(profession[5]) == true)
	)
		distprof = 0.5;
	else
		distprof = 1.0;

return(distprof);
}


public static String assignPro(String prof, Random generator, Random generator2, double dprothresh1, String[] profession,
		                       int LimPro1, int LimPro2, int LimPro3, int LimPro4, int LimPro5, int LimPro6)
{
	double distpro=0.0; int profix = 0;
	String pro2="";
	int ranval = 	 ((int)(generator2.nextInt(100))); // rnd will be between 0 and 9

	boolean got = false;
	
	//int LimPro1=60, LimPro2=61, LimPro3=71, LimPro4=81, LimPro5=91, LimPro6=86;
	
	int LimPro_MPSS = LimPro2, 
			
		LimPro_MPSS_M1 = LimPro1, LimPro_MPSS_M2 = LimPro3, LimPro_MPSS_M3 = LimPro4, LimPro_MPSS_M4 = LimPro5, 
		
		LimPro_MPSS_P1 = LimPro1, LimPro_MPSS_P2 = LimPro3, LimPro_MPSS_P3 = LimPro4, LimPro_MPSS_P4 = LimPro5, 
		
		LimPro_MPSS_SE1 = LimPro1, LimPro_MPSS_SE2 = LimPro3, LimPro_MPSS_SE3 = LimPro4, LimPro_MPSS_SE4 = LimPro5, 
		
		LimPro_MPSS_SA1 = LimPro1, LimPro_MPSS_SA2 = LimPro3, LimPro_MPSS_SA3 = LimPro4, LimPro_MPSS_SA4 = LimPro5, 
		
		LimPro_NP = LimPro2, 
		
		LimPro_NP_N1 = LimPro1, LimPro_NP_N2 = LimPro6, 
		
		LimPro_NP_P1 = LimPro1, LimPro_NP_P2 = LimPro6,
		
		LimPro_S = LimPro2; 

	
	//double distlim1 = 0.5; //, distlim2 = 1.0;
	
	
	while (!got)
	{
		profix =  ((int)(generator.nextInt(100))); // rnd will be between 0 and limit-1
		pro2 = profession[profix];
		
		distpro = dprof2(prof, pro2);
		
		//System.out.println(prof+" "+pro2+" "+distpro);
		
		if (prof.equals("Manager") || prof.equals("Professional") || prof.equals("Service") || prof.equals("Sales and office"))
		{
			if (ranval < LimPro_MPSS)
			{
				pro2 = prof; // 60% of the time assign the same
				got = true;
			}
			else if (prof.equals("Manager"))
			{
				if ((ranval > LimPro_MPSS_M1) && (ranval < LimPro_MPSS_M2)) // 10% of time
				{
					pro2 = "Professional"; // 10% of the time it'll be Professional
					got = true;
				}
				else if ((ranval > (LimPro_MPSS_M2-1)) && (ranval < LimPro_MPSS_M3)) // 10% of time
				{
					pro2 = "Service"; // 10% of the time it'll be Service
					got = true;
				}
				else if ((ranval > (LimPro_MPSS_M3-1)) && (ranval < LimPro_MPSS_M4)) // 10% of time
				{
					pro2 = "Sales and office"; // 10% of the time it'll be Service
					got = true;
				}
				else if ((ranval > (LimPro_MPSS_M4-1)) && (distpro > dprothresh1)) // 10% of time random
					got = true;
			}
			else if (prof.equals("Professional"))
			{
				if ((ranval > LimPro_MPSS_P1) && (ranval < LimPro_MPSS_P2)) // 10% of time
				{
					pro2 = "Manager"; // 10% of the time it'll be Professional
					got = true;
				}
				else if ((ranval > (LimPro_MPSS_P2-1)) && (ranval < LimPro_MPSS_P3)) // 10% of time
				{
					pro2 = "Service"; // 10% of the time it'll be Service
					got = true;
				}
				else if ((ranval > (LimPro_MPSS_P3-1)) && (ranval < LimPro_MPSS_P4)) // 10% of time
				{
					pro2 = "Sales and office"; // 10% of the time it'll be Service
					got = true;
				}
				else if ((ranval > (LimPro_MPSS_P4-1)) && (distpro > dprothresh1)) // 10% of time random
					got = true;
			}
			else if (prof.equals("Service"))
			{
				if ((ranval > LimPro_MPSS_SE1) && (ranval < LimPro_MPSS_SE2)) // 10% of time
				{
					pro2 = "Manager"; // 10% of the time it'll be Professional
					got = true;
				}
				else if ((ranval > (LimPro_MPSS_SE2-1)) && (ranval < LimPro_MPSS_SE3)) // 10% of time
				{
					pro2 = "Professional"; // 10% of the time it'll be Service
					got = true;
				}
				else if ((ranval > (LimPro_MPSS_SE3-1)) && (ranval < LimPro_MPSS_SE4)) // 10% of time
				{
					pro2 = "Sales and office"; // 10% of the time it'll be Service
					got = true;
				}
				else if ((ranval > (LimPro_MPSS_SE4-1)) && (distpro > dprothresh1)) // 10% of time random
					got = true;
			}
			else if (prof.equals("Sales and office"))
			{
				if ((ranval > LimPro_MPSS_SA1) && (ranval < LimPro_MPSS_SA2)) // 10% of time
				{
					pro2 = "Manager"; // 10% of the time it'll be Professional
					got = true;
				}
				else if ((ranval > (LimPro_MPSS_SA2-1)) && (ranval < LimPro_MPSS_SA3)) // 10% of time
				{
					pro2 = "Professional"; // 10% of the time it'll be Service
					got = true;
				}
				else if ((ranval > (LimPro_MPSS_SA3-1)) && (ranval < LimPro_MPSS_SA4)) // 10% of time
				{
					pro2 = "Service"; // 10% of the time it'll be Service
					got = true;
				}
				else if ((ranval > (LimPro_MPSS_SA4-1)) && (distpro > dprothresh1)) // 10% of time random
					got = true;
			}
		}
		else if (prof.equals("Natural resources construction and maintenance") || 
				 prof.equals("Production transportation and material moving"))
		{
			if (ranval < LimPro_NP)
			{
				pro2 = prof; // 60% of the time assign the same
				got = true;
			}
			else if (prof.equals("Natural resources construction and maintenance"))
			{
				if ((ranval > LimPro_NP_N1) && (ranval < LimPro_NP_N2)) // 25% of time
				{
					pro2 = "Production transportation and material moving"; // 25% of the time it'll be other one
					got = true;
				}
				else if ((ranval > (LimPro_NP_N2-1)) && (distpro > dprothresh1)) // 15% of time random
					got = true;
			}
			else if (prof.equals("Production transportation and material moving"))
			{
				if ((ranval > LimPro_NP_P1) && (ranval < LimPro_NP_P2)) // 25% of time
				{
					pro2 = "Natural resources construction and maintenance"; // 25% of the time it'll be other one
					got = true;
				}
				else if ((ranval > (LimPro_NP_P1-1)) && (distpro > dprothresh1)) // 15% of time random
					got = true;
			}
		}
		else if (prof.equals("Student"))
		{
			if (ranval < LimPro_S)
			{
				pro2 = prof; // 60% of the time assign the same
				got = true;
			}
			else if ((ranval > (LimPro_S-1)) && (distpro > dprothresh1)) // 40% of time random
				got = true;
		}
		else if (distpro > dprothresh1)
			got = true;

		//System.out.println("distres: "+distres);
	}

return(pro2);
}


public static double dlikes(String lk1, String lk2, String lk3, String lk21, String lk22, String lk23)
{
int i=0, j=0;

String likes[] = new String[6];

double plikesd[][] = new double[5][5];

//System.out.println("DISTANCE BETWEEN LIKES");

plikesd[0][0] = 0.00;  // entertainment - entertainment
plikesd[1][0] = 0.25;  // entertainment - music artist
plikesd[2][0] = 0.25;  // entertainment - tv show
plikesd[3][0] = 0.25;  // entertainment - soccer club
plikesd[4][0] = 0.50;  // entertainment - drink brand

plikesd[0][1] = 0.25;  // music artist - entertainment
plikesd[1][1] = 0.00;  // music artist - music artist
plikesd[2][1] = 0.25;  // music artist - tv show
plikesd[3][1] = 0.25;  // music artist - soccer club
plikesd[4][1] = 0.50;  // music artist - drink brand

plikesd[0][2] = 0.25;  // tv show - entertainment
plikesd[1][2] = 0.25;  // tv show - music artist
plikesd[2][2] = 0.00;  // tv show - tv show
plikesd[3][2] = 0.25;  // tv show - soccer club
plikesd[4][2] = 0.50;  // tv show - drink brand

plikesd[0][3] = 0.25;  // soccer club - entertainment
plikesd[1][3] = 0.25;  // soccer club - music artist
plikesd[2][3] = 0.00;  // soccer club - tv show
plikesd[3][3] = 0.00;  // soccer club - soccer club
plikesd[4][3] = 0.50;  // soccer club - drink brand

plikesd[0][4] = 0.50;  // drink brand - entertainment
plikesd[1][4] = 0.50;  // drink brand - music artist
plikesd[2][4] = 0.50;  // drink brand - tv show
plikesd[3][4] = 0.25;  // drink brand - soccer club
plikesd[4][4] = 0.00;  // drink brand - drink brand


likes[0] = "entertainment"		; //type A
likes[1] = "music artist"		; //type A
likes[2] = "tv show"			; //type A
likes[3] = "soccer club"		; //type C
likes[4] = "drink brand"		; //type B

String likesA[]    = new String[3]; 
String likesB[]    = new String[3]; 

likesA[0] = lk1;  likesA[1] = lk2;  likesA[2] = lk3;
likesB[0] = lk21; likesB[1] = lk22; likesB[2] = lk23;

int pos1=0, pos2=0, k=0, ii=0, jj=0;

double samepos=0.33, posplus1=0.66, posplus2=1.00; // weighting for ranking of likes.

double distlike1 = 0.0, distlike2 = 0.0, distlike3 = 0.0, distlike = 0.0;

if ( lk1.equals(lk21) &&  lk2.equals(lk22) &&  lk3.equals(lk23) )
	distlike = 0.0;
else
	for(i=0;i<3;i++)
	{
		for(j=0;j<3;j++)
		{
			for(k=0;k<5;k++)
			{
				if (likes[k].equals(likesA[i]))
				{
					pos1=k;
				}
				if (likes[k].equals(likesB[j]))
				{
					pos2=k;
				}
			}
			distlike = distlike + plikesd[pos1][pos2];
			if (likesA[i].equals(likesB[j])) // bonus for relative position
			{
				if (Math.abs(i-j) == 0)
					distlike = distlike * 0.25;
				else if (Math.abs(i-j) == 1)
					distlike = distlike * 0.50;
				else if (Math.abs(i-j) == 2)
					distlike = distlike * 0.75;
			
			}
			
			//System.out.println("distance between "+likesA[i]+" and "+likesB[j]+" is "+plikesd[pos1][pos2]+" with pos1= "+pos1+" and pos2= "+pos2+" distlike="+distlike);
		} //j
		//System.out.println("accumulated distance for "+likesA[i]+ " is: "+distlike);
	} //i

//System.out.println("distlike: "+distlike+" lk1:"+ lk1+ " lk2:"+ lk2+ " lk3:"+ lk3+ " lk21: "+ lk21+ " lk22:"+ lk22+ " lk23:"+ lk23);


distlike = distlike / 9.0;

//System.out.println("normalized distlike: "+distlike);

//System.out.println("DLIKES3. distlike: "+distlike);


return(distlike);
}


public static String[] assignLik(String lk1, String lk2, String lk3, Random generator, Random generator2, 
		                         int likL1, int likL2, int likL3, int likL4, 
		                         double dlikthresh1, double dlikthresh2,  
		                         String[] likes1, String[] likes2, String[] likes3)
{
	String[] likes =new String[265]; boolean got=false;
	
	String lk21="",lk22="",lk23=""; 
	int ranval=0, lkix1=0;
	double distlikes = 0.0;

	ranval = 	 ((int)(generator2.nextInt(100))); // rnd will be between 0 and limit-1

	//System.out.println("ranlim: "+ranlim);
	while (!got)
	{
		lkix1 =  ((int)(generator.nextInt(265))); // rnd will be between 0 and limit-1
		lk21 = likes1[lkix1]; lk22 = likes2[lkix1]; lk23 = likes3[lkix1];
		distlikes = dlikes(lk1, lk2, lk3, lk21, lk22, lk23);
		
		if (ranval < likL1) // 34% of time the same
		{
			likes[0] = lk1;	likes[1] = lk2;	likes[2] = lk3;
			got = true;
		}
		else if ((ranval >= likL1) && (ranval < likL2) && (distlikes < dlikthresh1)) // 15% of time between 0.0 and 0.15
		{
			likes[0] = lk21;	likes[1] = lk22;	likes[2] = lk23;
			got = true;
		}
		else if ((ranval >= likL2) && (ranval < likL3) && (distlikes < dlikthresh2)) // 20% of time between 0.15 and 0.24
		{
			likes[0] = lk21;	likes[1] = lk22;	likes[2] = lk23;
			got = true;
		}
		else if ((ranval >= likL3) || (distlikes > 0.0)) // 30% of time random
		{
			likes[0] = lk21;	likes[1] = lk22;	likes[2] = lk23;
			got = true;
		}

		//System.out.println("distlikes: "+distlikes+" lk1: "+lk1+" lk2: "+lk2+" lk3: "+lk3+" lk21: "+lk21+" lk22: "+lk22+" lk23: "+lk23);
	}
	
	
return(likes);
}


/*************************************************************************************************************/
/*********************************************** ASSIGN SEEDS ************************************************/
/*************************************************************************************************************/

public static void AssignSeeds(Random generator, String profile_age[],String profile_gen[],String profile_res[],
		                       String profile_rel[], String profile_mar[], String profile_prof[], String profile_pol[],
		                       String profile_seo[], String profile_lk1[], String profile_lk2[], String profile_lk3[])

{
	int i=0, j=0, count=0, profix=0;
	User nw;
	
	String age = "";
	
	String res=""; String gen=""; String ag=""; String rel=""; String mar=""; String prof="";
	String pol=""; String seo=""; String we="";
	String lk1="";	String lk2="";	String lk3=""; String lk21="";	String lk22="";	String lk23="";
	
	
	//for (i=0; i < numseeds; i++)
			//System.out.println("seed "+i+"="+RV.seeds.get(i));

		// FIRST ASSIGN PROTOTYPE PROFILES TO THE SEEDS OF EACH COMMUNITY
		try{
			
			Enumeration enc = RV.Communities.keys();
			double grafsize = RV.Users.size();
			double numcoms  = RV.Communities.size();

			
			int profilefreq[] = new int[10];
			int profilevalues[] = new int[1000];
			
			profilefreq[0]=216; // sum to 1000
			profilefreq[2]=211;
			profilefreq[1]=172;
			profilefreq[5]=157;
			profilefreq[3]=97;
			profilefreq[4]=81;
			profilefreq[8]=28;
			profilefreq[6]=24;
			profilefreq[9]=9;
			profilefreq[7]=5;
			
			int freq=0;
			
			// assign profiles based on frequencies
			for(i=0, count=0; i<10; i++)
			{
				freq = profilefreq[i];
				for (j=0;j<freq;++j)
				{
					profilevalues[count]=i;
				    ++count;
				}
			}
			
			// *********** next 10 lines only for rmat ***********
			profilevalues[0]=0; // assign profile 0 to community 0
			profilevalues[1]=2; // assign profile 2 to community 1
			profilevalues[2]=1; // assign profile 1 to community 2
			profilevalues[3]=4; // assign profile 4 to community 3
			profilevalues[4]=5; // assign profile 5 to community 4
			profilevalues[5]=3; // assign profile 3 to community 5
			profilevalues[6]=7; // assign profile 7 to community 6
			profilevalues[7]=9; // assign profile 9 to community 7
			profilevalues[8]=6; // assign profile 6 to community 8
			profilevalues[9]=8; // assign profile 8 to community 9
			
	  		enc = RV.Communities.keys();
	  		int comcount=0;
			while(enc.hasMoreElements()) // For all communities
			{
	    		Community co = (Community)RV.Communities.get((Integer)enc.nextElement());
	    		int co1 = co.getCommunity();
	 			
				
				profix =  ((int)(generator.nextInt(1000))); // rnd will be between 0 and limit-1
				int profileforthiscommunity = profilevalues[profix];
				
				//System.out.println("Profile "+profileforthiscommunity+" assigned to community "+co1);
	    		
				int profixx = profileforthiscommunity;
				
				profixx = profilevalues[co1]; // ************ this line only for rmat ****************
				
				System.out.println("Profile "+profixx+" assigned to community "+co1);

				ag = profile_age[profixx];  
				gen = profile_gen[profixx];   
				res = profile_res[profixx]; 
				rel = profile_rel[profixx]; 
				mar = profile_mar[profixx]; 
				prof = profile_prof[profixx]; 
				pol = profile_pol[profixx]; 
				seo = profile_seo[profixx]; 
				lk1 = profile_lk1[profixx]; 
				lk2 = profile_lk2[profixx];   
				lk3 = profile_lk3[profixx];

				++comcount;

				
				for(i=0;i<co.seedsc.size();i++) // for each unassigned seed of current community
				{
					int ids = (Integer)co.seedsc.get(i);
					
					nw = (User)RV.Users.get((Integer)ids);
					
					age       = nw.getAge();
					
					if (age == null || age.equals(""))
					{
						nw.loadAge(ag); nw.loadGender(gen);nw.loadResidence(res); 
						nw.loadReligion(rel); nw.loadMaritalStatus(mar); nw.loadProfession(prof);
						nw.loadPoliticalOrientation(pol); nw.loadSexualOrientation(seo);
						nw.loadLike(lk1, 1); nw.loadLike(lk2, 2); nw.loadLike(lk3, 3);

						//RV.Users.put(ids, nw);
						RV.Users.remove(nw.getUser());
						RV.Users.put(nw.getUser(),nw);
					}
				} // efor each seed
			} // ewhile for all communities
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("\nsomething went wrong writing the output file");
			return;
		}

}

/*************************************************************************************************************/
/*********************************************** ASSIGN NEIGHBOURS *******************************************/
/*************************************************************************************************************/
public static void AssignNeighbors(Random generator, Random generator2, String age[], String residence[],	
								   String gender[],
								   String sexualorientation[],
								   String politicalorientation[],
								   String religion[],
								   String maritalstatus[],
								   String profession[],
		                           String likes1[], String likes2[], String likes3[], boolean switchdistance)
		                        		   

{
	//*****************************************************************************************************//
    // KEY PARAMETERS TO TWEAK FOR SEED NEIGHBOR ATTRIBUTES
	int ranval=0;
	// RANLIM = 6; // Neighbors, 8 is equivalent to 20% random
	
	int i=0, j=0;
	User nw;
	
	String agef       = "";
	
	int genix = 0, resix = 0, agix = 0, weix = 0, lkix1 = 0, lkix2 = 0, lkix3 = 0;
	int relix = 0, marix = 0, profix = 0, polix = 0, seoix = 0;
	
	//Specific random thresholds for each attribute
	//double 	//distfage=1.1, 	limfage=0.5,   // AGE, that is, distage must be limage or less (0.2)
			//distfres=1.1, 	limfres=0.5,   // RESIDENCE (0.3)
			//distfpol=1.1, 	limfpol=0.5,   // POLITICAL ORIENTATION (0.2)
			//distfseo=1.1, 	limfseo=0.5,   // SEXUAL ORIENTATION (0.0)
			//distfrel=1.1, 	limfrel=0.5,   // RELIGION (0.0)
			//distfmar=1.1, 	limfmar=0.5,   // MARITAL STATUS (0.0)
			//distfpro=1.1, 	limfpro=0.5,   // PROFESSION (0.0)
			//distflikes=1.1, limflikes=0.5; // LIKES (0.3)
			//distfgen=1.1, 	limfgen=0.0;	  // GENDER (0.0)
	
	String res=""; String gen=""; String ag=""; String rel=""; String mar=""; String prof="";
	String pol=""; String seo=""; String we="";
	String lk1="";	String lk2="";	String lk3=""; String lk21="";	String lk22="";	String lk23="";
	
	boolean got = false;
	
	//************************************************************
	//PROBABILITY RANGES
	// The ranges may be attribute and attribute-value sensitive
	//************************************************************	
	//int polL1  = 61, polL2  = 60, polL3  = 91, polL4  = 90; // POLITICAL ORIENTATION (low)
	int polL1  = 51, polL2  = 50, polL3  = 76, polL4  = 75; // POLITICAL ORIENTATION (medium)
	//int polL1  = 41, polL2  = 40, polL3  = 71, polL4  = 70; // POLITICAL ORIENTATION (high)
	
	//int genL1  = 34; // GENDER (low)  SHOULD BE THE OTHER WAY ROUND
	int genL1  = 44; // GENDER (medium)
	//int genL1  = 54; // GENDER (high)
	
	//int likL1  = 34, likL2  = 50, likL3  = 70, likL4  = 90; // LIKES (low)
	int likL1  = 29, likL2  = 45, likL3  = 65, likL4  = 85; // LIKES (medium)
	//int likL1  = 24, likL2  = 40, likL3  = 60, likL4  = 80; // LIKES (high)
	
	//int agL1   = 61, agL2   = 60, agL3   = 91, agL4   = 90; // AGE (low)
	int agL1   = 51, agL2   = 50, agL3   = 81, agL4   = 80; // AGE (medium)
	//int agL1   = 41, agL2   = 40, agL3   = 71, agL4   = 70; // AGE (high)
	
	//int resL1  = 61, resL2  = 60, resL3  = 91, resL4  = 90; // RESIDENCE (low)
	int resL1  = 51, resL2  = 50, resL3  = 81, resL4  = 80; // RESIDENCE (medium)
	//int resL1  = 41, resL2  = 40, resL3  = 71, resL4  = 70; // RESIDENCE (high)
	
	//int relL1  = 61, relL2  = 60, relL3  = 91, relL4  = 90; // unused
	
	//int seoL1  = 61, seoL2  = 60, seoL3  = 91, seoL4  = 90; // SEXUAL ORIENTATION (low)
	//int seoL1h = 41, seoL2h = 40, seoL3h = 71, seoL4h = 70;
	int seoL1  = 51, seoL2  = 50, seoL3  = 81, seoL4  = 80; // SEXUAL ORIENTATION (medium)
	int seoL1h = 31, seoL2h = 30, seoL3h = 61, seoL4h = 60;
	//int seoL1  = 41, seoL2  = 40, seoL3  = 71, seoL4  = 70; // SEXUAL ORIENTATION (high)
	//int seoL1h = 21, seoL2h = 20, seoL3h = 51, seoL4h = 50;
	
	//int marL1  = 61, marL2  = 60, marL3  = 91, marL4  = 90; // unused
	//int marL1h = 41, marL2h = 40, marL3h = 71, marL4h = 70; // unused
	
	//int limMar1=51, limMar2=81, limMar3=31, limMar4=61, limMar5=91; // MARITAL STATUS (low)
	int limMar1=46, limMar2=76, limMar3=26, limMar4=56, limMar5=86; // MARITAL STATUS (medium)
	//int limMar1=41, limMar2=71, limMar3=21, limMar4=51, limMar5=81; // MARITAL STATUS (high)
 
	
	//int LimPro1=60, LimPro2=61, LimPro3=71, LimPro4=81, LimPro5=91, LimPro6=86; // PROFESSION (low)
	int LimPro1=50, LimPro2=51, LimPro3=61, LimPro4=71, LimPro5=81, LimPro6=76; // PROFESSION (medium)
	//int LimPro1=40, LimPro2=41, LimPro3=51, LimPro4=61, LimPro5=71, LimPro6=66; // PROFESSION (high)
		 
		 
	
	//int LimRel1 = 40,  LimRel2 = 41, LimRel3 = 86, LimRel4 = 61, LimRel5 = 71, LimRel6 = 81, // RELIGION (low)
	//	LimRel7 = 56, LimRel8 = 70;
	int LimRel1 = 35,  LimRel2 = 36, LimRel3 = 81, LimRel4 = 56, LimRel5 = 66, LimRel6 = 76, // RELIGION (medium)
	  LimRel7 = 51, LimRel8 = 65;
	//int LimRel1 = 30,  LimRel2 = 31, LimRel3 = 76, LimRel4 = 51, LimRel5 = 61, LimRel6 = 71, // RELIGION (high)
	  //LimRel7 = 46, LimRel8 = 60;
	
	//************************************************************
	
	
	
	//************************************************************************************************************
	//DISTANCE THRESHOLDS
	// The distance threshold depends on how the distance is calculated and may differ from attribute to attribute
	// It indicates the distance of the two closest categories. In the case of residence it indicates the distance
	// for categories in the same county, state and max. distance, respectively
	//************************************************************************************************************
	double dpolthresh=(double)((double)1.0/(double)6.0);  // POLITICS
	
	double dprothresh=(double)((double)1.0/(double)2.0);  // PROFESSION
	
	double dgenthresh=(double)1.0; // GENDER
	
	double dagthresh=(double)((double)1.0/(double)6.0);  // AGE
	
	double dresthresh1=(double)((double)1.0/(double)4.0); //county =.25 RESIDENCE
	double dresthresh2=(double)((double)1.0/(double)2.0); //state =.5
	double dresthresh3=(double)1.0; //1.0
	
	double drelthresh=(double)((double)1.0/(double)2.0);   // RELIGION
	
	double dseothresh=(double)((double)1.0/(double)2.0); // =.5   SEO
	
	double dmarthresh=(double)((double)1.0/(double)2.0); // =.5    MARITAL
	
	double dlikthresh1 = 0.15; // v close  > 0.00 and < .15  LIKES
	double dlikthresh2 = 0.24; // m close  > 0.15 and < .24

	//************************************************************************************************************

	
    
    //if (1==1) return 0;
    
    int mod1 = 0, mod2 = 0;
   
    
    //******************************************************************************************************************
	//ASSIGN THE FRIENDS ATTRIBUTES BASED ON THE SEED NODE ATTRIBUTES. BUT ONLY IF IN SAME COMMUNITY AS SEED NODE
	try{
		
		System.out.println("Number of communities:"+RV.Communities.size());
		
		Enumeration enc = RV.Communities.keys();
  		int comcount=0;
	
		while(enc.hasMoreElements())
		{
    		Community co = (Community)RV.Communities.get((Integer)enc.nextElement());
    		int co1 = co.getCommunity();
    		
			//System.out.println("Now process neighbors of seeds for community "+co1);
			++comcount;

			
			for(i=0;i<co.seedsc.size();i++) // for each seed of current community
			{
				int ids = (Integer)co.seedsc.get(i);
				
				nw = (User)RV.Users.get((Integer)ids);		
			
			
			if (nw == null)
				System.out.println("2 nw is null for seed id:"+ids);
			
			if(nw.friends.size()<1)
			{
				System.out.println("this seed has zero friends");
				return;
			}
			
			ag = nw.getAge(); res = nw.getResidence(); gen = nw.getGender();
			rel = nw.getReligion(); mar = nw.getMaritalStatus(); prof = nw.getProfession();
			pol = nw.getPoliticalOrientation(); seo = nw.getSexualOrientation();
			lk1 = nw.getLike(1); lk2 = nw.getLike(2); lk3 = nw.getLike(3);
			//mod1 = nw.getMOD();
			
		
			for (j=0; j< nw.friends.size();++j)  // for each neighbor
			{

				int userf1 = nw.friends.get(j);
				User nwf1 = (User)RV.Users.get(userf1);
				int ok=1;
				if (nwf1 != null)
				{
					for (int k=0; k<nwf1.communities.size() && ok==1;++k)
					{
						mod2 = nwf1.getCommunity(k);
						if (nw.communities.contains(mod2))
							ok=0;
					}
				
					agef       = nwf1.getAge();
				
					if (agef != null) // check its not already assigned
						ok = 1;
					if(agef != null && !agef.equals("")) 
						ok = 1;

				}
				
				if (ok == 0) // only if neighbor is in same community as its seed and its not already assigned
				{
					
				//assign *****************AGE*****************
				
				String ag2 = assignAge(ag, generator, generator2, dagthresh, age, agL1, agL2, agL3, agL4);
	
				nwf1.loadAge(ag2);

				// second assign *****************RESIDENCE*****************
				String res2 = assignRes(res, generator, generator2, dresthresh1, 
						   			    dresthresh2, dresthresh3, residence,
						   			    resL1, resL2, resL3, resL4);

				nwf1.loadResidence(res2);

				// next assign *****************POLITICAL ORIENTATION*****************
				String pol2 = assignPol(pol, generator, generator2, dpolthresh, politicalorientation,
 					   					polL1, polL2, polL3, polL4);
				
				nwf1.loadPoliticalOrientation(pol2);

				// next assign *****************SEXUAL ORIENTATION*****************

				String seo2 = assignSeo(seo, generator, generator2, dseothresh, 
	   				                    sexualorientation, seoL1, seoL2, seoL3, seoL4, seoL1h, seoL2h, seoL3h, seoL4h);
	
				nwf1.loadSexualOrientation(seo2);

				// next assign *****************RELIGION*****************
				String rel2 = assignRel(rel, generator, generator2, drelthresh, religion,
						LimRel1,  LimRel2, LimRel3, LimRel4, LimRel5, LimRel6, LimRel7, LimRel8);				

				nwf1.loadReligion(rel2);
				

				// next assign *****************MARITAL STATUS*****************
				String mar2 = assignMar(mar, generator, generator2, dagthresh, maritalstatus, 
						                limMar1, limMar2, limMar3, limMar4, limMar5);

				nwf1.loadMaritalStatus(mar2);

				
				// next assign *****************PROFESSION*****************
				String pro2 =  assignPro(prof, generator, generator2, dprothresh, profession,
	                                     LimPro1, LimPro2, LimPro3, LimPro4, LimPro5, LimPro6);				
				
				
				
				nwf1.loadProfession(pro2);
				

				// third assign *****************LIKES*****************
				String[] likes = assignLik(lk1, lk2, lk3, generator, generator2, 
                                           likL1, likL2, likL3, likL4, dlikthresh1, dlikthresh2, 
                                           likes1, likes2, likes3);
  
				nwf1.loadLike(likes[0], 1); nwf1.loadLike(likes[1], 2); nwf1.loadLike(likes[2], 3);
				
				
				
				// fourth assign *****************GENDER*****************
				String gen2 = assignGen(gen, generator, generator2, genL1, dgenthresh,
				                        gender, likes[2]);
			
				nwf1.loadGender(gen2);

				
				RV.Users.remove(nwf1.getUser());
				RV.Users.put(nwf1.getUser(),nwf1);
				
			} // end for each friend
				
			} // eif same community

		} // efor each seed
			
	} // ewhile each community
}
catch(Exception e){
	e.printStackTrace();
	System.out.println("\nsomething went wrong doing the data asignment");
	return;
}

}


/*************************************************************************************************************/
/*********************************************** ASSIGN UNASSIGNED *******************************************/
/*************************************************************************************************************/
public static void AssignUnassigned(Random generator, Random generator2, int RANLIMUNAS,String age[], String residence[],	
		   							String gender[],
		   							String sexualorientation[],
		   							String politicalorientation[],
		   							String religion[],
		   							String maritalstatus[],
		   							String profession[],
		   							String likes1[], String likes2[], String likes3[])
{
	int i=0, mod2=0;
	
	int genix = 0, resix = 0, agix = 0, weix = 0, lkix1 = 0, lkix2 = 0, lkix3 = 0;
	int relix = 0, marix = 0, profix = 0, polix = 0, seoix = 0;
	
	System.out.println("ASSIGN REST");
	//ASSIGN NODES THAT ARE NOT SEEDS NOR NEIGHBORS OF SEEDS.
	//THIS IS DONE FOR EACH NODE BY CALCULATING, FOR ALL NEIGHBORS WITH ASSIGNED VALUES, THE MODAL VALUE FOR EACH ATTRIBUTE.
	//THE MODAL VALUE IS THEN ASSIGNED WITH A X% PROBABILITY, WHERE 'X' IS BY DEFAULT EQUAL TO 70.
	String agel       = ""; String weightl    = "";	String genderl    = "";	String residencel = "";
	
	String religionl = "";	String maritalstatusl = "";	String professionl = "";
	String politicalorientationl = "";  String sexualorientationl = "";
	String like1 = "", like2 = "",like3 = "";
	
	String res=""; String gen=""; String ag=""; String rel=""; String mar=""; String prof="";
	String pol=""; String seo=""; String we="";
	String lk1="";	String lk2="";	String lk3=""; String lk21="";	String lk22="";	String lk23="";
	
    Vector attVals0, attVals1, attVals2, attVals3, attVals4, attVals5, attVals6, attVals7, attVals8, 
    attVals9, attVals10;
    Vector attVals0f, attVals1f, attVals2f, attVals3f, attVals4f, attVals5f, attVals6f, attVals7f, attVals8f, 
    attVals9f, attVals10f;
    
	attVals0 = new Vector();	attVals1 = new Vector();	attVals2 = new Vector();	attVals3 = new Vector();
    attVals4 = new Vector();	attVals5 = new Vector();	attVals6 = new Vector();	attVals7 = new Vector();
    attVals8 = new Vector();	attVals9 = new Vector();	attVals10 = new Vector();
	attVals0f = new Vector();	attVals1f = new Vector();	attVals2f = new Vector();	attVals3f = new Vector();
    attVals4f = new Vector();	attVals5f = new Vector();	attVals6f = new Vector();	attVals7f = new Vector();
    attVals8f = new Vector();	attVals9f = new Vector();	attVals10f = new Vector();
  	
	Enumeration en2 = RV.Users.keys();
	User nwn, nwf; int user1=0, ranval=0;
	int countall1 = 0, countall2=0, countall3=0, countall4=0;
	while (en2.hasMoreElements() ){  // for all users
		nwn = (User)RV.Users.get((Integer)en2.nextElement());
		user1 = nwn.getUser();
		//System.out.println("countall1: "+countall1+" countall2: "+countall2+" countall3: "+countall3+" countall4: "+countall4);
		++countall1;
		
		String agen       = nwn.getAge();
		
		int ix=0, f=1;
	
		if (agen == null || agen.equals("")) // only consider users whose data is currently unassigned
		{
			++countall2;
			
			//We assign the modal values 90% of the time, and random assignment 10% of the time
			
			ranval = 	 ((int)(generator2.nextInt(10))); // rnd will be between 0 and 9
			int ok=1;			
			for(i=0;i<nwn.friends.size() && ok==1;++i) // find at least one neighbor in the same community
			{
				int userf1 = nwn.friends.get(i);
				User nwf1 = (User)RV.Users.get(userf1);
				

				for (int k=0; k<nwf1.communities.size() && ok==1;++k)
				{
					mod2 = nwf1.getCommunity(k);
					if (nwn.communities.contains(mod2))
							ok=0;
				}
			}
			
			
			

			if ((ranval <= RANLIMUNAS) || (nwn.friends.size()<1) || ok==1)
			{ // ASSIGN RANDOMLY 10% OF THE TIME
				agix = 	 ((int)(generator.nextInt(12))); // rnd will be between 0 and limit-1
				genix =  ((int)(generator.nextInt(2))); // rnd will be between 0 and limit-1
				resix =  ((int)(generator.nextInt(6))); // rnd will be between 0 and limit-1
				relix =  ((int)(generator.nextInt(1000)));   // rnd will be between 0 and limit-1
				marix =  ((int)(generator.nextInt(96)));   // rnd will be between 0 and limit-1
				profix =  ((int)(generator.nextInt(100))); // rnd will be between 0 and limit-1
				polix =  ((int)(generator.nextInt(99)));   // rnd will be between 0 and limit-1
				seoix =  ((int)(generator.nextInt(100)));   // rnd will be between 0 and limit-1
			    lkix1 =  ((int)(generator.nextInt(265))); // rnd will be between 0 and limit-1
			
			    res = residence[resix]; gen = gender[genix]; ag = age[agix];
			    rel = religion[relix]; mar = maritalstatus[marix]; prof = profession[profix];
			    pol = politicalorientation[polix]; seo = sexualorientation[seoix];
			    lk1 = likes1[lkix1]; lk2 = likes2[lkix1]; lk3 = likes3[lkix1];
			
			    genix = 	 ((int)(generator.nextInt(10))); // rnd will be between 0 and limit-1
			    if (genix < 7 && lk3.equals("soccer club"))
			    	gen = "male";
			    else if (genix >= 7 && lk3.equals("soccer club"))
			    	gen="female";

			    nwn.loadAge(ag); nwn.loadResidence(res); nwn.loadGender(gen);
			    nwn.loadReligion(rel); nwn.loadMaritalStatus(mar); nwn.loadProfession(prof);
			    nwn.loadPoliticalOrientation(pol); nwn.loadSexualOrientation(seo);
			    nwn.loadLike(lk1, 1); nwn.loadLike(lk2, 2); nwn.loadLike(lk3, 3);

				RV.Users.remove(nwn.getUser());
				RV.Users.put(nwn.getUser(),nwn);
				++countall3;
			}
			else  // OTHERWISE USE THE MODAL VALUES OF ALL FRIENDS WHO HAVE AN ASSIGNED VALUE (90% OF THE TIME)
			{
				

			attVals0.clear();	attVals1.clear();	attVals2.clear();	attVals3.clear();
		    attVals4.clear();	attVals5.clear();	attVals6.clear();	attVals7.clear();
		    attVals8.clear();	attVals9.clear();	attVals10.clear();
			attVals0f.clear();	attVals1f.clear();	attVals2f.clear();	attVals3f.clear();
		    attVals4f.clear();	attVals5f.clear();	attVals6f.clear();	attVals7f.clear();
		    attVals8f.clear();	attVals9f.clear();	attVals10f.clear();
		    
			//int   modn  = nwn.getMOD();
			
			for(i=0;i<nwn.friends.size();++i) // for all neighbors who are in the same community
			{
				int userf1 = nwn.friends.get(i);
				User nwf1 = (User)RV.Users.get(userf1);
				
				//int   modf  = nwf1.getMOD();
				
				ok=1;
				for (int k=0; k<nwf1.communities.size() && ok==1;++k)
				{
					mod2 = nwf1.getCommunity(k);
					if (nwn.communities.contains(mod2))
							ok=0;
				}
				
				if (ok == 0) // only if neighbor is in same community as user
				{

				agel       		= nwf1.getAge();
				genderl    		= nwf1.getGender();
				residencel 		= nwf1.getResidence();
				religionl 		= nwf1.getReligion();
				maritalstatusl 	= nwf1.getMaritalStatus();
				professionl 	= nwf1.getProfession();
				politicalorientationl 	= nwf1.getPoliticalOrientation();
				sexualorientationl 		= nwf1.getSexualOrientation();
				like1 			= nwf1.getLike(1);
				like2 			= nwf1.getLike(2);
				like3 			= nwf1.getLike(3);
				
				
		  	    if(agel != null)
				if (attVals0.indexOf(agel)==-1)		
		  	    {
		  	    	attVals0.addElement(agel); attVals0f.addElement(1); 
		  	    }
		  	    else
		  	    {
		  	    	ix = attVals0.indexOf(agel);	f = (Integer)attVals0f.elementAt(ix);
		  	    	f++;							attVals0f.setElementAt(f, ix);
		  	    }
		    			
		  	    if(genderl != null)
		    	if (attVals1.indexOf(genderl)==-1)	
		    	{
		    		attVals1.addElement(genderl); attVals1f.addElement(1);
		    	}
		    	else
		    	{
		  	    	ix = attVals1.indexOf(genderl);	f = (Integer)attVals1f.elementAt(ix);
		  	    	f++;							attVals1f.setElementAt(f, ix);
		    	}
		    				
		  	    if(residencel != null)
		  	    if (attVals2.indexOf(residencel)==-1)	
		        {
		        	attVals2.addElement(residencel); attVals2f.addElement(1);
		        }
		        else
		        {
		  	    	ix = attVals2.indexOf(residencel);	f = (Integer)attVals2f.elementAt(ix);
		  	    	f++;							    attVals2f.setElementAt(f, ix);
		        }
		        			
		  	    if(religionl != null)
		  	    if (attVals3.indexOf(religionl)==-1)		
		        {
		        	attVals3.addElement(religionl); attVals3f.addElement(1); 
		        }
		        else
		        {
		  	    	ix = attVals3.indexOf(religionl);	f = (Integer)attVals3f.elementAt(ix);
		  	    	f++;							    attVals3f.setElementAt(f, ix);
		        }
		            		
		  	    if(maritalstatusl != null)
		  	    if (attVals4.indexOf(maritalstatusl)==-1)
		        {
		        	attVals4.addElement(maritalstatusl); attVals4f.addElement(1);
		        }
		        else
		        {
		  	    	ix = attVals4.indexOf(maritalstatusl);	f = (Integer)attVals4f.elementAt(ix);
		  	    	f++;							        attVals4f.setElementAt(f, ix);
		        }
		                	
		  	    if(professionl != null)
		  	    if (attVals5.indexOf(professionl)==-1)		
		        {
		        	attVals5.addElement(professionl); attVals5f.addElement(1); 
		        }
		        else
		        {
		  	    	ix = attVals5.indexOf(professionl);	f = (Integer)attVals5f.elementAt(ix);
		  	    	f++;							    attVals5f.setElementAt(f, ix);
		        }
		                	
		  	    if(politicalorientationl != null)
		  	    	if (attVals6.indexOf(politicalorientationl)==-1)
		        {
		        	attVals6.addElement(politicalorientationl); attVals6f.addElement(1);
		        }
		        else
		        {
		  	    	ix = attVals6.indexOf(politicalorientationl);	f = (Integer)attVals6f.elementAt(ix);
		  	    	f++;							        		attVals6f.setElementAt(f, ix);		        	
		        }
		                	
		  	    if(sexualorientationl != null)
		  	    if (attVals7.indexOf(sexualorientationl)==-1)
		        {
		        	attVals7.addElement(sexualorientationl); attVals7f.addElement(1);
		        }
		        else
		        {
		  	    	ix = attVals7.indexOf(sexualorientationl);	f = (Integer)attVals7f.elementAt(ix);
		  	    	f++;							        	attVals7f.setElementAt(f, ix);
		        }
		                 	
		  	    if(like1 != null)
		  	    if (attVals8.indexOf(like1)==-1)
		        {
		        	attVals8.addElement(like1); attVals8f.addElement(1);
		        }
		        else
		        {
		  	    	ix = attVals8.indexOf(like1);	f = (Integer)attVals8f.elementAt(ix);
		  	    	f++;							attVals8f.setElementAt(f, ix);   	
		        }
		                	
		  	    if(like2 != null)
		  	    if (attVals9.indexOf(like2)==-1)			
		        {
		        	attVals9.addElement(like2); attVals9f.addElement(1);
		        }
		        else
		        {
		  	    	ix = attVals9.indexOf(like2);	f = (Integer)attVals9f.elementAt(ix);
		  	    	f++;							attVals9f.setElementAt(f, ix); 
		        }
		                	
		  	    if(like3 != null)
		  	    if (attVals10.indexOf(like3)==-1)			
		        {
		        	attVals10.addElement(like3); attVals10f.addElement(1);
		        }
		        else
		        {
		  	    	ix = attVals10.indexOf(like3);	f = (Integer)attVals10f.elementAt(ix);
		  	    	f++;							attVals10f.setElementAt(f, ix); 
		        }
		  	    
				} // eif neighbor in same community
				
			} // efor each friend
			
		

		String modeage="", modegender = "", moderesidence = "", modereligion = "", modemaritalstatus = "", modeprofession = "";
		String modepoliticalorientation = "", modesexualorientation = "", modelike1 = "", modelike2 = "", modelike3 = "";
		int maxfreq=0, maxix=0;
		//Now we find the MODAL VALUE for each attribute and assign it as the attribute-value for the present node
		for(i=0, maxfreq=0, maxix=0; i< attVals0f.size();i++) // age
		{	f = (Integer)attVals0f.elementAt(i);
			if (f > maxfreq)
			{ maxfreq = f; maxix = i; }
		}; if (i>0) modeage = (String)attVals0.elementAt(maxix);
		
		for(i=0, maxfreq=0, maxix=0; i< attVals1f.size();i++) // gender
		{	f = (Integer)attVals1f.elementAt(i);
			if (f > maxfreq)
			{ maxfreq = f; maxix = i; }
		}; if (i>0) modegender = (String)attVals1.elementAt(maxix);
		
		for(i=0, maxfreq=0, maxix=0; i< attVals2f.size();i++) // residence
		{	f = (Integer)attVals2f.elementAt(i);
			if (f > maxfreq)
			{ maxfreq = f; maxix = i; }
		}; if (i>0) moderesidence = (String)attVals2.elementAt(maxix);
		
		for(i=0, maxfreq=0, maxix=0; i< attVals3f.size();i++) // religion
		{	f = (Integer)attVals3f.elementAt(i);
			if (f > maxfreq)
			{ maxfreq = f; maxix = i; }
		}; if (i>0) modereligion = (String)attVals3.elementAt(maxix);
		
		for(i=0, maxfreq=0, maxix=0; i< attVals4f.size();i++) // maritalstatus
		{	f = (Integer)attVals4f.elementAt(i);
			if (f > maxfreq)
			{ maxfreq = f; maxix = i; }
		}; if (i>0) modemaritalstatus = (String)attVals4.elementAt(maxix);
		
		for(i=0, maxfreq=0, maxix=0; i< attVals5f.size();i++) // profession
		{	f = (Integer)attVals5f.elementAt(i);
			if (f > maxfreq)
			{ maxfreq = f; maxix = i; }
		}; if (i>0) modeprofession = (String)attVals5.elementAt(maxix);
		
		for(i=0, maxfreq=0, maxix=0; i< attVals6f.size();i++) // politicalorientation
		{	f = (Integer)attVals6f.elementAt(i);
			if (f > maxfreq)
			{ maxfreq = f; maxix = i; }
		}; if (i>0) modepoliticalorientation = (String)attVals6.elementAt(maxix);
		
		for(i=0, maxfreq=0, maxix=0; i< attVals7f.size();i++) // sexualorientation
		{	f = (Integer)attVals7f.elementAt(i);
			if (f > maxfreq)
			{ maxfreq = f; maxix = i; }
		}; if (i>0) modesexualorientation = (String)attVals7.elementAt(maxix);
		
		for(i=0, maxfreq=0, maxix=0; i< attVals8f.size();i++) // like1
		{	f = (Integer)attVals8f.elementAt(i);
			if (f > maxfreq)
			{ maxfreq = f; maxix = i; }
		}; if (i>0) modelike1 = (String)attVals8.elementAt(maxix);
		
		for(i=0, maxfreq=0, maxix=0; i< attVals9f.size();i++) // like2
		{	f = (Integer)attVals9f.elementAt(i);
			if (f > maxfreq)
			{ maxfreq = f; maxix = i; }
		}; if (i>0) modelike2 = (String)attVals9.elementAt(maxix);
		
		for(i=0, maxfreq=0, maxix=0; i< attVals10f.size();i++) // like3
		{	f = (Integer)attVals10f.elementAt(i);
			if (f > maxfreq)
			{ maxfreq = f; maxix = i; }
		}; if (i>0) modelike3 = (String)attVals10.elementAt(maxix);
		
		// OK, got all the modal value, now assign them....
		//System.out.println("\nmodeage:"+modeage);
		nwn.loadAge(modeage);
		nwn.loadGender(modegender);
		nwn.loadResidence(moderesidence);
		nwn.loadReligion(modereligion);
		nwn.loadMaritalStatus(modemaritalstatus);
		nwn.loadProfession(modeprofession);
		nwn.loadPoliticalOrientation(modepoliticalorientation);
		nwn.loadSexualOrientation(modesexualorientation);
		nwn.loadLike(modelike1, 1); 	nwn.loadLike(modelike2, 2); 	nwn.loadLike(modelike3, 3);
		
		RV.Users.remove(nwn.getUser());
		RV.Users.put(nwn.getUser(),nwn);
		
		++countall4;
		
		} // eelse use the modal values
		
		} // eif null - only consider users whose data is currently unassigned
		
	} // ewhile for all users


}

/****************************************************************************************************************************/
/*********************************************** MAIN ASSIGNMENT ROUTINE *************************************/
/****************************************************************************************************************************/
public static int rAssignDataGeneralizedSensAttr4(int numnodes, ConfigurationModel Configuration)
{
	configuration = Configuration;
	for(AttributeModel attribute : configuration.getUserAttrributesList()) {
		System.out.println(attribute.getName());
	}
	
	
	Random generator = new Random(System.currentTimeMillis()*1000);
	Random generator2 = new Random(System.currentTimeMillis()*1000);
	Enumeration en1 = RV.Users.keys();
	String str="";
	User nw, nw2;
	int user1=0,user2=0,i=0, j=0, friends_ok=0;
	
	List<String[]> paramList = new ArrayList<String[]>();
	
	
	String profile_age[]       = new String[10]; 
	String profile_gen[]       = new String[10]; 
	String profile_res[]       = new String[10]; 
	String profile_rel[]       = new String[10]; 
	String profile_mar[]       = new String[10]; 
	String profile_prof[]      = new String[10]; 
	String profile_pol[]       = new String[10]; 
	String profile_seo[]       = new String[10]; 
	String profile_lk1[]       = new String[10]; 
	String profile_lk2[]       = new String[10]; 
	String profile_lk3[]       = new String[10]; 
	
	String residence[] = new String[6]; 
	String gender[]    = new String[2]; 
	//String age[]       = new String[12]; 
	
	//String greligion[]              = new String[9];  	 int religionf[]             = new int[9];
	String gmaritalstatus[]         = new String[4];     int maritalstatusf[]        = new int[4];
	String gprofession[]            = new String[10];    int professionf[]           = new int[10];
	String gpoliticalorientation[]  = new String[7];     int politicalorientationf[] = new int[7];
	String gsexualorientation[]     = new String[4];     int sexualorientationf[]    = new int[4];
	
	String religion[]              = new String[1000];
	String maritalstatus[]         = new String[96];
	String profession[]            = new String[100];
	String politicalorientation[]  = new String[99];
	String sexualorientation[]     = new String[100];
	
	
	String weight[]    = new String[3]; 
	String like[]      = new String[12]; 
	
	String glike1[]      = new String[4];
	String glike2[]      = new String[4];
	String glike3[]      = new String[4];
	int    glikef[]     = new int[4]; 
	
	String likes1[]     = new String[265]; 
	String likes2[]     = new String[265]; 
	String likes3[]     = new String[265]; 
	//int    likef[]     = new int[4]; 

	
	String res="";
	String gen="";
	String ag="";
	
	String rel="";
	String mar="";
	String prof="";
	String pol="";
	String seo="";
	
	String we="";
	String lk1="";	String lk2="";	String lk3="";
	String lk21="";	String lk22="";	String lk23="";
	
	int genix = 0, resix = 0, agix = 0, weix = 0, lkix1 = 0, lkix2 = 0, lkix3 = 0;
	
	int relix = 0, marix = 0, profix = 0, polix = 0, seoix = 0;
	
	residence[0] = "Palo Alto";
	residence[1] = "Santa Barbara";
	residence[2] = "Winthrop";
	residence[3] = "Boston";
	residence[4] = "Cambridge";
	residence[5] = "San Jose";
	
	gender[0] = "male";
	gender[1] = "female";
	
	/*age[0] = "18-25";
	age[1] = "18-25";
	age[2] = "18-25";
	age[3] = "26-35";
	age[4] = "26-35";
	age[5] = "26-35";
	age[6] = "36-45";
	age[7] = "36-45";
	age[8] = "46-55";
	age[9] = "56-65";
	age[10] = "66-75";
	age[11] = "76-85";*/
	
	List<Pair<String, Double>> religionParamList = new ArrayList<Pair<String, Double>>();
	
	for(AttributeModel attribute : configuration.getUserAttrributesList()) {
		if(attribute.getName().equals("Age")) {
			String[] age = new String[attribute.getParameterList().size()];
			for(int ii = 0; ii < attribute.getParameterList().size(); ii++) {
				age[ii] = attribute.getParameterList().get(ii).getKey();
				
			}
			paramList.add(age);
		}
		if(attribute.getName().equals("Religion")) {
			religionParamList = attribute.getParameterList();
		}
	}

	
	// TOTAL: 1000
	/*religion[0] = "Buddhist";  religionf[0] = 68; //6.8%
	greligion[1] = "Christian"; religionf[1] = 304;//30.4%
	greligion[2] = "Hindu";     religionf[2] = 138;//13.8%
	greligion[3] = "Jewish";    religionf[3] = 2;//0.2%
	greligion[4] = "Muslim";    religionf[4] = 257;//25.7%
	greligion[5] = "Sikh";      religionf[5] = 3;//0.3%
	greligion[6] = "Traditional Spirituality";  religionf[6] = 1;//0.1%
	greligion[7] = "Other Religions";           religionf[7] = 110;//10.9%
	greligion[8] = "No religious affiliation";  religionf[8] = 117;//11.7%*/
	
	//Christian 30.39%, 
	//Muslim 25.74%, Hindu 13.8%, Buddhist 6.77%, Sikh 0.35%, Jewish 0.22%, Baha'i 0.11%, other religions 10.95%, 
	//non-religious 9.66%, atheists 2.01%
	
	// TOTAL: 96
	gmaritalstatus[0] = "Single";   maritalstatusf[0] = 30; //30.4%
	gmaritalstatus[1] = "Married";  maritalstatusf[1] = 50; // 50.8%
	gmaritalstatus[2] = "Divorced"; maritalstatusf[2] = 10; //10.0%
	gmaritalstatus[3] = "Widowed";  maritalstatusf[3] = 6;  //6.1%
	//maritalstatus[4] = "Cohabiting";
	//maritalstatus[5] = "Civil Union";
	//maritalstatus[6] = "Domestic Partnership";
	//maritalstatus[7] = "Unmarried Partners";
	

	//Profession: ISCO-08 structure 
	// TOTAL: 100
	gprofession[0] = "Manager";     		professionf[0] = 12;      //12%
	gprofession[1] = "Professional";  	    professionf[1] = 17;      //17%
	gprofession[2] = "Service";       	    professionf[2] = 14;      //14%
	gprofession[3] = "Sales and office";    professionf[3] = 18;      //18%
	gprofession[4] = "Natural resources construction and maintenance"; professionf[4] = 7; //7%
	gprofession[5] = "Production transportation and material moving";  professionf[5] = 9; //9%
	gprofession[6] = "Student";             professionf[6] = 23; //23%

	
	// TOTAL: 99
	gpoliticalorientation[0] = "Far Left";     politicalorientationf[0] = 9;   //9.4%
	gpoliticalorientation[1] = "Left";         politicalorientationf[1] = 35;  //34.7%
	gpoliticalorientation[2] = "Center Left";  politicalorientationf[2] = 18; //18.1%
	gpoliticalorientation[3] = "Center";       politicalorientationf[3] = 18;  //18.0%
	gpoliticalorientation[4] = "Center Right"; politicalorientationf[4] = 10;  //10.5%
	gpoliticalorientation[5] = "Right";        politicalorientationf[5] = 8;   // 8.0%
	gpoliticalorientation[6] = "Far Right";    politicalorientationf[6] = 1;   //1.2%
	
	// TOTAL: 100
	gsexualorientation[0] = "Asexual";      sexualorientationf[0] = 1;// 1.3%
	gsexualorientation[1] = "Bisexual";     sexualorientationf[1] = 2;//1.8%
	gsexualorientation[2] = "Heterosexual"; sexualorientationf[2] = 95;//95.4%
	gsexualorientation[3] = "Homosexual";   sexualorientationf[3] = 2;// 1.5%

		
	weight[0] = "1-2";
	weight[1] = "3-6";
	weight[2] = "7-10";
	
	glike1[0] = "entertainment"		;	glike2[0] = "entertainment"		;	glike3[0] = "music artist"		;glikef[0]  = 113;
	glike1[1] = "music artist"		;	glike2[1] = "music artist"		;	glike3[1] = "entertainment"		;glikef[1]  = 65;
	glike1[2] = "drink brand"		;	glike2[2] = "drink brand"		;	glike3[2] = "entertainment"		;glikef[2]  = 47;
	glike1[3] = "tv show"			;	glike2[3] = "drink brand"		;	glike3[3] = "soccer club"		;glikef[3]  = 40;
	// sum is 265
	
	
	//System.out.println("WELCOME TO rassigndatageneralizedsensattr3");
		
	
	int num=0, count=0;    // assign likes based on frequencies
	for(i=0, count=0; i<4; i++)
	{
		num = glikef[i];
		for (j=0;j<num;++j)
		{
			likes1[count]=glike1[i];
			likes2[count]=glike2[i];
			likes3[count]=glike3[i];
		    ++count;
		}
	}
	// assign religion based on frequencies	
	/*for(i=0, count=0; i<9; i++)
	{
		num = religionf[i];
		for (j=0;j<num;++j)
		{
			religion[count]=greligion[i];
		    ++count;
		}
	}*/
	int religionPos = 0;
	int iii = 0;
	for(AttributeModel attribute : configuration.getUserAttrributesList()) {
		if(attribute.getName().equals("Religion")) {
			religionPos = iii;
			for(i = 0, count = 0; i < attribute.getParameterList().size(); i++){
				num = (int) (attribute.getParameterList().get(i).getValue() * 100);
				for(j = 0; j < num; ++j) {
					religion[count] = attribute.getParameterList().get(i).getKey();
					++count;
				}
			}
		}
		iii++;
	}
	
	
	// assign marital status based on frequencies
	for(i=0, count=0; i<4; i++)
	{
		num = maritalstatusf[i];
		for (j=0;j<num;++j)
		{
			maritalstatus[count]=gmaritalstatus[i];
		    ++count;
		}
	}
	
	// assign profession based on frequencies
	for(i=0, count=0; i<7; i++)
	{
		num = professionf[i];
		for (j=0;j<num;++j)
		{
			profession[count]=gprofession[i];
		    ++count;
		}
	}
	
	// assign political orientation based on frequencies
	for(i=0, count=0; i<7; i++)
	{
		num = politicalorientationf[i];
		for (j=0;j<num;++j)
		{
			politicalorientation[count]=gpoliticalorientation[i];
		    ++count;
		}
	}
	
	// assign sexual orientation based on frequencies
	for(i=0, count=0; i<4; i++)
	{
		num = sexualorientationf[i];
		for (j=0;j<num;++j)
		{
			sexualorientation[count]=gsexualorientation[i];
		    ++count;
		}
	}
	
	//for(i=0;i<410;i++)
		//System.out.println(likes[i]);
	
	//get N / D seed vertices
	
	double numseeds = RV.seeds.size();
	
	//System.out.println("numseeds: "+numseeds+" numnodes: "+numnodes);
	
	if (numseeds > numnodes)
	{
		System.out.println("ERROR. numseeds > numnodes");
		return -1;
	}
	
	
	
	// ASSIGN THE PROFILES
	profile_age[0]  = paramList.get(0)[2]; 					profile_gen[0]  = gender[0]; 			profile_res[0]  = residence[3]; 
	profile_rel[0]  = religionParamList.get(8).getKey();			profile_mar[0]  = gmaritalstatus[1]; 	profile_prof[0] = gprofession[3]; 
	profile_lk1[0]  = glike1[3];  				profile_lk2[0]  = glike2[3]; 			profile_lk3[0]  = glike3[3];
	profile_pol[0]  = gpoliticalorientation[3]; profile_seo[0]  = gsexualorientation[2]; 
	
	profile_age[1]  = paramList.get(0)[1]; 					profile_gen[1]  = gender[1]; 			profile_res[1]  = residence[4]; 
	profile_rel[1]  = religionParamList.get(0).getKey();			profile_mar[1]  = gmaritalstatus[2]; 	profile_prof[1] = gprofession[0]; 
	profile_lk1[1]  = glike1[1];  				profile_lk2[1]  = glike2[1]; 			profile_lk3[1]  = glike3[1];
	profile_pol[1]  = gpoliticalorientation[1]; profile_seo[1]  = gsexualorientation[1]; 
	
	profile_age[2]  = paramList.get(0)[0];					profile_gen[2]  = gender[0]; 			profile_res[2]  = residence[0]; 
	profile_rel[2]  = religionParamList.get(1).getKey();			profile_mar[2]  = gmaritalstatus[0]; 	profile_prof[2] = gprofession[6]; 
	profile_lk1[2]  = glike1[2];  				profile_lk2[2]  = glike2[2]; 			profile_lk3[2]  = glike3[2];
	profile_pol[2]  = gpoliticalorientation[2]; profile_seo[2]  = gsexualorientation[2];
	
	profile_age[3]  = paramList.get(0)[0];				profile_gen[3]  = gender[1]; 			profile_res[3]  = residence[2]; 
	profile_rel[3]  = religionParamList.get(4).getKey();			profile_mar[3]  = gmaritalstatus[0]; 	profile_prof[3] = gprofession[1]; 
	profile_lk1[3]  = glike1[0];  				profile_lk2[3]  = glike2[0]; 			profile_lk3[3]  = glike3[0];
	profile_pol[3]  = gpoliticalorientation[4]; profile_seo[3]  = gsexualorientation[2];
	
	profile_age[4]  = paramList.get(0)[4];					profile_gen[4]  = gender[0]; 			profile_res[4]  = residence[1]; 
	profile_rel[4]  = religionParamList.get(2).getKey();		profile_mar[4]  = gmaritalstatus[3]; 	profile_prof[4] = gprofession[4]; 
	profile_lk1[4]  = glike1[3];  				profile_lk2[4]  = glike2[3]; 			profile_lk3[4]  = glike3[3];
	profile_pol[4]  = gpoliticalorientation[5]; profile_seo[4]  = gsexualorientation[2];
	
	profile_age[5]  = paramList.get(0)[5];					profile_gen[5]  = gender[1]; 			profile_res[5]  = residence[5]; 
	profile_rel[5]  = religionParamList.get(3).getKey();			profile_mar[5]  = gmaritalstatus[1]; 	profile_prof[5] = gprofession[5]; 
	profile_lk1[5]  = glike1[1];  				profile_lk2[5]  = glike2[1]; 			profile_lk3[5]  = glike3[1];
	profile_pol[5]  = gpoliticalorientation[0]; profile_seo[5]  = gsexualorientation[2];
	
	profile_age[6]  = paramList.get(0)[0];				profile_gen[6]  = gender[1]; 			profile_res[6]  = residence[2]; 
	profile_rel[6]  = religionParamList.get(1).getKey();			profile_mar[6]  = gmaritalstatus[0]; 	profile_prof[6] = gprofession[1]; 
	profile_lk1[6]  = glike1[0];  				profile_lk2[6]  = glike2[0]; 			profile_lk3[6]  = glike3[0];
	profile_pol[6]  = gpoliticalorientation[4]; profile_seo[6]  = gsexualorientation[2];
	
	profile_age[7]  = paramList.get(0)[0];					profile_gen[7]  = gender[1]; 			profile_res[7]  = residence[2]; 
	profile_rel[7]  = religionParamList.get(3).getKey();			profile_mar[7]  = gmaritalstatus[0]; 	profile_prof[7] = gprofession[1]; 
	profile_lk1[7]  = glike1[0];  				profile_lk2[7]  = glike2[0]; 			profile_lk3[7]  = glike3[0];
	profile_pol[7]  = gpoliticalorientation[4]; profile_seo[7]  = gsexualorientation[2];
	
	profile_age[8]  = paramList.get(0)[4];					profile_gen[8]  = gender[0]; 			profile_res[8]  = residence[1]; 
	profile_rel[8]  = religionParamList.get(2).getKey();			profile_mar[8]  = gmaritalstatus[3]; 	profile_prof[8] = gprofession[4]; 
	profile_lk1[8]  = glike1[3];  				profile_lk2[8]  = glike2[3]; 			profile_lk3[8]  = glike3[3];
	profile_pol[8]  = gpoliticalorientation[1]; profile_seo[8]  = gsexualorientation[2];
	
	profile_age[9]  = paramList.get(0)[4];					profile_gen[9]  = gender[0]; 			profile_res[9]  = residence[1]; 
	profile_rel[9]  = religionParamList.get(2).getKey(); 			profile_mar[9]  = gmaritalstatus[3]; 	profile_prof[9] = gprofession[4]; 
	profile_lk1[9]  = glike1[3];  				profile_lk2[9]  = glike2[3]; 			profile_lk3[9]  = glike3[3];
	profile_pol[9]  = gpoliticalorientation[0]; profile_seo[9]  = gsexualorientation[2];
	
	
    // The following three routines do all the work
	AssignSeeds(generator, profile_age,profile_gen,profile_res, profile_rel, profile_mar, profile_prof, profile_pol,
                profile_seo, profile_lk1, profile_lk2, profile_lk3);
		
	boolean switchdistance = true;
	AssignNeighbors(generator, generator2, paramList.get(0), residence, gender, sexualorientation, politicalorientation, 
			        religion, maritalstatus, profession, likes1, likes2, likes3, switchdistance);
	
	
	int RANLIMUNAS= configuration.getRandomness(); // Unassigned nodes assignment, 0 is equivalent to 10% random assignment (low). 
	                  // 2 is 30% (medium), 4 is 50% (high). 
    // The remainder are assigned the modal values for each attribute for the 
    // neighbors with values assigned
	System.out.println("Randomness: " + RANLIMUNAS);
	
	AssignUnassigned(generator, generator2, RANLIMUNAS, paramList.get(0), residence, gender, sexualorientation, politicalorientation, 
	        religion, maritalstatus, profession, likes1, likes2, likes3);
	

	// AND FINALLY, WE ASSIGN THE WEIGHTS. WE DO THIS AS THE END BECAUSE THE WEIGHTS CALCULATION BETWEEN TWO NODES
	// IS BASED ON THE DISTANCE BETWEEN ALL THE RESPECTIVE ASSIGNED ATTRIBUTES.
	Enumeration en3 = RV.Users.keys();
	User na, nb; 
	double distattr = 0.0, maxdist=0.0;
	while (en3.hasMoreElements() ){  // for all users
		na = (User)RV.Users.get((Integer)en3.nextElement());
		user1 = na.getUser();
		String agen = na.getAge();
	
		if (agen != null)
		{

			for(i=0;i<na.friends.size();++i) // for all neighbors
			{
				user2 = na.friends.get(i);
				nb = (User)RV.Users.get(user2);
					
				distattr = 1.0 - calcDistanceBetweenUsers.calcDistanceBetweenUsers(user1, user2);

				na.weights.set(i, distattr);
				
				RV.Users.remove(na.getUser());
				RV.Users.put(na.getUser(),na);

			} // efor
		} // eif
	} // ewhile
	

	return 0;
	
} //fin de funcion 'rAssignDataGeneralizedSensAttr4'

} // end of public class rAssignDataGeneralizedSensAttr4