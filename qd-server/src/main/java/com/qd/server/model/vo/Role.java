package com.qd.server.model.vo;

import org.springframework.security.core.GrantedAuthority;

import com.qd.server.model.po.QdRole;

@SuppressWarnings("serial")
public class Role extends QdRole implements GrantedAuthority {

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return null;
	}

}
