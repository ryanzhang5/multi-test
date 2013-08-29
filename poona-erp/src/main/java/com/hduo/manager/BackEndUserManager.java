package com.hduo.manager;

import java.util.List;

import com.hduo.pojo.BackEndUser;

public interface BackEndUserManager {

	List<BackEndUser> getAllBackEndUser();

	BackEndUser getBackEndUser(long id);

	void addBackEndUser(BackEndUser backEndUser);

	void deleteBackEndUser(long id);

	void deleteBackEndUser2(Long id);

	void updateBackEndUser(BackEndUser backEndUser);

	BackEndUser loadUserByUsername(String username);
}
