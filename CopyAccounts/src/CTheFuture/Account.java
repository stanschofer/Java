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

	public static ArrayList getRecords(String dbURL,String acctName, String password)
	{
		ArrayList<Account> accounts = null;
		try {   
			Postgres db = new Postgres();
			//db.connect("//localhost:5432/Account", "account", "keleon68");
			db.connect(dbURL, acctName, password);
			if (db.isConnected())
			{
				String sql = "select  * from accounts";
				sql = sql + " order by account asc";
				accounts = db.Select(sql, password);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();   
		}

		return accounts;
	}
	
}
