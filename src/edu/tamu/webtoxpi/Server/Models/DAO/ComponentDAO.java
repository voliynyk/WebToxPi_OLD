package edu.tamu.webtoxpi.Server.Models.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

import edu.tamu.webtoxpi.Server.Models.Classes.Components;
import edu.tamu.webtoxpi.Server.Models.Classes.Componentsources;
import edu.tamu.webtoxpi.Server.Models.Classes.Types;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.GenericDAOImpl;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.HibernateUtil;
import edu.tamu.webtoxpi.Server.Models.DAOIntefaces.IComponentDAO;
import edu.tamu.webtoxpi.Server.Security.Auth;

public class ComponentDAO extends GenericDAOImpl<Components, Integer> implements IComponentDAO
{
	public Components checkExistComponent(String code, Types type, Componentsources componentSource)
	{
		Components result = null;
		if (StringUtils.isNotBlank(code) && type != null)
		{
			try
			{
				int compSource = -1;
				if (componentSource != null)
				{
					compSource = componentSource.getId();
				}
				Query query = HibernateUtil.getSession().getNamedQuery("checkExistComponent").setString("compCode", code).setInteger("typeId", type.getId()).setInteger("compSource", compSource);
				List<Components> results = query.list();
				if (results != null && results.size() > 0)
				{
					result = results.get(0);
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		return result;
	}
	
	public Components checkExistComponent(String code)
	{
		Components result = null;
		if (StringUtils.isNotBlank(code))
		{
			try
			{

				Query query = HibernateUtil.getSession().getNamedQuery("checkExistComponentByCode").setString("compCode", code);
				List<Components> results = query.list();
				if (results != null && results.size() > 0)
				{
					result = results.get(0);
				}
			}
			catch (Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
		return result;
	}

	public Components findExistOrCreateNewComponent(String code, Types type, Componentsources componentSource)
	{
		Components result = null;
		if (StringUtils.isNotBlank(code) && type != null)
		{
			result = checkExistComponent(code, type, componentSource);
			if (null == result)
			{
				result = new Components(0, type, Auth.getDefaultUnit(), Auth.getCurrentUser(), code, Auth.getCurrentDate());
				//type.getComponentses().add(result);
				if (componentSource != null)
				{
					//componentSource.getComponentses().add(result);
					result.setComponentsources(componentSource);
				}
				save(result);
			}
		}
		return result;
	}
	
	public List<Components> findComponents(String casrn, String chemical, String component)
	{
		List<Components> result = new ArrayList();
		Criteria criteria = HibernateUtil.getSession().createCriteria(Components.class, "component");
		
		
		if (StringUtils.isNotBlank(component))
		{
			criteria = criteria.createCriteria("result.components", "component").
								add(Restrictions.in("code", component.split(",")));
		}
		if (StringUtils.isNotBlank(casrn) || StringUtils.isNotBlank(chemical))
		{
			criteria = criteria.createCriteria("component.resultses", "result").
								createCriteria("result.orders" , "order");
		}
		
		if (StringUtils.isNotBlank(casrn))
		{
			criteria = criteria.createCriteria("order.casregistrynumbers", "casregistrynumber").
								add(Restrictions.in("code", casrn.split(",")));
		}
		
		if (StringUtils.isNotBlank(chemical))
		{
			criteria = criteria.createCriteria("order.chemicals" , "chemical").
								add(Restrictions.in("code", chemical.split(",")));
		}
		
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
