package ShireHoaDB;
import java.util.*;

public class Contact {



	private int contactid = -1;
	private  String  contactname ="";
	private String  phone  = "";
	
	public Contact()
	{

		this.contactid = 0;
		this.contactname = "";
		this.phone = "";		
	}
	

	
	public Contact(int contactid, String contactname ,String phone )
	{

		this.contactid = contactid;
		this.contactname = contactname;
		this.phone = phone;		
	}
	
	


	public String getContactName() {
		return contactname;
	}

	public void setContactName(String str) {
		this.contactname = str;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setphone(String phone) {
		this.phone = phone;
	}

	
	public int getContactID() {
		return contactid;
	}
	
	
	

}
