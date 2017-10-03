package ShireHoaDB;
import java.util.*;

public class OwnerContact {

	private int ownerid = 0;
	private int contactid = -1;
	private  String  relationship ="";
	private String contactname = "";
	private int  ownercontactid  = -1;
	private String  phone  = "";
	
	public OwnerContact()
	{
		this.ownerid = 0;
		this.contactid =0;
		this.relationship ="";
		this.ownercontactid = -1;
		this.phone = phone;
	}
	

	
	public OwnerContact(int ownercontactid, int ownerid,int contactid ,String contactname, String relationship, String phone )
	{
		this.ownerid = ownerid;
		this.contactid = contactid;
		this.relationship = relationship;
		this.ownercontactid = ownercontactid;	
		this.phone = phone;
		this.contactname = contactname;
	}
	
	public String getContactName() {
		return contactname;
	}
	
	public void setContactName(String contactname) {
		this.contactname = contactname;
	}
	


	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public void setOwnerID(int id) {
		this.ownerid = id;
	}

	public int getOwnerID() {
		return ownerid;
	}
	
	public void setRelationship(String str) {
		this.relationship = str;
	}

	public String getRelationship() {
		return relationship;
	}
	
	public void setOwnerid(int ownerid) {
		this.ownerid = ownerid;
	}

	public int getOwnerContactid() {
		return ownercontactid;
	}
	
	public int getContactID() {
		return contactid;
	}
	
	
	public void setContactID(int id) {
		contactid= id;
	}
	
}
