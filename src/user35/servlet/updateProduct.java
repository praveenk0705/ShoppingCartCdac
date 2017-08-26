package user35.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class updateProduct extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	PreparedStatement stSelect = null;
    Connection connection=null;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try
		{
			PrintWriter out = response.getWriter();
			ResultSet result = null;
			ServletContext context = getServletContext();
			connection = (Connection)context.getAttribute("globalConnection");
			out = response.getWriter();
			out.println("<html>");
			out.println("<head><title>User35 Update Product</title></head>");
			out.println("<body><form action='#' method='Get'>");
			out.println("<table border='0' align='center' width='50%'>");
			out.println("<tr><td>Cat Name : </td><td><select>");
			stSelect = connection.prepareStatement("select * from category");
			result = stSelect.executeQuery();
			
			while(result.next())
				out.println("<option>" + result.getString("catName") + "</option>");
			
			out.println("</select></td></tr>");
			out.println("<tr><td colspan='2' align='center'><input type='submit' value='Delete Selected'></td></tr>");
			out.println("</table></form></body></html>");
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