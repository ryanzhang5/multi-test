package com.hduo.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.hduo.dao.BackEndUserDao;
import org.hduo.dao.CardDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.BackEndUser;
import com.hduo.pojo.Card;
import com.hduo.util.Utils;

public class BackEndUserManagerImpl implements BackEndUserManager {
	private BackEndUserDao backEndUserDao;

	public void setBackEndUserDao(BackEndUserDao backEndUserDao) {
		this.backEndUserDao = backEndUserDao;
	}

	@Transactional
	public List<BackEndUser> getAllBackEndUser() {
		return this.backEndUserDao.getAllBackEndUser();
	}

	@Transactional
	public BackEndUser getBackEndUser(long id) {
		return this.backEndUserDao.getBackEndUser(id);
	}

	@Transactional
	public void addBackEndUser(BackEndUser backEndUser) {
		this.backEndUserDao.addBackEndUser(backEndUser);
	}

	@Transactional
	public void deleteBackEndUser(long id) {
		this.backEndUserDao.deleteBackEndUser(getBackEndUser(id));
	}
	@Transactional
	public void deleteBackEndUser2(Long userId) {
		this.backEndUserDao.deleteBackEndUser2(userId);
	}

	@Transactional
	public void updateBackEndUser(BackEndUser backEndUser) {
		backEndUserDao.updateBackEndUser(backEndUser);
	}
	@Transactional
	public BackEndUser loadUserByUsername(String userName){
		return backEndUserDao.getUserByUserName(userName);
	}

}
