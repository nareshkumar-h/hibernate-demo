package com.touristapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity(name="users")
@NamedQueries({  

	@NamedQuery(name = "viewAllUsers", query = "FROM com.touristapp.model.User u") ,
	@NamedQuery(name = "findByEmailAndPassword", query = "FROM com.touristapp.model.User u WHERE u.email = ?1 and u.password = ?2")	
 
})
public class User {

	public User(String name, String email, String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User(Integer id, String name, String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncrement
	@Column(name="id")
	private Integer id;
	
	@Column(name="name", nullable = false, length = 50)
	private String name;
	
	@Column(name="email", unique = true)
	private String email;
	
	private String password;
	
	private String role ="USER";

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", accounts=" + accounts + "]";
	}
	
	@OneToMany(mappedBy = "user", 
			//fetch = FetchType.LAZY,
			fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
	private List<Account> accounts = new ArrayList<Account>();

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
}
