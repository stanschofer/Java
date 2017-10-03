package ShireHoaDB;
import java.util.*;

public class Unit {


		
		
		
		private int address = 0;
		private String unitletter ="";
		private  String  landline ="";
		private int   unitid = -1;
		
		public Unit()
		{
			this.address = 0;
			this.unitletter ="";
			this.landline ="";
			this.unitid = -1;		
		}
		

		
		public Unit(int addressIn,String unitletterIn,String landlineIn, int unitidIN)
		{
			this.address = addressIn;
			this.unitletter = unitletterIn;
			this.landline = landlineIn;
			this.unitid = unitidIN;		
		}
		
		

		
		public void setAddress(int address) {
			this.address = address;
		}

		public int getAddress() {
			return address;
		}
		
		public void setUnitletter(String unitletter) {
			this.unitletter = unitletter;
		}

		public String getUnitletter() {
			return unitletter;
		}
		
		public void setLandline(String landline) {
			this.landline = landline;
		}

		public String getLandline() {
			return landline;
		}
		
		public int getUnitid() {
			return unitid;
		}

}
