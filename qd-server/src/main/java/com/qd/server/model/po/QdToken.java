package com.qd.server.model.po;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class QdToken implements Serializable {

	private String id;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 更新时间
	 */
	private String updateTime;

	/**
	 * 过期时间
	 */
	private String expireTime;

	/**
	 * LoginUser的json串
	 */
	private String val;

}
