package com.qd.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qd.server.entity.System;
import com.qd.server.mapper.SystemMapper;
import com.qd.server.query.SystemQuery;
import com.qd.server.service.ISystemService;
import com.qd.server.vo.system.SystemInfoVo;
import com.qd.server.vo.system.SystemListVo;
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
  * 基础配置 服务类实现
  * </p>
  *
  * @author 周琦
  * @since 2021-02-22
  */
@Service
public class SystemServiceImpl extends BaseServiceImpl<SystemMapper, System> implements ISystemService {

    @Autowired
    private SystemMapper systemMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        SystemQuery systemQuery = (SystemQuery) query;
        // 查询条件
        QueryWrapper<System> queryWrapper = new QueryWrapper<>();
        // 名称
        if (!StringUtils.isEmpty(systemQuery.getName())) {
            queryWrapper.like("name", systemQuery.getName());
        }
        queryWrapper.eq("is_deleted", 0);
        	queryWrapper.orderByDesc("system_id");

        // 获取数据列表
        IPage<System> page = new Page<>(systemQuery.getPageIndex(), systemQuery.getPageSize());
        IPage<System> data = systemMapper.selectPage(page, queryWrapper);
        List<System> systemList = data.getRecords();
        List<SystemListVo> systemListVoList = new ArrayList<>();
        if (!systemList.isEmpty()) {
            systemList.forEach(item -> {
                SystemListVo systemListVo = new SystemListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, systemListVo);
                systemListVoList.add(systemListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", systemListVoList);
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
        System entity = (System) super.getInfo(id);
        // 返回视图Vo
        SystemInfoVo systemInfoVo = new SystemInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, systemInfoVo);
        return systemInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(System entity) {
        if (entity.getSystemId() != null &&  !"".equals(entity.getSystemId())) {
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
    public JsonResult delete(System entity) {
        entity.setUpdateTime(java.lang.System.currentTimeMillis());
        entity.setIsDeleted(0);
        return super.delete(entity);
    }
}