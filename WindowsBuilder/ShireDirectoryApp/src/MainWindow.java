import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import ShireHoaDB.*;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.*;
import java.util.*;



import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class MainWindow {

	protected Shell shlShireDirectory;
	private ArrayList<Unit>  units;
	private ArrayList<owner>  currentOwners;
	private ArrayList<Phone>  currentPhones;
	private ArrayList<EmailAddress>  currentEmailAddresses;	
	private ArrayList<OwnerContact>  currentOwnerContacts;	
	
	static String url;
	static String user;
	static String password;
	private Table tblOwners;
	static String patternPhone;
	static  String patterEmail;
	List lstUnits;
	Label lblOwnersOf;
	private Label lblLandLine;
	private Text txtLandLine;
	private Text txtUnitLetter;
	private Text txtUnitNumber;

	private Label lblFirstname;
	private Label lblNewLabel;
	private Text txtFirstName;
	private Text txtLastName;
	Button btnAddOwner;	
	Button btnUpdateOwner;
	Button btnRemoveOwner;
	private Text txtErrorMsg;
	private Group grpOwner;
	private Button btnAddUnit;
	private Button btnUpdateUnit;
	private Button btnDeleteUnit;
	private Button btnNewUnit;
	private Table tblPhoneNumber;
	private Text txtPhoneNumber;
	private Label lblPhoneNumber;
	private Label lblPhoneType;
	private Button btnAddPhone;
	private Button btnUpdatePhone;
	private Button btnDeletePhone;
	private Group grpPhone;
	private Table tblEmailAddress;
	private Label lblEmailAddress;
	private Text txtEmailAddress;
	private Button btnAddEmailAddress;
	private Button btnUpdateEmailAddress;
	private Button btnDeleteEmailAddress;
	private Group grpEmailAddresses;
	private Table tblOwnerContact;
	private Button btnDeleteOwnerContact;
	private Text txtContactName;
	private Label lblContactName;
	private Label lblNewLabel_1;
	private Text txtContactPhone;
	private Button btnAddContact;
	private Button btnUpdateContact;
	private Group grpOwnerContact;
	private Label lblContactRelationship;
	private List lstRelationship;
	

	public static void main(String[] args) {
		String strURL = "localhost:5432";
		String strDbName = "shiretesting";
		patternPhone = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}";
		patterEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		try {			
			/*url = "//localhost:5432/shiretesting"; */
			user =  "postgres";
			password = "Brent2012";
			if (args.length > 0) {
			    try {
			        strURL = args[0];
			        if (args.length > 1) {
			        		 strDbName = args[1];
			        }
			     }
			     catch (Exception ex)
			     {
			    	 
			     }
				url =   "//" + strURL +  "/" + strDbName;  
			MainWindow window = new MainWindow();
			window.open();

		}


		} catch (Exception e) {
			e.printStackTrace();
			url =   "//" + strURL +  "/" + strDbName;  
		}
	}

	/**
	 * Open the window.
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlShireDirectory.open();
		shlShireDirectory.layout();
		while (!shlShireDirectory.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}


	protected void createContents() {
		shlShireDirectory = new Shell();
		shlShireDirectory.setMinimumSize(new Point(300, 400));
		shlShireDirectory.setSize(854, 1062);
		shlShireDirectory.setText("Shire Directory - Version 1.2" + " " + url);
		
		
		
		
		Label lblUnit = new Label(shlShireDirectory, SWT.NONE);
		lblUnit.setFont(SWTResourceManager.getFont("Arial", 14, SWT.BOLD));
		lblUnit.setBounds(26, 10, 57, 20);
		lblUnit.setText("Units:");
		
		ScrolledComposite scrolledComposite = new ScrolledComposite(shlShireDirectory, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		scrolledComposite.setBounds(26, 36, 137, 597);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		
		grpOwner = new Group(shlShireDirectory, SWT.BORDER | SWT.SHADOW_IN);
		grpOwner.setText("Owners");
		grpOwner.setBounds(181, 128, 662, 154);
		
		lblOwnersOf = new Label(grpOwner, SWT.NONE);
		lblOwnersOf.setBounds(8, 0, 164, 20);
		lblOwnersOf.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		lblOwnersOf.setText("Owners Of");
		
		tblOwners = new Table(grpOwner, SWT.BORDER | SWT.FULL_SELECTION);
		tblOwners.setBounds(0, 26, 333, 128);
		tblOwners.setHeaderVisible(true);
		tblOwners.setLinesVisible(true);
		TableColumn tblclmnUser = new TableColumn(tblOwners, SWT.NONE);
		tblclmnUser.setResizable(false);
		tblclmnUser.setMoveable(true);
		tblclmnUser.setWidth(200);
		tblclmnUser.setText("First Name");
		
		
		TableColumn tblclmnIdNumber = new TableColumn(tblOwners, SWT.NONE);
		tblclmnIdNumber.setWidth(200);
		tblclmnIdNumber.setResizable(false);
		tblclmnIdNumber.setMoveable(true);
		tblclmnIdNumber.setText("Last Name");
		
		lblFirstname = new Label(grpOwner, SWT.NONE);
		lblFirstname.setBounds(365, 26, 71, 20);
		lblFirstname.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		lblFirstname.setText("FirstName:");
		
		lblNewLabel = new Label(grpOwner, SWT.NONE);
		lblNewLabel.setBounds(364, 65, 72, 20);
		lblNewLabel.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		lblNewLabel.setText("Last Name:");
		
		txtFirstName = new Text(grpOwner, SWT.BORDER);
		txtFirstName.setBounds(442, 26, 180, 20);
		
		txtLastName = new Text(grpOwner, SWT.BORDER);
		txtLastName.setBounds(443, 62, 182, 19);
		
		btnAddOwner = new Button(grpOwner, SWT.NONE);
		btnAddOwner.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("");
				if (txtLastName.getText().length() < 2)
	    			{
	    				txtErrorMsg.setText("Last name has to be at least 2 characters");	 
	    				return;
	    			}
				if (txtFirstName.getText().length() < 3)
	    			{
	    				txtErrorMsg.setText("First Name has to be at least 3 characters");	 
	    				return;
	    			}
	
				owner person = new owner();
				person.setLastname(txtLastName.getText());
				person.setFirstname(txtFirstName.getText());
				Unit unit = units.get(lstUnits.getSelectionIndex());
				person.setOwnerid(unit.getUnitid());
	    			ownerDB ownerdb = new ownerDB(url, user, password);
	    			String strErrMsg= ownerdb.Add(person);
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("Owner Added");	 
	    				fillOwners(unit.getUnitid());
	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg);
	    			}
	    			
	    			ownerdb.disConnect();
	    		}
		});
		btnAddOwner.setBounds(340, 102, 84, 28);
		btnAddOwner.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnAddOwner.setText("Add Owner");
		
		btnUpdateOwner = new Button(grpOwner, SWT.NONE);
		btnUpdateOwner.setBounds(430, 102, 109, 28);
		btnUpdateOwner.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("");
				int i = tblOwners.getSelectionIndex();
				owner person = currentOwners.get(i);
				person.setLastname(txtLastName.getText());
				person.setFirstname(txtFirstName.getText());
				currentOwners.set(i, person);
	    			TableItem row = tblOwners.getItem(tblOwners.getSelectionIndex());
	    			row.setText(new String[]{person.getFirstname(),person.getLastname()});
	    			
	    			ownerDB ownerdb = new ownerDB(url, user, password);
	    			String strErrMsg= ownerdb.Update(person);
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("Owner Updates");	    				
	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg);
	    			}
	    			
	    			ownerdb.disConnect();    			
			}
		});
		btnUpdateOwner.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnUpdateOwner.setText("Update Owner");
		
		btnRemoveOwner = new Button(grpOwner, SWT.NONE);
		btnRemoveOwner.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("");
				
				int i = tblOwners.getSelectionIndex();
				owner person = currentOwners.get(i);
	    			PhoneDB phoneDB = new  PhoneDB(url, user, password);
	    			String strErrMsg= phoneDB.DeleteAllPhonesForOwner(person.getOwnerid());	    			
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("All Phones Deleted " + txtErrorMsg.getText() );	
 	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg + " " + txtErrorMsg.getText() );		    				
	    			}

	    			EmailAddressDB emailaddressDB = new  EmailAddressDB(url, user, password);
	    			strErrMsg= emailaddressDB.DeleteAllEmailAddressesForOwner(person.getOwnerid());	    			
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("All Email Adddesses Deleted" + txtErrorMsg.getText() );	
 	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg + " " + txtErrorMsg.getText() );		    				
	    			}	    			
	
	    			OwnerContactDB OwnerContactDB = new  OwnerContactDB(url, user, password);
	    			strErrMsg= OwnerContactDB.DeleteAllOwnerContactsForOwner(person.getOwnerid());	    			
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("All Owner Contacts Deleted" + txtErrorMsg.getText() );	
 	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg + " " + txtErrorMsg.getText() );		    				
	    			}	
	    			
	    			ownerDB ownerdb = new ownerDB(url, user, password);
	    			strErrMsg= ownerdb.Delete(person);
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText(" Owner Deleted "  + txtErrorMsg.getText() );	
	    				fillOwners(person.getUnitid());
	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg);
	    			}
	    		}
		});
		btnRemoveOwner.setBounds(545, 102, 113, 28);
		btnRemoveOwner.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnRemoveOwner.setText("Remove Owner");
		

		

		tblOwners.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {

				// Identify the selected row
				TableItem item = (TableItem) e.item;
				
				if (item == null)
					return;
				
				txtFirstName.setText(item.getText(0));				
				txtLastName.setText(item.getText(1));
				if (item.getText(0).length() > 0)
				{
					btnUpdateOwner.setEnabled(true); 
					btnRemoveOwner.setEnabled(true);
					int i = tblOwners.getSelectionIndex();
					owner person = currentOwners.get(i);
		    			fillPhones(person.getOwnerid());
		    			grpPhone.setText("Phone Numbers for "+ item.getText(0) + " " + item.getText(1));
		    			btnAddPhone.setEnabled(true);
		    			fillEmailAddress(person.getOwnerid());
		    			fillOwnerContact(person.getOwnerid());
		    			grpOwnerContact.setText("Owner Contacts for "+ item.getText(0) + " " + item.getText(1));
		    			grpEmailAddresses.setText("Email Addresses for "+ item.getText(0) + " " + item.getText(1));
				}
				else
				{
					btnUpdateOwner.setEnabled(false); 
					btnRemoveOwner.setEnabled(false);
					grpPhone.setText("Phone Numbers ");
	    				grpEmailAddresses.setText("Email Addresses");
				}

			}
		});
		
		
		lstUnits = new List(scrolledComposite, SWT.BORDER | SWT.V_SCROLL);
		lstUnits.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (lstUnits.getSelectionIndex() >=0)
				{
					String str = lstUnits.getSelection()[0];
					lblOwnersOf.setText("Owners of " + str);
					Unit unit = units.get(lstUnits.getSelectionIndex());
					txtLandLine.setText(unit.getLandline());
					txtUnitNumber.setText(Integer.toString(unit.getAddress()));
					txtUnitLetter.setText(unit.getUnitletter());		
					btnAddUnit.setEnabled(false);
					btnUpdateUnit.setEnabled(true);
					btnDeleteUnit.setEnabled(true);
					txtUnitNumber.setEnabled(false);
					txtUnitLetter.setEnabled(false);
					fillOwners(unit.getUnitid());
				}	
			}
		});
		lstUnits.setTouchEnabled(true);
		scrolledComposite.setContent(lstUnits);
		scrolledComposite.setMinSize(lstUnits.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		
		lblLandLine = new Label(shlShireDirectory, SWT.NONE);
		lblLandLine.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		lblLandLine.setBounds(180, 87, 71, 20);
		lblLandLine.setText("Land Line:");
		
		txtLandLine = new Text(shlShireDirectory, SWT.BORDER);
		txtLandLine.setBounds(270, 84, 186, 19);
		
		Label lblUnitNumber = new Label(shlShireDirectory, SWT.NONE);
		lblUnitNumber.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		lblUnitNumber.setBounds(180, 37, 84, 28);
		lblUnitNumber.setText("Unit Number:");
		
		Label lblUnitLetter = new Label(shlShireDirectory, SWT.NONE);
		lblUnitLetter.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		lblUnitLetter.setBounds(180, 63, 71, 20);
		lblUnitLetter.setText("Unit Letter:");
		
		txtUnitLetter = new Text(shlShireDirectory, SWT.BORDER);
		txtUnitLetter.setEnabled(false);
		txtUnitLetter.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtUnitLetter.setBounds(270, 63, 186, 19);
		
		txtUnitNumber = new Text(shlShireDirectory, SWT.BORDER);
		txtUnitNumber.setEnabled(false);
		txtUnitNumber.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		txtUnitNumber.setBounds(270, 34, 183, 20);
		
		txtErrorMsg = new Text(shlShireDirectory, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL);
		txtErrorMsg.setBounds(26, 1009, 817, 21);
		
		btnAddUnit = new Button(shlShireDirectory, SWT.NONE);
		btnAddUnit.setEnabled(false);
		btnAddUnit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("");
				Unit unit = new Unit();
				unit.setAddress(Integer.parseInt(txtUnitNumber.getText()));
				unit.setUnitletter(txtUnitLetter.getText());
				unit.setLandline(txtLandLine.getText());;

	    			
	    			unitDB unitdb = new unitDB(url, user, password);
	    			String strErrMsg= unitdb.Add(unit);
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("Unit added");	
	    				fillLstUnits();
	    				findUnit(unit);
	    				fillOwners(unit.getUnitid());
					btnAddUnit.setEnabled(false);
					btnUpdateUnit.setEnabled(true);
					btnDeleteUnit.setEnabled(true);
					txtUnitNumber.setEnabled(false);
					txtUnitLetter.setEnabled(false);
	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg);
	    			}
	    			unitdb.disConnect();	
			}
		});
		btnAddUnit.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnAddUnit.setBounds(583, 30, 94, 28);
		btnAddUnit.setText("ADD Unit");
		
		btnUpdateUnit = new Button(shlShireDirectory, SWT.NONE);
		btnUpdateUnit.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnUpdateUnit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("");
				int i = lstUnits.getSelectionIndex();
				Unit unit = units.get(i);
				unit.setAddress(Integer.parseInt(txtUnitNumber.getText()));
				unit.setUnitletter(txtUnitLetter.getText());
				unit.setLandline(txtLandLine.getText());;

	    			
	    			unitDB unitdb = new unitDB(url, user, password);
	    			txtErrorMsg.setText(unitdb.Update(unit));
	    			unitdb.disConnect();	
	    			}
		});
		btnUpdateUnit.setBounds(477, 56, 94, 28);
		btnUpdateUnit.setText("Update Unit");
		
		btnDeleteUnit = new Button(shlShireDirectory, SWT.NONE);
		btnDeleteUnit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("Not Implemented");
			}
		});
		btnDeleteUnit.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnDeleteUnit.setBounds(477, 80, 94, 28);
		btnDeleteUnit.setText("Delete Unit");
		
		btnNewUnit = new Button(shlShireDirectory, SWT.NONE);
		btnNewUnit.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnAddUnit.setEnabled(true);
				btnUpdateUnit.setEnabled(false);
				btnDeleteUnit.setEnabled(false);
				txtUnitNumber.setEnabled(true);
				txtUnitLetter.setEnabled(true);
				txtLandLine.setText("");
				txtUnitNumber.setText("");
				txtUnitLetter.setText("");	
				tblOwners.clearAll();
				currentOwners.clear();
				lstUnits.select(-1);
			}
		});
		btnNewUnit.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnNewUnit.setBounds(477, 30, 94, 28);
		btnNewUnit.setText("New Unit");
		
		grpPhone = new Group(shlShireDirectory, SWT.NONE);
		grpPhone.setText("Phone Numbers");
		grpPhone.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		grpPhone.setBounds(181, 288, 662, 144);

		List lstPhone = new List(grpPhone, SWT.BORDER);
		lstPhone.setBounds(453, 34, 94, 50);
		lstPhone.setItems(new String[] {"Cell", "Land Line"});
		lstPhone.setSelection(0);
		
		
		tblPhoneNumber = new Table(grpPhone, SWT.BORDER | SWT.FULL_SELECTION);
		tblPhoneNumber.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// Identify the selected row
				TableItem item = (TableItem) e.item;
				
				if (item == null)
					return;
				
				txtPhoneNumber.setText(item.getText(0));	

				if (item.getText(0).length() > 0)
				{
					btnUpdatePhone.setEnabled(true); 
					btnDeletePhone.setEnabled(true);
					btnAddPhone.setEnabled(true);
					for(int iPos=0;iPos<lstPhone.getItemCount();iPos++)
					{
						if ( lstPhone.getItem(iPos).compareTo(item.getText(1)) == 0  )
						{
							lstPhone.setSelection(iPos);
						}
					}				
				}
				else
				{
					btnUpdatePhone.setEnabled(false); 
					btnDeletePhone.setEnabled(false);
//
					btnAddPhone.setEnabled(false);				
				}			
			}
		});
		tblPhoneNumber.setBounds(0, 0, 336, 118);
		tblPhoneNumber.setHeaderVisible(true);
		tblPhoneNumber.setLinesVisible(true);
		TableColumn    tblclmPhoneNumber = new TableColumn(tblPhoneNumber, SWT.NONE);
		tblclmPhoneNumber.setResizable(false);
		tblclmPhoneNumber.setMoveable(true);
		tblclmPhoneNumber.setWidth(200);
		tblclmPhoneNumber.setText("Phone Number");
		
		
		TableColumn tblclmnPhoneType = new TableColumn(tblPhoneNumber, SWT.NONE);
		tblclmnPhoneType.setWidth(133);
		tblclmnPhoneType.setResizable(false);
		tblclmnPhoneType.setMoveable(true);
		tblclmnPhoneType.setText("Phone Type");
		
		txtPhoneNumber = new Text(grpPhone, SWT.BORDER);
		txtPhoneNumber.setBounds(453, 6, 129, 20);
		txtPhoneNumber.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 11, SWT.NORMAL));
		
		lblPhoneNumber = new Label(grpPhone, SWT.NONE);
		lblPhoneNumber.setBounds(342, 9, 105, 20);
		lblPhoneNumber.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		lblPhoneNumber.setText("Phone #:");
		
		lblPhoneType = new Label(grpPhone, SWT.NONE);
		lblPhoneType.setBounds(342, 35, 100, 20);
		lblPhoneType.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		lblPhoneType.setText("Phone Type:");
		

		btnAddPhone = new Button(grpPhone, SWT.NONE);
		btnAddPhone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("");

				if (txtPhoneNumber.getText().length() < 8)
	    			{
	    				txtErrorMsg.setText("Phone Number has to be at least 8 characters");	 
	    				return;
	    			}	
				if (!txtPhoneNumber.getText().matches(patternPhone))
				{
	    				txtErrorMsg.setText("Valid Phone Number has to match 123-456-7890 or 123)456-7890 or (123)4567890");	 
	    				return;					
				}
				owner own = currentOwners.get(tblOwners.getSelectionIndex());
				Phone phone = new Phone();
				phone.setPhoneNumber(txtPhoneNumber.getText());
				phone.setPhoneType(lstPhone.getItems()[lstPhone.getSelectionIndex()]);
				phone.setOwnerid(own.getOwnerid());

	    			
	    			PhoneDB phoneDB = new PhoneDB(url, user, password);
	    			String strErrMsg= phoneDB.Add(phone);
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("Phone Updated");	    				
	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg);
	    			}
	    			
	    			phoneDB.disConnect(); 
	    			fillPhones(own.getOwnerid());
	    		}
		});
		btnAddPhone.setBounds(353, 94, 94, 20);
		btnAddPhone.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnAddPhone.setText("Add Phone #");
		
		btnUpdatePhone = new Button(grpPhone, SWT.NONE);
		btnUpdatePhone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("");

				if (txtPhoneNumber.getText().length() < 8)
	    			{
	    				txtErrorMsg.setText("Phone Number has to be at least 8 characters");	 
	    				return;
	    			}	
				if (!txtPhoneNumber.getText().matches(patternPhone))
				{
	    				txtErrorMsg.setText("Valid Phone Number has to match 123-456-7890 or 123)456-7890 or (123)4567890");	 
	    				return;					
				}
				
				int i = tblPhoneNumber.getSelectionIndex();
				Phone phone = currentPhones.get(i);
				phone.setPhoneNumber(txtPhoneNumber.getText());
				phone.setPhoneType(lstPhone.getItems()[lstPhone.getSelectionIndex()]);
				currentPhones.set(i, phone);
	    			TableItem row = tblPhoneNumber.getItem(tblPhoneNumber.getSelectionIndex());
	    			row.setText(new String[]{phone.getPhoneNumber(),phone.getPhoneType()});
	    			
	    			PhoneDB phoneDB = new PhoneDB(url, user, password);
	    			String strErrMsg= phoneDB.Update(phone);
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("Phone Updated");	    				
	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg);
	    			}
	    			
	    			phoneDB.disConnect(); 
	    		}
		});
		btnUpdatePhone.setBounds(453, 90, 105, 28);
		btnUpdatePhone.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnUpdatePhone.setText("Update Phone #");
		
		btnDeletePhone = new Button(grpPhone, SWT.NONE);
		btnDeletePhone.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("");
				int i = tblPhoneNumber.getSelectionIndex();
				Phone phone = currentPhones.get(i);
	    			PhoneDB phoneDB = new PhoneDB(url, user, password);
	    			String strErrMsg= phoneDB.Delete(phone);
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("Phone Updated");	    				
	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg);
	    			}
	    			
	    			phoneDB.disConnect(); 
				owner own = currentOwners.get(tblOwners.getSelectionIndex());
	    			fillPhones(own.getOwnerid());
			}
		});
		
		btnDeletePhone.setBounds(553, 90, 109, 28);
		btnDeletePhone.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnDeletePhone.setText("Delete Phone #");
		
		grpEmailAddresses = new Group(shlShireDirectory, SWT.NONE);
		grpEmailAddresses.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		grpEmailAddresses.setText("Email Addresses");
		grpEmailAddresses.setBounds(189, 438, 654, 112);
		
		tblEmailAddress = new Table(grpEmailAddresses, SWT.BORDER | SWT.FULL_SELECTION);
		tblEmailAddress.setHeaderVisible(true);
		tblEmailAddress.setLinesVisible(true);
		TableColumn    tblclmEmailAddress = new TableColumn(tblEmailAddress, SWT.NONE);
		tblclmEmailAddress.setResizable(false);
		tblclmEmailAddress.setMoveable(true);
		tblclmEmailAddress.setWidth(200);
		tblclmEmailAddress.setText("Email Address");
		tblEmailAddress.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem item = (TableItem) e.item;
				
				if (item == null)
					return;
				
				txtEmailAddress.setText(item.getText(0));	

				if (item.getText(0).length() > 0)
				{
					btnUpdateEmailAddress.setEnabled(true); 
					btnDeleteEmailAddress.setEnabled(true);
					btnAddEmailAddress.setEnabled(true);
					txtEmailAddress.setText(item.getText(0));		
				}
				else
				{
					btnUpdateEmailAddress.setEnabled(false); 
					btnDeleteEmailAddress.setEnabled(false);
					btnAddEmailAddress.setEnabled(false);				
				}			
			}			
		});
		tblEmailAddress.setBounds(0, 0, 325, 112);
		tblEmailAddress.setHeaderVisible(true);
		tblEmailAddress.setLinesVisible(true);
		
		lblEmailAddress = new Label(grpEmailAddresses, SWT.NONE);
		lblEmailAddress.setBounds(341, 4, 99, 20);
		lblEmailAddress.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		lblEmailAddress.setText("Email Address:");
		
		txtEmailAddress = new Text(grpEmailAddresses, SWT.BORDER);
		txtEmailAddress.setBounds(446, 5, 201, 19);
		
		btnAddEmailAddress = new Button(grpEmailAddresses, SWT.NONE);
		btnAddEmailAddress.setEnabled(false);
		btnAddEmailAddress.setBounds(331, 45, 109, 28);
		btnAddEmailAddress.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("");
				if (txtEmailAddress.getText().length() < 8)
	    			{
	    				txtErrorMsg.setText("Email Address has to be at least 8 characters");	 
	    				return;
	    			}
				
				if (!txtEmailAddress.getText().matches(patterEmail) )
	    			{
	    				txtErrorMsg.setText("Email Address not valid");	 
	    				return;
	    			}
				owner own = currentOwners.get(tblOwners.getSelectionIndex());
				EmailAddress emailaddress = new EmailAddress();
				emailaddress.setEmailaddress(txtEmailAddress.getText());
				emailaddress.setOwnerid(own.getOwnerid());

	    			
				EmailAddressDB emailaddressDB = new EmailAddressDB(url, user, password);
	    			String strErrMsg= emailaddressDB.Add(emailaddress);
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("Email Address Updated");	    				
	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg);
	    			}
	    			
	    			emailaddressDB.disConnect(); 
	    			fillEmailAddress(own.getOwnerid());
	    		}
		});
		btnAddEmailAddress.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnAddEmailAddress.setText("Add Email");
		
		btnUpdateEmailAddress = new Button(grpEmailAddresses, SWT.NONE);
		btnUpdateEmailAddress.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("");
			if (txtEmailAddress.getText().length() < 8)
    			{
    				txtErrorMsg.setText("Email Address has to be at least 8 characters");	 
    				return;
    			}
			
			if (!txtEmailAddress.getText().matches(patterEmail) )
    			{
    				txtErrorMsg.setText("Email Address not valid");	 
    				return;
    			}
			int i = tblEmailAddress.getSelectionIndex();
				EmailAddress emailaddress = currentEmailAddresses.get(i);
				emailaddress.setEmailaddress(txtEmailAddress.getText());
				currentEmailAddresses.set(i, emailaddress);
	    			TableItem row = tblEmailAddress.getItem(tblEmailAddress.getSelectionIndex());
	    			row.setText(new String[]{emailaddress.getEmailaddress()});
	    			
	    			EmailAddressDB emailaddressDB = new EmailAddressDB(url, user, password);
	    			String strErrMsg= emailaddressDB.Update(emailaddress);
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("Email Address Updated");	    				
	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg);
	    			}
	    			
	    			emailaddressDB.disConnect(); 			}
		});
		btnUpdateEmailAddress.setEnabled(false);
		btnUpdateEmailAddress.setBounds(446, 49, 109, 20);
		btnUpdateEmailAddress.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnUpdateEmailAddress.setText("Update Email ");
		
		btnDeleteEmailAddress = new Button(grpEmailAddresses, SWT.NONE);
		btnDeleteEmailAddress.setEnabled(false);
		btnDeleteEmailAddress.setBounds(560, 45, 94, 28);
		btnDeleteEmailAddress.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnDeleteEmailAddress.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("");
				int i = tblEmailAddress.getSelectionIndex();
				EmailAddress emailaddress = currentEmailAddresses.get(i);
				EmailAddressDB emailaddressDB = new EmailAddressDB(url, user, password);
	    			String strErrMsg= emailaddressDB.Delete(emailaddress);
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("Phone Deleted");	    				
	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg);
	    			}
	    			
	    			emailaddressDB.disConnect(); 
				owner own = currentOwners.get(tblOwners.getSelectionIndex());
	    			fillEmailAddress(own.getOwnerid());			}
		});
		btnDeleteEmailAddress.setText("Delete Email");
		
		grpOwnerContact = new Group(shlShireDirectory, SWT.NONE);
		grpOwnerContact.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		grpOwnerContact.setText("Owner Contact");
		grpOwnerContact.setBounds(189, 599, 637, 302);
		
		tblOwnerContact = new Table(grpOwnerContact, SWT.BORDER | SWT.FULL_SELECTION);
		tblOwnerContact.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem item = (TableItem) e.item;
				
				if (item == null)
					return;
				
				txtContactName.setText("");	
				txtContactPhone.setText("");		
				lstRelationship.select(-1);
				if (item.getText(0).length() > 0)
				{
					btnUpdateContact.setEnabled(true); 
					btnDeleteOwnerContact.setEnabled(true);
					btnAddContact.setEnabled(true);
					txtContactName.setText(item.getText(0));		
					txtContactPhone.setText(item.getText(2));	
					for(int iPos=0;iPos<lstRelationship.getItemCount();iPos++)
					{
						if (item.getText(1).compareTo(lstRelationship.getItems()[iPos]) == 0 )
						{
							lstRelationship.select(iPos);
							lstRelationship.forceFocus();
							break;
						}
					}
				}
				else
				{
					btnUpdateContact.setEnabled(false); 
					btnUpdateContact.setEnabled(false);
					btnAddContact.setEnabled(false);				
				}
			}
		});
		tblOwnerContact.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 11, SWT.NORMAL));
		tblOwnerContact.setBounds(0, 0, 329, 93);
		tblOwnerContact.setHeaderVisible(true);
		tblOwnerContact.setLinesVisible(true);
		TableColumn    tblclmOwnerContactName= new TableColumn(tblOwnerContact, SWT.NONE);
		tblclmOwnerContactName.setResizable(false);
		tblclmOwnerContactName.setMoveable(true);
		tblclmOwnerContactName.setWidth(150);
		tblclmOwnerContactName.setText("Name");
		
		
		TableColumn tblclmnRelationship = new TableColumn(tblOwnerContact, SWT.NONE);
		tblclmnRelationship.setWidth(70);
		tblclmnRelationship.setResizable(false);
		tblclmnRelationship.setMoveable(true);
		tblclmnRelationship.setText("Relationship");	
		
		TableColumn tblclmnPhoneNumbeer = new TableColumn(tblOwnerContact, SWT.NONE);
		tblclmnPhoneNumbeer.setWidth(125);
		tblclmnPhoneNumbeer.setResizable(false);
		tblclmnPhoneNumbeer.setMoveable(true);
		tblclmnPhoneNumbeer.setText("Phone #");	
		
		btnDeleteOwnerContact = new Button(grpOwnerContact, SWT.NONE);
		btnDeleteOwnerContact.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("");
				int i = tblOwnerContact.getSelectionIndex();
				OwnerContact ownercontact = currentOwnerContacts.get(i);
				OwnerContactDB ownercontactDB = new OwnerContactDB(url, user, password);
	    			String strErrMsg= ownercontactDB.Delete(ownercontact);
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("Owner Contact deleted");	    				
	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg);
	    			}
	    			
	    			ownercontactDB.disConnect(); 
				owner own = currentOwners.get(tblOwners.getSelectionIndex());
				fillOwnerContact(own.getOwnerid());						
			}
		});
		btnDeleteOwnerContact.setBounds(0, 99, 153, 28);
		btnDeleteOwnerContact.setEnabled(false);
		btnDeleteOwnerContact.setText("Remove Owner Contact");
		
		txtContactName = new Text(grpOwnerContact, SWT.BORDER);
		txtContactName.setBounds(434, 0, 183, 20);
		txtContactName.setFont(SWTResourceManager.getFont(".AppleSystemUIFont", 11, SWT.NORMAL));
		
		lblContactName = new Label(grpOwnerContact, SWT.NONE);
		lblContactName.setBounds(335, 2, 93, 20);
		lblContactName.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		lblContactName.setText("Contact Name:");
		
		lblNewLabel_1 = new Label(grpOwnerContact, SWT.NONE);
		lblNewLabel_1.setBounds(335, 26, 93, 20);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		lblNewLabel_1.setText("Contact Phone:");
		
		txtContactPhone = new Text(grpOwnerContact, SWT.BORDER);
		txtContactPhone.setBounds(435, 26, 182, 20);
		
		btnAddContact = new Button(grpOwnerContact, SWT.NONE);
		btnAddContact.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("");
				if (txtContactName.getText().length() < 5)
	    			{
	    				txtErrorMsg.setText("Contact name has to be at least 5 characters");	 
	    				return;
	    			}
				if (txtContactPhone.getText().length() < 8)
	    			{
	    				txtErrorMsg.setText("Phone has to be at least 8 characters");	 
	    				return;
	    			}	
	
				if (!txtContactPhone.getText().matches(patternPhone))
				{
	    				txtErrorMsg.setText("Valid Contact Phone Number has to match 123-456-7890 or 123)456-7890 or (123)4567890");	 
	    				return;					
				}
				if (lstRelationship.getSelectionCount() == 0)
	    			{
	    				txtErrorMsg.setText("Please select a relationship");	 
	    				return;
	    			}				
				
				owner own = currentOwners.get(tblOwners.getSelectionIndex());
				OwnerContact ownercontact = new OwnerContact(-1, own.getOwnerid(),-1 ,txtContactName.getText(), lstRelationship.getSelection()[0], txtContactPhone.getText());		
				OwnerContactDB ownercontactDB = new OwnerContactDB(url, user, password);
	    			String strErrMsg= ownercontactDB.Add(ownercontact);
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("Owner Contact added");	    				
	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg);
	    			}
	    			
	    			ownercontactDB.disConnect(); 
	    			fillOwnerContact(own.getOwnerid());			}
		});
		btnAddContact.setBounds(336, 218, 115, 28);
		btnAddContact.setEnabled(false);
		btnAddContact.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnAddContact.setText("Add Contact");
		
		btnUpdateContact = new Button(grpOwnerContact, SWT.NONE);
		btnUpdateContact.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtErrorMsg.setText("");

			if (txtContactName.getText().length() < 5)
    			{
    				txtErrorMsg.setText("Contact name has to be at least 5 characters");	 
    				return;
    			}
			if (txtContactPhone.getText().length() < 8)
    			{
    				txtErrorMsg.setText("Phone has to be at least 8 characters");	 
    				return;
    			}	

			if (!txtContactPhone.getText().matches(patternPhone))
			{
    				txtErrorMsg.setText("Valid Contact Phone Number has to match 123-456-7890 or 123)456-7890 or (123)4567890");	 
    				return;					
			}
			if (lstRelationship.getSelectionCount() == 0)
    			{
    				txtErrorMsg.setText("Please select a relationship");	 
    				return;
    			}				
				
				int i = tblOwnerContact.getSelectionIndex();
				OwnerContact ownercontact = currentOwnerContacts.get(i);
				ownercontact.setContactName(txtContactName.getText());
				ownercontact.setPhone(txtContactPhone.getText());
				ownercontact.setRelationship( lstRelationship.getSelection()[0]);

				currentOwnerContacts.set(i, ownercontact);
	    			TableItem row = tblOwnerContact.getItem(tblOwnerContact.getSelectionIndex());
	    			row.setText(new String[]{ownercontact.getContactName(),ownercontact.getRelationship(),ownercontact.getPhone()});
	    			
	    			OwnerContactDB ownercontactDB = new OwnerContactDB(url, user, password);
	    			String strErrMsg= ownercontactDB.Update(ownercontact);
	    			if (strErrMsg=="")
	    			{
	    				txtErrorMsg.setText("Owner Contact Updated");	    				
	    			}
	    			else
	    			{
	    				txtErrorMsg.setText(strErrMsg);
	    			}
	    			
	    			ownercontactDB.disConnect(); 
	    		}
		});
		btnUpdateContact.setBounds(471, 218, 123, 28);
		btnUpdateContact.setEnabled(false);
		btnUpdateContact.setFont(SWTResourceManager.getFont("Arial", 10, SWT.BOLD));
		btnUpdateContact.setText("Uopdate Contact");
		
		lblContactRelationship = new Label(grpOwnerContact, SWT.NONE);
		lblContactRelationship.setFont(SWTResourceManager.getFont("Arial", 12, SWT.BOLD));
		lblContactRelationship.setBounds(335, 62, 129, 20);
		lblContactRelationship.setText("Contact Relationship:");
		
		lstRelationship = new List(grpOwnerContact, SWT.BORDER);
		lstRelationship.setItems(new String[] {"Brother", "Co-Owner/ Daughter", "Daughter", "Daughter-in-law", "Niece", "Sister", "Sister-in-Law", "Son", "Son-in-law"});
		lstRelationship.setBounds(482, 62, 135, 150);

		fillLstUnits();

	}
	
	protected void fillLstUnits()
	{
		//lstUnits.
		
		unitDB unitdb = new unitDB(url, user, password);
		if (unitdb.isConnected())
		{	
			lstUnits.removeAll();
			if (units != null)
			{
				units.clear();
			}
			units = unitdb.Select();
		    for (int iPos = 0; iPos < units.size(); iPos++) {
		    		Unit unit = units.get(iPos);   
			    	lstUnits.add(Integer.toString(unit.getAddress()) +  unit.getUnitletter() );		    
			}
		    
		    if (units.size() > 0)
		    {
		    		lstUnits.setSelection(0);
				String str = lstUnits.getSelection()[0];
				lblOwnersOf.setText("Owners of " + str);
				Unit unit = units.get(lstUnits.getSelectionIndex());
				txtLandLine.setText(unit.getLandline());
				txtUnitNumber.setText(Integer.toString(unit.getAddress()));
				txtUnitLetter.setText(unit.getUnitletter());	
				fillOwners(unit.getUnitid());
			}
		    
		    unitdb.disConnect();
			
		}
		else
		{
			units = null;
		}
	}
	
	
	protected int findUnit(Unit unit)
	{
		int iCursor= -1;
		for (int iPos=0; iPos< lstUnits.getItemCount(); iPos++)
		{
			String str = lstUnits.getItem(iPos);
			String currentItem = Integer.toString(unit.getAddress()) + unit.getUnitletter();
			if ( str.equals(currentItem) )
			{
				iCursor=iPos;
				lstUnits.setSelection(iPos);
				lstUnits.select(iPos);
				lstUnits.setFocus();
				int i = lstUnits.getSelectionIndex();
				unit = units.get(i);
				txtLandLine.setText(unit.getLandline());
				txtUnitNumber.setText(Integer.toString(unit.getAddress()));
				txtUnitLetter.setText(unit.getUnitletter());	
			}
			
		}
		
		
		return iCursor;
	}
	
	protected void fillOwners(int unitid)
	{
		
		
		txtFirstName.setText("");
		txtLastName.setText("");
		btnUpdateOwner.setEnabled(false); 
		btnRemoveOwner.setEnabled(false);
		ownerDB ownerdb = new ownerDB(url, user, password);
		if (ownerdb.isConnected())
		{	
			if (ownerdb != null)
			{
				if (currentOwners != null)
				{
					currentOwners.clear();
				}
				if (tblOwners.getItemCount() > 0)
				{
					tblOwners.removeAll();
					tblOwners.clearAll();;
				}
			}
			currentOwners = ownerdb.Select(unitid);
			

			
			for (int i =0; i<10;i++)
			{
				new TableItem(tblOwners,SWT.NONE);
			}
		    for (int iPos = 0; iPos < currentOwners.size(); iPos++) {

		    		owner item = currentOwners.get(iPos);  	    		
		    		TableItem row = tblOwners.getItem(iPos);
		    		row.setText(new String[]{item.getFirstname(),item.getLastname()});
		    		fillPhones(-1);
				grpPhone.setText("Phone Numbers ");
		    		fillEmailAddress(-1);
				grpEmailAddresses.setText("Email Address ");
    				fillOwnerContact(-1);
    				grpOwnerContact.setText("Owner Contacts");

			}
		    
		    ownerdb.disConnect();
			
		}
		else
		{
			units = null;
		}
	}
	
	
	protected void fillPhones(int ownerid)
	{
		
		
		txtPhoneNumber.setText("");
		btnUpdatePhone.setEnabled(false); 
		btnDeletePhone.setEnabled(false);
		btnAddPhone.setEnabled(false);
		PhoneDB phonedb = new PhoneDB(url, user, password);
		if (phonedb.isConnected())
		{	
			if (phonedb != null)
			{
				if (currentPhones != null)
				{
					currentPhones.clear();
				}
				if (tblPhoneNumber.getItemCount() > 0)
				{
					tblPhoneNumber.removeAll();
					tblPhoneNumber.clearAll();;
				}
			}
			
			if (ownerid >= 0)
			{
				currentPhones = phonedb.Select(ownerid);
				
	
				
				for (int i =0; i<10;i++)
				{
					new TableItem(tblPhoneNumber,SWT.NONE);
				}
			    for (int iPos = 0; iPos < currentPhones.size(); iPos++) {
	
			    		Phone item = currentPhones.get(iPos);  	    		
			    		TableItem row = tblPhoneNumber.getItem(iPos);
			    		row.setText(new String[]{item.getPhoneNumber(),item.getPhoneType()});
			    		btnAddPhone.setEnabled(true);
	
				}
		    
			}
		    phonedb.disConnect();
			
		}
		else
		{
			currentPhones = null;
		}
	}
	
	
	protected void fillEmailAddress(int ownerid)
	{
		
		
		txtEmailAddress.setText("");
		btnUpdateEmailAddress.setEnabled(false); 
		btnDeleteEmailAddress.setEnabled(false);
		btnAddEmailAddress.setEnabled(false);
		EmailAddressDB emailaddressdb = new EmailAddressDB(url, user, password);
		if (emailaddressdb.isConnected())
		{	
			if (emailaddressdb != null)
			{
				if (currentEmailAddresses != null)
				{
					currentEmailAddresses.clear();
				}
				if (tblEmailAddress.getItemCount() > 0)
				{
					tblEmailAddress.removeAll();
					tblEmailAddress.clearAll();;
				}
			}
			
			if (ownerid >= 0)
			{
				currentEmailAddresses = emailaddressdb.Select(ownerid);
	    			btnAddEmailAddress.setEnabled(true);
				
	
				
				for (int i =0; i<10;i++)
				{
					new TableItem(tblEmailAddress,SWT.NONE);
				}
			    for (int iPos = 0; iPos < currentEmailAddresses.size(); iPos++) {
	
			    		EmailAddress item = currentEmailAddresses.get(iPos);  	    		
			    		TableItem row = tblEmailAddress.getItem(iPos);
			    		row.setText(new String[]{item.getEmailaddress()});
				}
		    
			}
			emailaddressdb.disConnect();
			
		}
		else
		{
			currentEmailAddresses = null;
		}
	}
	
	
	protected void fillOwnerContact(int ownerid)
	{
		
		
		txtContactName.setText("");
		txtContactPhone.setText("");
		grpOwnerContact.setText("Owner Contacts");
		btnUpdateContact.setEnabled(false); 
		btnDeleteOwnerContact.setEnabled(false);
		btnAddContact.setEnabled(false);
		OwnerContactDB ownercontactdb = new OwnerContactDB(url, user, password);
		if (ownercontactdb.isConnected())
		{	
			if (ownercontactdb != null)
			{
				if (currentOwnerContacts != null)
				{
					currentOwnerContacts.clear();
				}
				if (tblOwnerContact.getItemCount() > 0)
				{
					tblOwnerContact.removeAll();
					tblOwnerContact.clearAll();;
				}
			}
			
			if (ownerid >= 0)
			{
				currentOwnerContacts = ownercontactdb.Select(ownerid);
				btnAddContact.setEnabled(true);
				
	
				
				for (int i =0; i<10;i++)
				{
					new TableItem(tblOwnerContact,SWT.NONE);
				}
			    for (int iPos = 0; iPos < currentOwnerContacts.size(); iPos++) {
	
			    		OwnerContact item = currentOwnerContacts.get(iPos);  	    		
			    		TableItem row = tblOwnerContact.getItem(iPos);
			    		row.setText(new String[]{item.getContactName(),item.getRelationship(),item.getPhone()});
				}
		    
			}
			ownercontactdb.disConnect();
			
		}
		else
		{
			currentOwnerContacts = null;
		}
	}
}
