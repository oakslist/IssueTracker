package org.training.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public class HibernateUtil extends HibernateDaoSupport {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	private static final ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
			new String[] { "springContext.xml" }, true);

	private static SessionFactory buildSessionFactory() {
		try {
			Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties());
			SessionFactory factory = configuration.buildSessionFactory(builder
					.build());
			
			return factory;
		} catch (Throwable e) {
			System.err.println("Initial SessionFactory creation failed. " + e);
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getHibSessionFactory() {
		return sessionFactory;
	}

	public static void shutdown() {
		// Close caches and connection pools
		getHibSessionFactory().close();
	}

//	public static ClassPathXmlApplicationContext getContext() {
//		return context;
//	}

}
