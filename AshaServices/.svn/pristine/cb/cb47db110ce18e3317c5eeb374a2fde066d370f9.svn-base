package com.icg.jkt.hibernateutils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.context.spi.CurrentSessionContext;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class HibernateUtlis {

	@Autowired
	protected SessionFactory factory;

	ThreadLocal<Session> threadlocal = new ThreadLocal<Session>();

	public HibernateUtlis() {
		System.out.println("HibernateUtlis intilize time");
	}

	public Session OpenSession() {
		Session session = null;
		if (threadlocal.get() == null) {
			session = factory.openSession();
			threadlocal.set(session);
		} else {
			session = threadlocal.get();
			if (!session.isOpen()) {
				threadlocal.remove();
				session = factory.openSession();
				threadlocal.set(session);
			}
		}

		return session;

	}

	public void CloseConnection() {
		if (threadlocal.get() != null) {
			threadlocal.get().clear();
			threadlocal.get().close();
			threadlocal.remove();
		}
	}

	public Session getCurrentSession() {
		return factory.getCurrentSession();

	}

}
