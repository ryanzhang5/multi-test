package com.hduo.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hduo.dao.BackEndUserDao;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.BackEndResource;
import com.hduo.pojo.BackEndRole;
import com.hduo.pojo.BackEndUser;

public class UserDetailServiceImpl implements UserDetailsService {
	private final static Logger logger = Logger
			.getLogger(UserDetailServiceImpl.class);

	private BackEndUserDao userDao;

	@SuppressWarnings("deprecation")
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
			if (username == null) {
				logger.error("username is null");
			}
			BackEndUser user = userDao.getUserByUserName(username);
			if (user == null) {
				logger.error(username + " is not exist",new UsernameNotFoundException(username + " is not exist"));
			}
			List<GrantedAuthority> authsList = new ArrayList<GrantedAuthority>();

			for (BackEndRole role : user.getRoles()) {
				for (BackEndResource resource : role.getResources()) {
					authsList.add(new GrantedAuthorityImpl(resource.getResourceUrl()));
				}
			}
			User userdetail = new User(user.getUserName(), user.getPassword(),authsList);

			return userdetail;
	}

	public void setUserDao(BackEndUserDao userDao) {
		this.userDao = userDao;
	}
}