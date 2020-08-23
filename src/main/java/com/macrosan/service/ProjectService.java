package com.macrosan.service;

import java.util.List;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.Project;

public interface ProjectService {
	PageObject<Project> findObjects(String proName, Integer pageCurrent);
	void saveObject(Project projet);
	void deleteObject(Integer id);
	Project findObjectById(Integer id);
	Project updateObject(Project project);
	List<Project> findObjectByName(String proName);
}
