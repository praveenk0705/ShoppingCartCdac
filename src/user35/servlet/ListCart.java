package user35.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ListCart
 */
public class ListCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			response.sendRedirect("login.html");
			return;
		}
		Vector objCart = (Vector)session.getAttribute("cart");
		
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><title>User35 List Cart</title></head>");
		out.println("<body>");
		out.println("Welcome <b>" + session.getAttribute("userName") + "</b><br />");
		
		if(objCart==null)
			out.println("Cart is empty");
		else
		{
			out.println("<table border='1'>");
			Iterator iter = objCart.iterator();
			double total = 0.0;
			while(iter.hasNext())
			{
				ProductItem objItem = (ProductItem)iter.next();
				out.println("<tr><td>" + objItem.getCatId() + "</td>");
				out.println("<td>" + objItem.getProdId() + "</td>");
				out.println("<td>" + objItem.getPrice() + "</td></tr>");
				total+=objItem.getPrice();
			}
			out.println("</table>");
			out.println("Cart Total is <b>" + total + "</b><br />");
		}
		out.println("<a href='Category'>Continue Shopping</a><br />");
		out.println("<a href='Pay'>Check Out</a><br />");
		out.println("<a href='Logout'>Logout</a><br />");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}

}
