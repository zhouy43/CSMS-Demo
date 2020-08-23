package com.macrosan.common.web;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.macrosan.vo.SysResult;

@ControllerAdvice		//全局异常处理类
public class GlobalExceptionhandler {
	@ExceptionHandler
	@ResponseBody
	public SysResult doHandleRuntimeException(Throwable e) {
		e.printStackTrace();
		return SysResult.fail();
	}
	
	//添加用户登录异常处理信息
	@ExceptionHandler(ShiroException.class)
	@ResponseBody
	public SysResult doHandleShiroException(ShiroException e) {
		if(e instanceof UnknownAccountException) {
			e.printStackTrace();
			return SysResult.fail("账户不存在");
		}else if(e instanceof LockedAccountException) {
			e.printStackTrace();
			return SysResult.fail("账户被禁用");
		}else if(e instanceof IncorrectCredentialsException) {
			e.printStackTrace();
			return SysResult.fail("密码错误");
		}else if(e instanceof AuthorizationException) {
			e.printStackTrace();
			return SysResult.fail("没有此操作的权限");
		}else {
			e.printStackTrace();
			return SysResult.fail("系统维护中");
		}
	}
}
