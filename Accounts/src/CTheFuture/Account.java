package CTheFuture;

import java.util.*;


public class Account {
	
	
	
	private String account ="";
	private String userid ="";
	private  String  password ="";
	private int   key = -1;
	
	public Account()
	{
		this.account ="";
		this.userid ="";
		this.password ="";
		this.key = -1;		
	}
	
	public Account(String acct,String id,String pwd)
	{
		this.account = acct;
		this.userid = id;
		this.password = pwd;
		this.key = -1;		
	}
	
	public Account(String acct,String id,String pwd, int key)
	{
		this.account = acct;
		this.userid = id;
		this.password = pwd;
		this.key = key;		
	}
	
	
	public Account( int key)
	{
		this.account = "";
		this.userid = "";
		this.password = "";
		this.key = key;		
	}
	
	
	public void setAccount(String acct) {
		this.account = acct;
	}

	public String getAccount() {
		return account;
	}
	
	public void setUserID(String id) {
		this.userid = id;
	}

	public String getUserID() {
		return userid;
	}
	
	public void setPassword(String pwd) {
		this.password = pwd;
	}

	public String getPassword() {
		return password;
	}
	
	public int getKey() {
		return key;
	}

	public static String makeTable(String acctName,  int key, String password)
	{
		String outPut = "<TABLE>";
		try {   
			/*
			String dbServer = "";
			String dbUser = "";
			String dbPassword = "";
			String useActiveMQ = "";
			try
			{
				// reads from context.xml
				// Ubantu /var/lib/tomcat6
				// Mac /Library/Tomcat/apache-tomcat-7.033     /usr/local/apache-tomcat-7.0.33
				Context initCtx = new InitialContext();
				Context envCtx = (Context) initCtx.lookup("java:comp/env");	
				
				dbServer = (String) envCtx.lookup("dbServer");		
				dbUser = (String) envCtx.lookup("dbUser");		
				dbPassword = (String) envCtx.lookup("dbPassword");		
				useActiveMQ = (String) envCtx.lookup("useActiveMQ");	
			}
			catch(NamingException ex)
			{
				outPut = outPut + "<TR><TD colspan=3>"   + ex.getMessage() +   "</TD></TR>";
				
			}
			*/
			
			Postgres db = new Postgres();
			db.connect();
//			db.connect(dbServer, dbUser, dbPassword);
			if (db.isConnected())
			{
				String sql = "select  * from accounts";
				if (acctName.length() > 0)
				{
					sql = sql + " where upper(account) like upper('%" + acctName + "%')";
				}
				sql = sql + " order by account asc";
				outPut = outPut + "<TR><TH>Account</TH><TH>UserID</TH><TH>Password</TH></TR>";
				ArrayList<Account> accounts = db.Select(sql, password);
				if (db.getErrorMessage() == "")
				{
					String tdClass = "";
					outPut = outPut + "<TR>";
					outPut = outPut + "<TD " + tdClass + " ><input type=\"text\" value=\"\" name=\"account\" maxlength=\"50\"  size=\"40\"></TD>";
					outPut = outPut + "<TD " + tdClass + " ><input type=\"text\" value=\"\" name=\"userid\" maxlength=\"50\"  size=\"40\"></TD>";
					outPut = outPut + "<TD " + tdClass + " ><input type=\"text\" value=\"\" name=\"passwd\" maxlength=\"50\"  size=\"40\"></TD>";
					outPut = outPut + "<TD " + tdClass + " ><input type=\"submit\" name=\"addSubmit\" value=\"Add\" ></TD>";
					outPut = outPut + "</TR>";
					for (int iPos = 0; iPos<accounts.size();iPos++)
					{
						
						if ( (iPos % 2) == 0)
						{
							tdClass = "";
						}
						else
						{
							tdClass = "class=TDalt ";
						}
						Account acct = accounts.get(iPos);
						outPut = outPut + "<TR>";
						if (key != acct.getKey())
						{
							outPut = outPut + "<TD " + tdClass + " >" + acct.getAccount()  + "</TD>";
							outPut = outPut + "<TD " + tdClass + " >" + acct.getUserID()  + "</TD>";
							outPut = outPut + "<TD " + tdClass + " >" + acct.getPassword()  + "</TD>";
							outPut = outPut + "<TD " + tdClass + " ><input type=\"submit\" name=\"" + acct.getKey()  + "\" value=\"Edit\" ></TD>";
						}
						else
						{
							outPut = outPut + "<TD " + tdClass + " ><input type=\"text\" value=\"" + acct.getAccount()  + "\" name=\"account" + acct.getKey()      + "\" maxlength=\"50\"  size=\"40\"></TD>";
							outPut = outPut + "<TD " + tdClass + " ><input type=\"text\" value=\"" + acct.getUserID()  + "\" name=\"userid" + acct.getKey()      + "\" maxlength=\"50\"  size=\"40\"></TD>";
							outPut = outPut + "<TD " + tdClass + " ><input type=\"text\" value=\"" + acct.getPassword()  + "\" name=\"passwd" + acct.getKey()      + "\" maxlength=\"50\"  size=\"40\"></TD>";
							outPut = outPut + "<TD " + tdClass + " ><input type=\"submit\" name=\"" + acct.getKey()  + "\" value=\"Update\" ><input type=\"submit\" name=\"" + acct.getKey()  + "\" value=\"Delete\" ></TD>";
						}
						outPut = outPut + "</TR>";
					}
				}
				else
				{
					outPut = outPut + "<TR><TD COLSPAN=3>" + db.getErrorMessage() + "</TR>";
				}

			}
			else
			{
				outPut = outPut + "<TR><TD COLSPAN=3>unable to connect: //localhost:5432/Account</TR>";
				outPut =  outPut + "<TR><TD COLSPAN=3>" + db.getErrorMessage() + "</TR>";	
			}
		} catch (Exception e) {   			e.printStackTrace();   
			outPut = " outPut + <TR><TD COLSPAN=3>"+ e.getMessage() + "</TR>";
		} finally {   
			try {   

			} catch (Exception e) {   
				outPut =  outPut + "<TR><TD COLSPAN=3>" + e.getMessage() + "</TR>";
			}   
		}
		outPut = outPut + "</TABLE>";
		return outPut;
	}
	
}
