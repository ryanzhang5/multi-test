package com.hduo.manager;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.BackEndRole;

public interface BackEndRoleManager {

	List<BackEndRole> getAllBackEndRole();

	BackEndRole getBackEndRole(long id);

	void addBackEndRole(BackEndRole backEndRole);

	void deleteBackEndRole(long id);
	
	public void deleteBackEndRole2(long id);

	void updateBackEndRole(BackEndRole backEndRole);
}
