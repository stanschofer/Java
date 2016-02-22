<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"   import="java.util.*,CTheFuture.Account,CTheFuture.Postgres"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="accountstyle.css">
<%
	String  addToTitle="";
	Postgres db = new Postgres();
	if (db.UseActiveMQ().equals("Y"))
	{
		addToTitle = " with Backup to ActiveMQ at " + db.ActiveMQIPAddress()  + " queue:" + db.ActiveMQQueue();
	}
	else
	{
		addToTitle = db.getErrorMessage() + ":" + db.UseActiveMQ() +   "|" + db.ActiveMQIPAddress()  + "|" + db.ActiveMQQueue();
	}
%>
<title>My Accounts 1.1<%=addToTitle %></title>
</head>
<body>
<%
	String searchText = "";
	String password =  "";
	if (request.getAttribute("searchText") !=  null)
	{
		searchText = (String)request.getAttribute("searchText");
	}
	if (request.getAttribute("password") !=  null)
	{
		password = (String)request.getAttribute("password");
	}
	String errorMessage = "";
	if (request.getAttribute("errorMessage") !=  null)
	{
		errorMessage = (String)request.getAttribute("errorMessage");
	}
	int key = -1;
	if (request.getAttribute("EditID")!= null)
	{
		key = Integer.parseInt(request.getAttribute("EditID").toString());
	}
%>
<FORM method="post" action="MyFormProcessor" >
<TABLE>
<tr>
	<td>
		Account:
	</td>
	<td>
		<input type="text" value="<%=searchText%>" name="searchValue"  maxlength="40"  size="40">
	</td>
	<td>
		<input type="submit" value="Search" name="searchSubmit" >
	</td>
</tr>
<tr>
	<td>
		Password:
	</td>
	<td>
		<input type="password" value="<%=password%>" name="password"  maxlength="40"  size="40">
	</td>
	<td>
	</td>
</tr>
<tr>
	<td  class=".TDerrorMsg" colspan=3><%=errorMessage%></td>
</tr>
<tr>
	<td colspan="3">
			<%=Account.makeTable(searchText.trim(),key,password)%>
	</td>
</tr>
</TABLE>
</FORM>
</body>
</html>