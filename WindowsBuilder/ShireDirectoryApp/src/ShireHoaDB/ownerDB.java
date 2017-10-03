package ShireHoaDB;

import java.util.*;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class ownerDB extends dbBase{
	
	private String errorMessage;
    private Boolean error;
    
    public ownerDB(String dbServer,String dbUser,String dbPassword)
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
	
    

	   public ArrayList<owner> Select (int unitID)
	    {
		    ArrayList<owner> owners = new ArrayList();
		    	ResultSet rs = null;
		    	Statement statement = null;
		    error = false;
		    	errorMessage = "";
		    	try {
		    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
		    		{
		    			statement = this.getCon().createStatement();   
		    			rs = statement   
		                    .executeQuery("select * from owner where unitid=" + Integer.toString(unitID)  + "  order by lastname, firstname" );   
	
		    			while (rs.next()) 
		    			{ 
		    				try 
		    				{
		    					owner ownerRec = new owner(rs.getInt("ownerid"),
		    						rs.getString("lastname"),
		    						rs.getString("firstname"),
		    						rs.getInt("unitid"));
		    					
		    					owners.add(ownerRec);
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
		    	
		    	return owners;
	   }


	   
	   public String Update(owner person)
	   {
		   	int retVal = 0;
		    	String errorMessage = "";
		    	Statement statement = null;
		    	try {
		    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
		    		{
		    			statement = this.getCon().createStatement();  
		    			retVal = statement.executeUpdate("update owner set lastname='" + person.getLastname() + "', firstname='" +  person.getFirstname() +"' where ownerid=" + person.getOwnerid() );   
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
	   
	   public String Add(owner person)
	   {
		   	int retVal = 0;
		    	String errorMessage = "";
		    	Statement statement = null;
		    	try {
		    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
		    		{
		    			statement = this.getCon().createStatement();   
		    			retVal = statement.executeUpdate("insert into owner (unitid, lastname,firstname)  values(" + Integer.toString(person.getOwnerid()) + ",'" + person.getLastname() + "', '" +  person.getFirstname() + "')" );   
		    			if (retVal == 0)
		    			{
		    				errorMessage = "nothing added";
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
	   
	   public String Delete(owner person)
	   {
		   	int retVal = 0;
		    	String errorMessage = "";
		    	Statement statement = null;
		    	try {
		    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
		    		{
		    			statement = this.getCon().createStatement();   
		    			String strQuery = "delete from owner where ownerid=" + Integer.toString(person.getOwnerid());
		    			retVal = statement.executeUpdate(strQuery);   
		    			if (retVal == 0)
		    			{
		    				errorMessage = "nothing deleted";
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

