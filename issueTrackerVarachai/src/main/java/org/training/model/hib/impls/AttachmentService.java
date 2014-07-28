package org.training.model.hib.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.training.model.beans.hibbeans.Attachment;
import org.training.model.hib.dao.impls.ifaces.IAttachmentDAO;
import org.training.model.hib.ifaces.IAttachmentService;
import org.training.model.impls.DaoException;

@Service(value="attachmentService")
public class AttachmentService implements IAttachmentService {

	@Autowired
	private IAttachmentDAO attachmentDAOImpl;

	@Override
	public List<Attachment> getExistAttachmentsByIssueId(int issueId)
			throws DaoException {
		return attachmentDAOImpl.getExistAttachmentsByIssueId(issueId);
	}

	@Override
	public boolean addAttachment(Attachment attachment) throws DaoException {
		return attachmentDAOImpl.addAttachment(attachment);
	}

	@Override
	public Attachment getAttachmentBuId(int id) throws DaoException {
		return attachmentDAOImpl.getAttachmentById(id);
	}
		
}
