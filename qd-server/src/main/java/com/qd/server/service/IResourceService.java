package com.qd.server.service;

import java.util.List;

import com.qd.server.model.po.QdResource;

public interface IResourceService {

	List<QdResource> getByType(String type);

}
