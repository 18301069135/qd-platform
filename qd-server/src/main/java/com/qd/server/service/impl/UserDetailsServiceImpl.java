package com.qd.server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qd.server.dto.UserDto;
import com.qd.server.entity.User;
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
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("code", userCode);
		User user = userService.getOne(queryWrapper);
		if (user == null) {
			throw new UsernameNotFoundException("用户名：" + userCode + "不存在！");
		}
		return new UserDto(user);
	}

	/***
	 * 登陆失败次数超过五次，锁住
	 */
	@Override
	public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {
		String userCode = event.getAuthentication().getPrincipal().toString();
		QueryWrapper<User> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("code", userCode);
		User user = userService.getOne(queryWrapper);
		if (user == null) {
			throw new UsernameNotFoundException("用户名：" + userCode + "不存在！");
		}
		if (user.getErrorNum() == 5) {
			user.setIsLock(1L);
		} else {
			user.setErrorNum(user.getErrorNum() + 1);
		}
		userService.edit(user);
	}

}
