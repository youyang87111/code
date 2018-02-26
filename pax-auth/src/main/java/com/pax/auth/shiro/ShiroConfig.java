package com.pax.auth.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pax.auth.shiro.cache.SimpleMapCache;
import com.pax.auth.shiro.realm.JdbcRealm;
import com.pax.auth.shiro.realm.UrlPermissionResovler;
import com.pax.auth.shiro.session.SessionDAO;

import net.spy.memcached.MemcachedClient;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.PermissionResolver;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;

@Configuration
public class ShiroConfig {
	
	@Value("${shiro.deleteInvalidSessions:true}")
	private boolean deleteInvalidSessions;
	
	@Value("${shiro.sessionValidationSchedulerEnabled:true}")
	private boolean sessionValidationSchedulerEnabled;
	
	@Value("${shiro.sessionValidationInterval:60000}")
	private long sessionValidationInterval;
	
	@Value("${shiro.globalSessionTimeout:70000}")
	private long globalSessionTimeout;
	
	@Value("${shiro.sessionIdCookieName:paxSid}")
	private String sessionIdCookieName;
	
	@Bean
	public UrlPermissionResovler urlPermissionResovler() {
		UrlPermissionResovler urlPermissionResovler = new UrlPermissionResovler();
		return urlPermissionResovler;
	}
	
	@Bean
	public SessionDAO sessionDAO(MemcachedClient memcachedClient) {
		SessionDAO sessionDAO = new SessionDAO(memcachedClient);
		return sessionDAO;
	}
	
	@Bean
	public SimpleMapCache simpleMapCache(MemcachedClient memcachedClient) {
		SimpleMapCache simpleMapCache = new SimpleMapCache(memcachedClient);
		return simpleMapCache;
	}
	
	@Bean
	public SessionManager sessionManager(SessionDAO sessionDAO) {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setDeleteInvalidSessions(deleteInvalidSessions);
		sessionManager.setSessionValidationSchedulerEnabled(sessionValidationSchedulerEnabled);
		sessionManager.setSessionValidationInterval(sessionValidationInterval);
		sessionManager.setSessionDAO(sessionDAO);
		sessionManager.setGlobalSessionTimeout(globalSessionTimeout);
		sessionManager.getSessionIdCookie().setName(sessionIdCookieName);
		return sessionManager;
	}
	
	
	@Bean
	public HashedCredentialsMatcher hashMatcher() {
		HashedCredentialsMatcher hashMatcher = new HashedCredentialsMatcher();
		hashMatcher.setHashAlgorithmName("md5");
		return hashMatcher;
	}
	
	@Bean
    public JdbcRealm realm(HashedCredentialsMatcher hashMatcher,Cache simpleMapCache){
		JdbcRealm realm = new JdbcRealm();
		realm.setCredentialsMatcher(hashMatcher);
		realm.setCachingEnabled(true);
		realm.setAuthenticationCache(simpleMapCache);
		realm.setAuthorizationCache(simpleMapCache);
		
        return realm;
    }


    @Bean
    public SecurityManager securityManager(SessionManager sessionManager,Realm realm,PermissionResolver permissionResolver){
        DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
        securityManager.setSessionManager(sessionManager);
        securityManager.setRealm(realm);
        ModularRealmAuthorizer authorizer = (ModularRealmAuthorizer) securityManager.getAuthorizer();
        authorizer.setPermissionResolver(permissionResolver);
        return securityManager;
    }
    
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        
        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        
        //拦截器.
        Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/static/**", "anon");
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/**", "authc");
        
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }
    
}
