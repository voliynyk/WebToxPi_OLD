package edu.tamu.webtoxpi.Client.DataManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import edu.tamu.webtoxpi.Server.Models.Classes.Components;
import edu.tamu.webtoxpi.Server.Models.Classes.Orders;
import edu.tamu.webtoxpi.Server.Models.Classes.Casregistrynumbers;
import edu.tamu.webtoxpi.Server.Models.Classes.Chemicals;
import edu.tamu.webtoxpi.Server.Models.Classes.Sources;
import edu.tamu.webtoxpi.Server.Models.Classes.Results;
import edu.tamu.webtoxpi.Server.Models.Classes.Weights;
import edu.tamu.webtoxpi.Server.Models.Classes.Groups;
import edu.tamu.webtoxpi.Server.Models.Classes.Types;
import edu.tamu.webtoxpi.Server.Models.Classes.Componentsources;
import edu.tamu.webtoxpi.Server.Models.DAO.DAOManager;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.HibernateUtil;
import edu.tamu.webtoxpi.Server.Outbound.OutData;
import edu.tamu.webtoxpi.Server.Security.Auth;

public class DataManager
{
	public static List<Results> getResultsByComponentCode(String code)
	{
		return DAOManager.getInstance().getResultDAO().findResultsByComponentCode(code);
	}
	
	public static List<Results> getResults(String casrn, String chemical, String component)
	{
		return DAOManager.getInstance().getResultDAO().findResults(casrn, chemical, component);
	}
	
	public static List<Components> getComponents(String casrn, String chemical, String component)
	{
		return DAOManager.getInstance().getComponentDAO().findComponents(casrn, chemical, component);
	}
	
	public static List<Orders> getOrders(String casrn, String chemical, String component)
	{
		return DAOManager.getInstance().getOrderDAO().findOrders(casrn, chemical, component);
	}
	
