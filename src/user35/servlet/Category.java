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
 * Servlet implementation class Welcome
 */
public class Category extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	ResultSet result = null;
	PreparedStatement stSelect = null;
	
	public void init(ServletConfig config)
	{
		try 
		{
			super.init(config);
			ServletContext context = getServletContext();
			connection = (Connection)context.getAttribute("globalConnection");
		} 
		catch (ServletException e) 
		{
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);
		
		if(session==null)
		{
			response.sendRedirect("login.html");
			return;
		}
		PrintWriter out = response.getWriter();
		
		try 
		{
			stSelect = connection.prepareStatement("select * from category");
			result = stSelect.executeQuery();
			
			out.println("<html>");
			out.println("<head>");
			out.println("<title>");
			out.println("Category");
			out.println("</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("Welcome <b>" + (String)session.getAttribute("userName") + "</b><hr />");
			out.println("<a href='admin.html'>Manage Category</a><hr />");
			out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<td>Category ID</td>");
			out.println("<td>Category Name</td>");
			out.println("<td>Category Description</td>");
			out.println("<td>Category Image</td>");
			out.println("</tr>");
			while(result.next())
			{
				out.println("<tr>");
				out.println("<td><a href='Products?catId=" 
						+ result.getInt("catId") 
						+ "'>" 
						+ result.getString("catName")
						+ "</a></td>");
				out.println("<td>" + result.getString("catDesc") + "</td>");
				out.println("<td>" + result.getString("CatDesc") + "</td>");
				out.println("<td><img src = 'Images/" + result.getString("CatImgSrc") 
						+ "' width='120' height='120' alt='Image'>"
						+ "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				if(result!=null)
					result.close();
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
	}

	@Override
	public void destroy()
	{
		try 
		{
			if(stSelect!=null)
				stSelect.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
					throws ServletException, IOException 
	{
		doGet(request,response);
	}
}