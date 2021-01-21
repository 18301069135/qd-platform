package com.qd.server.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.qd.server.model.po.QdToken;

@Mapper
public interface QdTokenMapper {

	QdToken get(String token);
	
	void insert(QdToken model);

	void update(QdToken model);

	int delete(String token);

}
