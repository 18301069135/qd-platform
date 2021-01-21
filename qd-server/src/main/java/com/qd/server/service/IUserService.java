package com.qd.server.service;

import com.qd.server.model.po.QdUser;

public interface IUserService {

	QdUser getByCode(String userCode);

	int edit(QdUser user, boolean isUpdatePassword);

}
