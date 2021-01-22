package com.qd.server.model.vo;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.qd.server.model.po.QdResource;
import com.qd.server.model.po.QdUser;

import io.jsonwebtoken.lang.Collections;
import lombok.Data;

@SuppressWarnings("serial")
@Data
public class LoginUser implements UserDetails {

	private QdUser user;

	private String token;

	/** 登陆时间戳（毫秒） */
	private Long loginTime;

	/** 过期时间戳 */
	private Long expireTime;

	public LoginUser(QdUser user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		List<Role> roles = user.getRoles();
		if (!Collections.isEmpty(roles)) {
			for (Role role : roles) {
				List<QdResource> resources = role.getResources();
				if (!Collections.isEmpty(resources)) {
					for (QdResource resource : resources) {
						authorities.add(new SimpleGrantedAuthority(String.valueOf(resource.getId())));
					}
				}
			}
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getName();
	}

	/***
	 * 判断帐号是否已过期
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * 判断帐号是否已被锁定
	 */
	@Override
	public boolean isAccountNonLocked() {
		return user.getIsLock() == 0 ? true : false;
	}

	/***
	 * 判断凭证是否已过期
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * 判断用户帐号是否已启用
	 */
	@Override
	public boolean isEnabled() {
		return user.getEnabled() == 1 ? true : false;
	}

}
