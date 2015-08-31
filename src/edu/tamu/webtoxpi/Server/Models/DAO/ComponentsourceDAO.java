package edu.tamu.webtoxpi.Server.Models.DAO;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import edu.tamu.webtoxpi.Server.Models.Classes.Componentsources;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.GenericDAOImpl;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.HibernateUtil;
import edu.tamu.webtoxpi.Server.Models.DAOIntefaces.IComponentsourceDAO;
import edu.tamu.webtoxpi.Server.Security.Auth;

public class ComponentsourceDAO extends GenericDAOImpl<Componentsources, Integer> implements IComponentsourceDAO
{

	public Componentsources checkExistComponentSource(String code)
	{
		Componentsources result = null;
		if (StringUtils.isNotBlank(code))
		{
			try
			{
				Query query = HibernateUtil.getSession().getNamedQuery("checkExistComponentSource").setString("code", code);
				List<Componentsources> results = query.list();
				if (results != null && results.size() > 0)
				{
					result = results.get(0);
				}
			}
			catch (Exception ex)
			{

			}
		}
		return result;

	}
	
	public Componentsources findExistOrCreateNewComponentSources(String code)
	{
		Componentsources result = null;
		if (StringUtils.isNotBlank(code))
		{
			result = checkExistComponentSource(code);
			if (null == result)
			{
				result = new Componentsources(0, Auth.getCurrentUser(), code, Auth.getCurrentDate());
				save(result);
			}
		}
		return result;
	}
}
