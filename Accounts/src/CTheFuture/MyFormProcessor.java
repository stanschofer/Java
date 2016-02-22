package CTheFuture;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.*;



/**
 * Servlet implementation class MyFormProcessor
 */
@WebServlet("/MyFormProcessor")
public class MyFormProcessor extends HttpServlet {
	public static final String docType = "<!DOCTYPE HTMLPublic \"-//W3C//DTD HTML 4.0 "+ "Transitional//EN\">\n";

	private static final long serialVersionUID = 1L;
	private String strType = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyFormProcessor() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//viewParameters(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*viewParameters(request,response);
		if (1==1)
			return;*/


		String password = request.getParameter("password");
		request.setAttribute("password",password);
		String msg = "";
		String value = request.getParameter("searchSubmit");
		String nextJSP = "Accounts.jsp";
		if ( (value != null) && ( value.equals("Search")) )
		{
			request.setAttribute("EditID","-1");
			msg += "Search:";

			value = "";
			if (request.getParameter("searchValue") != null)
			{
				value = request.getParameter("searchValue");
			}
			request.setAttribute("searchText",value);
			request.setAttribute("errorMessage",msg);
			request.getRequestDispatcher(nextJSP).forward(request, response); 
			return;
		}

		value = request.getParameter("addSubmit");
		if ( (value != null) && ( value.equals("Add")) )
		{
			msg += "Add:";
			request.setAttribute("EditID","-1");
			Postgres db = new Postgres();
			db.connect();
			//db.connect(dbServer, dbUser, dbPassword);
			if (db.isConnected())
			{
				String tag  = "userid";
				String userid = getValue( request,  tag);
				tag  = "account";
				String accountName = getValue( request,  tag);
				tag  = "passwd";
				String passwd = getValue( request,  tag);
				if (
					(userid.length() > 0) 
					&& (accountName.length() > 0) 
					&& (passwd.length() > 0)
					)
				{
					Account acct = new Account(accountName,userid,passwd);
					int iRetValue = db.InsertAccount(acct,password);
					if (iRetValue !=1)
					{
						msg += db.getErrorMessage();						
					}
					else
					{
						msg += db.getErrorMessage();						
						msg += accountName+ "," + userid + "," + passwd;
					}
				}
				else
				{
					msg += "Missing :" + accountName+ "," + userid + "," + passwd;					
				}
			}
			value = "";
			if (request.getParameter("searchValue") != null)
			{
				value = request.getParameter("searchValue");
			}
			request.setAttribute("searchText",value);

			request.setAttribute("errorMessage",msg);
			
			request.getRequestDispatcher(nextJSP).forward(request, response); 
			return;
		}

		int key = id(request);
		request.setAttribute("EditID",key);
		if (strType.contains("Update"))
		{
			msg += "Update:";
			Postgres db = new Postgres();
			db.connect();
			//db.connect(dbServer, dbUser, dbPassword);
			if (db.isConnected())
			{
				String tag  = "userid" + String.valueOf(key);
				String userid = getValue( request,  tag);
				tag  = "account" + String.valueOf(key);
				String accountName = getValue( request,  tag);
				tag  = "passwd" + String.valueOf(key);
				String passwd = getValue( request,  tag);
				if (
					(userid.length() > 0) 
					&& (accountName.length() > 0) 
					&& (passwd.length() > 0)
					)
				{
					Account acct = new Account(accountName,userid,passwd,key);
					db.UpdateAccount(acct,password);
				}
			}
			
		}
		else if (strType.contains("Delete"))
		{
			Account acct = new Account(key);
			msg += "Deleting ID: " + String.valueOf(key) + "  ";
			Postgres db = new Postgres();
			db.connect();
			//db.connect(dbServer, dbUser, dbPassword);
			if (db.isConnected())
			{			
				int iRetValue = db.DeleteAccount(acct);;
				if (iRetValue !=1)
				{
					msg += db.getErrorMessage();						
				}
				else
				{
					msg += "Deleted ID: " + String.valueOf(key);
				}
			}
			else
			{
				msg += "Not connected" ;				
			}
		}
		
			//response.sendRedirect(nextJSP);
		if (request.getParameter("searchValue") != null)
		{
			value = request.getParameter("searchValue");
		}
			
			
		request.setAttribute("searchText",value);
		request.setAttribute("errorMessage",msg);
			
			
		request.getRequestDispatcher(nextJSP).forward(request, response); 
	}

	private  String getValue(HttpServletRequest request, String tag)
	{
		String value = "";
		if (request.getParameter(tag) != null)
		{
			value = request.getParameter(tag);
		}
	    
	    return value;
		
	}

	
	private  int id (HttpServletRequest request)
	{
		int id = -1;
	    Enumeration paramNames = request.getParameterNames();
	    while(paramNames.hasMoreElements()) 
	    {
	      String paramName = (String)paramNames.nextElement();
          strType = request.getParameter(paramName);
	      if (strType.contains("Edit"))
	      {
	          id = Integer.parseInt(paramName);
	          return id;
	      }
	      else if (strType.contains("Update"))
	      {
	          id = Integer.parseInt(paramName);
	          return id;
	      }
	      else if (strType.contains("Delete"))
	      {
	          id = Integer.parseInt(paramName);
	          return id;
	      }
	    }
	    
	    return id;
		
	}

	private static void viewParameters(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		   	response.setContentType("text/html");
		    PrintWriter out = response.getWriter();
		    String title = "Reading All Request Parameters";
		    out.println(headWithTitle(title) +
		                "<BODY BGCOLOR=\"#FDF5E6\">\n" +
		                "<H1 ALIGN=CENTER>" + title + "</H1>\n" +
		                "<TABLE BORDER=1 ALIGN=CENTER>\n" +
		                "<TR BGCOLOR=\"#FFAD00\">\n" +
		                "<TH>Parameter Name<TH>Parameter Value(s)");
		    Enumeration paramNames = request.getParameterNames();
		    while(paramNames.hasMoreElements()) {
		      String paramName = (String)paramNames.nextElement();
		      out.println("<TR><TD>" + paramName + "\n<TD>");
		      String[] paramValues = request.getParameterValues(paramName);
		      if (paramValues.length == 1) {
		        String paramValue = paramValues[0];
		        if (paramValue.length() == 0)
		          out.print("<I>No Value</I>");
		        else
		          out.print(paramValue);
		      } else {
		        out.println("<UL>");
		        for(int i=0; i<paramValues.length; i++) {
		          out.println("<LI>" + paramValues[i]);
		        }
		        out.println("</UL>");
		      }
		    }
		    out.println("</TABLE>\n</BODY></HTML>");		
	}
	
	public static String headWithTitle(String title)
	{
		return ( docType 
				+ "<HTML>" 
				+ "<HEAD><TITLE>" + title + "</TITLE><HEAD>\n"
				);
	}
}
