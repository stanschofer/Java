package CTheFuture;

import java.sql.Connection;   
import java.sql.DriverManager;   
import java.sql.ResultSet;   
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.*;


public class Postgres {

	private String errorMessage;
    private Connection con;
    private Boolean error;
    
    public Postgres()
    {
    	errorMessage = "";
    	con = null;
    }
    
    public  String getErrorMessage()
    {
    	return errorMessage;
    }
    
    public Boolean getError()
    {
    	return error;
    }
    
    public boolean isConnected()
    {
    	return (con != null);
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
    	} 
    	catch (SQLException ex) {
    		errorMessage =  ex.getMessage();
    		error = true;
    	}
    	
    	return error;
     }
    
    public ArrayList<iTunesMusic> Select (String query, String password)
    {
    	ArrayList<iTunesMusic> albums = new ArrayList<iTunesMusic>();
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
 
    			while (rs.next()) { 
    				try 
    				{
    					iTunesMusic album = new iTunesMusic(rs.getString("album"),
    						rs.getString("name"),
    						rs.getString("artist"),
    						rs.getString("albumartist"),
    						rs.getString("genre"),
						rs.getString("kind"),
						rs.getString("location"));
    					albums.add(album);
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
    	
    	return albums;
   }
    
    public Boolean execQuery (String query)
    {
    	Boolean retValue = true;
     	error = false;
    	errorMessage = "";
    	try {
    		if ( (con != null) &&  ( !con.isClosed()))
    		{
       		   	PreparedStatement st = con.prepareStatement(query);
    			int recs = st.executeUpdate(); 
    			if (recs != 1)
    			{
    				retValue = false;
    				errorMessage = "Record not inserted!";
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
    	
    	return retValue;
   }

 /*   
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
 */   
    public int InsertAlbum(iTunesMusic album)
    {
    	int recs = -1;
       	error = false;
    	errorMessage = "";
    	try {
    		if ( (con != null) &&  ( !con.isClosed()))
    		{
     		   	PreparedStatement st = con.prepareStatement("Insert into albums (album,name,artist,albumartist,genre,kind,location) values(?,?,?,?,?,?,?)");
    	    	st.setString(1, album.getAlbum());
    	    	st.setString(2, album.getName());
    	    	st.setString(3, album.getArtist());
    	    	st.setString(4, album.getAlbumArtist());
    	    	st.setString(5, album.getGenre());
    	    	st.setString(6, album.getKind());
    	    	st.setString(7, album.getLocation());
   	    	recs = st.executeUpdate();
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
    
  /*  
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
  */  
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
