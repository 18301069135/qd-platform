package com.qd.server.dto;

import org.springframework.security.core.GrantedAuthority;

import com.qd.server.entity.Role;

@SuppressWarnings("serial")
public class RoleDto extends Role implements GrantedAuthority {

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

}
