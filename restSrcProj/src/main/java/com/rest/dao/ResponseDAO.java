package com.rest.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.rest.pojo.User;

@Repository
public class ResponseDAO {

	public static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public void persist(User response) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(response);
		session.getTransaction().commit();
	}

	public void delete(User response) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.delete(response);
		session.getTransaction().commit();
	}

	public void update(int olduserId, int newuserid, String password) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query query = session
				.createQuery("update User u set u.userId=:newuserid,u.password=:newpassword where u.userId=:olduserid");
		query.setParameter("newuserid", newuserid);
		query.setParameter("newpassword", password);
		query.setParameter("olduserid", olduserId);
		query.executeUpdate();

		session.getTransaction().commit();
	}

	public User getInfo(String userId) {
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from User");
		List<User> list = query.list();
		return list.get(0);

	}

}
