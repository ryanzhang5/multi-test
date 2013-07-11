package com.hduo.manager;

import java.util.List;

import com.hduo.pojo.Doc;

public interface DocManager {

	List<Doc> getAllDocs();

	void addDoc(Doc doc);

	void deleteDoc(long id);
	
	Doc getDocById(long id);

}
