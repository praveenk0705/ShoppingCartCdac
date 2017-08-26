package user35.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class finalDelete
 */
public class finalDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	Connection connection = null;
	PreparedStatement stSelect= null;
	
	@Override
	public void init(ServletConfig config) throws ServletException
    {
    	try
		{
			ServletContext context = config.getServletContext();
			connection = (Connection)context.getAttribute("globalConnection");
			stSelect = connection.prepareStatement("delete * from category where catName = ?");
		}
    	
    	catch (SQLException e)
		{
			e.printStackTrace();
		}
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			stSelect.setString(1, request.getParameter("catName"));
			ResultSet result = stSelect.executeQuery();
			response.sendRedirect("admin.html");
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}

}
