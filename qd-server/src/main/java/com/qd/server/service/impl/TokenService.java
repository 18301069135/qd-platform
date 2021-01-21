package com.qd.server.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.qd.core.util.DateUtil;
import com.qd.server.mapper.QdTokenMapper;
import com.qd.server.model.po.QdToken;
import com.qd.server.model.vo.LoginUser;
import com.qd.server.model.vo.Token;
import com.qd.server.service.ITokenService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class TokenService implements ITokenService {

	/**
	 * token过期秒数
	 */
	@Value("${token.expire.seconds}")
	private Integer expireSeconds;

	/**
	 * 私钥
	 */
	@Value("${token.jwtSecret}")
	private String jwtSecret;

	@Resource
	private QdTokenMapper tokenMapper;

	@Override
	public Token save(LoginUser loginUser) {
		loginUser.setToken(UUID.randomUUID().toString());
		loginUser.setLoginTime(System.currentTimeMillis());
		loginUser.setExpireTime(loginUser.getLoginTime() + expireSeconds * 1000);
		QdToken model = new QdToken();
		model.setId(loginUser.getToken());
		model.setCreateTime(DateUtil.getNowDateString());
		model.setUpdateTime(DateUtil.getNowDateString());
		model.setExpireTime(DateUtil.getYyyyMMddHHMMDDString(new Date(loginUser.getExpireTime())));
		model.setVal(JSONObject.toJSONString(loginUser));
		tokenMapper.insert(model);
		String jwtToken = createJWTToken(loginUser);
		return new Token(jwtToken, loginUser.getLoginTime());
	}

	@Override
	public LoginUser getUser(String jwtToken) {
		String uuid = getUUIDFromJWT(jwtToken);
		if (uuid != null) {
			QdToken model = tokenMapper.get(uuid);
			return toLoginUser(model);
		}
		return null;
	}

	@Override
	public int delete(String jwtToken) {
		String uuid = getUUIDFromJWT(jwtToken);
		if (uuid != null) {
			QdToken model = tokenMapper.get(uuid);
			LoginUser loginUser = toLoginUser(model);
			if (loginUser != null) {
				return tokenMapper.delete(uuid);
			}
		}
		return 0;
	}

	@Override
	public void update(LoginUser loginUser) {
		loginUser.setLoginTime(System.currentTimeMillis());
		loginUser.setExpireTime(loginUser.getLoginTime() + expireSeconds * 1000);
		QdToken model = tokenMapper.get(loginUser.getToken());
		model.setUpdateTime(DateUtil.getNowDateString());
		model.setExpireTime(DateUtil.getYyyyMMddHHMMDDString(new Date(loginUser.getExpireTime())));
		model.setVal(JSONObject.toJSONString(loginUser));
		tokenMapper.update(model);
	}

	private static Key KEY = null;
	private static final String LOGIN_USER_KEY = "LOGIN_USER_KEY";

	/**
	 * 生成jwt
	 * 
	 * @param loginUser
	 * @return
	 */
	private String createJWTToken(LoginUser loginUser) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(LOGIN_USER_KEY, loginUser.getToken());// 放入一个随机字符串，通过该串可找到登陆用户
		String jwtToken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, getKeyInstance())
				.compact();
		return jwtToken;
	}

	private LoginUser toLoginUser(QdToken model) {
		if (model == null) {
			return null;
		}
		// 校验是否已过期
		if (DateUtil.getYyyyMMddHHMMDDSSSString(model.getExpireTime()).getTime() > System.currentTimeMillis()) {
			return JSONObject.parseObject(model.getVal(), LoginUser.class);
		}
		return null;
	}

	private Key getKeyInstance() {
		if (KEY == null) {
			synchronized (TokenService.class) {
				if (KEY == null) {// 双重锁
					byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret);
					KEY = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
				}
			}
		}
		return KEY;
	}

	private String getUUIDFromJWT(String jwt) {
		if ("null".equals(jwt) || StringUtils.isEmpty(jwt)) {
			return null;
		}
		Map<String, Object> jwtClaims = null;
		try {
			jwtClaims = Jwts.parser().setSigningKey(getKeyInstance()).parseClaimsJws(jwt).getBody();
			if (jwtClaims != null) {
				return jwtClaims.get(LOGIN_USER_KEY) + "";
			}
		} catch (ExpiredJwtException e) {
			log.error("{}已过期", jwt);
		} catch (Exception e) {
			log.error("{}", e);
		}
		return null;
	}

}
