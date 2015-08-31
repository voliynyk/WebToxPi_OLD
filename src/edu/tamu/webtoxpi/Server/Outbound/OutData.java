package edu.tamu.webtoxpi.Server.Outbound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.tamu.webtoxpi.Server.Models.Classes.*;

public class OutData
{
	private List<Components> componentsOrder = null;
	private List<Orders> ordersOrder = null;
	private HashMap<Orders, HashMap<Components, Results>> resultsMap = null;
	
	public OutData()
	{
		componentsOrder = new ArrayList<Components>();
		ordersOrder = new ArrayList<Orders>();
		resultsMap = new HashMap<Orders, HashMap<Components, Results>>();
	}
	
	public List<Components> getComponentsOrder()
	{
		return componentsOrder;
	}
	public void setComponentsOrder(List<Components> componentsOrder)
	{
		this.componentsOrder = componentsOrder;
	}
	public List<Orders> getOrdersOrder()
	{
		return ordersOrder;
	}
	public void setOrdersOrder(List<Orders> ordersOrder)
	{
		this.ordersOrder = ordersOrder;
	}
	public HashMap<Orders, HashMap<Components, Results>> getResultsMap()
	{
		return resultsMap;
	}
	public void setResultsMap(HashMap<Orders, HashMap<Components, Results>> resultsMap)
	{
		this.resultsMap = resultsMap;
	}
}
