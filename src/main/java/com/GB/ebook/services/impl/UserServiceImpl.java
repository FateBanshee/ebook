package com.GB.ebook.services.impl;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.GB.ebook.dao.UserMapper;
import com.GB.ebook.entity.Role;
import com.GB.ebook.entity.User;
import com.GB.ebook.services.interfaces.RoleService;
import com.GB.ebook.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {
	// 实现类
	@Autowired
	private UserMapper userMapper;
	// UserMapper映射接口，生成一个实现对象
	@Autowired
	private RoleService roleService;

	public User login(String userName) {
		if(isNumberic(userName)){
			return userMapper.findByPhone(userName);
		}else if(checkEmail(userName)){
			return userMapper.findByEmail(userName);
		}
		return userMapper.findByUserName(userName);
	}

	public int addUser(User user) {
		// TODO Auto-generated method stub

		return userMapper.insertUser(user);
	}

	public List<User> listUser() {
		// TODO Auto-generated method stub
		List<User> listUser = userMapper.listUser();

		return listUser;
	}

	public User getUserByUserName(String userName) {
		return userMapper.queryUserByUserName(userName);
	}
	
	
	public User getUserByAccountName(String accountName) {
		return userMapper.findByAccountName(accountName);
	}

	public String updateUserRole(User user) {
		List<Role> listRole = roleService.ListRole();
		Iterator<Role> it = listRole.iterator();
		while (it.hasNext()) {
			Role tmp = it.next();
			if (user.getRoleName().equals(tmp.getRoleName())) {
				user.setRoleId(tmp.getRoleId());
				break;
			}
		}
		int updateUserRole = userMapper.updateUserRole(user);

		return null;
	}


	public boolean deleteUser(String userName) throws DataAccessException {
		/**
		 * @Arron
		 *
		 */

		System.out.println("userDelete = " + userMapper.deleteUser(userName));

		return false;
	}


	public int updateUserInfo(User user) {
		// TODO Auto-generated method stub

		return userMapper.updateUserInfo(user);
	}

	
	public int updateIsExist(String userName) {
		// TODO Auto-generated method stub
		return userMapper.updateIsExist(userName);
	}

	
	public int updateUserInfoNPsw(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateUserInfoNPsw(user);
	}

	public static boolean isNumberic(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	public static boolean checkEmail(String email) {// 验证邮箱的正则表达式
		boolean flag = false;
		try {
			String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
			Pattern regex = Pattern.compile(check);
			Matcher matcher = regex.matcher(email);
			flag = matcher.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;

	}

}
