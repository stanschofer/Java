package ShireHoaDB;

import java.util.*;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class unitDB extends dbBase{
	
	private String errorMessage;
    private Boolean error;
    
    public unitDB(String dbServer,String dbUser,String dbPassword)
    {
   
        	errorMessage = "";
    		try
    		{
    			if (this.connect(dbServer, dbUser, dbPassword))	
    			{
    				error = false;
    			}
    			else
    			{
    				error = true;
    			}

    		}
    		catch(Exception ex)
    		{
    			errorMessage = ex.getMessage();
    		}   
    	}
	
	   public ArrayList<Unit> Select ()
	    {
	    	ArrayList<Unit> units = new ArrayList();
	    	ResultSet rs = null;
			Statement statement = null;
	     	error = false;
	    	errorMessage = "";
	    	try {
	    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
	    		{
	    			statement = this.getCon().createStatement();   
	    			rs = statement   
	                    .executeQuery("select * from unit order by address, unitletter" );   

	    			while (rs.next()) 
	    			{ 
	    				try 
	    				{
	    					Unit unit = new Unit(rs.getInt("address"),
	    						rs.getString("unitletter"),
	    						rs.getString("landline"),
	    						rs.getInt("unitid"));
	    					
	    					units.add(unit);
	    				}
	    				catch (Exception ex)
	    				{
	    					errorMessage = ex.getMessage();
	    	    				error = true;    					
	    				}
	     		}   
	    		}
	    		else
	    		{
	    			errorMessage = "Connection is not open!";
	    			error = true;
	    		}
	    	} 
	    	catch (SQLException ex) {
	    		errorMessage =  ex.getMessage();
	    	}
	    	
	    	return units;
	   }

	   
	   public String Update(Unit unit)
	   {
		   	int retVal = 0;
		    	String errorMessage = "";
		    	Statement statement = null;
		    	try {
		    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
		    		{
		    			statement = this.getCon().createStatement();   
		    			retVal = statement.executeUpdate("update unit set address='" + unit.getAddress() +  "', unitletter='" +  unit.getUnitletter() +  "', landline='" + unit.getLandline() + "' where unitid=" + unit.getUnitid() );   
		    			if (retVal == 0)
		    			{
		    				errorMessage = "nothing updated";
		    			}

		    		}
		    		else
		    		{
		    			errorMessage = "Connection is not open!";
		    			error = true;
		    		}
		    	} 
		    	catch (SQLException ex) {
		    		errorMessage =  ex.getMessage();
		    	}
				   
				   
				 			   
		   return errorMessage;
	   
	   }
	   
	   
	   
	   public String Add(Unit unit)
	   {
		   	int retVal = 0;
		    	String errorMessage = "";
		    	Statement statement = null;
		    	try {
		    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
		    		{
		    			statement = this.getCon().createStatement();   
		    			retVal = statement.executeUpdate("insert into unit (address,unitletter,landline) values (" + unit.getAddress() +  ",'" +  unit.getUnitletter() +  "', '" + unit.getLandline() + "')" );   
		    			if (retVal == 0)
		    			{
		    				errorMessage = "nothing inserted";
		    			}

		    		}
		    		else
		    		{
		    			errorMessage = "Connection is not open!";
		    			error = true;
		    		}
		    	} 
		    	catch (SQLException ex) {
		    		errorMessage =  ex.getMessage();
		    	}
				   
				   
				 			   
		   return errorMessage;
	   
	   }
}


