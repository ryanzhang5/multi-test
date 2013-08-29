package com.hduo.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "back_end_resource")
@NamedQuery(name = "resource.selectAll", query = "select n from BackEndResource n ")
public class BackEndResource {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "resource_name")
	private String resourceName;

	@Column(name = "resource_url")
	private String resourceUrl;

	@Column(name = "resource_number")
	private Long resourceNumber;

	@Column(name = "parent_id")
	private Long parentId;

	@ManyToMany(mappedBy = "resources", targetEntity = BackEndRole.class)
	private Set<BackEndRole> roles = new HashSet<BackEndRole>();

	@Column(name = "comment")
	private String comment;

	public BackEndResource() {
	};

	public BackEndResource(String resourceName, Long resourceNumber,
			Long parentId, String comment) {
		this.resourceName = resourceName;
		this.resourceNumber = resourceNumber;
		this.parentId = parentId;
		this.comment = comment;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public Long getResourceNumber() {
		return resourceNumber;
	}

	public void setResourceNumber(Long resourceNumber) {
		this.resourceNumber = resourceNumber;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Set<BackEndRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<BackEndRole> roles) {
		this.roles = roles;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	@Override
	public String toString() {
		return "BackEndResource [id=" + id + ", resourceName=" + resourceName
				+ ", resourceNumber=" + resourceNumber + ", parentId="
				+ parentId + ", comment=" + comment + "]";
	}

}
