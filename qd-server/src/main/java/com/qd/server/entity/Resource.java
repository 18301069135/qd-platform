package com.qd.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.qd.common.common.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 资源管理
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("qd_resource")
@SuppressWarnings("serial")
public class Resource extends BaseEntity {

	/**
	 * 主键
	 */
	@TableId(value = "resource_id", type = IdType.UUID)
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
	 * 是否叶节点 0否 1是
	 */
	private Integer isLeaf;

	/**
	 * 资源地址
	 */
	private String resourceUrl;

	/**
	 * 资源排序
	 */
	private Long orders;

	/**
	 * 资源图标
	 */
	private String resourceIcon;

	/**
	 * 父节点主键
	 */
	private String parentId;

	/**
	 * 资源图标颜色
	 */
	private String resourceIconColor;

}