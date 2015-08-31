package edu.tamu.webtoxpi.Server.Outbound;

public class OutViewSources
{
	private int sourceId;
	private String sourceCode;
	
	public OutViewSources(int sourceId, String sourceCode)
	{
		this.sourceId = sourceId;
		this.sourceCode = sourceCode;
	}

	public int getSourceId()
	{
		return sourceId;
	}

	public void setSourceId(int sourceId)
	{
		this.sourceId = sourceId;
	}

	public String getSourceCode()
	{
		return sourceCode;
	}

	public void setSourceCode(String sourceCode)
	{
		this.sourceCode = sourceCode;
	}
}
