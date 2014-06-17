package org.training.constants;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.training.myhibernate.User;
import org.training.myhibernate.UserUn;
import org.training.persistence.HibernateUtil;

public class AppServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	public AppServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			System.out.println("Maven + Hibernate + H2");
	        Session session = HibernateUtil.getSessionFactory().openSession();
							
	        User user = new User();
	               	 
	        user.setFirstName("Siarhei");
	        user.setLastName("Varachai");
	        user.setEmailAddress("ss@vv122.com");
	        user.setPassword("111");
	        user.setRoleId(1);
	        
	        try {
	        	session.beginTransaction();
	        	session.delete(user);
	        	session.getTransaction().commit();
	        	
				session.beginTransaction();
				session.save(user);
				session.getTransaction().commit();
				System.out.println("user saved with ID: " + user.getId());
			}
			catch (HibernateException e) {
				e.printStackTrace();
				System.out.println("1111111111111111111111111111111111111111");
				session.getTransaction().rollback();
			}
	 
	        int id = user.getId();
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			try {
				session.beginTransaction();
		        User user2 = (User) session.get(User.class, id);
		        System.out.println(user2);

		        session.getTransaction().commit();
			}
			catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			
			System.out.println("annotation");
			
			UserUn userUn = new UserUn();
          	 
			userUn.setFirstName("Alex");
			userUn.setLastName("Ivanov");
			userUn.setEmailAddress("aa@ii21.com");
			userUn.setPassword("222");
			userUn.setRoleId(2);
	        
			session = HibernateUtil.getSessionFactory().openSession();
			
	        try {
	        	session.beginTransaction();
	        	session.delete(userUn);
	        	session.getTransaction().commit();
	        	
				session.beginTransaction();
				session.save(userUn);
				session.getTransaction().commit();
				System.out.println("userUn saved with ID: " + userUn.getId());
			}
			catch (HibernateException e) {
				e.printStackTrace();
				System.out.println("222222222222222222222222222222222222222222222222");
				session.getTransaction().rollback();
			}
	 
	        int idUn = userUn.getId();
	        
			
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			
			try {
				session.beginTransaction();
		        UserUn userUn2 = (UserUn) session.get(UserUn.class, idUn);
		        System.out.println(userUn2);

		        session.getTransaction().commit();
			}
			catch (HibernateException e) {
				e.printStackTrace();
				session.getTransaction().rollback();
			}
			
	        
	        
		} catch (Exception ex) {
			HibernateUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			System.out.println("3333333333333333333333333333333333333");
			if (ServletException.class.isInstance(ex)) {
				throw (ServletException) ex;
			} else {
				throw new ServletException(ex);
			}
		}

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
	}

}
