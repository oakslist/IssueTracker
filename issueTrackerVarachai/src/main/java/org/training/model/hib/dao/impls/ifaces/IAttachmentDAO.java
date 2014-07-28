package org.training.model.hib.dao.impls.ifaces;

import java.util.List;

import org.training.model.beans.hibbeans.Attachment;
import org.training.model.impls.DaoException;

public interface IAttachmentDAO {

	public List<Attachment> getExistAttachmentsByIssueId(int issueId)
			throws DaoException;

	public boolean addAttachment(Attachment attachment) throws DaoException;
	
	public Attachment getAttachmentById(int id) throws DaoException;

}
