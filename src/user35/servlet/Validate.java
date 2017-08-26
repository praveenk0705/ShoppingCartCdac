package user35.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Validate
 */
public class Validate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   
	Connection connection = null;
	ResultSet result = null;
	@Override
	public void init(ServletConfig config)
	{
		
		try 
		{
			super.init(config);
			String driver = config.getInitParameter("driverClass");
			String dbUrl = config.getInitParameter("dbUrl");
			String user = config.getInitParameter("userName");
			String password = config.getInitParameter("password");
			
			Class.forName(driver);
			connection = DriverManager.getConnection(dbUrl, user, password);
			if(connection==null)
			{
				System.out.println("Connection Failed");
				return;
			}
			ServletContext context = getServletContext();
			context.setAttribute("globalConnection", connection);
		} 
		
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		}
	}
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
 	{
 		PrintWriter out = response.getWriter();
 		PreparedStatement stSelect = null;
 		try 
 		{
				String userName = request.getParameter("userName");
				String password = request.getParameter("password");
				
				/*Cookie objCookie = new Cookie("usercookie",userName);
				objCookie.setMaxAge(120);
				response.addCookie(objCookie);*/
				
			
				stSelect = connection.prepareStatement("select * from users where username= ? and password= ?");
				stSelect.setString(1, userName);
				stSelect.setString(2, password);
				result = stSelect.executeQuery();
				
				if(result.next())
				{
					HttpSession session = request.getSession();
					session.setAttribute("userName", userName);
					response.sendRedirect("Category");
				}
				else
					response.sendRedirect("login.html");
		} 
 		catch (SQLException e) 
 		{
			e.printStackTrace();
		}
 		catch(NullPointerException e)
 		{
 			out.println("Database Connection Not Established");
 		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}
}
