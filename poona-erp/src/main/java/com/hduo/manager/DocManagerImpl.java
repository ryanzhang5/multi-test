package com.hduo.manager;

import java.util.List;

import org.apache.log4j.Logger;
import org.hduo.dao.DocDao;
import org.springframework.transaction.annotation.Transactional;

import com.hduo.pojo.Doc;

public class DocManagerImpl implements DocManager {
	private final static Logger logger = Logger.getLogger(DocManagerImpl.class);
	private DocDao docDao;

	public void setDocDao(DocDao docDao) {
		this.docDao = docDao;
	}

	@Transactional
	public List<Doc> getAllDocs() {
		return docDao.getAllDocs();
	}

	@Transactional
	public void addDoc(Doc doc) {
		docDao.addDoc(doc);

	}

	@Transactional
	public void deleteDoc(long id) {
		docDao.deleteDoc(id);

	}
	@Transactional
	public Doc getDocById(long id){
		return docDao.getDoc(id);
	}
}
