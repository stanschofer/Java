package ShireHoaDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


	public class OwnerContactDB extends dbBase{
		
		private String errorMessage;
	    private Boolean error;
	    
	    public OwnerContactDB(String dbServer,String dbUser,String dbPassword)
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
		
	    

		   public ArrayList<OwnerContact> Select (int ownerID)
		    {
			    ArrayList<OwnerContact> ownercontacts = new ArrayList();
			    	ResultSet rs = null;
			    	Statement statement = null;
			    error = false;
			    	errorMessage = "";
			    	try {
			    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
			    		{
			    			statement = this.getCon().createStatement();   
			    			rs = statement   
			                    .executeQuery("select ow.ownercontactid, ow.ownerid, ow.contactid, c.contactname, ow.relationship, c.phone from ownercontact ow join contact c on c.contactid=ow.contactid where ownerid=" + Integer.toString(ownerID)  + "  order by contactname" );   
		
			    			while (rs.next()) 
			    			{ 
			    				try 
			    				{
			    					OwnerContact ownercontactsRec = new OwnerContact(rs.getInt("ownercontactid"),
			    						rs.getInt("ownerid"),
			    						rs.getInt("contactid"),
			    						rs.getString("contactname"),
			    						rs.getString("relationship"),
			    						rs.getString("phone"));
			    					
			    					ownercontacts.add(ownercontactsRec);
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
			    	
			    	return ownercontacts;
		   }


	   
		   public String Update(OwnerContact ownercontact)
		   {
			   	int retVal = 0;
			    	String errorMessage = "";
			    	Statement statement = null;
			    	try {
			    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
			    		{
			    			statement = this.getCon().createStatement();  
			    			retVal = statement.executeUpdate("update ownercontact set relationship='" + ownercontact.getRelationship() +"' where ownercontactid=" + ownercontact.getOwnerContactid() );   
			    			if (retVal == 0)
			    			{
			    				errorMessage = "ownercontact not updated";
			    			}
			    			else
			    			{
			    				String strQuery = "update contact set contactname='" + ownercontact.getContactName() + "',  phone='" + ownercontact.getPhone() + "' where contactid=" + ownercontact.getContactID();
				    			retVal = statement.executeUpdate( strQuery);   	    				
				    			if (retVal == 0)
				    			{
				    				errorMessage = "contact not updated";
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
					   
					   
					 			   
			   return errorMessage;
		   
		   }
	   
		   public String Add(OwnerContact ownercontact)
		   {
			   	int retVal = 0;
			    	String errorMessage = "";
			    	Statement statement = null;
			    	ResultSet rs = null;
			    	
			    	try {
			    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
			    		{
			    			statement = this.getCon().createStatement();  
			    			String strQuery = "";
			    			strQuery ="select contactid from contact where contactname='" + ownercontact.getContactName() + "' and phone='" + ownercontact.getPhone() + "'";
					    	rs = statement.executeQuery(strQuery); 
					    	if (!rs.next())
					    	{
				    			strQuery = "insert into contact (contactname,phone)  values('" + ownercontact.getContactName() + "','" + ownercontact.getPhone()+ "') returning contactid" ;
				    			rs = statement.executeQuery(strQuery); 
				    			if (rs.next())
				    			{
				    				retVal = rs.getInt("contactid");
				    			}	
				    			else
				    			{
				    				errorMessage = "Contact not added";
				    			};
			    			}	
			    			else
			    			{
			    				retVal = rs.getInt("contactid");
			    			}
			    			if (retVal > 0)
			    			{
				    			strQuery = "insert into ownercontact (ownerid, contactid,relationship)  values(" + Integer.toString(ownercontact.getOwnerID()) + "," + Integer.toString(retVal) + ", '" +  ownercontact.getRelationship() + "')" ;
				    			retVal = statement.executeUpdate(strQuery);   
				    			if (retVal == 0)
				    			{
				    				errorMessage = "OwnerContact not added";
				    			}
			    			}
			    			else
			    			{
			    				errorMessage = "Contact not added, OwnerContact not added";
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
		   
		   public String Delete(OwnerContact ownercontact)
		   {
			   	int retVal = 0;
			    	String errorMessage = "";
			    	Statement statement = null;
			    	ResultSet rs = null;
			    	
			    	try {
			    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
			    		{
			    			statement = this.getCon().createStatement();   
			    			String strQuery = "delete from ownercontact where ownercontactid=" + Integer.toString(ownercontact.getOwnerContactid());
			    			retVal = statement.executeUpdate(strQuery);   
			    			if (retVal == 0)
			    			{
			    				errorMessage = "owner contact not deleted";
			    			}
			    			else
			    			{
			    				strQuery = "select count(*) as recs from ownercontact where contactid=" + Integer.toString(ownercontact.getContactID());
				    			rs = statement .executeQuery(strQuery);
				    			if (rs.next())
				    			{
					    			if (rs.getInt("recs") == 0)
					    			{
					    				strQuery = "delete from contact where contactid=" + Integer.toString(ownercontact.getContactID());
						    			retVal = statement.executeUpdate(strQuery);   
						    			if (retVal == 0)
						    			{
						    				errorMessage = "contact not deleted";
						    			}
						    			
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
					   
					   
					 			   
			   return errorMessage;
		   
		   }
		   
		   
		   public String DeleteAllOwnerContactsForOwner(int ownerid)
		   {
			   	int retVal = 0;
			    	String errorMessage = "";
			    	Statement statement = null;
			    	Statement statementDelete = null;
			    	ResultSet rs = null;
			    	try {
			    		if ( (this.getCon() != null) &&  ( !(this.getCon().isClosed())  )   )
			    		{
			    			statementDelete = this.getCon().createStatement(); 
			    			statement = this.getCon().createStatement();
			    			String strQuery = "select oc1.contactid as contactid from ownercontact oc1 where ownerid="
				    			+ Integer.toString(ownerid) 
				    			+ " and  row(oc1.contactid,1) in (select contactid , count(*) from ownercontact oc2   group by contactid) ";
			    			rs = statement.executeQuery( strQuery);   			
			    			while (rs.next()) 
			    			{ 
			    				try 
			    				{
					    			strQuery = "delete from contact where contactid=" + Integer.toString(rs.getInt("contactid"));
					    			retVal = statementDelete.executeUpdate(strQuery); 	
					    			if (retVal == 0)
					    			{
				    					errorMessage = "DeleteAllOwnerContactsForOwner - record not deleted from contact with contactid " + Integer.toString(rs.getInt("contactid"));
			    	    					error = true;    									    				
					    			}
			    				}
			    				catch (Exception ex)
			    				{
			    					errorMessage = "DeleteAllOwnerContactsForOwner - " +ex.getMessage();
			    	    				error = true;    					
			    				}
			     		}  
			    			
			    			
			    			strQuery = "delete from ownercontact where ownerid=" + Integer.toString(ownerid);
			    			retVal = statementDelete.executeUpdate(strQuery);   
			    			if (retVal > 0)
			    			{
			    				errorMessage = "all ownercontacts deleted";
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
