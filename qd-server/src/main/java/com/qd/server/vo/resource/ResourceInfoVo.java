package com.qd.server.vo.resource;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 资源管理表单Vo
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
@Data
public class ResourceInfoVo {

    /**
     * 资源管理ID
     */
    private Integer id;

    /**
     * 主键
     */
    private String resourceId;

    /**
     * 资源类型
     */
    private Integer resourceType;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 是否叶节点  0否 1是
     */
    private Integer isLeaf;

    /**
     * 资源地址
     */
    private String resourceUrl;

    /**
     * 资源排序
     */
    private Integer orders;

    /**
     * 资源图标
     */
    private String resourceIcon;

    /**
     * 父节点主键
     */
    private String parentId;

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
     * 是否删除  0 否  1是
     */
    private Integer isDeleted;

    /**
     * 备注
     */
    private String remark;

    /**
     * 资源图标颜色
     */
    private String resourceIconColor;

}