package com.qd.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qd.server.entity.Role;
import com.qd.server.mapper.RoleMapper;
import com.qd.server.query.RoleQuery;
import com.qd.server.service.IRoleService;
import com.qd.server.vo.role.RoleInfoVo;
import com.qd.server.vo.role.RoleListVo;
import com.qd.common.common.BaseQuery;
import com.qd.common.common.BaseServiceImpl;
import com.qd.common.utils.DateUtils;
import com.qd.common.utils.JsonResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * <p>
 * 角色管理 服务类实现
 * </p>
 *
 * @author 周琦
 * @since 2021-02-22
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RoleMapper, Role> implements IRoleService {

	@Autowired
	private RoleMapper roleMapper;

	/**
	 * 获取数据列表
	 *
	 * @param query 查询条件
	 * @return
	 */
	@Override
	public JsonResult getList(BaseQuery query) {
		RoleQuery roleQuery = (RoleQuery) query;
		// 查询条件
		QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
		// 机构名称
		if (!StringUtils.isEmpty(roleQuery.getName())) {
			queryWrapper.like("name", roleQuery.getName());
		}
		queryWrapper.eq("is_deleted", 0);
		queryWrapper.orderByDesc("role_id");

		// 获取数据列表
		IPage<Role> page = new Page<>(roleQuery.getPageIndex(), roleQuery.getPageSize());
		IPage<Role> data = roleMapper.selectPage(page, queryWrapper);
		List<Role> roleList = data.getRecords();
		List<RoleListVo> roleListVoList = new ArrayList<>();
		if (!roleList.isEmpty()) {
			roleList.forEach(item -> {
				RoleListVo roleListVo = new RoleListVo();
				// 拷贝属性
				BeanUtils.copyProperties(item, roleListVo);
				roleListVoList.add(roleListVo);
			});
		}

		// 返回结果
		Map<String, Object> result = new HashMap<>();
		result.put("total", data.getTotal());
		result.put("size", data.getSize());
		result.put("current", data.getCurrent());
		result.put("pages", data.getPages());
		result.put("records", roleListVoList);
		return JsonResult.success("操作成功", result);
	}

	/**
	 * 获取详情Vo
	 *
	 * @param id 记录ID
	 * @return
	 */
	@Override
	public Object getInfo(Serializable id) {
		Role entity = (Role) super.getInfo(id);
		// 返回视图Vo
		RoleInfoVo roleInfoVo = new RoleInfoVo();
		// 拷贝属性
		BeanUtils.copyProperties(entity, roleInfoVo);
		return roleInfoVo;
	}

	/**
	 * 添加、更新记录
	 *
	 * @param entity 实体对象
	 * @return
	 */
	@Override
	public JsonResult edit(Role entity) {
		if (entity.getRoleId() != null && !"".equals(entity.getRoleId())) {
			entity.setUpdateTime(java.lang.System.currentTimeMillis());
		} else {
			entity.setCreateUser("1");
			entity.setCreateTime(java.lang.System.currentTimeMillis());
		}
		return super.edit(entity);
	}

	/**
	 * 删除记录
	 *
	 * @param entity 实体对象
	 * @return
	 */
	@Override
	public JsonResult delete(Role entity) {
		entity.setUpdateTime(java.lang.System.currentTimeMillis());
		entity.setIsDeleted(0);
		return super.delete(entity);
	}
}