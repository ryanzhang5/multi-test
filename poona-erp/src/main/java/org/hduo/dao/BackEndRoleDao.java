package org.hduo.dao;

import java.util.List;

import com.hduo.pojo.BackEndRole;

public class BackEndRoleDao extends Dao {

	public void addBackEndRole(BackEndRole backEndrole) {
		getSession().save(backEndrole);
	}

	public BackEndRole getBackEndRole(Long id) {
		return (BackEndRole) getSession().get(BackEndRole.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<BackEndRole> getAllBackEndRole() {
		List<BackEndRole> cards = (List<BackEndRole>) getSession().getNamedQuery(
				"role.selectAll").list();
		return cards;
	}

	public void deleteBackEndRole(BackEndRole backEndRole) {
		getSession().delete(backEndRole);
	}
	
	public void deleteBackEndRole2(Long roleId) {
		String sqlA = "delete from user_role where role_id = "+ roleId;
		String sqlB = "delete from role_resource where role_id = "+ roleId;
		String sqlC = "delete from back_end_role where id = " + roleId;
		getSession().createSQLQuery(sqlA).executeUpdate();
		getSession().createSQLQuery(sqlB).executeUpdate();
		getSession().createSQLQuery(sqlC).executeUpdate();
	}

	public void updateBackEndRole(BackEndRole backEndRole) {
		getSession().update(backEndRole);
	}
}
