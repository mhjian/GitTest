package com.hejian.men.entity;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.type.Type;

public class CustomUUIDGenerator extends UUIDGenerator {

	/* (non-Javadoc)
	 * @see org.hibernate.id.UUIDGenerator#configure(org.hibernate.type.Type, java.util.Properties, org.hibernate.dialect.Dialect)
	 */
	@Override
	public void configure(Type arg0, Properties arg1, Dialect arg2)
			throws MappingException {
		super.configure(arg0, arg1, arg2);
	}

	/* (non-Javadoc)
	 * @see org.hibernate.id.UUIDGenerator#generate(org.hibernate.engine.SessionImplementor, java.lang.Object)
	 * Hibernatge 3.6 UUID 默认为36位，其中包含-
	 */
	@Override
	public Serializable generate(SessionImplementor session, Object object)
			throws HibernateException {
		IdEntity entity = (IdEntity) object;
		if(entity.getId() != null) {
			return entity.getId();
		}
		String uuid = super.generate(session, object).toString().replaceAll("-", "");
		return uuid;
	}
	
}