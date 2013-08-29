package com.hduo.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hduo.manager.BackEndRoleManager;
import com.hduo.manager.BackEndUserManager;
import com.hduo.pojo.BackEndRole;
import com.hduo.pojo.BackEndRoleVO;
import com.hduo.pojo.BackEndUser;
import com.hduo.pojo.Customer;
import com.opensymphony.xwork2.ActionSupport;

public class BackEndUserAction extends ActionSupport {
	
	private final static Logger logger = Logger.getLogger(BackEndUserAction.class);
	
	private BackEndUserManager backEndUserManager;
	
	private BackEndRoleManager backEndRoleManager;
	
	private List<BackEndUser> userList ;
	private List<BackEndRole> roleList ;
	private List<BackEndRoleVO> rolevoList ;
	private InputStream inputStream;
	
	private String userId;
	
	public String getAllUser(){
		userList = backEndUserManager.getAllBackEndUser();
		return SUCCESS;
	}
	
	public String toAddUser(){
		return SUCCESS;
	}
	public String toResetPassword(){
		return SUCCESS;
	}
	public String resetPassword(){
		try {
		HttpServletRequest request = ServletActionContext.getRequest();
		BackEndUser user = backEndUserManager.getBackEndUser(Long.valueOf(userId));
		user.setPassword(request.getParameter("password"));
		backEndUserManager.updateBackEndUser(user);
		inputStream = new BufferedInputStream(new ByteArrayInputStream("0".getBytes()));
	} catch (Exception e) {
		e.printStackTrace();
		inputStream = new BufferedInputStream(new ByteArrayInputStream("1".getBytes()));
	}
		return SUCCESS;
	}
	public String toAssignDepartment(){
		roleList = backEndRoleManager.getAllBackEndRole();
		BackEndUser user = backEndUserManager.getBackEndUser(Long.valueOf(userId));
		rolevoList = new ArrayList<BackEndRoleVO>();
		for (BackEndRole role : roleList) {
			boolean selected = false;
			for (BackEndRole selectedRole : user.getRoles()) {
				if(role.getId().equals(selectedRole.getId())){
					selected = true;
					break;
				}
			}
			BackEndRoleVO vo = new BackEndRoleVO();
			vo.setId(role.getId());
			vo.setRoleName(role.getRoleName());
			if(selected){
				vo.setSelected(true);
			}
			rolevoList.add(vo);
		}
		return SUCCESS;
	}
	
	
	public String assignDepartment(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String departmentId = request.getParameter("departmentId");
			BackEndUser user = backEndUserManager.getBackEndUser(Long.valueOf(userId));
			user.getRoles().clear();
			if(!departmentId.equals("")){
				String[] roleIds = departmentId.split(",");
				for (String roleid : roleIds) {
					user.getRoles().add(backEndRoleManager.getBackEndRole(Long.valueOf(roleid)));
				}
			}
			backEndUserManager.updateBackEndUser(user);
			
			inputStream = new BufferedInputStream(new ByteArrayInputStream("0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream("1".getBytes()));
		}
		return SUCCESS;
	}
	
	
	public String saveUser(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String userName = request.getParameter("userName");
			String realName = request.getParameter("realName");
			String password = request.getParameter("password");
			String comment = request.getParameter("comments");
			logger.info("----------------------------------------save role--------");
			BackEndUser user = new BackEndUser(userName,realName,password, comment);
			backEndUserManager.addBackEndUser(user);
			inputStream = new BufferedInputStream(new ByteArrayInputStream("0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream("1".getBytes()));
		}
		return SUCCESS;
	}
	
	public String checkUser(){
			HttpServletRequest request = ServletActionContext.getRequest();
			String j_username = request.getParameter("j_username");
			String j_password = request.getParameter("j_password");
			BackEndUser user = backEndUserManager.loadUserByUsername(j_username);
			if(user != null && user.getPassword().equals(j_password)){
				inputStream = new BufferedInputStream(new ByteArrayInputStream("0".getBytes()));
			}else{
				inputStream = new BufferedInputStream(new ByteArrayInputStream("1".getBytes()));
			}
		return SUCCESS;
	}
	
	public String checkUserExist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String name = request.getParameter("name");

		BackEndUser user = backEndUserManager.loadUserByUsername(name);
		if (user != null) {
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		} else {
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"0".getBytes()));
		}
		return SUCCESS;
	}
	
	
	public String deleteUser(){
		try {
			logger.info("-----------------------delete user--------"+ userId);
			backEndUserManager.deleteBackEndUser2(Long.valueOf(userId));
			inputStream = new BufferedInputStream(new ByteArrayInputStream("0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		}
		return SUCCESS;
	}
	
	public void setBackEndRoleManager(BackEndRoleManager backEndRoleManager) {
		this.backEndRoleManager = backEndRoleManager;
	}
	
	public void setBackEndUserManager(BackEndUserManager backEndUserManager) {
		this.backEndUserManager = backEndUserManager;
	}
	
	public List<BackEndUser> getUserList() {
		return userList;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public List<BackEndRole> getRoleList() {
		return roleList;
	}

	public List<BackEndRoleVO> getRolevoList() {
		return rolevoList;
	}

	public void setRolevoList(List<BackEndRoleVO> rolevoList) {
		this.rolevoList = rolevoList;
	}
	
	
}
