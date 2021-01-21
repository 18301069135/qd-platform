package com.qd.server.service.impl;

import org.springframework.stereotype.Service;

import com.qd.server.model.po.QdUser;
import com.qd.server.service.IUserService;

@Service
public class UserService implements IUserService {

	@Override
	public QdUser getByCode(String userCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int edit(QdUser user, boolean isUpdatePassword) {
		// TODO Auto-generated method stub
		return 0;
	}

}
