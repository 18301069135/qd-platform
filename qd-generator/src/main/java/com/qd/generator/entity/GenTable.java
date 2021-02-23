package com.qd.generator.entity;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 代码生成业务表
 * </p>
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("qd_gen_table")
public class GenTable extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * 表名称
	 */
	private String tableName;

	/**
	 * 表描述
	 */
	private String tableComment;

	/**
	 * 实体类名称
	 */
	private String className;

	/**
	 * 使用的模板（crud单表操作 tree树表操作）
	 */
	private String tplCategory;

	/**
	 * 生成包路径
	 */
	private String packageName;

	/**
	 * 生成模块名
	 */
	private String moduleName;

	/**
	 * 生成业务名
	 */
	private String businessName;

	/**
	 * 生成功能名
	 */
	private String functionName;

	/**
	 * 生成功能作者
	 */
	private String functionAuthor;

	/**
	 * 其它生成选项
	 */
	private String options;

	/**
	 * 备注
	 */
	private String note;

	/**
	 * 添加人
	 */
	private Integer createUser;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
	 * 更新人
	 */
	private Integer updateUser;

	/**
	 * 更新时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

	/**
	 * 有效标识
	 */
	private Integer mark;

	/**
	 * 主键信息
	 */
	private GenTableColumn pkColumn;

	/**
	 * 表列信息
	 */
	@Valid
	private List<GenTableColumn> columns;

	/**
	 * 树编码字段
	 */
	private String treeCode;

	/**
	 * 树父编码字段
	 */
	private String treeParentCode;

	/**
	 * 树名称字段
	 */
	private String treeName;

}
