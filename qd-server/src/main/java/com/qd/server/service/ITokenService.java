package com.qd.server.service;

import com.qd.server.model.vo.LoginUser;
import com.qd.server.model.vo.Token;

public interface ITokenService {

	Token save(LoginUser loginUser);

	LoginUser getUser(String token);

	int delete(String token);

	void update(LoginUser loginUser);

}
