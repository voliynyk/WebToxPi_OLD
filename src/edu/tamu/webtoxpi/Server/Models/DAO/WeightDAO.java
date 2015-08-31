package edu.tamu.webtoxpi.Server.Models.DAO;

import java.util.List;
import edu.tamu.webtoxpi.Server.Models.Classes.Weights;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.GenericDAOImpl;
import edu.tamu.webtoxpi.Server.Models.DAOIntefaces.IWeightDAO;
import edu.tamu.webtoxpi.Server.Security.Auth;

public class WeightDAO extends GenericDAOImpl<Weights, Integer> implements IWeightDAO
{


	public Weights findExistOrCreateNewWeight(int weightValue)
	{
		Weights result = null;
		List<Weights> weightList = findAll(Weights.class);
		for (Weights weight : weightList)
		{
			if (weightValue == weight.getWeight())
			{
				return weight;
			}
		}
		result = new Weights(0, Auth.getCurrentUser(), Integer.toString(weightValue), weightValue, Auth.getCurrentDate());
		save(result);
		return result;
	}
}
