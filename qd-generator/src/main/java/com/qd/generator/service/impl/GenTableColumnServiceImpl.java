package com.qd.generator.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qd.core.service.impl.BaseServiceImpl;
import com.qd.generator.entity.GenTableColumn;
import com.qd.generator.mapper.GenTableColumnMapper;
import com.qd.generator.service.IGenTableColumnService;

/**
 * 代码生成业务表字段 服务实现类
 */
@Service
public class GenTableColumnServiceImpl extends BaseServiceImpl<GenTableColumnMapper, GenTableColumn>
		implements IGenTableColumnService {

	@Autowired
	private GenTableColumnMapper genTableColumnMapper;

	/**
	 * 获取表字段信息
	 *
	 * @param tableId 表ID
	 * @return
	 */
	@Override
	public List<GenTableColumn> selectGenTableColumnListByTableId(Integer tableId) {
		return genTableColumnMapper.selectGenTableColumnListByTableId(tableId);
	}

}
