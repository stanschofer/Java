


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import CTFActiveMQ.ActiveMQ;
import CTFEncryptDecrypt.CTFEncryptDecrypt;
import CTheFuture.Account;
import CTheFuture.Postgres;

public class CopyAccounts {

	/**
	 * @param args
	 */
/*	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			CTFEncryptDecrypt encryption = new CTFEncryptDecrypt("keleon68");
			
			ActiveMQ queue = new ActiveMQ();
			if (queue.connect("192.168.1.114:61616", "Account") )
			{
				
			}
			else
			{
				System.out.println(queue.getErrorMessage());
			}
			ArrayList<Account> accounts = Account.getRecords("//192.168.1.114:5432/Account", "account", "keleon68");
			for(Account acct :accounts)
			{
				System.out.println(acct.getKey()+ ":" +acct.getAccount()+ ":" +acct.getUserID()+ ":" +acct.getPassword());
				String msg = String.format("Insert into Accounts (key,account,userid,password) values({0},{1},{2},{3})",acct.getKey(),acct.getAccount(),acct.getUserID(), encryption.encrypt(acct.getPassword())  );
				queue.send(msg);
			}
			
			queue.close();
		}
		catch (Exception ex)
		{
			
		}

	} */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try
		{
			Postgres db = new Postgres();
			db.connect("//localhost:5432/Account", "account", "keleon68");
			
			if (!db.isConnected())
			{
				System.out.println("Cannot access databse");
				System.out.println(db.getErrorMessage());
				return;
			}

			ActiveMQ queue = new ActiveMQ();
			if (queue.connect("192.168.1.170:61616", "Account") )
			{
				
			}
			else
			{
				System.out.println("Cannot access ActiveMQ");
				System.out.println(queue.getErrorMessage());
				return;
			}
			String msg = queue.recv();
			while (!msg.isEmpty())
			{
				if (!db.execQuery(msg))
				{
					break;
				} 
				msg = queue.recv();
			}
			
			queue.close();
		}
		catch (Exception ex)
		{
			
		}

	}
	
	
}
