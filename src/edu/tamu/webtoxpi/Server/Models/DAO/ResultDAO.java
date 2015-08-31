package edu.tamu.webtoxpi.Server.Models.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import edu.tamu.webtoxpi.Server.InBound.UpdateResult;
import edu.tamu.webtoxpi.Server.Models.Classes.Components;
import edu.tamu.webtoxpi.Server.Models.Classes.Orders;
import edu.tamu.webtoxpi.Server.Models.Classes.Results;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.GenericDAOImpl;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.HibernateUtil;
import edu.tamu.webtoxpi.Server.Models.DAOIntefaces.IResultDAO;
import edu.tamu.webtoxpi.Server.Security.Auth;

public class ResultDAO extends GenericDAOImpl<Results, Integer> implements IResultDAO
{

	public Results checkExistResult(int order, int component)
	{
		Results result = null;
		try
		{
			Query query = HibernateUtil.getSession().getNamedQuery("checkExistResult").setInteger("compId", component).setInteger("orderId", order);
			List<Results> results = query.list();
			if (results != null && results.size() > 0)
			{
				result = results.get(0);
			}
		}
		catch (Exception ex)
		{

		}
		return result;
	}

	public List<Results> findResultsByComponentCode(String code)
	{
		List<Results> result = new ArrayList();
		if (StringUtils.isNotBlank(code))
		{
			try
			{
				Query query = HibernateUtil.getSession().getNamedQuery("findResultsByComponentCode").setString("code", code);
				List<Results> results = query.list();
				if (results != null)
				{
					return results;
				}
	
			}
			catch (Exception ex)
			{

			}
		}
		return result;
	}
	
	public List<Results> findResults(String casrn, String chemical, String component)
	{
		List<Results> result = new ArrayList();
		Criteria criteria = HibernateUtil.getSession().createCriteria(Results.class, "result");
		
		if (StringUtils.isNotBlank(casrn) || StringUtils.isNotBlank(chemical))
		{
			criteria = criteria.createCriteria("result.orders" , "order");
		}
		
		if (StringUtils.isNotBlank(casrn))
		{
			criteria = criteria.createCriteria("order.casregistrynumbers" , "casregistrynumber").
					add(Restrictions.in("code", casrn.split(",")));
		}
		
		if (StringUtils.isNotBlank(chemical))
		{
			criteria = criteria.createCriteria("order.chemicals" , "chemical").
								add(Restrictions.in("code", chemical.split(",")));
		}
		
		if (StringUtils.isNotBlank(component))
		{
			criteria = criteria.createCriteria("result.components" , "component").
								add(Restrictions.in("code", component.split(",")));
		}
		
		try
		{
			result = criteria.list();
		}
		catch (Exception e)
		{

		}
		
		return result;
	}

	public Results findExistOrCreateNewResult(Orders order, Components component, Object value)
	{
		Results result = null;
		if (order != null && component != null)
		{
			result = checkExistResult(order.getId(), component.getId());
			if (null == result)
			{
				result = new Results(0, component, order, Auth.getCurrentUser(), Auth.getCurrentDate());
				if (value != null)
				{
					// TODO change BigDecimal to Decimal
					result.setStrresult(String.valueOf(value));
					//Numresult(new BigDecimal((Double) value));
				}
				save(result);
			}

		}
		return result;
	}
	
	
	public Results updateResult(UpdateResult inputResult)
	{
		Results result = null;
		if (inputResult != null)
		{
			try
			{
				HibernateUtil.beginTransaction();
				Orders order = DAOManager.getInstance().getOrderDAO().findOrder(inputResult.getSource(), inputResult.getCasrn(), inputResult.getChemical());
				if (order != null)
				{
					Components component = DAOManager.getInstance().getComponentDAO().checkExistComponent(inputResult.getComponent());
					if (component != null)
					{
						Results currentResult = checkExistResult(order.getId(), component.getId());
						if (currentResult != null)
						{
							currentResult.setStrresult(inputResult.getNewValue());
							save(currentResult);
						}
					}
				}
				HibernateUtil.commitTransaction();
			}
			catch (Exception e)
			{
				HibernateUtil.rollbackTransaction();
			}
		}
		return result;
	}
}
