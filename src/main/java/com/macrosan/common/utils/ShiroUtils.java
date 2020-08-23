package com.macrosan.common.utils;

import org.apache.shiro.SecurityUtils;

import com.macrosan.pojo.User;

public class ShiroUtils {
	  public static String getUsername() {
		  return getUser().getUsername();
	  }
	  public static User getUser() {
		  return  (User)
           SecurityUtils.getSubject().getPrincipal();
	  }

}
