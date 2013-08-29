package com.hduo.pojo;

import java.util.HashSet;
import java.util.Set;

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
@Table(name = "back_end_user")
@NamedQuery(name = "user.selectAll", query = "select n from BackEndUser n ")
public class BackEndUser {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "user_name")
	private String userName;
	
	@Column(name = "real_name")
	private String realName;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany( targetEntity=BackEndRole.class,fetch=FetchType.EAGER)
	@JoinTable( name="user_role",joinColumns=@JoinColumn(name="user_id"),inverseJoinColumns=@JoinColumn(name="role_id"))	
	private Set<BackEndRole> roles = new HashSet<BackEndRole>();

	@Column(name = "comment")
	private String comment;

	public BackEndUser() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public BackEndUser(String userName, String realName,String password, String comment) {
		this.userName = userName;
		this.realName = realName;
		this.password = password;
		this.comment = comment;
	}
	
	
	
	

	public Set<BackEndRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<BackEndRole> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "BackEndUser [id=" + id + ", userName=" + userName
				+ ", password=" + password + ", comment=" + comment + "]";
	}

	
}
