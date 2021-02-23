package com.qd.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qd.server.entity.Trie;
import com.qd.server.mapper.TrieMapper;
import com.qd.server.query.TrieQuery;
import com.qd.server.service.ITrieService;
import com.qd.server.vo.trie.TrieInfoVo;
import com.qd.server.vo.trie.TrieListVo;
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
  * 树形字典 服务类实现
  * </p>
  *
  * @author 周琦
  * @since 2021-02-22
  */
@Service
public class TrieServiceImpl extends BaseServiceImpl<TrieMapper, Trie> implements ITrieService {

    @Autowired
    private TrieMapper trieMapper;

    /**
     * 获取数据列表
     *
     * @param query 查询条件
     * @return
     */
    @Override
    public JsonResult getList(BaseQuery query) {
        TrieQuery trieQuery = (TrieQuery) query;
        // 查询条件
        QueryWrapper<Trie> queryWrapper = new QueryWrapper<>();
        // 指标名称
        if (!StringUtils.isEmpty(trieQuery.getName())) {
            queryWrapper.like("name", trieQuery.getName());
        }
        queryWrapper.eq("is_deleted", 0);
        	queryWrapper.orderByDesc("trie_id");

        // 获取数据列表
        IPage<Trie> page = new Page<>(trieQuery.getPageIndex(), trieQuery.getPageSize());
        IPage<Trie> data = trieMapper.selectPage(page, queryWrapper);
        List<Trie> trieList = data.getRecords();
        List<TrieListVo> trieListVoList = new ArrayList<>();
        if (!trieList.isEmpty()) {
            trieList.forEach(item -> {
                TrieListVo trieListVo = new TrieListVo();
                // 拷贝属性
                BeanUtils.copyProperties(item, trieListVo);
                trieListVoList.add(trieListVo);
            });
        }

        // 返回结果
        Map<String, Object> result = new HashMap<>();
        result.put("total", data.getTotal());
        result.put("size", data.getSize());
        result.put("current", data.getCurrent());
        result.put("pages", data.getPages());
        result.put("records", trieListVoList);
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
        Trie entity = (Trie) super.getInfo(id);
        // 返回视图Vo
        TrieInfoVo trieInfoVo = new TrieInfoVo();
        // 拷贝属性
        BeanUtils.copyProperties(entity, trieInfoVo);
        return trieInfoVo;
    }

    /**
     * 添加、更新记录
     *
     * @param entity 实体对象
     * @return
     */
    @Override
    public JsonResult edit(Trie entity) {
        if (entity.getTrieId() != null &&  !"".equals(entity.getTrieId())) {
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
    public JsonResult delete(Trie entity) {
        entity.setUpdateTime(java.lang.System.currentTimeMillis());
        entity.setIsDeleted(0);
        return super.delete(entity);
    }
}