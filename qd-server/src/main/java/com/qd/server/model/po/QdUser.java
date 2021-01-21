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
	private Long login_type;
	private Long login_level;
	private String telephone;
	private int error_num;
	private String last_error_time;
	private String last_login_time;
	private int is_lock;
	private String pwd_latest_date;
	private Long pwd_isedit;
	private Long last_ver;
	private String latest_op_date;
	private String create_date;
	private Long granted_user_id;
	private int enabled;
	private String card_id;
	private String end_date;
	private String start_date;
	private Long belong_org;
	private int login_mode;
	private String password;
	private String name;
	private String code;
	private String id;
	private List<Role> roles;

}
