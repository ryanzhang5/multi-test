package com.hduo.action;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.hduo.manager.DocManager;
import com.hduo.pojo.Doc;
import com.opensymphony.xwork2.ActionSupport;

public class DocAction extends ActionSupport {
	private final static Logger logger = Logger.getLogger(DocAction.class);
	private File Filedata;
	private DocManager docManager;
	private String uploadifyFileName;
	private List<Doc> docList;
	private String fileName;
	private InputStream inputStream;
	private String realDocPath;
	private String docId;
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<Doc> getDocList() {
		return docList;
	}

	public void setDocList(List<Doc> docList) {
		this.docList = docList;
	}

	public String addDoc() throws Exception {
		System.out.println("----------------------" + realDocPath);
		HttpServletRequest request = ServletActionContext.getRequest();

		String target = realDocPath + File.separator+ request.getParameter("Filename");
		logger.info("--------------------addDoc " + target);
		Filedata.renameTo(new File(target));

		Doc doc = new Doc();
		doc.setFileName(request.getParameter("Filename"));
		doc.setFullPath(target);
		docManager.addDoc(doc);
		return null;
	}
	

	public String getUploadifyFileName() {
		return uploadifyFileName;
	}

	public void setUploadifyFileName(String uploadifyFileName) {
		this.uploadifyFileName = uploadifyFileName;
	}

	public String getAllDocs() {
		docList = docManager.getAllDocs();
		logger.info("++++++++++++++++++++++++++++++++++++++" + docList.size());
		return SUCCESS;
	}

	public String downloadDoc() {

		try {
			String newName = "";
			if (fileName != null && !"".equals(fileName)) {
				newName = new String(fileName.getBytes("iso8859-1"), "utf-8");
				logger.info("------------------------------------filename is "
						+ newName);
			}
			inputStream = new FileInputStream(realDocPath + File.separator + newName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	
	public String deleteDoc() {

		try {
			logger.info("-----------------------delete doc--------"+ docId);
			Doc doc = docManager.getDocById(Long.valueOf(docId));
			File file = new File(realDocPath + File.separator+ doc.getFileName());
			file.delete();
			docManager.deleteDoc(Long.valueOf(docId));
			inputStream = new BufferedInputStream(new ByteArrayInputStream("0".getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
			inputStream = new BufferedInputStream(new ByteArrayInputStream(
					"1".getBytes()));
		}
		return SUCCESS;
	}
	
	public DocManager getDocManager() {
		return docManager;
	}

	public void setDocManager(DocManager docManager) {
		this.docManager = docManager;
	}

	public File getFiledata() {
		return Filedata;
	}

	public void setFiledata(File filedata) {
		Filedata = filedata;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public String getRealDocPath() {
		return realDocPath;
	}

	public void setRealDocPath(String realDocPath) {
		this.realDocPath = realDocPath;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

}
