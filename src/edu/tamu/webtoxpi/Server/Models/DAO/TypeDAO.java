package edu.tamu.webtoxpi.Server.Models.DAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import edu.tamu.webtoxpi.Server.Models.Classes.Groups;
import edu.tamu.webtoxpi.Server.Models.Classes.Types;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.GenericDAOImpl;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.HibernateUtil;
import edu.tamu.webtoxpi.Server.Models.DAOIntefaces.ITypeDAO;
import edu.tamu.webtoxpi.Server.Security.Auth;

public class TypeDAO extends GenericDAOImpl<Types, Integer> implements ITypeDAO
{
	
	public Types findExistOrCreateNewType(String code, Groups group)
	{
		Types result = null;
		if (StringUtils.isNotBlank(code) && group != null)
		{
			List<Types> typesList = findAll(Types.class);
			for (Types type : typesList)
			{
				if (code.equals(type.getCode()) && type.getGroups().getId() == group.getId())
				{
					return type;
				}
			}

			result = new Types(0, group, Auth.getCurrentUser(), code, Auth.getCurrentDate());
			//group.getTypeses().add(result);
			save(result);
		}
		return result;
	}
	public List<Types> findTypesByGroup(String code)
	{		
		if (StringUtils.isNotBlank(code))
		{
			try
			{
				Query query = HibernateUtil.getSession().getNamedQuery("findTypesByGroup").setString("code", code);
				List<Types> types = query.list();
				if (types != null)
				{
					return types;
				}
	
			}
			catch (Exception ex)
			{

			}
		}
		return new ArrayList();
	}
}
