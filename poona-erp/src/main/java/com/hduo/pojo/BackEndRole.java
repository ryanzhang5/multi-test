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

@Entity
@Table(name = "back_end_role")
@NamedQuery(name = "role.selectAll", query = "select n from BackEndRole n ")
public class BackEndRole {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "role_name")
	private String roleName;

	@Column(name = "comment")
	private String comment;

	@ManyToMany( targetEntity=BackEndResource.class,fetch=FetchType.EAGER)
	@JoinTable( name="role_resource",joinColumns=@JoinColumn(name="role_id"),inverseJoinColumns=@JoinColumn(name="resource_id"))	
	private Set<BackEndResource> resources = new HashSet<BackEndResource>();
	
	
	@ManyToMany(mappedBy = "roles",targetEntity = BackEndUser.class )
	private Set<BackEndUser> users = new HashSet<BackEndUser>();
	
	
	public BackEndRole() {
	}

	public BackEndRole(String roleName, String comment) {
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

	@Override
	public String toString() {
		return "BackEndRole [id=" + id + ", roleName=" + roleName
				+ ", comment=" + comment + "]";
	};

}
