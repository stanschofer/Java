package parser;
import java.io.*;
import java.util.*;
import java.text.*;


public class main {

	/**
	 * written by SWS  2/1/2016
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String filename = "";
		String dir = "/Users/swschofer/Documents/Schedules/";
		String fullpathIn = "";
		String fullpathOut = "";
		String strStartDate = "";
		String strEndDate = "";
		String strSport = "";
		String strOpponent = "";
		String strLocation  = "";
		String strDescription = "";
		String strOut = "";
		
		String header = "BEGIN:VCALENDAR\rCALSCALE:GREGORIAN\rX-WR-TIMEZONE;VALUE=TEXT:US/Eastern\rMETHOD:PUBLISH\rPRODID:-//Apple Computer\\, Inc//iCal 1.0//EN\rX-WR-CALNAME;VALUE=TEXT:Example\rVERSION:2.0";
		String footer = "\rEND:VCALENDAR";
		String alarm = "\rBEGIN:VALARM\rTRIGGER;VALUE=DURATION:-P1D\rACTION:DISPLAY\rDESCRIPTION:Event reminder\rEND:VALARM";
		int iPos = 0;

		
		if (args.length == 0)
		{
			System.out.println("Need a file in /Users/swschofer/Documents/Schedules/");
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
		
		fullpathOut = fullpathIn.replace(".txt", ".ics");
		

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

            bufferedWriter.write(header);
            
            while((line = bufferedReader.readLine()) != null) {
            	String testDate = line;
            	strOut = "\rBEGIN:VEVENT\rSEQUENCE:"  + String.valueOf(iPos);
            	
            	if ((line = bufferedReader.readLine()) != null)
            	{
            		String[] parts = line.split("\t");
            		if (parts.length >= 3)
            		{
            			if (parts[0] !="" )
            			{	
            				DateFormat formatter = new SimpleDateFormat("MM/dd/yy,h:mm a");
            				DateFormat formatterOut = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
            				try {
            					testDate += "," + parts[0];
            					Date date = formatter.parse(testDate);
            					strStartDate = formatterOut.format(date);
            					
            				    Calendar cl = Calendar. getInstance();
            				    cl.setTime(date);
            				    cl.add(Calendar.HOUR, 2);
            					strEndDate = formatterOut.format(cl.getTime());
            					strOut = strOut + "\rDTSTART;TZID=US/Eastern:" + strStartDate;
            					strOut = strOut + "\rDTSTAMP:" + strStartDate;

            				} 
            				catch (ParseException e) {
            					System.out.println(e.getMessage());
            					continue;
            				}
            			}
            			else
            			{
        					System.out.println("Could not find date " + line);
            				continue;
            			}
            			if (parts[1] !="" )
            			{	
            				strSport = parts[1];
            			}
            			else
            			{
        					System.out.println("Could not find sport " + line);
            				continue;
            			}            			
            			if (parts[2] !="" )
            			{	
            				String temp = parts[2];
            				strDescription = temp;
            				int iPosInStr = temp.indexOf("vs.");
            				if ( iPosInStr > 0)
            				{
            					String str1, str2;
            					str1  = temp.substring(iPosInStr+3); 
            					str2 = temp.substring(0,iPosInStr-1);
            					str1 = str1.trim();
            					str2 = str2.trim();
            					if (str2.matches("Kentucky"))
            					{
            						strOpponent = str1;
            						strLocation = str2;
            					}
            					else
            					{
            						strOpponent = str2;
            						strLocation = str2;
            					}

            				}
            				else
            				{
            					System.out.println("Could not find vs." + line);
            					continue;            					
            				}
            			}
            			else
            			{
        					System.out.println("Could not find OponentLocation " + line);
        					continue;
            			}  
            			strLocation = parts[parts.length-1];
            		}
            		else
            		{
    					System.out.println("Line missing data " + line);
            			continue;
            		}
            		System.out.println(strStartDate + " " + strEndDate + " " + strSport +  " " + strDescription + " " + strLocation);
                	
        			strOut = strOut + "\rSUMMARY:" + strSport;
        			strOut = strOut + " " + strDescription;
        			strOut = strOut + " " + strLocation;
        			strOut = strOut + "\rDTEND;TZID=US/Eastern:" + strEndDate;
        			strOut = strOut + alarm;
        			strOut = strOut + "\rEND:VEVENT";  
                	bufferedWriter.write(strOut);   
            	}
            }   

        	bufferedReader.close();  
      	
        	bufferedWriter.write(footer);
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
