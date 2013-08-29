package com.hduo.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

public class BackEndRoleVO {
	private Long id;

	private String roleName;

	private String comment;

	private boolean selected = false;
	
	private Set<BackEndResource> resources = new HashSet<BackEndResource>();
	
	
	private Set<BackEndUser> users = new HashSet<BackEndUser>();
	
	
	public BackEndRoleVO() {
	}

	public BackEndRoleVO(String roleName, String comment) {
		this.roleName = roleName;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Set<BackEndResource> getResources() {
		return resources;
	}

	public void setResources(Set<BackEndResource> resources) {
		this.resources = resources;
	}
	

	public Set<BackEndUser> getUsers() {
		return users;
	}

	public void setUsers(Set<BackEndUser> users) {
		this.users = users;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	@Override
	public String toString() {
		return "BackEndRoleVO [id=" + id + ", roleName=" + roleName
				+ ", comment=" + comment + ", selected=" + selected
				+ ", resources=" + resources + ", users=" + users + "]";
	}



}
