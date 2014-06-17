package org.training.persistence;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();
	 
    private static SessionFactory buildSessionFactory() {
    	try { 
    	      	    
    		Configuration configuration = new Configuration().configure();
    		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
    		applySettings(configuration.getProperties());
    		SessionFactory factory = configuration.buildSessionFactory(builder.build());
    		return factory;
       	} catch (Throwable e) {
       		System.err.println("Initial SessionFactory creation failed. " + e);
    		throw new ExceptionInInitializerError(e);
    	}
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public static void shutdown() {
    	// Close caches and connection pools
    	getSessionFactory().close();
    }
	
}
