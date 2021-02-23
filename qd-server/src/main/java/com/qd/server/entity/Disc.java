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
 * 数据字典集
 * </p>
 *
 * @author 周琦
 * @since 2021-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("qd_disc")
@SuppressWarnings("serial")
public class Disc extends BaseEntity {

    /**
     * 字典集名称
     */
    private String discName;

    /**
     * 字典集名称
     */
    private String discCode;

    /**
     * 字典集代码
     */
    @TableId(value = "model.columnName", type = IdType.UUID)
    private String discId;

    /**
     * 排序
     */
    private Long orders;

}