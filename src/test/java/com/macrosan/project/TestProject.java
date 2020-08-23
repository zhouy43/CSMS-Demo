package com.macrosan.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.Project;
import com.macrosan.service.ProjectService;

@SpringBootTest
public class TestProject {
	@Autowired
	private ProjectService projectService;
	
	@Test
	public void testProjectService() {
		PageObject<Project> findObjects = projectService.findObjects(null, 1);
		System.out.println(findObjects);
	}
	
}
