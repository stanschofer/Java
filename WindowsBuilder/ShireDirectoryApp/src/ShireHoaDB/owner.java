package ShireHoaDB;
import java.util.*;

public class owner {


		
		
		
		private int ownerid = 0;
		private String lastname ="";
		private  String  firstname ="";
		private int  unitid  = -1;
		
		public owner()
		{
			this.ownerid = 0;
			this.lastname ="";
			this.firstname ="";
			this.unitid = -1;		
		}
		

		
		public owner(int ownerid,String lastname,String firstname, int unitidIN)
		{
			this.lastname = lastname;
			this.firstname = firstname;
			this.ownerid = ownerid;
			this.unitid = unitidIN;		
		}
		
		

		
		public void setLastname(String lastname) {
			this.lastname = lastname;
		}

		public String getLastname() {
			return lastname;
		}
		
		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getFirstname() {
			return firstname;
		}
		
		public void setOwnerid(int ownerid) {
			this.ownerid = ownerid;
		}

		public int getOwnerid() {
			return ownerid;
		}
		
		public int getUnitid() {
			return unitid;
		}
		
		
		public void setUnitid(int unitd) {
			unitid= unitd;
		}

}

