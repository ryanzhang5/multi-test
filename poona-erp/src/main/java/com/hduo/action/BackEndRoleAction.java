package com.hduo.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hduo.manager.BackEndResourceManager;
import com.hduo.manager.BackEndRoleManager;
import com.hduo.pojo.BackEndResource;
import com.hduo.pojo.BackEndRole;
import com.hduo.pojo.Card;
import com.hduo.util.SystemConstant;
import com.opensymphony.xwork2.ActionSupport;

public class BackEndRoleAction extends ActionSupport {
	
	private final static Logger logger = Logger.getLogger(BackEndRoleAction.class);
	
	private BackEndRoleManager backEndRoleManager;
	
	private BackEndResourceManager backEndResourceManager;
	private InputStream inputStream;
	private String jsonString;
	private BackEndRole role;
	
	private String roleId;
	
	private List<BackEndRole> roleList ;
	
	public String getAllRole(){
		roleList = backEndRoleManager.getAllBackEndRole();
		return SUCCESS;
	}
	
	public String toAddRole(){
		return SUCCESS;
	}
	
	public String saveRole(){
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			String roleName = request.getParameter("roleName");
			String comment = request.getParameter("comments");
			logger.info("----------------------------------------saveCards--------");
			BackEndRole role = new BackEndRole(roleName, comment);
			backEndRoleManager.addBackEndRole(role);
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		}
		return SUCCESS;
	}
	
	
	
	public String deleteRole(){
		try {
			logger.info("-----------------------delete role--------"+ roleId);
			backEndRoleManager.deleteBackEndRole2(Long.valueOf(roleId));
			inputStream = new BufferedInputStream(new ByteArrayInputStream("0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		}
		return SUCCESS;
	}
	
	public String getAllResource(){
		List<BackEndResource> resourceList = backEndResourceManager.getAllBackEndResource();
		role = backEndRoleManager.getBackEndRole(Long.valueOf(roleId));
		StringBuilder sb = new StringBuilder();
		
		sb.append("[");
		for (BackEndResource resource : resourceList) {
			
			boolean select = false;
			for (BackEndResource selectedResource : role.getResources()) {
				if(selectedResource.getId().equals(resource.getId())){
					select = true;
					break;
				}
			}
			if(select){
				sb.append("{id:"+resource.getId()+", pId:"+resource.getParentId()+",open:true, checked:true,name: '"+resource.getResourceName()+"'},");
			}else{
				sb.append("{id:"+resource.getId()+", pId:"+resource.getParentId()+",open:true, name: '"+resource.getResourceName()+"'},");
			}
		}
		sb.append("]");
		
	    jsonString = sb.toString();
		jsonString.replace(",]", "]");
		//jsonString = "[{id:1, pId:0, name: '父节点1'},{id:11, pId:1, name: '子节点1'},{id:12, pId:1, name: '子节点2'}]";
		System.out.println("--------------------------------" + jsonString);
		return SUCCESS;
	}
	
	public String roleAuthorize(){
		
		
		try {
			role = backEndRoleManager.getBackEndRole(Long.valueOf(roleId));
			
			HttpServletRequest request = ServletActionContext.getRequest();
			String resourceId = request.getParameter("resourceId");
			role.getResources().clear();
			System.out.println("------------------role is " + roleId);
			System.out.println("------------------resourceId is " + resourceId);
			if(!resourceId.equals("")){
				String[] functions = resourceId.split(",");
				for (String function : functions) {
					role.getResources().add(backEndResourceManager.getBackEndResourceById(Long.valueOf(function)));
				}
			}
			backEndRoleManager.updateBackEndRole(role);
			inputStream = new BufferedInputStream(new ByteArrayInputStream("0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		}
		return SUCCESS;
	}
	
	
	public void setBackEndResourceManager(
			BackEndResourceManager backEndResourceManager) {
		this.backEndResourceManager = backEndResourceManager;
	}
	
	public void setBackEndRoleManager(BackEndRoleManager backEndRoleManager) {
		this.backEndRoleManager = backEndRoleManager;
	}
	public String getJsonString() {
		return jsonString;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
	public List<BackEndRole> getRoleList() {
		return roleList;
	}
	public BackEndRole getRole() {
		return role;
	}
}
