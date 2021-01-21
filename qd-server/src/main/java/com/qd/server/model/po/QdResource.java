package com.qd.server.model.po;

import java.io.Serializable;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class QdResource implements Serializable {

	private String id;

	private String resourcePattern;

	private String resourceMethod;

	private String type;

}
