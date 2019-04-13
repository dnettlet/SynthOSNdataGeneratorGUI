package genDataNOapplication;

import java.util.List;

import genDataNOapplication.RV.RV;
import genDataNOapplication.model.ConfigurationModel;

// Copyright (C) 2018  David F. Nettleton (david.nettleton@upf.edu)
// License: GNU GENERAL PUBLIC LICENSE v3.0   See LICENSE for the full license.

/*import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;*/


/**
 * @author  David Nettleton
 * @created Nov 28th, 2012
 * @last modified 	April 30th, 2018
 */
public class GenDataNO //extends JFrame 
{
static GenDataNO thetestRV;



/**
 */
public static double main(ConfigurationModel configuration)
{
   /*try 
   {
       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
   }
   catch ( ClassNotFoundException e ) 
   {
   }
   catch ( InstantiationException e ) 
   {
   }
   catch ( IllegalAccessException e ) 
   {
   }
   catch ( UnsupportedLookAndFeelException e ) 
   {
   }*/
	
   RV rv1 = new RV();
   double ret = rv1.RVp(configuration);
   
   return ret;
   
} // fin de main
} // end of clase URL
