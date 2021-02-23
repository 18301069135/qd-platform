package com.qd.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qd.server.entity.Disc;
import com.qd.server.mapper.DiscMapper;
import com.qd.server.query.DiscQuery;
import com.qd.server.service.IDiscService;
import com.qd.server.vo.disc.DiscInfoVo;
import com.qd.server.vo.disc.DiscListVo;
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
  * 数据字典集 服务类实现
  * </p>
  *
  * @author 周琦
  * @since 2021-02-22
  */
@Service
public class DiscServiceImpl extends BaseServiceImpl<DiscMapper, Disc> implements IDiscService {

    @Autowired
    private DiscMapper discMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        DiscQuery discQuery = (DiscQuery) query;
        // 查询条件
        QueryWrapper<Disc> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("is_deleted", 0);
        	queryWrapper.orderByDesc("disc_id");

        // 获取数据列表
        IPage<Disc> page = new Page<>(discQuery.getPageIndex(), discQuery.getPageSize());
        IPage<Disc> data = discMapper.selectPage(page, queryWrapper);
        List<Disc> discList = data.getRecords();
        List<DiscListVo> discListVoList = new ArrayList<>();
        if (!discList.isEmpty()) {
            discList.forEach(item -> {
                DiscListVo discListVo = new DiscListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, discListVo);
                discListVoList.add(discListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", discListVoList);
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
        Disc entity = (Disc) super.getInfo(id);
        // 返回视图Vo
        DiscInfoVo discInfoVo = new DiscInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, discInfoVo);
        return discInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Disc entity) {
        if (entity.getDiscId() != null &&  !"".equals(entity.getDiscId())) {
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
    public JsonResult delete(Disc entity) {
        entity.setUpdateTime(java.lang.System.currentTimeMillis());
        entity.setIsDeleted(0);
        return super.delete(entity);
    }
}