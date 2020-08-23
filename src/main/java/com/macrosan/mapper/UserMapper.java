package com.macrosan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.macrosan.pojo.User;
import com.macrosan.pojo.UserRole;
import com.macrosan.vo.UserRoleVo;

@Mapper
public interface UserMapper {
	int getRowCount(@Param("username")String username);
	List<User> getUserObjects(@Param("username")String username, 
							  @Param("startIndex")Integer startIndex, 
							  @Param("pageSize")Integer pageSize);
	List<UserRoleVo> getUserRole(@Param("id")Integer id);
	int saveUserObject(@Param("user")User user);
	int deleteUserObject(@Param("id")Integer id);
	User findUserById(@Param("id")Integer id);
	int updateUserInfo(@Param("user")User user);
	User findUserByUserName(@Param("username")String username);
	int updateUserPassword(
			@Param("id")Integer id,
			@Param("salt")String salt, 
			@Param("newHexPW")String newHexPW);
}
