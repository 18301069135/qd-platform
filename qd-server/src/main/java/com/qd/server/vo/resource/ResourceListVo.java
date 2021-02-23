package com.qd.server.vo.resource;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * <p>
 * 资源管理列表Vo
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
@Data
public class ResourceListVo {

	/**
	 * 资源管理ID
	 */
	private Integer id;

	/**
	 * 主键
	 */
	private String resourceId;

	/**
	 * 资源类型:1模块 2目录 3菜单 4节点
	 */
	private Integer resourceType;

	/**
	 * 类型名称
	 */
	private String resourceTypeName;

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
	 * 是否删除 0 否 1是
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

	/**
	 * 子级菜单
	 */
	private List<ResourceListVo> children = new ArrayList<>();

}