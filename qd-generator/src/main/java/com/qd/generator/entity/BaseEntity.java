package com.qd.generator.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;

/**
 * 基类实体对象
 *
 */
@SuppressWarnings("serial")
@Data
public class BaseEntity implements Serializable {

	/**
	 * 主键ID
	 */
	@TableId(value = "id", type = IdType.AUTO)
	private Integer id;

}
