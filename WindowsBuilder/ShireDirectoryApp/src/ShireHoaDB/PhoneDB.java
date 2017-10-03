package ShireHoaDB;

import java.util.*;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

public class PhoneDB extends dbBase{
	
	private String errorMessage;
    private Boolean error;
    
    public PhoneDB(String dbServer,String dbUser,String dbPassword)
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
	
	   public ArrayList<Phone> Select (int owneridIn)
	    {
	    	ArrayList<Phone> phones = new ArrayList();
	    	ResultSet rs = null;
			Statement statement = null;
	     	error = false;
	    	errorMessage = "";
	    	try {
	    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
	    		{
	    			statement = this.getCon().createStatement();   
	    			rs = statement   
	                    .executeQuery("select phonenumber, ownerid, phonetype, phoneid from phone where ownerid=" + Integer.toString(owneridIn) +  " order by phonenumber" );   

	    			while (rs.next()) 
	    			{ 
	    				try 
	    				{
	    					Phone phone = new Phone(rs.getInt("ownerid"),
	    						rs.getString("phonenumber"),
	    						rs.getString("phonetype"),
	    						rs.getInt("phoneid"));
	    					
	    					phones.add(phone);
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
	    	
	    	return phones;
	   }
	   public String Update(Phone phone)
	   {
		   	int retVal = 0;
		    	String errorMessage = "";
		    	Statement statement = null;
		    	try {
		    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
		    		{
		    			statement = this.getCon().createStatement();  
		    			retVal = statement.executeUpdate("update phone set phonenumber='" + phone.getPhoneNumber() +"' , phonetype='" + phone.getPhoneType() + "' where phoneid=" + phone.getPhoneID() );   
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
	   
	   public String Add(Phone phone)
	   {
		   	int retVal = 0;
		    	String errorMessage = "";
		    	Statement statement = null;
		    	try {
		    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
		    		{
		    			statement = this.getCon().createStatement();   
		    			String str = "insert into phone ( phonenumber, phonetype,ownerid)  values('"  + phone.getPhoneNumber() + "', '" + phone.getPhoneType() + "', " 
		    			+  Integer.toString(phone.getOwnerid()) + ")";
		    			retVal = statement.executeUpdate(str );   
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
	   
	   public String Delete(Phone phone)
	   {
		   	int retVal = 0;
		    	String errorMessage = "";
		    	Statement statement = null;
		    	try {
		    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
		    		{
		    			statement = this.getCon().createStatement();   
		    			String strQuery = "delete from phone where phoneid=" + Integer.toString(phone.getPhoneID());
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
	   
	   public String DeleteAllPhonesForOwner(int ownerid)
	   {
		   	int retVal = 0;
		    	String errorMessage = "";
		    	Statement statement = null;
		    	try {
		    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
		    		{
		    			statement = this.getCon().createStatement();   
		    			String strQuery = "delete from phone where ownerid=" + Integer.toString(ownerid);
		    			retVal = statement.executeUpdate(strQuery);   
		    			if (retVal > 0)
		    			{
		    				errorMessage = " all phones deleted";
		    			}

		    		}
		    		else
		    		{
		    			errorMessage = "Connection is not open!";
		    			error = true;
		    		}
		    	} 
		    	catch (SQLException ex) {
		    		errorMessage =  "DeleteAllPhonesForOwner - " + ex.getMessage();
		    	}
				   
				   
				 			   
		   return errorMessage;
	   
	   }

}

