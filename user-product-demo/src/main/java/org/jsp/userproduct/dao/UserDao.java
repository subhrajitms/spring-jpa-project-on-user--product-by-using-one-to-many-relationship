package org.jsp.userproduct.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.userproduct.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class UserDao {
    
	@Autowired
	private EntityManager manager;
	
	public User registerUser(User u)
	{
		EntityTransaction transaction = manager.getTransaction();
		manager.persist(u);
		transaction.begin();
		transaction.commit();
		return u;
	}
	
	public User updateUser(User u)
	{
		User userDb=manager.find(User.class, u.getId());
		if(userDb!=null)
		{
			userDb.setName(u.getName());
			userDb.setEmail(u.getEmail());
			userDb.setPhone(u.getPhone());
			userDb.setPassword(u.getPassword());
			EntityTransaction transaction=manager.getTransaction();
			transaction.begin();
			transaction.commit();
			return userDb;
		}
		return null;
	}
	
	public User findById(int id)
	{
		return manager.find(User.class, id);
	}
	
	public User verifyUserByPhone(long phone,String password)
	{
	  Query q=manager.createQuery("select u from User u where u.phone=?1 and u.password=?2");
	  q.setParameter(1, phone);
	  q.setParameter(2, password);
	  try {
		  return (User)q.getSingleResult();
	  }catch(NoResultException e)
	  {
		  return null;
	  }
	}
	public User verifyUserByEmail(String email,String password)
	{
		Query q=manager.createQuery("select u from User u where u.email=?1 and u.password=?2");
		  q.setParameter(1, email);
		  q.setParameter(2, password);
		  try {
			  return (User)q.getSingleResult();
		  }catch(NoResultException e)
		  {
			  return null;
		  }
	}
	

}
