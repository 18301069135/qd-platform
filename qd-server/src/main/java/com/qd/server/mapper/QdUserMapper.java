package com.qd.server.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.qd.server.model.po.QdUser;

@Mapper
public interface QdUserMapper {

	QdUser get(String code);

	int update(QdUser user);

}
