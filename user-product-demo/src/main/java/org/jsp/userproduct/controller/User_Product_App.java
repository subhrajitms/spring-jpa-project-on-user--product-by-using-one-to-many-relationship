package org.jsp.userproduct.controller;

import java.util.Scanner;

import org.jsp.userproduct.User_Productconfig;
import org.jsp.userproduct.dao.ProductDao;
import org.jsp.userproduct.dao.UserDao;
import org.jsp.userproduct.dto.Product;
import org.jsp.userproduct.dto.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class User_Product_App {
	static UserDao uDao;
	static ProductDao pDao;
	static Scanner ip = new Scanner(System.in);
	static {
		ApplicationContext context = new AnnotationConfigApplicationContext(User_Productconfig.class);
		uDao = context.getBean(UserDao.class);
		pDao = context.getBean(ProductDao.class);
	}

	public static void main(String[] args) {
		while(true)
		{
			System.out.println("1.Register User");
			System.out.println("2.Update User");
			System.out.println("3.Find User By user Id");
			System.out.println("4.Verify User By Phone number And Password");
			System.out.println("5.Verify User By Email and Password...");
			System.out.println("6.Add Product");
			System.out.println("7.Find Product By User Id");
			System.out.println("8.Find Product By User phone number and Password");
			System.out.println("9.Find Product By Brand");
			System.out.println("10.Find Product By Category");
			System.out.println("11.Find User By Product Id..");
			
			System.out.println("Enter Your Choice..");
			int choice=ip.nextInt();
			switch(choice)
			{
			case 1:{
				register();
				break;
			}
			case 2:{
				update();
				break;
			}
			case 3:{
				findByUserId();
				break;
			}
			case 4:{
				findByUserByPhone();
				break;
			}
			case 5:{
				findUserByEmail();
				break;
			}
			case 6:{
				add();
				break;
			}
			}
		}
	}
	public static void register()
	{
		System.out.println("Enter name,phone,email and Password to Save User..");
		User u=new User();
		u.setName(ip.next());
		u.setPhone(ip.nextLong());
		u.setEmail(ip.next());
		u.setPassword(ip.next());
		u=uDao.registerUser(u);
		System.out.println("User Saved With the Id:"+u.getId());
	}
	public static void update()
	{
		System.out.println("Enter User Id...");
		int id=ip.nextInt();
		System.out.println("Enter name,phone,email and Password to Save User..");
		User u=new User();
		u.setName(ip.next());
		u.setPhone(ip.nextLong());
		u.setEmail(ip.next());
		u.setPassword(ip.next());
		u=uDao.updateUser(u);
		if(u!=null)
		{
			System.out.println("User "+u.getId()+ " is Updated ");
		}
		else
		{
			System.out.println("Invalid User ID..");
		}
	}
	public static void findByUserId()
	{
		System.out.println("Enter User Id..");
		int id=ip.nextInt();
		User u=uDao.findById(id);
		if(u!=null)
		{
			System.out.println("User Id:"+u.getId());
			System.out.println("User Name:"+u.getName());
			System.out.println("User Phone Number:"+u.getPhone());
			System.out.println("User Email.:"+u.getEmail());
			System.out.println("User Password:"+u.getPassword());
			
		}
		else
		{
			System.out.println("Invalid user ID");
		}
	}
	public static void findByUserByPhone()
	{
		System.out.println("Enter User phone number and password.");
		long phone=ip.nextLong();
		String password=ip.next();
		User u=uDao.verifyUserByPhone(phone, password);
		if(u!=null)
		{
			System.out.println("User Id:"+u.getId());
			System.out.println("User Name:"+u.getName());
			System.out.println("User Phone Number:"+u.getPhone());
			System.out.println("User Email.:"+u.getEmail());
			System.out.println("User Password:"+u.getPassword());
			
		}
		else
		{
			System.out.println("Invalid user phone Number and password.");
		}
	}
	public static void findUserByEmail()
	{
		System.out.println("Enter User Email Id and password.");
		String email=ip.next();
		String password=ip.next();
		User u=uDao.verifyUserByEmail(email, password);
		if(u!=null)
		{
			System.out.println("User Id:"+u.getId());
			System.out.println("User Name:"+u.getName());
			System.out.println("User Phone Number:"+u.getPhone());
			System.out.println("User Email.:"+u.getEmail());
			System.out.println("User Password:"+u.getPassword());
			
		}
		else
		{
			System.out.println("Invalid user phone Number and password.");
		}
	}
	public static void add()
	{
		System.out.println("Enter User Id ..");
		int id=ip.nextInt();
		System.out.println("Enter Product name,brand,category,description,cost to Save the Record..");
		Product p=new Product();
		p.setName(ip.next());
		p.setBrand(ip.next());
		p.setCategory(ip.next());
		p.setDescription(ip.next());
		p.setCost(ip.nextDouble());
		p=pDao.addProduct(p, id);
		System.out.println("Product Saved With a Id:"+p.getId());
	}
	
}
