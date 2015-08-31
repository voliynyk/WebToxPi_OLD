package edu.tamu.webtoxpi.Server.Outbound;

public class OutViewChemical
{
	private int chemicalId;
	private int orderId;
	private String sourceCode;
	private String casrnCode;
	private String chemicalCode;
	
	public OutViewChemical()
	{}
	
	public OutViewChemical(int chemicalId, String chemicalCode)
	{
		this.chemicalId = chemicalId;
		this.chemicalCode = chemicalCode;
	}
	
	public OutViewChemical(int orderId, String sourceCode, String casrnCode, String chemicalCode)
	{
		this.orderId = orderId;
		this.sourceCode = sourceCode;
		this.casrnCode = casrnCode;
		this.chemicalCode = chemicalCode;
	}
	
	public int getOrderId()
	{
		return orderId;
	}
	public void setOrderId(int orderId)
	{
		this.orderId = orderId;
	}
	public String getSourceCode()
	{
		return sourceCode;
	}
	public void setSourceCode(String sourceCode)
	{
		this.sourceCode = sourceCode;
	}
	public String getCasrnCode()
	{
		return casrnCode;
	}
	public void setCasrnCode(String casrnCode)
	{
		this.casrnCode = casrnCode;
	}
	public String getChemicalCode()
	{
		return chemicalCode;
	}
	public void setChemicalCode(String chemicalCode)
	{
		this.chemicalCode = chemicalCode;
	}

	public int getChemicalId()
	{
		return chemicalId;
	}

	public void setChemicalId(int chemicalId)
	{
		this.chemicalId = chemicalId;
	}
}
