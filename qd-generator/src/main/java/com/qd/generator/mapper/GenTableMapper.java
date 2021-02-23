package com.qd.generator.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qd.generator.entity.GenTable;
import com.qd.generator.query.GenTableQuery;

/**
 * <p>
 * 代码生成业务表 Mapper 接口
 * </p>
 */
public interface GenTableMapper extends BaseMapper<GenTable> {

	/**
	 * 获取业务表列表
	 *
	 * @param query 查询条件
	 * @return
	 */
	List<GenTable> selectGenTableList(GenTableQuery query);

	/**
	 * 获取数据库表
	 *
	 * @param query 查询条件
	 * @return
	 */
	List<GenTable> selectDbTableList(GenTableQuery query);

	/**
	 * 根据表明获取数据库列表
	 *
	 * @param tableNames 数据库名
	 * @return
	 */
	List<GenTable> selectDbTableListByNames(String[] tableNames);

	/**
	 * 插入数据表
	 *
	 * @param genTable 待生成数据表
	 * @return
	 */
	int insertGenTable(GenTable genTable);

	/**
	 * 根据表ID获取表信息
	 *
	 * @param tableId 表ID
	 * @return
	 */
	GenTable selectGenTableById(Integer tableId);

	/**
	 * 修改业务表信息
	 *
	 * @param genTable 业务表
	 * @return
	 */
	int updateGenTable(GenTable genTable);

	/**
	 * 根据表名查询业务表
	 *
	 * @param tableName 表名
	 * @return
	 */
	GenTable selectGenTableByName(String tableName);

}
