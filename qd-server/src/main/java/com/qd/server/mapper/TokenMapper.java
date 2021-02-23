package com.qd.server.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.qd.server.entity.Token;

/**
 * <p>
 * 令牌管理 Mapper 接口
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
@Mapper
public interface TokenMapper {

	Token get(String token);

	void insert(Token model);

	void update(Token model);

	int delete(String token);
}
