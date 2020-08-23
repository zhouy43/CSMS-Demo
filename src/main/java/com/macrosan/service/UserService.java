package com.macrosan.service;

import java.util.List;

import com.macrosan.common.PageObject;
import com.macrosan.pojo.User;
import com.macrosan.pojo.UserRole;
import com.macrosan.vo.UserRoleVo;

public interface UserService {
	PageObject<User> getPageobjects(String username, Integer pageCurrent);
	List<UserRoleVo> getUserRoleById(Integer id);
	int saveUserObject(User user, Integer[] roleIds);
	int deleteUserObject(Integer id);
	User findUserById(Integer id);
	List<UserRole> getUserRolesById(Integer id);
	int updateUserInfo(User user, Integer[] roleIds);
	int updateUserPassword(String pwd, String newPwd, String cfgPwd);
}
