package com.qd.server.model.po;

import java.io.Serializable;
import java.util.List;

import com.qd.server.model.vo.Role;

import lombok.Data;

@Data
@SuppressWarnings("serial")
public class QdUser implements Serializable {

	private String idnum;
	private String email;
	private String sn;
	private Long loginType;
	private Long loginLevel;
	private String telephone;
	private int errorNum;
	private String lastErrorTime;
	private String lastLoginTime;
	private int isLock;
	private String pwdLatestDate;
	private Long pwdIsedit;
	private Long lastVer;
	private String latestOpDate;
	private String createDate;
	private Long grantedUserId;
	private int enabled;
	private String cardId;
	private String endDate;
	private String startDate;
	private Long belongOrg;
	private int loginMode;
	private String password;
	private String name;
	private String code;
	private String id;
	private List<Role> roles;

}
