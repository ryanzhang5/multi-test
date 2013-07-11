package org.hduo.dao;

import java.util.List;

import com.hduo.pojo.Doc;

public class DocDao extends Dao {

	public void addDoc(Doc doc) {
		getSession().save(doc);
	}

	public Doc getDoc(Long id) {
		return (Doc) getSession().get(Doc.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Doc> getAllDocs() {
		List<Doc> docs = (List<Doc>) getSession()
				.getNamedQuery("doc.selectAll").list();
		return docs;
	}

	public void deleteDoc(long id) {
		getSession().delete(getDoc(id));
	}
}
