package com.pax.auth.shiro.realm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import org.apache.poi.hssf.record.ContinueRecord;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.pax.auth.entity.User;
import com.pax.auth.service.UserService;
import com.pax.core.util.ApplicationContextUtil;
import com.pax.core.util.DateUtils;
import com.pax.core.util.TranslationUtils;
import com.pax.core.util.WebUtils;

public class JdbcRealm extends AuthorizingRealm {
	
	/**
	 * 授权
	 * @param principals
	 * @return
	 * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		
		UserService userService = (UserService) ApplicationContextUtil.getContext()
			.getBean("userService");
		
		User user = ((User) principals.getPrimaryPrincipal());
		
		List<String> authTags = userService.getAllAuthTags(user);
		info.setStringPermissions(new HashSet<String>(authTags));
		
		/*List<String> resUrls = userService.getAllResUrls(user);
		info.setRoles(new HashSet<String>(resUrls));*/
		
		return info;
	}
	
	/**
	 * 登陆认证
	 * @param token
	 * @return
	 * @throws AuthenticationException
	 * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UserService userService = (UserService) ApplicationContextUtil.getContext()
			.getBean("userService");
		String loginname = token.getPrincipal().toString();
		User user = userService.login(loginname);
		if (user == null) {
			throw new AuthenticationException((TranslationUtils.getInstance(WebUtils.getSession() == null ? null : (Locale) WebUtils.getSession().getAttribute("locale")).__("用户名或密码错误")));
		} else {
			if ("1".equals(user.getStatus())) {
				throw new AuthenticationException((TranslationUtils.getInstance(WebUtils.getSession() == null ? null : (Locale) WebUtils.getSession().getAttribute("locale")).__("用户已被冻结")));
			}
			if ("3".equals(user.getStatus())) {
				throw new AuthenticationException(TranslationUtils.getInstance(WebUtils.getSession() == null ? null : (Locale) WebUtils.getSession().getAttribute("locale")).__("用户已被注销"));
			}
			if ("1".equals(user.getSite().getStatus())) {
				throw new AuthenticationException(TranslationUtils.getInstance(WebUtils.getSession() == null ? null : (Locale) WebUtils.getSession().getAttribute("locale")).__("系统维护中"));
			}
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, user.getPassword(),
			this.getName());
		info.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
		return info;
	}
	
}
