package com.macrosan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.macrosan.pojo.UserRole;

@Mapper
public interface UserRoleMapper {
	int saveUserRole(@Param("userId")Integer userId, @Param("ids")Integer[] roleIds);
	int deleteUserRole(Integer id);
	List<UserRole> getUserRolesById(Integer id);
}
