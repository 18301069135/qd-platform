package com.qd.generator.query;

import com.qd.common.common.BaseQuery;

import lombok.Data;

/**
 * 表生成查询条件
 */
@Data
public class GenTableQuery extends BaseQuery {

	/**
	 * 表名
	 */
	private String tableName;

	/**
	 * 表描述
	 */
	private String tableComment;

}
