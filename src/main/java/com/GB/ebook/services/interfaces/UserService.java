package com.GB.ebook.services.interfaces;

import java.util.List;

import com.GB.ebook.entity.User;

public interface UserService {
	User login(String userName);
	
	/**
	 * @Arron
	 *添加用户
	 */
	int addUser(User user);
	
	int updateUserInfo(User user);
	
	int updateUserInfoNPsw(User user);
	
	int updateIsExist(String userName);
	
	String updateUserRole(User user);
	
	boolean deleteUser(String userName);
	
	List<User> listUser();
	
	User getUserByUserName(String userName);
	
	User getUserByAccountName(String accountName);
}
