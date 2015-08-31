package edu.tamu.webtoxpi.Server.Outbound;

public class OutViewCASRN
{
	private int casrnId;
	private String casrnCode;
	
	public OutViewCASRN(int casrnId, String casrnCode)
	{
		this.casrnId = casrnId;
		this.casrnCode = casrnCode;
	}

	public int getCasrnId()
	{
		return casrnId;
	}

	public void setCasrnId(int casrnId)
	{
		this.casrnId = casrnId;
	}

	public String getCasrnCode()
	{
		return casrnCode;
	}

	public void setCasrnCode(String casrnCode)
	{
		this.casrnCode = casrnCode;
	}
}
