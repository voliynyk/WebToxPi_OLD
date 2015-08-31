package edu.tamu.webtoxpi.Client;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = { "/RegisterServlet" }, initParams = { @WebInitParam(name = "username", value = ""), @WebInitParam(name = "email", value = ""), @WebInitParam(name = "password", value = ""),
		@WebInitParam(name = "confirmpassword", value = "") })
public class RegisterServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public RegisterServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
