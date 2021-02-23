package com.qd.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qd.server.entity.DiscItem;
import com.qd.server.mapper.DiscItemMapper;
import com.qd.server.query.DiscItemQuery;
import com.qd.server.service.IDiscItemService;
import com.qd.server.vo.discitem.DiscItemInfoVo;
import com.qd.server.vo.discitem.DiscItemListVo;
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
  * 数据字典项 服务类实现
  * </p>
  *
  * @author 周琦
  * @since 2021-02-22
  */
@Service
public class DiscItemServiceImpl extends BaseServiceImpl<DiscItemMapper, DiscItem> implements IDiscItemService {

    @Autowired
    private DiscItemMapper discItemMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        DiscItemQuery discItemQuery = (DiscItemQuery) query;
        // 查询条件
        QueryWrapper<DiscItem> queryWrapper = new QueryWrapper<>();
        // 字典项名称
        if (!StringUtils.isEmpty(discItemQuery.getName())) {
            queryWrapper.like("name", discItemQuery.getName());
        }
        queryWrapper.eq("is_deleted", 0);
        	queryWrapper.orderByDesc("item_id");

        // 获取数据列表
        IPage<DiscItem> page = new Page<>(discItemQuery.getPageIndex(), discItemQuery.getPageSize());
        IPage<DiscItem> data = discItemMapper.selectPage(page, queryWrapper);
        List<DiscItem> discItemList = data.getRecords();
        List<DiscItemListVo> discItemListVoList = new ArrayList<>();
        if (!discItemList.isEmpty()) {
            discItemList.forEach(item -> {
                DiscItemListVo discItemListVo = new DiscItemListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, discItemListVo);
                discItemListVoList.add(discItemListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", discItemListVoList);
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
        DiscItem entity = (DiscItem) super.getInfo(id);
        // 返回视图Vo
        DiscItemInfoVo discItemInfoVo = new DiscItemInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, discItemInfoVo);
        return discItemInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(DiscItem entity) {
        if (entity.getItemId() != null &&  !"".equals(entity.getItemId())) {
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
    public JsonResult delete(DiscItem entity) {
        entity.setUpdateTime(java.lang.System.currentTimeMillis());
        entity.setIsDeleted(0);
        return super.delete(entity);
    }
}