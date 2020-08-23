package com.macrosan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macrosan.mapper.RoleMapper;
import com.macrosan.pojo.Role;
import com.macrosan.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> getAllRoles() {
		List<Role> roleList = roleMapper.getAllRoles();
		return roleList;
	}

}
