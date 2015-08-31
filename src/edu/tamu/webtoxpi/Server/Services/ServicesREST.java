package edu.tamu.webtoxpi.Server.Services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;

import edu.tamu.webtoxpi.Client.DataManager.DataManager;
import edu.tamu.webtoxpi.Server.InBound.UpdateResult;
import edu.tamu.webtoxpi.Server.Models.Classes.Groups;
import edu.tamu.webtoxpi.Server.Models.Classes.Orders;
import edu.tamu.webtoxpi.Server.Models.Classes.Results;
import edu.tamu.webtoxpi.Server.Models.DAO.DAOManager;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.HibernateUtil;
import edu.tamu.webtoxpi.Server.Outbound.OutViewChemical;
import edu.tamu.webtoxpi.Server.Outbound.OutViewComponent;
import edu.tamu.webtoxpi.Server.Outbound.OutViewResult;

@Path("/results")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ServicesREST
{
	@GET
	@Path("/getallorders")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutViewChemical> getAllOrders()
	{
		return DataManager.getInstance().getAllOrdersForView();
	}
	
	@GET
	@Path("/getcomponents/{orderid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutViewComponent> getComponentsByOrder(@PathParam("orderid") String orderid)
	{
		List<OutViewComponent> returnValue = new ArrayList<OutViewComponent>();
		try
		{
			int id = Integer.parseInt(orderid);
			returnValue = DataManager.getInstance().getComponentsForView(id);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return returnValue;
	}
	
//	@GET
//	@Path("/getgroups/{weightid}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<OutViewComponent> getGroupsByWeight(@PathParam("weightid") String weightid)
//	{
//		List<OutViewComponent> returnValue = new ArrayList<OutViewComponent>();
//		try
//		{
//			int id = Integer.parseInt(weightid);
//			returnValue = DataManager.getInstance().getComponentsForView(id);
//		}
//		catch (Exception e)
//		{
//			System.out.println(e.getMessage());
//		}
//
//		return returnValue;
//		
//		
//		
//		try
//		{
//			HibernateUtil.beginTransaction();
//			return DAOManager.getInstance().getGroupDAO().findGroupsByWeight(code);
//		}
//		finally
//		{
//			HibernateUtil.rollbackTransaction();
//		}
//	}
	
	@GET
	@Path("/getresult/{orderid}/{componentid}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutViewResult> getResultByOrderComponent(@PathParam("orderid") String orderid, @PathParam("componentid") String componentid)
	{
		List<OutViewResult> returnValue = new ArrayList<OutViewResult>();
		try
		{
			int ordId = Integer.parseInt(orderid);
			int cmpId = Integer.parseInt(componentid);
			returnValue = DataManager.getInstance().getResultsForView(ordId, cmpId);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

		return returnValue;
	}

	@POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(String jsonString) {
    	ObjectMapper mapper = new ObjectMapper();
    	UpdateResult result = null;
    	try
		{
    		result = mapper.readValue(jsonString, UpdateResult.class);
    		DAOManager.getInstance().getResultDAO().updateResult(result);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
    	
        return Response.status(200).build();
    }
}