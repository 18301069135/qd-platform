package com.qd.server.security;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.util.DigestUtils;

import com.alibaba.fastjson.JSONObject;
import com.qd.common.utils.HttpClientUtil;

public class LoginTest {

	@Test
	public void login() {
		String url = "http://192.168.1.102:8082/login";
		Map<String, String> param = new HashMap<>();
		param.put("username", "admin");
		param.put("password", DigestUtils.md5DigestAsHex("1".getBytes()));
		// 1、登陆
		String result = HttpClientUtil.doPost(url, param);
		JSONObject jsonObject = JSONObject.parseObject(result);
		String token = jsonObject.getString("token");
		System.out.println("令牌:" + token);
		//2、首页
		

	}

}
