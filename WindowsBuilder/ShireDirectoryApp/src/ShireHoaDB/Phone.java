package ShireHoaDB;
import java.util.*;

public class Phone {


		
		
		
		private int ownerid = 0;
		private String phonenumber ="";
		private  String  phonetype ="";
		private int  phoneid  = -1;
		
		public Phone()
		{
			this.ownerid = 0;
			this.phonenumber ="";
			this.phonetype ="";
			this.phoneid = -1;		
		}
		

		
		public Phone(int ownerid,String phonenumber,String phonetype, int phoneid)
		{
			this.phonenumber = phonenumber;
			this.phonetype = phonetype;
			this.ownerid = ownerid;
			this.phoneid = phoneid;		
		}
		
		

		
		public void setPhoneNumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}

		public String getPhoneNumber() {
			return phonenumber;
		}
		
		public void setPhoneType(String phonetype) {
			this.phonetype = phonetype;
		}

		public String getPhoneType() {
			return phonetype;
		}
		
		public void setOwnerid(int ownerid) {
			this.ownerid = ownerid;
		}

		public int getOwnerid() {
			return ownerid;
		}
		
		public int getPhoneID() {
			return phoneid;
		}

}


