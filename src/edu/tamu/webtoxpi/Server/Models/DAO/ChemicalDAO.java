package edu.tamu.webtoxpi.Server.Models.DAO;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import edu.tamu.webtoxpi.Server.Models.Classes.Chemicals;

import edu.tamu.webtoxpi.Server.Models.DAO.Util.GenericDAOImpl;
import edu.tamu.webtoxpi.Server.Models.DAOIntefaces.IChemicalDAO;
import edu.tamu.webtoxpi.Server.Security.Auth;

public class ChemicalDAO extends GenericDAOImpl<Chemicals, Integer> implements IChemicalDAO
{
	public Chemicals findExistOrCreateNewChemical(String code)
	{
		Chemicals result = null;
		if (StringUtils.isNotBlank(code))
		{
			List<Chemicals> sourcesList = findAll(Chemicals.class);
			for (Chemicals chemical : sourcesList)
			{
				if (code.equals(chemical.getCode()))
				{
					return chemical;
				}
			}
			result = new Chemicals(0, Auth.getCurrentUser(), code, Auth.getCurrentDate());
			save(result);
		}
		return result;
	}
}
