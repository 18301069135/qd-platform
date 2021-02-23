package com.qd.common.common;

import java.io.Serializable;

import lombok.Data;

/**
 * 基类实体对象
 *
 */
@SuppressWarnings("serial")
@Data
public class BaseEntity implements Serializable {

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 创建人
	 */
	private String createUser;

	/**
	 * 创建时间
	 */
	private Long createTime;

	/**
	 * 更新时间
	 */
	private Long updateTime;

	/**
	 * 是否删除
	 */
	private Integer isDeleted;

}
