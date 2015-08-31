package edu.tamu.webtoxpi.Server.Services;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import edu.tamu.webtoxpi.Client.DataManager.LoadDataForViewManager;
import edu.tamu.webtoxpi.Server.Outbound.*;


@Path("/loadsetupdata")
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoadDataService
{
	@GET
	@Path("/getallsources")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutViewSources> getAllSources()
	{
		return LoadDataForViewManager.getInstance().getAllSourcesForView();
	}
	
	@GET
	@Path("/getallcasrns")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutViewCASRN> getAllCASRNs()
	{
		return LoadDataForViewManager.getInstance().getAllCASRNSForView();
	}
	
	@GET
	@Path("/getallchemicals")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutViewChemical> getAllChemicals()
	{
		return LoadDataForViewManager.getInstance().getAllChemicalsForView();
	}
	
	@GET
	@Path("/getallweights")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutViewWeight> getAllWeights()
	{
		return LoadDataForViewManager.getInstance().getAllWeightsForView();
	}
	
	@GET
	@Path("/getallgroups")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutViewGroup> getAllGroups()
	{
		return LoadDataForViewManager.getInstance().getAllGroupsForView();
	}
	
	@GET
	@Path("/getalltypes")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutViewType> getAllTypes()
	{
		return LoadDataForViewManager.getInstance().getAllTypesForView();
	}
	
	@GET
	@Path("/getallcomponents")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutViewComponent> getAllComponents()
	{
		return LoadDataForViewManager.getInstance().getAllComponentsForView();
	}
}
