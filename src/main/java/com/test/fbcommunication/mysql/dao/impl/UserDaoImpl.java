package com.test.fbcommunication.mysql.dao.impl;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.test.fbcommunication.mysql.dao.UserDao;
import com.test.fbcommunication.mysql.dao.manager.BeanManager;
import com.test.fbcommunication.mysql.model.User;

public class UserDaoImpl implements UserDao {

	public Boolean saveOrUpdate(User userDetails) {
		if (null == userDetails) {
			return false;
		}

		Transaction tx;
		Session session = null;

		try {
			session = BeanManager.getSessionFactory().openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(userDetails);
			tx.commit();

			return true;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}

		return false;
	}

	public User fetchData(Long fbId) {
		Session session = null;

		try {
			session = BeanManager.getSessionFactory().openSession();
			User user = (User) session.getNamedQuery("User.findByFbid")
					.setParameter("fbid", fbId).setCacheable(false)
					.uniqueResult();

			return user;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			if (null != session && session.isOpen()) {
				session.close();
			}
		}

		return null;
	}

	public static void main(String[] args) {
		UserDaoImpl userDaoImpl = new UserDaoImpl();

		// try {
		// User user = new User();
		// user.setFbid(112L);
		// user.setFirstname("Anil");
		// user.setGender("Male");
		// user.setLastname("Tiwari");
		// user.setLink("sdfdsfdsfds");
		// user.setLocation("Pune");
		// user.setMiddlename("Shrikant");
		// user.setUpdatedtime(new Date());
		// user.setVerified(true);
		//
		// Boolean saved = userDaoImpl.saveOrUpdate(user);
		// if (saved) {
		// System.out.println("Saved successfully");
		// }
		//
		// } catch (Exception e) {
		// System.out.println(e.getMessage());
		// }

		try {
			User user = userDaoImpl.fetchData(112L);

			if (null != user) {
				System.out.println(user.getFirstname() + "--"
						+ user.getLastname());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}
