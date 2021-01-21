package com.qd.core.util;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class ResponseInfo implements Serializable {

	private String code;
	private String message;

	public ResponseInfo(String code, String message) {
		this.code = code;
		this.message = message;
	}

}
