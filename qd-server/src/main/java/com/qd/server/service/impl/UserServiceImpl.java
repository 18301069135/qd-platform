package com.qd.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qd.server.entity.User;
import com.qd.server.mapper.UserMapper;
import com.qd.server.query.UserQuery;
import com.qd.server.service.IUserService;
import com.qd.server.vo.user.UserInfoVo;
import com.qd.server.vo.user.UserListVo;
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
  * 用户管理 服务类实现
  * </p>
  *
  * @author 周琦
  * @since 2021-02-22
  */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        UserQuery userQuery = (UserQuery) query;
        // 查询条件
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // 名称
        if (!StringUtils.isEmpty(userQuery.getName())) {
            queryWrapper.like("name", userQuery.getName());
        }
        queryWrapper.eq("is_deleted", 0);
        	queryWrapper.orderByDesc("user_id");

        // 获取数据列表
        IPage<User> page = new Page<>(userQuery.getPageIndex(), userQuery.getPageSize());
        IPage<User> data = userMapper.selectPage(page, queryWrapper);
        List<User> userList = data.getRecords();
        List<UserListVo> userListVoList = new ArrayList<>();
        if (!userList.isEmpty()) {
            userList.forEach(item -> {
                UserListVo userListVo = new UserListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, userListVo);
                userListVoList.add(userListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", userListVoList);
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
        User entity = (User) super.getInfo(id);
        // 返回视图Vo
        UserInfoVo userInfoVo = new UserInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, userInfoVo);
        return userInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(User entity) {
        if (entity.getUserId() != null &&  !"".equals(entity.getUserId())) {
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
    public JsonResult delete(User entity) {
        entity.setUpdateTime(java.lang.System.currentTimeMillis());
        entity.setIsDeleted(0);
        return super.delete(entity);
    }
}