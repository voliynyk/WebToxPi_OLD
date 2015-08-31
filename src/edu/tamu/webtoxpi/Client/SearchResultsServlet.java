package edu.tamu.webtoxpi.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.tamu.webtoxpi.Client.DataManager.DataManager;
import edu.tamu.webtoxpi.Server.Outbound.OutData;
import edu.tamu.webtoxpi.Server.Outbound.OutSearchResult;

@WebServlet(urlPatterns = { "/SearchResultsServlet" }, initParams = { @WebInitParam(name = "casrn", value = ""), @WebInitParam(name = "chemical", value = ""), @WebInitParam(name = "component", value = "") })
public class SearchResultsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public SearchResultsServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String casrn = request.getParameter("casrn");
		String chemical = request.getParameter("chemical");
		String component = request.getParameter("component");

		String outdata = DataManager.getJSON(casrn, chemical, component);
		request.setAttribute("outdata", outdata);

		this.getServletContext().getRequestDispatcher("/FoundResults.jsp").include(request, response);

		// response.getWriter().append("Served at:").append(request.getContextPath());
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
