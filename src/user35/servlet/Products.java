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
 * Servlet implementation class Products
 */
public class Products extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	Connection connection = null;
	ResultSet result = null;
	PreparedStatement stSelect = null;
	
	@Override
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
		try 
		{
			HttpSession session = request.getSession(false);
			if(session==null)
			{
				response.sendRedirect("login.html");
				return;
			}
			
			PrintWriter out = response.getWriter();
			
			Cookie []arrCookie = request.getCookies();
			int len = arrCookie.length;
			out.println("Welcome " + arrCookie[len-1].getValue());
			
			stSelect = connection.prepareStatement("select * from products where catId = ?");
			
			int catId = Integer.parseInt(request.getParameter("catId"));
			stSelect.setInt(1, catId);
			result = stSelect.executeQuery();
			out.println("<html>");
			out.println("<head><title>User35 - Shopping Complex</title></head>");
			out.println("<body>");
			out.println("<table border='1' align='center' width='80%'>");
			out.println("<tr>");
			out.println("<th width='10%'>Prod. No.</th>");
			out.println("<th width='10%'>Cat Id</th>");
			out.println("<th width='20%'>Prod. Name</th>");
			out.println("<th width='30%'>Prod. Description</th>");
			out.println("<th width='10%'>Price</th>");
			out.println("<th>Image</th>");
			out.println("<th>Select</th>");
			out.println("</tr>");
			
			while(result.next())
			{
				out.println("<tr>");
				out.println("<td>" + result.getInt("ProdId") + "</td>");
				out.println("<td>" + result.getInt("CatId") + "</td>");
				out.println("<td>" + result.getString("ProdName") + "</td>");
				out.println("<td>" + result.getString("ProdDesc") + "</td>");
				out.println("<td>" + result.getFloat("Price") + "</td>");
				out.println("<td><img src='Images/" + result.getString("ProdImgSrc") + "' width='120' height='120' /> </td>");
				out.println("<td><a href='AddToCart?catId=" + result.getInt("catId") + 
						"&prodId=" + result.getInt("prodId") + "&price=" + result.getFloat("price") + "'>Add To Cart</a></td>");
				out.println("</tr>");
			}
			out.println("</table>");
			out.println("</body>");
			out.println("</html>");
		} 
		catch (NumberFormatException e) 
		{
			e.printStackTrace();
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
				throws ServletException, IOException 
	{
		doGet(request,response);
	}
	
	@Override
	public void destroy()
	{
		try 
		{
			if(connection!=null)
				connection.close();
			if(stSelect!=null)
				stSelect.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
}

