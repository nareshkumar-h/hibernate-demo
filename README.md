# hibernate-demo


##### Entity Class => User,Account

```java
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
	
	@OneToMany(mappedBy = "user", 
			//fetch = FetchType.LAZY,
			fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
	private List<Account> accounts = new ArrayList<Account>();

  //setter/getters
	
}

```

```java
package com.touristapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //AutoIncrement
	private Integer id;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="user_id")
	private User user;
	
	@Column(name="account_type")
	private String accountType;
	
	private Integer balance;

  //getter-setters
	
	
}

```



```java
package com.touristapp.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.touristapp.model.User;
import com.touristapp.util.HibernateUtil;

public class UserDAO {

	public static void save(User user) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		try {
			session.persist(user);
			tx.commit();
		} catch (Exception ex) {
			tx.rollback();
		} finally {
			session.close();
		}
	}

	// select * from users where id = ?
	public static User findById(int id) {
		Session session = HibernateUtil.getSession();
		User user = session.find(User.class, id);// find
		return user;
	}

	// select * from users : List<User>
	public static List<User> findAll() {
		Session session = HibernateUtil.getSession();
//		Query<User> user = session.createQuery("select u from com.touristapp.model.User u", User.class);
		Query<User> user = session.createNativeQuery("select u.* from users u", User.class);
		return user.getResultList();
	}

	// @Query("select u from User u")
	public static User findByEmailAndPassword(String email, String password) {
		Session session = HibernateUtil.getSession();
//		 Query<User> query = session.createQuery("select u from com.touristapp.model.User u where u.email =?1 and password =?2", User.class);
//		Query<User> query = session.createNativeQuery("select u.* from users u where email = ? and password = ?", User.class);
		Query<User> query = session.createNamedQuery("findByEmailAndPassword", User.class);
		query.setParameter(1, email);
		query.setParameter(2, password);
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException e) {
			System.out.println("No record exists");
		}
		return user;

	}

	public static long count() {
		Session session = HibernateUtil.getSession();

		Query<Long> count = session.createNativeQuery("select count(*) from users u", Long.class);
		return count.uniqueResult();
	}

	public static List<String> findEmailIds() {
		Session session = HibernateUtil.getSession();

		Query<String> count = session.createNativeQuery("select email from users u", String.class);
		return count.getResultList();
	}

	public static void delete(int id) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		User user = session.find(User.class, id);
		session.remove(user);

		tx.commit();
		session.close();
	}

	public static void update(User user) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		session.merge(user);

		tx.commit();
		session.close();
	}

	public static List<User> getUsers(String role) {
		Session session = HibernateUtil.getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class); // return user object

		Root<User> root = query.from(User.class);

		// select * from users where email = ?
		query.select(root).where(builder.equal(root.get("role"), role));
		// order by name asc;
		query.orderBy(builder.asc(root.get("name")));
		Query<User> createQuery = session.createQuery(query);

		// pagination 1 -10 rows
		createQuery.setFirstResult(0);
		createQuery.setMaxResults(10);

		List<User> userList = createQuery.getResultList();
		return userList;

	}
}
```

##### Configuration => persistence.xml

```xml
<persistence version="2.2" 
   xmlns="http://xmlns.jcp.org/xml/ns/persistence"  
   xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" 
   xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/persistence   
    http://xmlns.jcp.org/xml/ns/persistence/persistence_2_2.xsd"
  >
    <persistence-unit name="touristapp-pu">
	
		<class>com.touristapp.model.User</class>
		<class>com.touristapp.model.Account</class>
		

		<properties>	    
			<property name="javax.persistence.jdbc.driver" value="com.mysql.cj.jdbc.Driver"/>
            <property name="javax.persistence.jdbc.url" value="jdbc:mysql://101.53.133.59:3306/revature_training_db"/> 
            <property name="hibernate.dialect" value="org.hibernate.dialect.MySQL8Dialect" />
            <property name="javax.persistence.jdbc.user" value="rev_user"/> 
            <property name="javax.persistence.jdbc.password" value="rev_user"/>
            <property name="hibernate.show_sql" value = "true" /> 
            <property name="hibernate.format_sql" value = "true" />
			
            <!-- <property name="javax.persistence.schema-generation.database.action" value="drop-and-create"/> --> 
			
	    </properties>
	       
	</persistence-unit> 
	 
</persistence>
```

##### HibernateUtil.java

```java
package com.touristapp.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {

	private static EntityManagerFactory factory;

	public static Session getSession() {
		if (factory == null) {
			factory = Persistence.createEntityManagerFactory("touristapp-pu");
		}
		EntityManager entityManager = factory.createEntityManager();
		Session session = entityManager.unwrap(org.hibernate.Session.class);

		SessionFactory factory = session.getSessionFactory();

		return session;
	}
}
```


