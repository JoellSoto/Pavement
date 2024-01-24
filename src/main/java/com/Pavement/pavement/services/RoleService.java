package com.Pavement.pavement.services;

import java.util.List;

import com.Pavement.pavement.entities.Roles;
import com.Pavement.pavement.entities.User;




public interface RoleService {

	List<Roles> getAllRoles();
	
	Roles removeAllUserFromRole(int roleId);
	
	User removeUserFromRole(int userId,int roleId);
	
	User assignUserToRole(int userId,int roleId);
	
}
