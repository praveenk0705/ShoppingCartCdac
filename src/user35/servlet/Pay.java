package user35.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.Naming;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Pay
 */
public class Pay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		if(session==null)
		{
			response.sendRedirect("login.html");
			return;
		}
		out.println("<html>");
		out.println("<head><title>Payment Gateway</title></head>");
		out.println("<body>");
		out.println("<form action='PaymentClient'>");
		out.println("<table border='1' align='center' width='60%'>");
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
		/*CardValidator remote = (CardValidator)Naming.lookup("PaymentService");
		boolean  status = remote.CardValidator(555, 1520);
		
		if(status)
			out.println("Card Accepted");
		else
			out.println("Card Denied");*/
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doGet(request,response);
	}

}
