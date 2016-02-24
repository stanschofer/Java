
import java.io.*;
import java.util.*;
import java.text.*;

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
				String filename = "";
				String dir = "/home/stanschofer/Documents/SSAE 16 Audit Reports All user 20160422/";
				String fullpathIn = "";
				String fullpathOut = "";
				String strLogon = "";
				String strPrevLogon = "";


				
				int iRecs = 0;
				int iFileCntr = 1;

				
				if (args.length == 0)
				{
					System.out.println("No Arguements");
					return;
				}
				if (args.length == 1)
				{
					filename = args[0];
					fullpathIn = dir + filename; 
					
				}
				else
				{
					filename = args[0];
					dir = args[1];
					fullpathIn = dir + filename; 
					
				}
				
				fullpathOut = fullpathIn.replace(".csv", "-1.csv");
				

		        // The name of the file to open.

		        // This will reference one line at a time
		        String line = null;
		        FileReader fileReader;
		        FileWriter fileWriter;

		        try {
		            // FileReader reads text files in the default encoding.
		            fileReader = new FileReader(fullpathIn);

		            // Always wrap FileReader in BufferedReader.
		            BufferedReader bufferedReader =  new BufferedReader(fileReader);

		            //now open output
		            // Assume default encoding.
		            fileWriter = new FileWriter(fullpathOut);

		            // Always wrap FileWriter in BufferedWriter.
		            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
		            
		            while((line = bufferedReader.readLine()) != null) {	
		            	iRecs++;
		            	if (line  != null)
		            	{
		            		String[] parts = line.split(",");
		            		if (parts.length >= 3)
		            		{
		            			if (parts[0] !="" )
		            			{	
		            				try {
		            					strLogon = parts[0];
		            					if (iRecs > 1000000)
		            					{
		            						if (strLogon!= strPrevLogon)
		            						{
		            				        	bufferedWriter.close();
		            				        	iFileCntr++;
		            				        	fullpathOut = fullpathOut.replace("-"+Integer.toString(iFileCntr-1),"-"+ Integer.toString(iFileCntr));
		            				            //now open output
		            				            // Assume default encoding.
		            				            fileWriter = new FileWriter(fullpathOut);
		    		        					System.out.println("Opening for output " + fullpathOut);

		            				            // Always wrap FileWriter in BufferedWriter.
		            				            bufferedWriter = new BufferedWriter(fileWriter);
		            				            iRecs = 0;
		            						}		            						
		            					}
	            						bufferedWriter.write(line + "\r"); 
	            						bufferedWriter.flush();

	
		            				} 
		            				catch (Exception e) {
		            					System.out.println(e.getMessage());
		            					continue;
		            				}
		            			}
		            			else
		            			{
		        					System.out.println("Could not find logon " + line);
		            				continue;
		            			}
		            		}
		            		else
		            		{
		    					System.out.println("Line missing data " + line);
		            			continue;
		            		}

		                	strPrevLogon = strLogon;
		            	}
		            	else
		            	{
	    					System.out.println("Line too short " + line);
	            			continue;		            		
		            	}
		            }   

		        	bufferedReader.close();  
		      	
		        	bufferedWriter.close();

		        }
		        catch(FileNotFoundException ex) {
		            System.out.println(ex.getMessage());                
		        }
		        catch(IOException ex) {
		            System.out.println(ex.getMessage());                  

		       }
		 
		  	}

}
