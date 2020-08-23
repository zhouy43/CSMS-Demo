package com.macrosan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.macrosan.pojo.Role;

@Mapper
public interface RoleMapper {
	List<Role> getAllRoles();
	List<String> getPermissionsByRoleIds(@Param("ids")Integer[] ids);
}
