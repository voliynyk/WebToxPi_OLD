package edu.tamu.webtoxpi.Server.Models.Classes;
// Generated Aug 26, 2015 7:27:58 PM by Hibernate Tools 4.3.1.Final

import java.math.BigDecimal;
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

/**
 * Units generated by hbm2java
 */
@Entity
@Table(name = "units", schema = "public")
public class Units implements java.io.Serializable
{

	private int id;
	private Units units;
	private Users users;
	private String code;
	private String name;
	private BigDecimal volume;
	private Date updateddt;
	private Set<Units> unitses = new HashSet<Units>(0);
	private Set<Components> componentses = new HashSet<Components>(0);

	public Units()
	{
	}

	public Units(int id, Users users, String code, Date updateddt)
	{
		this.id = id;
		this.users = users;
		this.code = code;
		this.updateddt = updateddt;
	}

	public Units(int id, Units units, Users users, String code, String name, BigDecimal volume, Date updateddt, Set<Units> unitses, Set<Components> componentses)
	{
		this.id = id;
		this.units = units;
		this.users = users;
		this.code = code;
		this.name = name;
		this.volume = volume;
		this.updateddt = updateddt;
		this.unitses = unitses;
		this.componentses = componentses;
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
	@JoinColumn(name = "relatedunit")
	public Units getUnits()
	{
		return this.units;
	}

	public void setUnits(Units units)
	{
		this.units = units;
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

	@Column(name = "code", nullable = false)
	public String getCode()
	{
		return this.code;
	}

	public void setCode(String code)
	{
		this.code = code;
	}

	@Column(name = "name")
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Column(name = "volume", precision = 131089, scale = 0)
	public BigDecimal getVolume()
	{
		return this.volume;
	}

	public void setVolume(BigDecimal volume)
	{
		this.volume = volume;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "units")
	public Set<Units> getUnitses()
	{
		return this.unitses;
	}

	public void setUnitses(Set<Units> unitses)
	{
		this.unitses = unitses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "units")
	public Set<Components> getComponentses()
	{
		return this.componentses;
	}

	public void setComponentses(Set<Components> componentses)
	{
		this.componentses = componentses;
	}

}
