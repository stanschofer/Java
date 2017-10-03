package ShireHoaDB;
import java.util.*;

public class EmailAddress {


		
		
		
		private int ownerid = 0;
		private String emailaddress ="";
		private int  emailaddressid  = -1;
		
		public EmailAddress()
		{
			this.ownerid = 0;
			this.emailaddress ="";
			this.emailaddressid = -1;		
		}
		

		
		public EmailAddress(int ownerid,String emailaddress, int emailaddressid)
		{
			this.emailaddress = emailaddress;
			this.ownerid = ownerid;
			this.emailaddressid = emailaddressid;		
		}
		
		

		
		public void setEmailaddress(String emailaddress) {
			this.emailaddress = emailaddress;
		}

		public String getEmailaddress() {
			return emailaddress;
		}
		

		
		public void setOwnerid(int ownerid) {
			this.ownerid = ownerid;
		}

		public int getOwnerid() {
			return ownerid;
		}
		
		public int getEmailaddressid() {
			return emailaddressid;
		}

}


