package ShireHoaDB;

import java.util.*;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class EmailAddressDB extends dbBase{
	
	private String errorMessage;
    private Boolean error;
    
    public EmailAddressDB(String dbServer,String dbUser,String dbPassword)
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
	
	   public ArrayList<EmailAddress> Select (int owneridIn)
	    {
	    	ArrayList<EmailAddress> emailaddresses = new ArrayList();
	    	ResultSet rs = null;
			Statement statement = null;
	     	error = false;
	    	errorMessage = "";
	    	try {
	    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
	    		{
	    			statement = this.getCon().createStatement();   
	    			rs = statement   
	                    .executeQuery("select emailaddressid, ownerid, emailaddress from emailaddress where ownerid=" + Integer.toString(owneridIn) +  " order by emailaddress" );   

	    			while (rs.next()) 
	    			{ 
	    				try 
	    				{
	    					EmailAddress emailAddr = new EmailAddress(rs.getInt("ownerid"),
	    						rs.getString("emailaddress"),
	    						rs.getInt("emailaddressid"));
	    					
	    					emailaddresses.add(emailAddr);
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
	    	
	    	return emailaddresses;
	   }

	   public String Update(EmailAddress email)
	   {
		   	int retVal = 0;
		    	String errorMessage = "";
		    	Statement statement = null;
		    	try {
		    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
		    		{
		    			statement = this.getCon().createStatement();  
		    			retVal = statement.executeUpdate("update emailaddress set emailaddress='" + email.getEmailaddress() +"' where emailaddressid=" + email.getEmailaddressid() );   
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
	   
	   public String Add(EmailAddress email)
	   {
		   	int retVal = 0;
		    	String errorMessage = "";
		    	Statement statement = null;
		    	try {
		    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
		    		{
		    			statement = this.getCon().createStatement();   
		    			retVal = statement.executeUpdate("insert into emailaddress ( emailaddress,ownerid)  values('"  + email.getEmailaddress() + "', " +  Integer.toString(email.getOwnerid()) + ")" );   
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
	   
	   public String Delete(EmailAddress email)
	   {
		   	int retVal = 0;
		    	String errorMessage = "";
		    	Statement statement = null;
		    	try {
		    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
		    		{
		    			statement = this.getCon().createStatement();   
		    			String strQuery = "delete from emailaddress where emailaddressid=" + Integer.toString(email.getEmailaddressid());
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
	   
	   public String DeleteAllEmailAddressesForOwner(int ownerid)
	   {
		   	int retVal = 0;
		    	String errorMessage = "";
		    	Statement statement = null;
		    	try {
		    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
		    		{
		    			statement = this.getCon().createStatement();   
		    			String strQuery = "delete from emailaddress where ownerid=" + Integer.toString(ownerid);
		    			retVal = statement.executeUpdate(strQuery);   
		    			if (retVal > 0)
		    			{
		    				errorMessage = "all emailaddresses deleted";
		    			}

		    		}
		    		else
		    		{
		    			errorMessage = "Connection is not open!";
		    			error = true;
		    		}
		    	} 
		    	catch (SQLException ex) {
		    		errorMessage =  "DeleteAllEmailAddressesForOwner - " + ex.getMessage();
		    	}
				   
				   
				 			   
		   return errorMessage;
	   
	   }
}
