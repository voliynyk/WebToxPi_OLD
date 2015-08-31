package edu.tamu.webtoxpi.Server.Outbound;

public class OutViewComponent
{
	private int componentId;
	private String unitCode;
	private String componentCode;
	private String componentName;
	private String typeCode;
	private String groupCode;
	private String weightCode;
	
	public OutViewComponent()
	{
	}
	
	public OutViewComponent(int componentId, String componentCode, String componentName)
	{
		this.componentId = componentId;
		this.componentCode = componentCode;
		this.componentName = componentName;
	}
	
	public OutViewComponent(int componentId, String unitCode, String componentCode, String typeCode, String groupCode, String weightCode)
	{
		this.componentId = componentId;
		this.unitCode = unitCode;
		this.componentCode = componentCode;
		this.typeCode = typeCode;
		this.groupCode = groupCode;
		this.weightCode = weightCode;
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
	
	public String getComponentName()
	{
		return componentName;
	}

	public void setComponentName(String componentName)
	{
		this.componentName = componentName;
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
