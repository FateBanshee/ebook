package com.GB.ebook.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import com.GB.ebook.entity.User;

public interface UserMapper {
	//数据库接口
	User findByUserName(String userName);
	
	User findByAccountName(String accountName);
	
	User findByEmail(String userName);
	
	User findByPhone(String userName);
	
	int insertUser(User user);
	
	int updateUserInfo(User user);
	
	int updateUserInfoNPsw(User user);
	
	int updateIsExist(String userName);
	
	List<User> listUser();
	
	User queryUserByUserName(String userName);

	int updateUserRole(User user);
	 
	boolean deleteUser(String userName) throws DataAccessException;
}
