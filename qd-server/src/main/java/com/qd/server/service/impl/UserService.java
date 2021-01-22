package com.qd.server.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qd.server.mapper.QdUserMapper;
import com.qd.server.model.po.QdUser;
import com.qd.server.service.IUserService;

@Service
public class UserService implements IUserService {

	@Resource
	private QdUserMapper userMapper;

	@Override
	public QdUser getByCode(String userCode) {
		return userMapper.get(userCode);
	}

	@Override
	public int edit(QdUser user, boolean isUpdatePassword) {
		return userMapper.update(user);
	}

}
