package com.qd.generator.service;

import java.util.List;

import com.qd.core.service.IBaseService;
import com.qd.generator.entity.GenTableColumn;

/**
 * <p>
 * 代码生成业务表字段 服务类
 * </p>
 *
 * @author 鲲鹏
 * @since 2020-03-26
 */
public interface IGenTableColumnService extends IBaseService<GenTableColumn> {

	/**
	 * 查询表字段信息
	 *
	 * @param tableId 表ID
	 * @return
	 */
	List<GenTableColumn> selectGenTableColumnListByTableId(Integer tableId);

}
