package com.qd.server.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.qd.common.common.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户管理
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("qd_user")
@SuppressWarnings("serial")
public class User extends BaseEntity {

	/**
	 * 主键
	 */
	@TableId(value = "user_id", type = IdType.UUID)
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
	private Long belongOrg;

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
	 * 被锁状态 0否 1是
	 */
	private Long isLock;

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
	private Long errorNum;

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
	private Long age;

	/**
	 * 手机
	 */
	private String phone;

	/**
	 * 所属区域
	 */
	private String region;

}