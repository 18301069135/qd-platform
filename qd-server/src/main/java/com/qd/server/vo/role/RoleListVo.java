package com.qd.server.vo.role;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 角色管理列表Vo
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
@Data
public class RoleListVo {

    /**
    * 角色管理ID
    */
    private Integer id;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 父节点id
     */
    private String parentId;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 是否叶节点  0否 1是
     */
    private Integer isLeaf;

    /**
     * 备注
     */
    private String remark;

    /**
     * 排序
     */
    private Integer orders;

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