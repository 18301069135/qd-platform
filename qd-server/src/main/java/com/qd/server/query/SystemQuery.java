package com.qd.server.query;

import com.qd.common.common.BaseQuery;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 基础配置查询条件
 * </p>
 *
 * @author 周琦
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SystemQuery extends BaseQuery {

	/**
	 * 代码
	 */
	private String code;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 值
	 */
	private String value;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 排序
	 */
	private Long orders;

}
