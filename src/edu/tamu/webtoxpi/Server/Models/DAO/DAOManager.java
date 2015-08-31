package edu.tamu.webtoxpi.Server.Models.DAO;

public class DAOManager
{
	private static DAOManager instance = null;

	protected CasregistrynumberDAO casregistrynumberDAO = null;
	protected ChemicalDAO chemicalDAO = null;
	protected ComponentDAO componentDAO = null;
	protected ComponentsourceDAO componentsourceDAO = null;
	protected GroupDAO groupDAO = null;
	protected NoteDAO noteDAO = null;
	protected OrderDAO orderDAO = null;
	protected ResultDAO resultDAO = null;
	protected SearchtemplateDAO searchtemplateDAO = null;
	protected SourceDAO sourceDAO = null;
	protected TypeDAO typeDAO = null;
	protected UserDAO userDao = null;
	protected ViewtemplateDAO viewtemplateDAO = null;
	protected WeightDAO weightDAO = null;
	protected ImportinfoDAO importinfoDAO = null;
	protected UnitDAO unitDAO = null;

	private DAOManager()
	{
	}

	public static DAOManager getInstance()
	{
		if (instance == null)
		{
			synchronized (DAOManager.class)
			{
				if (instance == null)
				{
					instance = new DAOManager();
				}
			}
		}
		return instance;
	}

	public CasregistrynumberDAO getCasregistrynumberDAO()
	{
		if (this.casregistrynumberDAO == null)
		{
			this.casregistrynumberDAO = new CasregistrynumberDAO();
		}
		return this.casregistrynumberDAO;
	}
	
	public ChemicalDAO getChemicalDAO()
	{
		if (this.chemicalDAO == null)
		{
			this.chemicalDAO = new ChemicalDAO();
		}
		return this.chemicalDAO;
	}

	
	public ComponentDAO getComponentDAO()
	{
		if (this.componentDAO == null)
		{
			this.componentDAO = new ComponentDAO();
		}
		return this.componentDAO;
	}

	
	public ComponentsourceDAO getComponentsourceDAO()
	{
		if (this.componentsourceDAO == null)
		{
			this.componentsourceDAO = new ComponentsourceDAO();
		}
		return this.componentsourceDAO;
	}

	
	public GroupDAO getGroupDAO()
	{
		if (this.groupDAO == null)
		{
			this.groupDAO = new GroupDAO();
		}
		return this.groupDAO;
	}

	
	public NoteDAO getNoteDAO()
	{
		if (this.noteDAO == null)
		{
			this.noteDAO = new NoteDAO();
		}
		return this.noteDAO;
	}

	
	public OrderDAO getOrderDAO()
	{
		if (this.orderDAO == null)
		{
			this.orderDAO = new OrderDAO();
		}
		return this.orderDAO;
	}

	
	public ResultDAO getResultDAO()
	{
		if (this.resultDAO == null)
		{
			this.resultDAO = new ResultDAO();
		}
		return this.resultDAO;
	}

	
	public SearchtemplateDAO getSearchtemplateDAO()
	{
		if (this.searchtemplateDAO == null)
		{
			this.searchtemplateDAO = new SearchtemplateDAO();
		}
		return this.searchtemplateDAO;
	}

	
	public SourceDAO getSourceDAO()
	{
		if (this.sourceDAO == null)
		{
			this.sourceDAO = new SourceDAO();
		}
		return this.sourceDAO;
	}

	
	public TypeDAO getTypeDAO()
	{
		if (this.typeDAO == null)
		{
			this.typeDAO = new TypeDAO();
		}
		return this.typeDAO;
	}

	
	public UserDAO getUserDao()
	{
		if (this.userDao == null)
		{
			this.userDao = new UserDAO();
		}
		return this.userDao;
	}

	
	public ViewtemplateDAO getViewtemplateDAO()
	{
		if (this.viewtemplateDAO == null)
		{
			this.viewtemplateDAO = new ViewtemplateDAO();
		}
		return this.viewtemplateDAO;
	}

	public WeightDAO getWeightDAO()
	{
		if (this.weightDAO == null)
		{
			this.weightDAO = new WeightDAO();
		}
		return this.weightDAO;
	}
	
	public ImportinfoDAO getImportinfoDAO()
	{
		if (this.importinfoDAO == null)
		{
			this.importinfoDAO = new ImportinfoDAO();
		}
		return this.importinfoDAO;
	}
	
	public UnitDAO getUnitDAO()
	{
		if (this.unitDAO == null)
		{
			this.unitDAO = new UnitDAO();
		}
		return this.unitDAO;
	}
}
