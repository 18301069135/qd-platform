package com.qd.server.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qd.common.common.BaseQuery;
import com.qd.common.common.BaseServiceImpl;
import com.qd.common.utils.JsonResult;
import com.qd.server.entity.UserRole;
import com.qd.server.mapper.UserRoleMapper;
import com.qd.server.query.UserRoleQuery;
import com.qd.server.service.IUserRoleService;
import com.qd.server.vo.userrole.UserRoleInfoVo;
import com.qd.server.vo.userrole.UserRoleListVo;

/**
  * <p>
  * 用户角色关联 服务类实现
  * </p>
  *
  * @author 周琦
  * @since 2021-02-22
  */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleMapper, UserRole> implements IUserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        UserRoleQuery userRoleQuery = (UserRoleQuery) query;
        // 查询条件
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        	queryWrapper.orderByDesc("user_id");
        	queryWrapper.orderByDesc("role_id");

        // 获取数据列表
        IPage<UserRole> page = new Page<>(userRoleQuery.getPageIndex(), userRoleQuery.getPageSize());
        IPage<UserRole> data = userRoleMapper.selectPage(page, queryWrapper);
        List<UserRole> userRoleList = data.getRecords();
        List<UserRoleListVo> userRoleListVoList = new ArrayList<>();
        if (!userRoleList.isEmpty()) {
            userRoleList.forEach(item -> {
                UserRoleListVo userRoleListVo = new UserRoleListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, userRoleListVo);
                userRoleListVoList.add(userRoleListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", userRoleListVoList);
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
        UserRole entity = (UserRole) super.getInfo(id);
        // 返回视图Vo
        UserRoleInfoVo userRoleInfoVo = new UserRoleInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, userRoleInfoVo);
        return userRoleInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(UserRole entity) {
        return super.edit(entity);
    }

    /**
     * 删除记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult delete(UserRole entity) {
        return super.delete(entity);
    }
}