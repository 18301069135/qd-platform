package com.qd.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.qd.server.model.po.QdUser;
import com.qd.server.model.vo.LoginUser;
import com.qd.server.service.IUserService;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl
		implements UserDetailsService, ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

	@Autowired
	private IUserService userService;

	@Override
	public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
		if (StringUtils.isEmpty(userCode)) {
			throw new UsernameNotFoundException("userCode不能为空！");
		}
		QdUser user = userService.getByCode(userCode);
		if (user == null) {
			throw new UsernameNotFoundException("用户名：" + userCode + "不存在！");
		}
		return new LoginUser(user);
	}

	/***
	 * 登陆失败次数超过五次，锁住
	 */
	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		String userCode = event.getAuthentication().getPrincipal().toString();
		QdUser user = userService.getByCode(userCode);
		if (user.getError_num() == 5) {
			user.setIs_lock(1);
		} else {
			user.setError_num(user.getError_num() + 1);
		}
		userService.edit(user, false);
	}

}
