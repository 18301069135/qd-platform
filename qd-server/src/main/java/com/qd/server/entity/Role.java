package com.qd.server.entity;

import java.util.List;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qd.common.common.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色管理
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("qd_role")
@SuppressWarnings("serial")
public class Role extends BaseEntity {

	/**
	 * 角色id
	 */
	@TableId(value = "role_id", type = IdType.UUID)
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
	 * 是否叶节点 0否 1是
	 */
	private Integer isLeaf;

	/**
	 * 排序
	 */
	private Long orders;

}