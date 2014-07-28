package org.training.model.hib.ifaces;

import java.util.List;

import org.training.model.beans.hibbeans.Attachment;
import org.training.model.impls.DaoException;

public interface IAttachmentService {
	
	public List<Attachment> getExistAttachmentsByIssueId(int issueId) throws DaoException;
	public boolean addAttachment(Attachment attachment) throws DaoException;
	public Attachment getAttachmentBuId(int id) throws DaoException;

}
