package edu.tamu.webtoxpi.Server.Outbound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import edu.tamu.webtoxpi.Server.Models.Classes.*;

public class OutSearchResult
{
	private Sources source;
	private Casregistrynumbers casRegistryNumber;
	private Chemicals chemical;
	private List<Components> components = new ArrayList();
	private HashMap<Components, List<Results>> resultsMap = new HashMap<Components, List<Results>>();

	public Sources getSource()
	{
		return source;
	}
	public void setSource(Sources source)
	{
		this.source = source;
	}
	public Casregistrynumbers getCasRegistryNumber()
	{
		return casRegistryNumber;
	}
	public void setCasRegistryNumber(Casregistrynumbers casRegistryNumber)
	{
		this.casRegistryNumber = casRegistryNumber;
	}
	public Chemicals getChemical()
	{
		return chemical;
	}
	public void setChemical(Chemicals chemical)
	{
		this.chemical = chemical;
	}
	public HashMap<Components, List<Results>> getResultsMap()
	{
		return resultsMap;
	}
	public void setResultsMap(HashMap<Components, List<Results>> resultsMap)
	{
		this.resultsMap = resultsMap;
	}
	public List<Components> getComponents()
	{
		return components;
	}
	public void setComponents(List<Components> components)
	{
		this.components = components;
	}
}
