package com.hduo.manager;

import java.util.List;

import com.hduo.pojo.BackEndResource;

public interface BackEndResourceManager {

	List<BackEndResource> getAllBackEndResource();
	
	BackEndResource getBackEndResourceById(Long id);
}
