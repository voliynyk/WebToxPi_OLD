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

/**
 * Groups generated by hbm2java
 */
@Entity
@Table(name = "groups", schema = "public")
public class Groups implements java.io.Serializable
{

	private int id;
	private Users users;
	private Weights weights;
	private String code;
	private String name;
	private Integer color;
	private Date updateddt;
	private Set<Types> typeses = new HashSet<Types>(0);

	public Groups()
	{
	}

	public Groups(int id, Users users, Weights weights, String code, Date updateddt)
	{
		this.id = id;
		this.users = users;
		this.weights = weights;
		this.code = code;
		this.updateddt = updateddt;
	}

	public Groups(int id, Users users, Weights weights, String code, String name, Integer color, Date updateddt, Set<Types> typeses)
	{
		this.id = id;
		this.users = users;
		this.weights = weights;
		this.code = code;
		this.name = name;
		this.color = color;
		this.updateddt = updateddt;
		this.typeses = typeses;
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
	@JoinColumn(name = "updatedby", nullable = false)
	public Users getUsers()
	{
		return this.users;
	}

	public void setUsers(Users users)
	{
		this.users = users;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "weight_id", nullable = false)
	public Weights getWeights()
	{
		return this.weights;
	}

	public void setWeights(Weights weights)
	{
		this.weights = weights;
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

	@Column(name = "color")
	public Integer getColor()
	{
		return this.color;
	}

	public void setColor(Integer color)
	{
		this.color = color;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "groups")
	public Set<Types> getTypeses()
	{
		return this.typeses;
	}

	public void setTypeses(Set<Types> typeses)
	{
		this.typeses = typeses;
	}

}