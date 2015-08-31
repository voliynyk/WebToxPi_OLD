package edu.tamu.webtoxpi.Server.Models.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import edu.tamu.webtoxpi.Server.Models.Classes.Casregistrynumbers;
import edu.tamu.webtoxpi.Server.Models.Classes.Chemicals;
import edu.tamu.webtoxpi.Server.Models.Classes.Importinfo;
import edu.tamu.webtoxpi.Server.Models.Classes.Orders;
import edu.tamu.webtoxpi.Server.Models.Classes.Sources;
import edu.tamu.webtoxpi.Server.Models.Classes.Users;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.GenericDAOImpl;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.HibernateUtil;
import edu.tamu.webtoxpi.Server.Models.DAOIntefaces.IOrderDAO;
import edu.tamu.webtoxpi.Server.Security.Auth;

public class OrderDAO extends GenericDAOImpl<Orders, Integer> implements IOrderDAO
{
	public Orders checkExistResult(int casrnId, int chemId, int sourceId)
	{
		Orders result = null;
		try
		{
			Query query = HibernateUtil.getSession().getNamedQuery("checkExistOrder").setInteger("casrnId", casrnId).setInteger("chemId", chemId).setInteger("sourceId", sourceId);
			List<Orders> orders = query.list();
			if (orders != null && orders.size() > 0)
			{
				result = orders.get(0);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	public Orders findExistOrCreateNewOrder(Users user, String fileName, Sources source, Casregistrynumbers casrne, Chemicals chemical)
	{
		Orders result = null;
		if (user != null && source != null && casrne != null && chemical != null)
		{			
			result = checkExistResult(casrne.getId(), chemical.getId(), source.getId());
			if (null == result)
			{
				result = new Orders(0, casrne, chemical, source, user, 0, Auth.getCurrentDate());
				if (StringUtils.isNotBlank(fileName))
				{
					Importinfo importInfo = DAOManager.getInstance().getImportinfoDAO().findExistOrCreateNewImport(fileName);
				
					if (importInfo != null)
					{
						result.setImportinfo(importInfo);
					}
				}
				save(result);
			}			
		}
		
		return result;
	}
	
	public Orders findOrder(String sourceCode, String casrnCode, String chemCode)
	{
		Orders result = null;
		try
		{
			Query query = HibernateUtil.getSession().getNamedQuery("checkExistOrderByCodes").setString("sourceCode", sourceCode).setString("casrnCode", casrnCode).setString("chemCode", chemCode);
			List<Orders> orders = query.list();
			if (orders != null && orders.size() > 0)
			{
				result = orders.get(0);
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	public List<Orders> findOrders(String casrn, String chemical, String component)
	{
		List<Orders> result = new ArrayList();
		Criteria criteria = HibernateUtil.getSession().createCriteria(Orders.class, "order");

		if (StringUtils.isNotBlank(casrn))
		{
			criteria.createAlias("order.casregistrynumbers" , "casregistrynumber");
			criteria.add(Restrictions.in("casregistrynumber.code", casrn.split(",")));
		}
		if (StringUtils.isNotBlank(chemical))
		{
			criteria.createAlias("order.chemicals", "chemical");
			criteria.add(Restrictions.in("chemical.code", chemical.split(",")));
		}
		if (StringUtils.isNotBlank(component))
		{
			criteria.createAlias("order.resultses", "results");
			criteria.createAlias("results.components", "component");
			criteria.add(Restrictions.in("component.code", component.split(",")));
		}
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		try
		{
			result = criteria.list();
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
		
		return result;
	}
}
