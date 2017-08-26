package user35.servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCart
 */
public class AddToCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			response.sendRedirect("login.html");
			return;
		}
		
		int catId = Integer.parseInt(request.getParameter("catId"));
		int prodId = Integer.parseInt(request.getParameter("prodId"));
		float price = Float.parseFloat(request.getParameter("price"));
		
		ProductItem objItem = new ProductItem(catId,prodId,price);
		
		Vector objCart = (Vector) session.getAttribute("cart");
		
		if(objCart==null)
		{
			objCart = new Vector();
			session.setAttribute("cart", objCart);
		}
		objCart.add(objItem);
		response.sendRedirect("ListCart");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}
}
