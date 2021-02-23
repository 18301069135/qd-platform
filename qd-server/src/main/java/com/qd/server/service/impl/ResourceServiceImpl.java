package com.qd.server.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qd.common.common.BaseQuery;
import com.qd.common.common.BaseServiceImpl;
import com.qd.common.utils.JsonResult;
import com.qd.server.constant.ConfigConstant;
import com.qd.server.entity.Resource;
import com.qd.server.mapper.ResourceMapper;
import com.qd.server.query.ResourceQuery;
import com.qd.server.service.IResourceService;
import com.qd.server.vo.resource.ResourceInfoVo;
import com.qd.server.vo.resource.ResourceListVo;

/**
 * <p>
 * 资源管理 服务类实现
 * </p>
 *
 * @author 周琦
 * @since 2021-02-22
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<ResourceMapper, Resource> implements IResourceService {

	@Autowired
	private ResourceMapper resourceMapper;

	/**
	 * 获取数据列表
	 *
	 * @param query 查询条件
	 * @return
	 */
	@Override
	public JsonResult getList(BaseQuery query) {
		ResourceQuery resourceQuery = (ResourceQuery) query;
		// 查询条件
		QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("pid", 0);
		// 菜单名称
		if (!StringUtils.isEmpty(resourceQuery.getName())) {
			queryWrapper.like("name", resourceQuery.getName());
		}
		queryWrapper.eq("is_deleted", 0);
		queryWrapper.orderByAsc("orders");

		// 查询数据
		List<Resource> resourceList = resourceMapper.selectList(queryWrapper);
		List<ResourceListVo> resourceListVoList = new ArrayList<>();
		if (!resourceList.isEmpty()) {
			resourceList.forEach(item -> {
				ResourceListVo resourceListVo = new ResourceListVo();
				// 拷贝属性
				BeanUtils.copyProperties(item, resourceListVo);
				// 获取类型名称
				resourceListVo.setResourceTypeName(ConfigConstant.RESOURCE_TYPE_LIST.get(item.getResourceType()));
				// 获取子级
				resourceListVo.setChildren(this.getChildResourceList(item.getResourceId()));
				resourceListVoList.add(resourceListVo);
			});
		}
		return JsonResult.success("操作成功", resourceListVoList);
	}

	/**
	 * 根据父级ID获取子级菜单
	 *
	 * @param pid 父级ID
	 * @return
	 */
	private List<ResourceListVo> getChildResourceList(String pid) {
		QueryWrapper<Resource> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("parent_id", pid);
		queryWrapper.eq("is_deleted", 0);
		queryWrapper.orderByAsc("orders");

		List<ResourceListVo> resourceListVoList = new ArrayList<>();
		List<Resource> resourceList = resourceMapper.selectList(queryWrapper);
		if (!resourceList.isEmpty()) {
			resourceList.forEach(item -> {
				// 菜单列表Vo
				ResourceListVo resourceListVo = new ResourceListVo();
				// 拷贝属性
				BeanUtils.copyProperties(item, resourceListVo);
				// 获取类型名称
				resourceListVo.setResourceTypeName(ConfigConstant.RESOURCE_TYPE_LIST.get(item.getResourceType()));
				// 获取子级
				List<ResourceListVo> childrenResourceList = this.getChildResourceList(item.getResourceId());
				if (childrenResourceList != null) {
					resourceListVo.setChildren(childrenResourceList);
				}
				resourceListVoList.add(resourceListVo);
			});
		}
		return resourceListVoList;
	}

	/**
	 * 获取详情Vo
	 *
	 * @param id 记录ID
	 * @return
	 */
	@Override
	public Object getInfo(Serializable id) {
		Resource entity = (Resource) super.getInfo(id);
		// 返回视图Vo
		ResourceInfoVo resourceInfoVo = new ResourceInfoVo();
		// 拷贝属性
		BeanUtils.copyProperties(entity, resourceInfoVo);
		return resourceInfoVo;
	}

	/**
	 * 添加、更新记录
	 *
	 * @param entity 实体对象
	 * @return
	 */
	@Override
	public JsonResult edit(Resource entity) {
		if (entity.getResourceId() != null && !"".equals(entity.getResourceId())) {
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
	public JsonResult delete(Resource entity) {
		entity.setUpdateTime(java.lang.System.currentTimeMillis());
		entity.setIsDeleted(0);
		return super.delete(entity);
	}
}