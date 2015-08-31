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

/**
 * Servlet implementation class NewResultsServlet
 */
@WebServlet(urlPatterns = { "/NewResultsServlet" }, initParams = { @WebInitParam(name = "casrn", value = ""), @WebInitParam(name = "chemical", value = ""), @WebInitParam(name = "component", value = "") })
public class NewResultsServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewResultsServlet()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if ("ByChemichal".equals(request.getParameter("TypeOfInput")))
		{
			String casrn = request.getParameter("casrn");
			String chemicalsource = request.getParameter("chemicalsource");
			String chemical = request.getParameter("chemical");
			
			if (casrn != null && !casrn.isEmpty() &&
				chemicalsource != null && !chemicalsource.isEmpty() &&
				chemical != null && !chemical.isEmpty())
			{
				DataManager.newResults(casrn, chemicalsource, chemical);
			

				String outdata = DataManager.getJSON(casrn, chemical, chemicalsource, "");
				request.setAttribute("outdata", outdata);

				this.getServletContext().getRequestDispatcher("/FoundResults.jsp").include(request, response);
			}
		}
		else
		{
			if ("ByComponent".equals(request.getParameter("TypeOfInput")))
			{
				String componentName = request.getParameter("componentName");
				
				if (componentName != null && !componentName.isEmpty())
				{
					DataManager.newResults(componentName);
					
					String outdata = DataManager.getJSON("", "", "", componentName);
					request.setAttribute("outdata", outdata);

					this.getServletContext().getRequestDispatcher("/FoundResults.jsp").include(request, response);
				}
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
		doPost(request, response);
	}

}
