package com.qd.server.security;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.util.DigestUtils;

import com.qd.core.util.HttpClientUtil;

public class loginTest {

	@Test
	public void login() {
		String url = "http://192.168.1.102:8082/login";
		Map<String, String> param = new HashMap<>();
		param.put("username", "admin");
		param.put("password", DigestUtils.md5DigestAsHex("1".getBytes()));
		String result = HttpClientUtil.doPost(url, param);
		
		System.out.println(result);
	}

}
