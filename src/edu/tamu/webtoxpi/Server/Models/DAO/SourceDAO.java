package edu.tamu.webtoxpi.Server.Models.DAO;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import edu.tamu.webtoxpi.Server.Models.Classes.Sources;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.GenericDAOImpl;
import edu.tamu.webtoxpi.Server.Models.DAOIntefaces.ISourceDAO;
import edu.tamu.webtoxpi.Server.Security.Auth;

public class SourceDAO extends GenericDAOImpl<Sources, Integer> implements ISourceDAO
{
	
	public Sources findExistOrCreateNewSource(String code)
	{
		Sources result = null;
		if (StringUtils.isNotBlank(code))
		{
			List<Sources> sourcesList = findAll(Sources.class);
			for (Sources source : sourcesList)
			{
				if (code.equals(source.getCode()))
				{
					return source;
				}
			}
			result = new Sources(0, Auth.getCurrentUser(), code, Auth.getCurrentDate());
			save(result);
			
		}
		return result;
	}
}