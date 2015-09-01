package edu.tamu.webtoxpi.Client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import edu.tamu.webtoxpi.Client.DataManager.DataManager;

@WebServlet(urlPatterns = { "/SearchResultsServlet" }, initParams = { @WebInitParam(name = "casrn", value = ""), @WebInitParam(name = "chemical", value = ""), @WebInitParam(name = "component", value = "") })
public class SearchResultsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public SearchResultsServlet()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String source = request.getParameter("source");
		String casrn = request.getParameter("casrn");
		String chemical = request.getParameter("chemical");
		
/*		String weight = request.getParameter("weight");
		String group = request.getParameter("group");
		String type = request.getParameter("type");*/
		String component = request.getParameter("component");
		
		boolean searchById = false;
		if (StringUtils.isNotBlank(request.getParameter("searchById")))
		{
			searchById = Boolean.parseBoolean(request.getParameter("searchById"));
		}


		String outdata = DataManager.getJSON(source, casrn, chemical, component, searchById);
		request.setAttribute("outdata", outdata);

		this.getServletContext().getRequestDispatcher("/FoundResults.jsp").include(request, response);

		// response.getWriter().append("Served
		// at:").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
