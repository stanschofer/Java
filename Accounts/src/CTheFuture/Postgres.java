package CTheFuture;
import java.sql.Connection;   
import java.sql.DriverManager;   
import java.sql.ResultSet;   
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import CTFActiveMQ.ActiveMQ;
import CTFEncryptDecrypt.*;

public class Postgres {

	private String errorMessage;
    private Connection con;
    private Boolean error;
    private String useActiveMQ="N";
    private String activeMQIPAddress="";
    private String activeMQQueue="";

    
    private String dbServer = "";
    private String dbUser = "";
    private String dbPassword = "";
	
	public Postgres()
    {
    	errorMessage = "";
    	con = null;
		try
		{
			// reads from context.xml
			// Ubantu /var/lib/tomcat6
			// Mac /Library/Tomcat/apache-tomcat-7.033     /usr/local/apache-tomcat-7.0.33

			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			/* Lookup environment entry */ 
			useActiveMQ = (String) envCtx.lookup("useActiveMQ");	
			activeMQIPAddress = (String) envCtx.lookup("activeMQIPAddress");	
			activeMQQueue = (String) envCtx.lookup("activeMQQueue");	
			dbServer = (String) envCtx.lookup("dbServer");		
			dbUser = (String) envCtx.lookup("dbUser");		
			dbPassword = (String) envCtx.lookup("dbPassword");		

		}
		catch(NamingException ex)
		{
			errorMessage = ex.getMessage();
		}
    }
    
    public  String getErrorMessage()
    {
    	return errorMessage;
    }
    
    public  String UseActiveMQ()
   {
  	return useActiveMQ;
   }
 
    public  String ActiveMQIPAddress()
   {
  	return activeMQIPAddress;
   }

    public  String ActiveMQQueue()
   {
  	return activeMQQueue;
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
    	        	errorMessage="<h1>Driver not found:" + e + e.getMessage() + "</h1>";
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
    	        	errorMessage="<h1>Driver not found:" + e + e.getMessage() + "</h1>";
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
    
    public ArrayList<Account> Select (String query, String password)
    {
    	ArrayList<Account> accounts = new ArrayList();
    	ResultSet rs = null;
		Statement statement = null;
     	error = false;
    	errorMessage = "";
    	try {
    		if ( (con != null) &&  ( !con.isClosed()))
    		{
    			statement = con.createStatement();   
    			rs = statement   
                    .executeQuery(query);   
    			CTFEncryptDecrypt encryption = new CTFEncryptDecrypt(password);

    			while (rs.next()) { 
    				try 
    				{
    					Account acct = new Account(rs.getString("account"),
    						rs.getString("userid"),
    						encryption.decrypt(rs.getString("password")),
    						rs.getInt("key"));
    					accounts.add(acct);
    				}
    				catch (Exception ex)
    				{
    					
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
    	
    	return accounts;
   }
    
    public int DeleteAccount(Account acct)
    {
    	int recs = -1;
       	error = false;
    	errorMessage = "";
    	try {
    		if ( (con != null) &&  ( !con.isClosed()))
    		{
    		   	PreparedStatement st = con.prepareStatement("DELETE FROM Accounts WHERE key = ?");
    	    	st.setInt(1, acct.getKey());
    	    	recs = st.executeUpdate();
      	    	if (recs == 1)
    	    	{
      	    		if (useActiveMQ.equals("Y"))
      	    		{
	    				ActiveMQ queue = new ActiveMQ();
	    				if (queue.connect(activeMQIPAddress, activeMQQueue) )
	    				{
	     	       			String msg =String.format("DELETE FROM Accounts WHERE key = %d",acct.getKey());
	    	       			queue.send(msg);
	    	       			queue.close();
	      				}
	    				else
	    				{
	     				}
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
	
    	return recs;
    }   
    
    public int InsertAccount(Account acct, String password)
    {
    	int recs = -1;
       	error = false;
    	errorMessage = "";
    	try {
    		if ( (con != null) &&  ( !con.isClosed()))
    		{
    			CTFEncryptDecrypt encryption = new CTFEncryptDecrypt(password);
    		   	PreparedStatement st = con.prepareStatement("Insert into Accounts (account,userid,password) values(?,?,?)");
    	    	st.setString(1, acct.getAccount());
       	    	st.setString(2, acct.getUserID());
       	    	st.setString(3, encryption.encrypt(acct.getPassword()));
    	    	recs = st.executeUpdate();
    	    	if (recs == 1)
    	    	{
      	    		if (useActiveMQ.equals("Y"))
      	    		{
	      	    		ActiveMQ queue = new ActiveMQ();
	    				if (queue.connect(activeMQIPAddress, activeMQQueue) )
	    				{
	    		   			ResultSet rs = null;
	    					Statement statement = null;
	    		   			statement = con.createStatement();   
	      		   			String query = String.format("select max(key) as key, account from accounts where account='%s' group by account",acct.getAccount());
	    	    			rs = statement.executeQuery(query);   
	    	    			if (rs.next()) 
	    	    			{ 
	     	       				String msg =String.format("Insert into Accounts (key,account,userid,password) values(%d,'%s','%s','%s')",rs.getInt("key"),acct.getAccount(),acct.getUserID(),encryption.encrypt(acct.getPassword()));
	     	       				queue.send(msg);
	    	       				queue.close();
	     	    			}
	     				}
	    				else
	    				{
	     				}
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
			error = true;
    	}
    	catch (Exception ex) {
    		errorMessage =  ex.getMessage();
			error = true;
    	}
	
    	return recs;
    }
    
    
    public int UpdateAccount(Account acct, String password)
    {
    	int recs = -1;
       	error = false;
    	errorMessage = "";
    	try {
    		if ( (con != null) &&  ( !con.isClosed()))
    		{
    			CTFEncryptDecrypt encryption = new CTFEncryptDecrypt(password);
    		   	PreparedStatement st = con.prepareStatement("Update  Accounts set account=?, userid=?, password=? where key=?");
    	    	st.setString(1, acct.getAccount());
       	    	st.setString(2, acct.getUserID());
       	    	st.setString(3, encryption.encrypt(acct.getPassword()));
      	    	st.setInt(4, acct.getKey());
    	    	recs = st.executeUpdate();
    	    	if (recs == 1)
    	    	{
      	    		if (useActiveMQ.equals("Y"))
      	    		{
	      	    		ActiveMQ queue = new ActiveMQ();
	    				if (queue.connect(activeMQIPAddress, activeMQQueue) )
	    				{
	     	       			String msg =String.format("Update  Accounts set account='%s', userid='%s', password='%s' where key=%d",acct.getAccount(),acct.getUserID(),encryption.encrypt(acct.getPassword()),acct.getKey());
	    	       			queue.send(msg);
	    	       			queue.close();
	      				}
	    				else
	    				{
	    	    			errorMessage = "ActiveMQ Connection is not open!";
	    	    			error = true;
	    				}
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
			error = true;
    	}
    	catch (Exception ex) {
    		errorMessage =  ex.getMessage();
			error = true;
    	}
	
    	return recs;
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
