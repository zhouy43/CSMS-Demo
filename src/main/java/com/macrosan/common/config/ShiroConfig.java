package com.macrosan.common.config;

import java.util.LinkedHashMap;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Configuration		//注解描述一个对象为类对象
public class ShiroConfig {
	
	//添加安全管理器,并交给spring管理
	@Bean
	public SecurityManager securityManager(Realm realm,CacheManager cacheManager,SessionManager sessionManager,RememberMeManager rememberMeManager) {
		DefaultWebSecurityManager sManager = new DefaultWebSecurityManager();
		sManager.setRealm(realm);
		sManager.setCacheManager(cacheManager);
		sManager.setSessionManager(sessionManager);
		sManager.setRememberMeManager(rememberMeManager);
		return sManager;
	}
	
	//添加ShiroFilterFactoryBean对象,通过此对象设置资源的匿名访问,认证访问等
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean sFBean = new ShiroFilterFactoryBean();
		sFBean.setSecurityManager(securityManager);
		//设置登录拦截页面(登录页面)
		sFBean.setLoginUrl("/doLoginUI");
		//定义Map指定请求过滤规则(哪些资源允许匿名访问,哪些资源需要认证访问)
		LinkedHashMap<String, String> filterChainMap = new LinkedHashMap<>();
		filterChainMap.put("/bower_components/**", "anon");
		filterChainMap.put("/dist/**", "anon");
		filterChainMap.put("/plugins/**", "anon");
		filterChainMap.put("/imgs/**", "anon");
		filterChainMap.put("/users/doLogin", "anon");	//允许该路径匿名访问
		filterChainMap.put("/doLogout", "logout");	//允许该路径匿名访问
		//除了静态资源可以匿名访问,其他资源均需要认证访问,改为本地认证
		filterChainMap.put("/**", "user");
		sFBean.setFilterChainDefinitionMap(filterChainMap);
		return sFBean;
	}
	
	//设置bean对象生命周期管理
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	//为目标业务对象创建代理对象
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}
	
	//配置advisor对象,shiro底层会通过此对象的matches方法返回值,决定是否创建代理对象,进行权限控制
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}
	
	//添加shiro记住我功能
	@Bean
	public RememberMeManager shiroRememberMe() {
		CookieRememberMeManager crmManager = new CookieRememberMeManager();
		SimpleCookie cookie = new SimpleCookie();
		cookie.setName("shiroRememberMe");
		cookie.setMaxAge(7*24*60*60);		//一周有效期
		crmManager.setCookie(cookie);
		return crmManager;
	}
	
	//开启shiro缓存,缓存用户的权限信息
	@Bean
	public CacheManager shiroCacheManager() {
		return new MemoryConstrainedCacheManager();
	}
	
	//开启shiro会话时长管理
	@Bean
	public SessionManager shiroSessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setGlobalSessionTimeout(60*60*1000);
		return sessionManager;
	}
}
