package ShireHoaDB;
import java.sql.Connection;   
import java.sql.DriverManager;   
import java.sql.ResultSet;   
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;

public class dbBase {

	private String errorMessage;
    private Connection con;
    private Boolean error;
    private String dbServer = "";
    private String dbUser = "";
    private String dbPassword = "";
	
	public dbBase()
    {
    	errorMessage = "";
    	con = null;
		try
		{

			dbServer = "192.168.254.111";		
			dbUser = "ShireHOA";		
			dbPassword = "June2017";		

		}
		catch(Exception ex)
		{
			errorMessage = ex.getMessage();
		}
    }
    
    public  String getErrorMessage()
    {
    	return errorMessage;
    }
    
    public Connection getCon()
    {
    		return con;
    }
    
    public Boolean getError()
    {
    	return error;
    }
    
    public boolean isConnected()
    {
    	return (con != null);
    }
 
    public boolean connect ()
    {
 		error = false;
    	errorMessage = "";
    	try {
    		try {
    	 	    Class.forName("org.postgresql.Driver");
    	        } catch (ClassNotFoundException e) {
    	        	errorMessage="Driver not found:" + e + e.getMessage();
    	        	return false;
    	        }
     		con = DriverManager.getConnection("jdbc:postgresql:" + dbServer, dbUser, dbPassword);
    	} 
    	catch (SQLException ex) {
    		errorMessage =  ex.getMessage();
    		error = true;
    	}
    	
    	return error;
     }

    
    public boolean connect (String url,String user,String password)
    {
 		error = false;
    	errorMessage = "";
    	try {
    		try {
    	 	    Class.forName("org.postgresql.Driver");
    	        } catch (ClassNotFoundException e) {
    	        	errorMessage="Driver not found:" + e + e.getMessage();
    	        	return false;
    	        }
     		con = DriverManager.getConnection("jdbc:postgresql:" + url, user, password);
     	    dbServer = url;
     	    dbUser = user;
     	    dbPassword = password;
   	} 
    	catch (SQLException ex) {
    		errorMessage =  ex.getMessage();
    		error = true;
    	}
    	
    	return error;
     }
    
    public boolean disConnect()
    {
       	boolean error = false;
    	errorMessage = "";
    	try {
    		if ( (con != null) &&  ( !con.isClosed()))
    		{
    			con.close();
    		}
    		else
    		{
    			errorMessage = "Connection is not open!";
    			error = true;
    		}
    	} 
    	catch (SQLException ex) {
    		errorMessage =  ex.getMessage();
    		error = true;
    	}
    	
    	return error;
    	
    }

}
