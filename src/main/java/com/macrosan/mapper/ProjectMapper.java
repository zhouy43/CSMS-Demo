package com.macrosan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.macrosan.pojo.Project;

@Mapper
public interface ProjectMapper {
	List<Project> findObjects(@Param("proName")String proName, 
							@Param("startIndex")Integer startIndex,
							@Param("pageSize")Integer pageSize);
	Integer getRowCount(@Param("proName")String proName);
	void saveObject(Project projet);
	void deleteObject(Integer id);
	Project findObjectById(@Param("id")Integer id);
	int updateObject(@Param("project")Project project);
	List<Project> findObjectsByName(@Param("proName")String proName);
}
