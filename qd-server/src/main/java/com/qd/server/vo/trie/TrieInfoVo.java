package com.qd.server.vo.trie;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 树形字典表单Vo
 * </p>
 *
 * @author 周琦
 * @since 2021-02-22
 */
@Data
public class TrieInfoVo {

    /**
     * 树形字典ID
     */
    private Integer id;

    /**
     * 主键
     */
    private String trieId;

    /**
     * 体系代码
     */
    private String rsCode;

    /**
     * 指标名称
     */
    private String name;

    /**
     * 指标代码
     */
    private String code;

    /**
     * 显示图标
     */
    private String isIcon;

    /**
     * 排序
     */
    private Integer orders;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否叶节点
     */
    private Integer isLeaf;

    /**
     * 父节点
     */
    private String parseId;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Integer createTime;

    /**
     * 更新时间
     */
    private Integer updateTime;

    /**
     * 是否删除
     */
    private Integer isDeleted;

}