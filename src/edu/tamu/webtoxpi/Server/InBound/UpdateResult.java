package edu.tamu.webtoxpi.Server.InBound;

public class UpdateResult
{
	public String source;
	public String casrn;
	public String chemical;
	public String component;
	public String newValue;
	
	public UpdateResult()
	{
		
	}
	
	public UpdateResult(String source, String casrn, String chemical, String component, String newValue)
	{
		this.source = source;
		this.casrn = casrn;
		this.chemical = chemical;
		this.component = component;
		this.newValue = newValue;
	}
	
	public String getSource()
	{
		return source;
	}
	public void setSource(String source)
	{
		this.source = source;
	}
	public String getCasrn()
	{
		return casrn;
	}
	public void setCasrn(String casrn)
	{
		this.casrn = casrn;
	}
	public String getChemical()
	{
		return chemical;
	}
	public void setChemical(String chemical)
	{
		this.chemical = chemical;
	}
	public String getComponent()
	{
		return component;
	}
	public void setComponent(String component)
	{
		this.component = component;
	}
	public String getNewValue()
	{
		return newValue;
	}
	public void setNewValue(String newValue)
	{
		this.newValue = newValue;
	}
}
