package org.jsp.userproduct.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.jsp.userproduct.dto.Product;
import org.jsp.userproduct.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProductDao {
	@Autowired
	private EntityManager manager;
	
	public Product addProduct(Product p,int id)
	{
		User u=manager.find(User.class, id);
		if(u!=null)
		{
		   u.getProducts().add(p);
		   p.setUser(u);	
		   EntityTransaction transaction=manager.getTransaction();
		   manager.persist(p);
		   transaction.begin();
		   transaction.commit();
		   return p;
		}
		return null;
	}
	public List<Product> findProductByUid(int id)
	{
		Query q=manager.createQuery("select u.products from User u where u.id=?1");
		q.setParameter(1, id);
		return q.getResultList();
	}
	public List<Product> findProductByPhone(long phone,String password)
	{
		Query q=manager.createQuery("select u.products from User u where u.phone=?1 and u.password=?2");
		q.setParameter(1, phone);
		q.setParameter(2, password);
		return q.getResultList();
	}
	public List<Product> findByBrand(String brand)
	{
		Query q=manager.createQuery("select p from Product p where p.brand=?1");
		q.setParameter(1, brand);
		return q.getResultList();
	}
	public List<Product> findByCategory(String category)
	{
		Query q=manager.createQuery("select p from Product p where p.category=?2");
		q.setParameter(1, category);
		return q.getResultList();
	}
	public User findByProductId(int id)
	{
		Query q=manager.createQuery("select p.user where Product p where p.id=?1");
		q.setParameter(1, id);
		try {
			return (User)q.getSingleResult();
		}catch(NoResultException e)
		{
			return null;
		}
	}
	
}
