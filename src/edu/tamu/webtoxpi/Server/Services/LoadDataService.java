package edu.tamu.webtoxpi.Server.Services;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import edu.tamu.webtoxpi.Client.DataManager.DataManager;
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
		return DataManager.getInstance().getAllSourcesForView();
	}
	
	@GET
	@Path("/getallcasrns")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutViewCASRN> getAllCASRNs()
	{
		return DataManager.getInstance().getAllCASRNSForView();
	}
	
	@GET
	@Path("/getallchemicals")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutViewChemical> getAllChemicals()
	{
		return DataManager.getInstance().getAllChemicalsForView();
	}
	
	@GET
	@Path("/getallweights")
	@Produces(MediaType.APPLICATION_JSON)
	public List<OutViewWeight> getAllWeights()
	{
		return DataManager.getInstance().getAllWeightsForView();
	}
}
