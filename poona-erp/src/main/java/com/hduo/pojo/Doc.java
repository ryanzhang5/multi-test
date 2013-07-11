package com.hduo.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "doc")
@NamedQuery(name = "doc.selectAll", query = "select n from Doc n ")
public class Doc {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="fileName")
	private String fileName;
	@Column(name="fullPath")
	private String fullPath;
	@Column(name="comments")
	private String comments;

	public Doc() {
	};


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFullPath() {
		return fullPath;
	}


	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}
	
	

}