	public static OutData getSearchResult(String casrn, String chemical, String component)
	{
		OutData returnValue = new OutData();
		
		try
		{
			HibernateUtil.beginTransaction();

			List<Orders> orders = DAOManager.getInstance().getOrderDAO().findOrders(casrn, chemical, component);
			returnValue.getOrdersOrder().addAll(orders);
			
			List<String> components = new ArrayList<String>();
			if (StringUtils.isNotBlank(component))
			{
				components = Arrays.asList(component.split(","));
			}
			
			for (Orders order : orders)
			{
				HashMap<Components, Results> currentOrder = returnValue.getResultsMap().get(order);
				if (null == currentOrder)
				{
					currentOrder = new HashMap<Components, Results>();
					returnValue.getResultsMap().put(order, currentOrder);
				}

				for (Results result : order.getResultses())
				{
					if (StringUtils.isBlank(component) || components.contains(result.getComponents().getCode()))
					{
						if (!returnValue.getComponentsOrder().contains(result.getComponents()))
						{
							returnValue.getComponentsOrder().add(result.getComponents());
						}
						Results currentComponent = currentOrder.get(result.getComponents());
						if (null == currentComponent)
						{
							currentOrder.put(result.getComponents(), result);
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}

		
		return returnValue;
	}
	
	public static String getJSON(String casrn, String chemical, String component)
	{
		OutData outData = getSearchResult(casrn, chemical, component);
		
		JSONArray list = new JSONArray();
		try
		{
			for (Orders order : outData.getOrdersOrder())
			{
				HashMap<Components, Results> components = outData.getResultsMap().get(order);
				
				JSONObject obj = new JSONObject();
				//obj.put("orderid", order.getId());
				obj.put("reservedFieldSource", order.getSources().getCode());
				obj.put("reservedFieldCASRN", order.getCasregistrynumbers().getCode());
				obj.put("reservedFieldChemical", order.getChemicals().getCode());
				for(Components currentComp : outData.getComponentsOrder())
				{
					String strValue = null;
					Results result = components.get(currentComp);
					if (result != null)
					{
						strValue = result.getStrresult();
					}
					if (StringUtils.isBlank(strValue))
					{
						strValue = "N/A";
					}
					obj.put(currentComp.getCode(), strValue);
				}
				list.put(obj);
			}
		}

		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}	
		
		return list.toString();
	}
	public static List<Weights> getWeights()
	{
		try
		{
			HibernateUtil.beginTransaction();
			return DAOManager.getInstance().getWeightDAO().findAll(Weights.class);
		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}
	}
	public static List<Groups> getGroups()
	{
		try
		{
			HibernateUtil.beginTransaction();
			return DAOManager.getInstance().getGroupDAO().findAll(Groups.class);
		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}
	}
	public static List<Groups> getGroups(String code)
	{
		try
		{
			HibernateUtil.beginTransaction();
			return DAOManager.getInstance().getGroupDAO().findGroupsByWeight(code);
		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}
	}
	
//	public List<OutViewComponent> getComponentsForView(int orderId)
//	{
//		List<OutViewComponent> returnComponents = new ArrayList<OutViewComponent>();
//		try
//		{
//			HibernateUtil.beginTransaction();
//			Orders order = DAOManager.getInstance().getOrderDAO().findByID(Orders.class, orderId);
//			if (order != null)
//			{
//				for (Results result : order.getResultses())
//				{
//					// TODO get value based on Component unit
//					if (StringUtils.isNotBlank(result.getStrresult()))
//					{
//						Components component = result.getComponents();
//						returnComponents.add(new OutViewComponent(component.getId(), component.getUnits().getCode(), component.getCode(), component.getTypes().getCode(),
//								component.getTypes().getGroups().getCode(), component.getTypes().getGroups().getWeights().getCode()));
//					}
//				}
//			}
//
//		}
//		finally
//		{
//			HibernateUtil.rollbackTransaction();
//		}
//
//		return returnComponents;
//	}
	public static List<Types> getTypes()
	{
		try
		{
			HibernateUtil.beginTransaction();
			return DAOManager.getInstance().getTypeDAO().findAll(Types.class);
		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}
	}
	public static List<Componentsources> getComponentSources()
	{
		try
		{
			HibernateUtil.beginTransaction();
			return DAOManager.getInstance().getComponentsourceDAO().findAll(Componentsources.class);
		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}
	}	
	public static List<Components> getComponents()
	{
		try
		{
			HibernateUtil.beginTransaction();
			return DAOManager.getInstance().getComponentDAO().findAll(Components.class);
		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}
	}
	public static List<Sources> getChSources()
	{
		try
		{
			HibernateUtil.beginTransaction();
			return DAOManager.getInstance().getSourceDAO().findAll(Sources.class);
		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}
	}
	public static List<Casregistrynumbers> getCasregistrynumbers()
	{
		try
		{
			HibernateUtil.beginTransaction();
			return DAOManager.getInstance().getCasregistrynumberDAO().findAll(Casregistrynumbers.class);
		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}
	}
	public static List<Chemicals> getChemicals()
	{
		try
		{
			HibernateUtil.beginTransaction();
			return DAOManager.getInstance().getChemicalDAO().findAll(Chemicals.class);
		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}
	}
	public static void newResultsForChemical(String sCasrn, String sChemicalSource, String sChemical)
	{
		try
		{
			HibernateUtil.beginTransaction();
			Casregistrynumbers casrn = DAOManager.getInstance().getCasregistrynumberDAO().findExistOrCreateNewCASRN(sCasrn);
			Sources chemicalSource = DAOManager.getInstance().getSourceDAO().findExistOrCreateNewSource(sChemicalSource);
			Chemicals chemical = DAOManager.getInstance().getChemicalDAO().findExistOrCreateNewChemical(sChemical);
				
			for (Components component : (List<Components>)DAOManager.getInstance().getComponentDAO().findAll(Components.class))
			{
				Orders order = DAOManager.getInstance().getOrderDAO().findExistOrCreateNewOrder(Auth.getCurrentUser(), "", chemicalSource, casrn, chemical);
				DAOManager.getInstance().getResultDAO().findExistOrCreateNewResult(order, component, null);
			}
			HibernateUtil.commitTransaction();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			HibernateUtil.rollbackTransaction();
		}
		
	}
}