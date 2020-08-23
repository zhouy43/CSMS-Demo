package com.macrosan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.macrosan.pojo.Role;
import com.macrosan.service.RoleService;
import com.macrosan.vo.SysResult;

@RestController
@RequestMapping("role")
public class RoleController {
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("doFindRoles")
	public SysResult doGetRoles() {
		List<Role> roleList = roleService.getAllRoles();
		return SysResult.success(roleList);
	}
	
}
