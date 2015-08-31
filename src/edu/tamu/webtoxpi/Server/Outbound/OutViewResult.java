package edu.tamu.webtoxpi.Server.Outbound;

public class OutViewResult
{
	private int resultId;
	private String resultValue;
	private String resultType;

	private int orderId;
	private String sourceCode;
	private String casrnCode;
	private String chemicalCode;
	
	private int componentId;
	private String unitCode;
	private String componentCode;
	private String typeCode;
	private String groupCode;
	private String weightCode;
	
	public OutViewResult()
	{
	}
	
	public OutViewResult(int resultId, String resultValue, String resultType, int orderId, int componentId)
	{
		this.resultId = resultId;
		this.resultValue = resultValue;
		this.resultType = resultType;
		this.orderId = orderId;
		this.componentId = componentId;
	}

	public int getResultId()
	{
		return resultId;
	}

	public void setResultId(int resultId)
	{
		this.resultId = resultId;
	}

	public String getResultValue()
	{
		return resultValue;
	}

	public void setResultValue(String resultValue)
	{
		this.resultValue = resultValue;
	}
	
	public String getResultType()
	{
		return resultType;
	}

	public void setResultType(String resultType)
	{
		this.resultType = resultType;
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

	public int getComponentId()
	{
		return componentId;
	}

	public void setComponentId(int componentId)
	{
		this.componentId = componentId;
	}

	public String getUnitCode()
	{
		return unitCode;
	}

	public void setUnitCode(String unitCode)
	{
		this.unitCode = unitCode;
	}

	public String getComponentCode()
	{
		return componentCode;
	}

	public void setComponentCode(String componentCode)
	{
		this.componentCode = componentCode;
	}

	public String getTypeCode()
	{
		return typeCode;
	}

	public void setTypeCode(String typeCode)
	{
		this.typeCode = typeCode;
	}

	public String getGroupCode()
	{
		return groupCode;
	}

	public void setGroupCode(String groupCode)
	{
		this.groupCode = groupCode;
	}

	public String getWeightCode()
	{
		return weightCode;
	}

	public void setWeightCode(String weightCode)
	{
		this.weightCode = weightCode;
	}
}
