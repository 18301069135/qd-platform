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
 * 数据字典项
 * </p>
 *
 * @author 周琦
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("qd_disc_item")
@SuppressWarnings("serial")
public class DiscItem extends BaseEntity {

    /**
     * 主键
     */
    @TableId(value = "model.columnName", type = IdType.UUID)
    private String itemId;

    /**
     * 字典项值
     */
    private String itemValue;

    /**
     * 字典项名称
     */
    private String name;

    /**
     * 默认选中
     */
    private Integer isSelect;

    /**
     * 排序
     */
    private Long orders;

    /**
     * 字典集外键
     */
    private String discId;

    /**
     * 颜色
     */
    private String itemColor;

}