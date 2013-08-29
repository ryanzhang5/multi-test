package com.hduo.manager;

import java.util.List;

import org.hduo.dao.BackEndResourceDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.BackEndResource;

public class BackEndResourceManagerImpl implements BackEndResourceManager {
	private BackEndResourceDao	backEndResourceDao;

	public void setBackEndResourceDao(BackEndResourceDao backEndResourceDao) {
		this.backEndResourceDao = backEndResourceDao;
	}

	@Transactional
	public List<BackEndResource> getAllBackEndResource() {
		return backEndResourceDao.getAllBackEndResource();
	}
	@Transactional
	public BackEndResource getBackEndResourceById(Long id) {
		return backEndResourceDao.getBackEndResource(id);
	}

	
}
