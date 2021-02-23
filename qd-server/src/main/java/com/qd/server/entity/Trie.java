package com.qd.server.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.qd.common.common.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;


/**
 * <p>
 * 树形字典
 * </p>
 *
 * @author 周琦
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("qd_trie")
@SuppressWarnings("serial")
public class Trie extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "model.columnName", type = IdType.UUID)
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
    private Long orders;

    /**
     * 是否叶节点
     */
    private Integer isLeaf;

    /**
     * 父节点
     */
    private String parseId;

}