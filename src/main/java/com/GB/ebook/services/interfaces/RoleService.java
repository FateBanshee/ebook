package com.GB.ebook.services.interfaces;

import java.util.List;

import com.GB.ebook.entity.Role;

public interface RoleService {
	List<Role> ListRole();
	
	int addRole(Role role);
	
	Role findRoleByName(String roleName);
	
	String updateRole(Role role);
	
	int deleteRoleByName(int roleId);
	
	Role findRoleById(int roleId);
}
