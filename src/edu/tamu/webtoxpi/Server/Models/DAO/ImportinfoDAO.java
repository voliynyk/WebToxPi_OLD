package edu.tamu.webtoxpi.Server.Models.DAO;

import java.util.List;
import org.apache.commons.lang3.StringUtils;
import edu.tamu.webtoxpi.Server.Models.Classes.Importinfo;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.GenericDAOImpl;
import edu.tamu.webtoxpi.Server.Models.DAOIntefaces.IImportInfoDAO;
import edu.tamu.webtoxpi.Server.Security.Auth;

public class ImportinfoDAO extends GenericDAOImpl<Importinfo, Integer> implements IImportInfoDAO
{
	
	public Importinfo findExistOrCreateNewImport(String originalFileName, String fileName)
	{
		Importinfo result = null;
		if (StringUtils.isNotBlank(originalFileName))
		{
			List<Importinfo> importList = findAll(Importinfo.class);
			for (Importinfo importInfo : importList)
			{
				if (originalFileName.equals(importInfo.getFilename()))
				{
					return importInfo;
				}
			}
			result = new Importinfo(0, Auth.getCurrentUser(), Auth.getCurrentDate(), "Note", originalFileName);
			save(result);
		}
		return result;
	}
}
