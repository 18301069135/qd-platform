package com.qd.server.model.po;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class QdRole implements Serializable {

	private String id;

	private List<QdResource> resources;

}
