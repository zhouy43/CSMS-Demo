package com.macrosan.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.macrosan.mapper.RoleMapper;
import com.macrosan.mapper.UserMapper;
import com.macrosan.mapper.UserRoleMapper;
import com.macrosan.pojo.User;
import com.macrosan.pojo.UserRole;

@Service
public class ShiroUserRealm extends AuthorizingRealm {
	
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMapper roleMapper;
	
	/*
	 * 设置凭证管理器(与用户添加操作使用相同的加密算法) 
	 */
	@Override
	public void setCredentialsMatcher(CredentialsMatcher credentialsMatcher) {
		//构建凭证匹配对象
		HashedCredentialsMatcher cMatcher = new HashedCredentialsMatcher();
		//设置加密算法
		cMatcher.setHashAlgorithmName("MD5");
		//设置加密次数
		cMatcher.setHashIterations(1);
		super.setCredentialsMatcher(cMatcher);
	}
	
	//通过此方法完成授权信息的获取及封装,返回给authorizer进行授权
	//需要授权访问时,在对应的业务层方法上添加@RequiresPermissions("sys:admin")注解
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取用户登录信息,比如用户ID
		User user = (User) principals.getPrimaryPrincipal();
		Integer userId = user.getId();
		//根据用户ID查询用户的角色
		List<UserRole> userRoles = userRoleMapper.getUserRolesById(userId);
		Integer[] roles = new Integer[userRoles.size()];
		for (int i = 0;i<userRoles.size();i++) {
			roles[i] = userRoles.get(i).getRole_id();
		}
		//根据用户角色查询用户的权限,可能会有重复权限
		List<String> permissions = roleMapper.getPermissionsByRoleIds(roles);
		//使用set集合对权限进行去重
		Set<String> set = new HashSet<>();	//最终分割完成后的权限信息
		for (String each : permissions) {
			//对权限进行处理,根据","分割
			String[] split = each.split(",");
			for (String permission : split) {
				set.add(permission);	
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();	//set集合可以去重
		info.setStringPermissions(set);		//应该使用setStringPermissions方法设置权限!构造方法传入的set是设置角色!
		return info;
	}

	/*
	 * 通过此方法完成认证数据的获取及封装,系统底层会将认证数据传递给认证管理器,由认证管理器完成认证操作
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//1.获取用户名,先将token转换为UsernamePasswordToken
		UsernamePasswordToken uToken = (UsernamePasswordToken)token;
		String username = uToken.getUsername();
		//基于用户名查询用户信息
		User userFinded = userMapper.findUserByUserName(username);
		if(userFinded == null) {
			throw new UnknownAccountException();
		}
		//判断用户是否被禁用
		if(!userFinded.getValid()) {
			throw new LockedAccountException();
		}
		//获取盐值
		ByteSource credentialsSalt = ByteSource.Util.bytes(userFinded.getSalt());
		//封装用户信息
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
								userFinded, 		//principal(身份)
								userFinded.getPassword(), 	//密码
								credentialsSalt, 	//盐值
								getName()			//realName
								);
		return info;	//返回封装结果,返回结果会传递给认证管理器(后续认证管理器会通过此信息完成认证)
	}

}
