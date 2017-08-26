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

/**
 * Servlet implementation class deleteCategory
 */
public class deleteCategory extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PreparedStatement stSelect = null;
    Connection connection=null;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = null;
		try
		{
			ResultSet result = null;
			ServletContext context = getServletContext();
			connection = (Connection)context.getAttribute("globalConnection");
			out = response.getWriter();
			out.println("<html>");
			out.println("<head><title>User35 Delete Category</title></head>");
			out.println("<body><form action='finalDelete' method='Get'>");
			out.println("<table border='0' align='center' width='50%'>");
			out.println("<tr><td>Cat Name : </td><td><select name='catName'>");
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
			out.println("Database Connection Not Established");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}

}
