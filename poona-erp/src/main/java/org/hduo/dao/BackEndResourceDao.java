package org.hduo.dao;

import java.util.List;

import com.hduo.pojo.BackEndResource;

public class BackEndResourceDao extends Dao {

	public void addBackEndResource(BackEndResource backEndResource) {
		getSession().save(backEndResource);
	}

	public BackEndResource getBackEndResource(Long id) {
		return (BackEndResource) getSession().get(BackEndResource.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<BackEndResource> getAllBackEndResource() {
		List<BackEndResource> cards = (List<BackEndResource>) getSession().getNamedQuery(
				"resource.selectAll").list();
		return cards;
	}

	public void deleteBackEndResource(BackEndResource backEndResource) {
		getSession().delete(backEndResource);
	}

	public void updateBackEndResource(BackEndResource backEndResource) {
		getSession().update(backEndResource);
	}
}
