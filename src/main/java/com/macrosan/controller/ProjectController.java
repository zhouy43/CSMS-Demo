package com.macrosan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.Project;
import com.macrosan.service.ProjectService;
import com.macrosan.vo.SysResult;

@RestController
@RequestMapping("/project/")
public class ProjectController {
	@Autowired
	private ProjectService projectService;
	/*
	 * http://localhost:8100/project/doFindObjects?pageCurrent=1&proName= 
	 * 
	 */
	@RequestMapping("doFindObjects")
	public SysResult doFindObjects(String proName,Integer pageCurrent) {
		PageObject<Project> pageObject = projectService.findObjects(proName,pageCurrent);
		return SysResult.success(pageObject);
	}
	
	/*
	 * http://localhost:8100/product/doSaveObject 
	 */
	@RequestMapping("doSaveObject")
	public SysResult doSaveObject(Project projet) {
		projectService.saveObject(projet);
		return SysResult.success();
	}
	
	/*
	 * http://localhost:8100/project/doDeleteObject 
	 */
	@RequestMapping("doDeleteObject")
	public SysResult doDeleteObject(Integer id) {
		projectService.deleteObject(id);
		return SysResult.success();
	}
	
	@RequestMapping("doFindProById")
	public SysResult doFindObjectById(Integer id) {
		Project project = projectService.findObjectById(id);
		return SysResult.success(project);
	}
	
	@RequestMapping("doFindProByName")
	public SysResult doFindObjectByName(String proName) {
		List<Project> projectList = projectService.findObjectByName(proName);
		if(projectList.size() > 0) {
			return SysResult.success(projectList);
		}else {
			return SysResult.fail();
		}
	}
	
	/*
	 * project/doUpdateProject 
	 */
	@RequestMapping("doUpdateProject")
	public SysResult doUpdateObject(Project project) {
		projectService.updateObject(project);
		return SysResult.success();
	}
}
