package com.test.fbcommunication.mysql.dao;

import com.test.fbcommunication.mysql.model.User;

public interface UserDao {
	public Boolean saveOrUpdate(User user);

	public User fetchData(Long fbId);
}
