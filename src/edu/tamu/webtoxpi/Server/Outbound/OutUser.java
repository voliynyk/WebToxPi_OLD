package edu.tamu.webtoxpi.Server.Outbound;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import edu.tamu.webtoxpi.Server.Models.Classes.Users;

@XmlRootElement(name = "User")
public class OutUser {
	private int id;
	private String login;
	private String password;
	private String firstname;
	private String lastname;
	private String email;
	private Date registereddt;
	private Date lastvisitdt;

	public OutUser() {
	}

	public OutUser(Users user) {
		this.id = user.getId();
		this.login = user.getLogin();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		this.email = user.getEmail();
		this.registereddt = user.getRegistereddt();
		this.lastvisitdt = user.getLastvisitdt();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegistereddt() {
		return registereddt;
	}

	public void setRegistereddt(Date registereddt) {
		this.registereddt = registereddt;
	}

	public Date getLastvisitdt() {
		return lastvisitdt;
	}

	public void setLastvisitdt(Date lastvisitdt) {
		this.lastvisitdt = lastvisitdt;
	}
}
