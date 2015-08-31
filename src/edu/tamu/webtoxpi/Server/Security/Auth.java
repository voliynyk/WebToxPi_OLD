package edu.tamu.webtoxpi.Server.Security;

import java.util.Calendar;
import java.util.Date;

import edu.tamu.webtoxpi.Server.Models.Classes.Units;
import edu.tamu.webtoxpi.Server.Models.Classes.Users;
import edu.tamu.webtoxpi.Server.Models.DAO.DAOManager;

public class Auth
{
	
	public static Users getCurrentUser()
	{
		return DAOManager.getInstance().getUserDao().findByID(Users.class, 1);
	}
	
	public static Units getDefaultUnit()
	{
		return DAOManager.getInstance().getUnitDAO().findByID(Units.class, 1);
	}
	
	public static Date getCurrentDate()
	{
		return Calendar.getInstance().getTime();
	}
}
