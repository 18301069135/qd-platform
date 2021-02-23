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
 * 令牌记录
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("qd_token")
@SuppressWarnings("serial")
public class Token extends BaseEntity {

	/**
	 * 主键
	 */
	@TableId(value = "id", type = IdType.UUID)
	private String id;

	/**
	 * 过期时间
	 */
	private String expireTime;

	/**
	 * 字符串
	 */
	private String val;

}