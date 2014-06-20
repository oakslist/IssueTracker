package org.training.model.hib.impls;

import org.training.model.beans.hibbeans.Priority;
import org.training.model.beans.hibbeans.Resolution;
import org.training.model.beans.hibbeans.Status;
import org.training.model.beans.hibbeans.Type;

public enum LoadedClassNameEnum {

	TYPE(Type.class.getSimpleName()), 
	STATUS(Status.class.getSimpleName()),
	RESOLUTION(Resolution.class.getSimpleName()),
	PRIORITY(Priority.class.getSimpleName());
	
	private final String className;

	LoadedClassNameEnum(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}
	
}
