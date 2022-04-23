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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({  

	@NamedQuery(name = "viewAllUsers", query = "FROM com.touristapp.model.User u") ,
	@NamedQuery(name = "findByEmailAndPassword", 
	query = "FROM com.touristapp.model.User u WHERE u.email = ?1 and u.password = ?2"),
	@NamedQuery(name = "findByName", 
	query = "FROM com.touristapp.model.User u WHERE u.name = ?1"),
 
})
@Entity(name="users")
public class User {
	
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ "]";
	}
	
//	@OneToMany(mappedBy = "user", 
//			fetch = FetchType.LAZY,
//			//fetch = FetchType.EAGER,
//            cascade = CascadeType.ALL)
//	private List<Account> accounts = new ArrayList<Account>();

	
}

