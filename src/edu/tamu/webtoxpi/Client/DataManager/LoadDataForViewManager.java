package edu.tamu.webtoxpi.Client.DataManager;

import java.util.ArrayList;
import java.util.List;

import edu.tamu.webtoxpi.Server.Models.Classes.Casregistrynumbers;
import edu.tamu.webtoxpi.Server.Models.Classes.Chemicals;
import edu.tamu.webtoxpi.Server.Models.Classes.Components;
import edu.tamu.webtoxpi.Server.Models.Classes.Groups;
import edu.tamu.webtoxpi.Server.Models.Classes.Sources;
import edu.tamu.webtoxpi.Server.Models.Classes.Types;
import edu.tamu.webtoxpi.Server.Models.Classes.Weights;
import edu.tamu.webtoxpi.Server.Models.DAO.DAOManager;
import edu.tamu.webtoxpi.Server.Models.DAO.Util.HibernateUtil;
import edu.tamu.webtoxpi.Server.Outbound.OutViewCASRN;
import edu.tamu.webtoxpi.Server.Outbound.OutViewChemical;
import edu.tamu.webtoxpi.Server.Outbound.OutViewComponent;
import edu.tamu.webtoxpi.Server.Outbound.OutViewGroup;
import edu.tamu.webtoxpi.Server.Outbound.OutViewSources;
import edu.tamu.webtoxpi.Server.Outbound.OutViewType;
import edu.tamu.webtoxpi.Server.Outbound.OutViewWeight;

public class LoadDataForViewManager
{
	private static LoadDataForViewManager instance = null;
	
	private LoadDataForViewManager()
	{
	}

	public static LoadDataForViewManager getInstance()
	{
		if (instance == null)
		{
			synchronized (LoadDataForViewManager.class)
			{
				if (instance == null)
				{
					instance = new LoadDataForViewManager();
				}
			}
		}
		return instance;
	}
	
	public List<OutViewSources> getAllSourcesForView()
	{
		List<OutViewSources> returnSources = new ArrayList<OutViewSources>();
		try
		{
			HibernateUtil.beginTransaction();
			List<Sources> sources = DAOManager.getInstance().getSourceDAO().findAll(Sources.class);

			for (Sources source : sources)
			{
				returnSources.add(new OutViewSources(source.getId(), source.getCode(), source.getName()));
			}

		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}

		return returnSources;
	}
	
	public List<OutViewCASRN> getAllCASRNSForView()
	{
		List<OutViewCASRN> returnCASRNS = new ArrayList<OutViewCASRN>();
		try
		{
			HibernateUtil.beginTransaction();
			List<Casregistrynumbers> casrns = DAOManager.getInstance().getCasregistrynumberDAO().findAll(Casregistrynumbers.class);

			for (Casregistrynumbers casrn : casrns)
			{
				returnCASRNS.add(new OutViewCASRN(casrn.getId(), casrn.getCode(), casrn.getName()));
			}

		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}

		return returnCASRNS;
	}
	
	public List<OutViewChemical> getAllChemicalsForView()
	{
		List<OutViewChemical> returnChemicals = new ArrayList<OutViewChemical>();
		try
		{
			HibernateUtil.beginTransaction();
			List<Chemicals> chemicals = DAOManager.getInstance().getChemicalDAO().findAll(Chemicals.class);

			for (Chemicals chemical : chemicals)
			{
				returnChemicals.add(new OutViewChemical(chemical.getId(), chemical.getCode()));
			}

		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}

		return returnChemicals;
	}
	
	public List<OutViewWeight> getAllWeightsForView()
	{
		List<OutViewWeight> returnWeights = new ArrayList<OutViewWeight>();
		try
		{
			HibernateUtil.beginTransaction();
			List<Weights> weights = DAOManager.getInstance().getWeightDAO().findAll(Weights.class);

			for (Weights weight : weights)
			{
				returnWeights.add(new OutViewWeight(weight.getId(), weight.getCode(), weight.getName()));
			}

		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}

		return returnWeights;
	}
	
	public List<OutViewGroup> getAllGroupsForView()
	{
		List<OutViewGroup> returnGroups = new ArrayList<OutViewGroup>();
		try
		{
			HibernateUtil.beginTransaction();
			List<Groups> groups = DAOManager.getInstance().getGroupDAO().findAll(Groups.class);

			for (Groups group : groups)
			{
				returnGroups.add(new OutViewGroup(group.getId(), group.getCode(), group.getName()));
			}
		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}

		return returnGroups;
	}
	
	public List<OutViewType> getAllTypesForView()
	{
		List<OutViewType> returnTypes = new ArrayList<OutViewType>();
		try
		{
			HibernateUtil.beginTransaction();
			List<Types> types = DAOManager.getInstance().getTypeDAO().findAll(Types.class);

			for (Types type : types)
			{
				returnTypes.add(new OutViewType(type.getId(), type.getCode(), type.getName()));
			}

		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}

		return returnTypes;
	}
	
	public List<OutViewComponent> getAllComponentsForView()
	{
		List<OutViewComponent> returnComponents = new ArrayList<OutViewComponent>();
		try
		{
			HibernateUtil.beginTransaction();
			List<Components> components = DAOManager.getInstance().getComponentDAO().findAll(Components.class);

			for (Components component : components)
			{
				returnComponents.add(new OutViewComponent(component.getId(), component.getCode(), component.getName()));
			}

		}
		finally
		{
			HibernateUtil.rollbackTransaction();
		}

		return returnComponents;
	}
}
