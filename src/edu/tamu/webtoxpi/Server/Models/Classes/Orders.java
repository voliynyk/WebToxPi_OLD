package edu.tamu.webtoxpi.Server.Models.Classes;
// Generated Aug 26, 2015 7:27:58 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 * Orders generated by hbm2java
 */
@Entity
@Table(name = "orders", schema = "public", uniqueConstraints = @UniqueConstraint(columnNames = { "source_id", "casr_id", "chemical_id" }) )
public class Orders implements java.io.Serializable
{

	private int id;
	private Casregistrynumbers casregistrynumbers;
	private Chemicals chemicals;
	private Importinfo importinfo;
	private Sources sources;
	private Users users;
	private int ordnumber;
	private Date updateddt;
	private Set<Results> resultses = new HashSet<Results>(0);

	public Orders()
	{
	}

	public Orders(int id, Casregistrynumbers casregistrynumbers, Chemicals chemicals, Sources sources, Users users, int ordnumber, Date updateddt)
	{
		this.id = id;
		this.casregistrynumbers = casregistrynumbers;
		this.chemicals = chemicals;
		this.sources = sources;
		this.users = users;
		this.ordnumber = ordnumber;
		this.updateddt = updateddt;
	}

	public Orders(int id, Casregistrynumbers casregistrynumbers, Chemicals chemicals, Importinfo importinfo, Sources sources, Users users, int ordnumber, Date updateddt, Set<Results> resultses)
	{
		this.id = id;
		this.casregistrynumbers = casregistrynumbers;
		this.chemicals = chemicals;
		this.importinfo = importinfo;
		this.sources = sources;
		this.users = users;
		this.ordnumber = ordnumber;
		this.updateddt = updateddt;
		this.resultses = resultses;
	}

	@Id

	@Column(name = "id", unique = true, nullable = false)
	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "casr_id", nullable = false)
	public Casregistrynumbers getCasregistrynumbers()
	{
		return this.casregistrynumbers;
	}

	public void setCasregistrynumbers(Casregistrynumbers casregistrynumbers)
	{
		this.casregistrynumbers = casregistrynumbers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "chemical_id", nullable = false)
	public Chemicals getChemicals()
	{
		return this.chemicals;
	}

	public void setChemicals(Chemicals chemicals)
	{
		this.chemicals = chemicals;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "import_id")
	public Importinfo getImportinfo()
	{
		return this.importinfo;
	}

	public void setImportinfo(Importinfo importinfo)
	{
		this.importinfo = importinfo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "source_id", nullable = false)
	public Sources getSources()
	{
		return this.sources;
	}

	public void setSources(Sources sources)
	{
		this.sources = sources;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "updatedby", nullable = false)
	public Users getUsers()
	{
		return this.users;
	}

	public void setUsers(Users users)
	{
		this.users = users;
	}

	@Column(name = "ordnumber", nullable = false)
	public int getOrdnumber()
	{
		return this.ordnumber;
	}

	public void setOrdnumber(int ordnumber)
	{
		this.ordnumber = ordnumber;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateddt", nullable = false, length = 29)
	public Date getUpdateddt()
	{
		return this.updateddt;
	}

	public void setUpdateddt(Date updateddt)
	{
		this.updateddt = updateddt;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<Results> getResultses()
	{
		return this.resultses;
	}

	public void setResultses(Set<Results> resultses)
	{
		this.resultses = resultses;
	}

}