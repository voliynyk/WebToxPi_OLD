package edu.tamu.webtoxpi.Server.Models.DAO;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Query;

import edu.tamu.webtoxpi.Server.Models.Classes.Groups;
import edu.tamu.webtoxpi.Server.Models.Classes.Results;
import edu.tamu.webtoxpi.Server.Models.Classes.Weights;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.GenericDAOImpl;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.HibernateUtil;
import edu.tamu.webtoxpi.Server.Models.DAOIntefaces.IGroupDAO;
import edu.tamu.webtoxpi.Server.Security.Auth;

public class GroupDAO extends GenericDAOImpl<Groups, Integer> implements IGroupDAO
{

	public Groups findExistOrCreateNewGroup(String code, Weights weight, Color color)
	{
		Groups result = null;
		if (StringUtils.isNotBlank(code) && weight != null)
		{
			List<Groups> groupsList = findAll(Groups.class);
			for (Groups group : groupsList)
			{
				if (code.equals(group.getCode()) && weight.getId() == group.getWeights().getId())
				{
					return group;
				}
			}
			
			result = new Groups(0, Auth.getCurrentUser(), weight, code, Auth.getCurrentDate());
			//weight.getGroupses().add(result);
			//TODO Implement Color <-> int
			if (color != null)
			{
				result.setColor(color.getAlpha());				
			}
			save(result);
		}
		

		return result;
	}
	
	public List<Groups> findGroupsByWeight(String code)
	{		
		if (StringUtils.isNotBlank(code))
		{
			try
			{
				Query query = HibernateUtil.getSession().getNamedQuery("findGroupsByWeight").setString("code", code);
				List<Groups> groups = query.list();
				if (groups != null)
				{
					return groups;
				}
	
			}
			catch (Exception ex)
			{

			}
		}
		return new ArrayList();
	}
}
