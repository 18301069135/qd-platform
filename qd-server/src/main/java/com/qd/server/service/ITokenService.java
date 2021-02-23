package com.qd.server.service;

import com.qd.server.dto.TokenDto;
import com.qd.server.dto.UserDto;

/**
 * <p>
 * 令牌记录 服务类
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
public interface ITokenService {

	TokenDto save(UserDto loginUser);

	UserDto getUser(String token);

	int delete(String token);

	void update(UserDto loginUser);

}