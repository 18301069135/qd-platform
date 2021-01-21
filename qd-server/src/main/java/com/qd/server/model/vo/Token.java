package com.qd.server.model.vo;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class Token implements Serializable {

	private String token;

	/** 登陆时间戳（毫秒） */
	private Long loginTime;

	public Token(String token, Long loginTime) {
		this.token = token;
		this.loginTime = loginTime;
	}

}
