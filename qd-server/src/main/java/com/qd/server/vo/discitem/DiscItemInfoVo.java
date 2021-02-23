package com.qd.server.vo.discitem;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 数据字典项表单Vo
 * </p>
 *
 * @author 周琦
 * @since 2021-02-22
 */
@Data
public class DiscItemInfoVo {

    /**
     * 数据字典项ID
     */
    private Integer id;

    /**
     * 主键
     */
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
    private Integer orders;

    /**
     * 备注
     */
    private String remark;

    /**
     * 字典集外键
     */
    private String discId;

    /**
     * 颜色
     */
    private String itemColor;

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