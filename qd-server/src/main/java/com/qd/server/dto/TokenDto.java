package com.qd.server.dto;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class TokenDto implements Serializable {

	private String token;

	/** 登陆时间戳（毫秒） */
	private Long loginTime;

	public TokenDto(String token, Long loginTime) {
		this.token = token;
		this.loginTime = loginTime;
	}

}
