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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class NewCategory
 */
public class NewCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection connection = null;
    PreparedStatement stSelect = null;
    @Override
	public void init(ServletConfig config)
    {
    	ServletContext context = config.getServletContext();
    	connection = (Connection)context.getAttribute("globalConnection");
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException 
	{
		ResultSet result = null;
		try {
			HttpSession session = request.getSession(false);
			if(session==null)
			{
				response.sendRedirect("login.html");
				return;
			}
			int catId = Integer.parseInt(request.getParameter("catId"));
			String catName = request.getParameter("catName");
			String catDesc = request.getParameter("catDesc");
			String imgurl = request.getParameter("catImgUrl");
			
			stSelect = connection.prepareStatement("insert into category values(?,?,?,?)");
			stSelect.setInt(1, catId);
			stSelect.setString(2, catName);
			stSelect.setString(3, catDesc);
			stSelect.setString(4, imgurl);
			result = stSelect.executeQuery();
			response.sendRedirect("addCategory.html");
		} 
		catch (NumberFormatException e) 
		{
			response.sendRedirect("addCategory.html");
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException 
	{
		doGet(request,response);
	}
	
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
}