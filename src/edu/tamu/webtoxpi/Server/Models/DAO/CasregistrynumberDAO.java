package edu.tamu.webtoxpi.Server.Models.DAO;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import edu.tamu.webtoxpi.Server.Models.Classes.Casregistrynumbers;
import edu.tamu.webtoxpi.Server.Models.Classes.Orders;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.GenericDAOImpl;
import edu.tamu.webtoxpi.Server.Models.DAOIntefaces.ICasregistrynumberDAO;
import edu.tamu.webtoxpi.Server.Models.DAOIntefaces.IOrderDAO;
import edu.tamu.webtoxpi.Server.Security.Auth;

public class CasregistrynumberDAO extends GenericDAOImpl<Casregistrynumbers, Integer> implements ICasregistrynumberDAO
{
	public Casregistrynumbers findExistOrCreateNewCASRN(String code)
	{
		Casregistrynumbers result = null;
		if (StringUtils.isNotBlank(code))
		{
			List<Casregistrynumbers> sourcesList = findAll(Casregistrynumbers.class);
			for (Casregistrynumbers casrn : sourcesList)
			{
				if (code.equals(casrn.getCode()))
				{
					return casrn;
				}
			}
			result = new Casregistrynumbers(0, Auth.getCurrentUser(), code, Auth.getCurrentDate());
			save(result);
		}
		return result;
	}
}
