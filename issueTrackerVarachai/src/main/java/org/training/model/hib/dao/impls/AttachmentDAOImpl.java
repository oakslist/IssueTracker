package org.training.model.hib.dao.impls;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.training.model.beans.hibbeans.Attachment;
import org.training.model.hib.dao.impls.ifaces.IAttachmentDAO;
import org.training.model.impls.DaoException;

@Repository(value="attachmentDAOImpl")
public class AttachmentDAOImpl implements IAttachmentDAO {
	
	private static final Logger LOG = Logger.getLogger(AttachmentDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	@Override
	public List<Attachment> getExistAttachmentsByIssueId(int issueId)
			throws DaoException {
		System.out.println("get all attachments by issue id");
		LOG.info("get all attachments by issue id");
		List<Attachment> attachments = new ArrayList<Attachment>();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			attachments = (List<Attachment>) session.createQuery(
				    "from Attachment a where a.issue.id = ?")
				   .setInteger(0, issueId).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in get all attachments by issue id " + e);
		}
		return attachments;
	}

	@Override
	public boolean addAttachment(Attachment attachment) throws DaoException {
		boolean isSet = false;
		System.out.println("Set in attachment");
		LOG.info("Set in attachment");
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(attachment);
			session.getTransaction().commit();
			isSet = true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in set attachment " + e);
		}
		return isSet;
	}

	@Override
	public Attachment getAttachmentById(int id) throws DaoException {
		System.out.println("get attachment by id");
		LOG.info("get attachment by id");
		Attachment attachment = new Attachment();
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			attachment = (Attachment) session.createQuery(
				    "from Attachment a where a.id = ?")
				   .setInteger(0, id).uniqueResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			System.out.println("eror in get attachment by id " + e);
		}
		return attachment;
	}
	
	
}
