package com.qd.server.vo.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户管理表单Vo
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
@Data
public class UserInfoVo {

    /**
     * 用户管理ID
     */
    private Integer id;

    /**
     * 主键
     */
    private String userId;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 所属机构
     */
    private Integer belongOrg;

    /**
     * 启用时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startDate;

    /**
     * 停用时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;

    /**
     * 被锁状态  0否 1是
     */
    private Integer isLock;

    /**
     * 最后登录时间
     */
    private String lastLoginTime;

    /**
     * 最后被锁时间
     */
    private String lastErrorTime;

    /**
     * 密码错误次数
     */
    private Integer errorNum;

    /**
     * 固定电话
     */
    private String telephone;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 身份证号码
     */
    private String idnum;

    /**
     * 性别 1男 2女
     */
    private Integer sex;

    /**
     * 所属机构
     */
    private String orgId;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 手机
     */
    private String phone;

    /**
     * 所属区域
     */
    private String region;

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