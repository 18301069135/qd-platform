package com.qd.server.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色关联
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
@Data
@Accessors(chain = true)
@TableName("qd_user_role")
@SuppressWarnings("serial")
public class UserRole implements Serializable {

	/**
	 * 用户主键
	 */
	private String userId;

	/**
	 * 角色主键
	 */
	private String roleId;

}