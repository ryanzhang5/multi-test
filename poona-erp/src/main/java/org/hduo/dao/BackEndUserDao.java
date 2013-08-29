package org.hduo.dao;

import java.util.List;

import com.hduo.pojo.BackEndUser;

public class BackEndUserDao extends Dao {

	public void addBackEndUser(BackEndUser backEndUser) {
		getSession().save(backEndUser);
	}

	public BackEndUser getBackEndUser(Long id) {
		return (BackEndUser) getSession().get(BackEndUser.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<BackEndUser> getAllBackEndUser() {
		List<BackEndUser> cards = (List<BackEndUser>) getSession().getNamedQuery(
				"user.selectAll").list();
		return cards;
	}

	public BackEndUser getUserByUserName(String userName){
		List<BackEndUser> userList = getAllBackEndUser();
		for (BackEndUser backEndUser : userList) {
			if (backEndUser.getUserName().equals(userName)) {
				return backEndUser;
			}
		}
		return null;
	}
	
	
	public void deleteBackEndUser(BackEndUser backEndUser) {
		getSession().delete(backEndUser);
	}

	public void deleteBackEndUser2(Long userId) {
		String sqlA = "delete from user_role where user_id = "+ userId;
		String sqlC = "delete from back_end_user where id = " + userId;
		getSession().createSQLQuery(sqlA).executeUpdate();
		getSession().createSQLQuery(sqlC).executeUpdate();
	}

	
	public void updateBackEndUser(BackEndUser backEndUser) {
		getSession().update(backEndUser);
	}
}
