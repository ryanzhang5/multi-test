package com.hduo.manager;

import java.util.List;

import org.hduo.dao.BackEndRoleDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.BackEndRole;

public class BackEndRoleManagerImpl implements BackEndRoleManager {
	private BackEndRoleDao backEndRoleDao;

	public void setBackEndRoleDao(BackEndRoleDao backEndRoleDao) {
		this.backEndRoleDao = backEndRoleDao;
	}




	@Transactional
	public List<BackEndRole> getAllBackEndRole() {
		return backEndRoleDao.getAllBackEndRole();
	}




	@Transactional
	public BackEndRole getBackEndRole(long id) {
		return backEndRoleDao.getBackEndRole(id);
	}




	@Transactional
	public void addBackEndRole(BackEndRole backEndRole) {
		backEndRoleDao.addBackEndRole(backEndRole);
	}




	@Transactional
	public void deleteBackEndRole(long id) {
		backEndRoleDao.deleteBackEndRole(getBackEndRole(id));
	}

	@Transactional
	public void deleteBackEndRole2(long id) {
		backEndRoleDao.deleteBackEndRole2(id);
	}


	@Transactional
	public void updateBackEndRole(BackEndRole backEndRole) {
		backEndRoleDao.updateBackEndRole(backEndRole);
	}

	
	
}
