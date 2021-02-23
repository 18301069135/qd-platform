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
import com.qd.common.utils.DateUtils;
import com.qd.server.dto.TokenDto;
import com.qd.server.dto.UserDto;
import com.qd.server.entity.Token;
import com.qd.server.mapper.TokenMapper;
import com.qd.server.service.ITokenService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

/**
 * <p>
 * 令牌记录 服务类实现
 * </p>
 *
 * @author 周琦
 * @since 2021-02-20
 */
@Log4j2
@Service
public class TokenServiceImpl implements ITokenService {

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
	private TokenMapper tokenMapper;

	@Override
	public TokenDto save(UserDto loginUser) {
		loginUser.setToken(UUID.randomUUID().toString());
		loginUser.setLoginTime(System.currentTimeMillis());
		loginUser.setExpireTime(loginUser.getLoginTime() + expireSeconds * 1000);
		Token model = new Token();
		model.setId(loginUser.getToken());
		model.setCreateTime(java.lang.System.currentTimeMillis());
		model.setUpdateTime(java.lang.System.currentTimeMillis());
		model.setExpireTime(DateUtils.dateTime(new Date(loginUser.getExpireTime())));
		model.setVal(JSONObject.toJSONString(loginUser));
		tokenMapper.insert(model);
		String jwtToken = createJWTToken(loginUser);
		return new TokenDto(jwtToken, loginUser.getLoginTime());
	}

	@Override
	public UserDto getUser(String jwtToken) {
		String uuid = getUUIDFromJWT(jwtToken);
		if (uuid != null) {
			Token model = tokenMapper.get(uuid);
			return toLoginUser(model);
		}
		return null;
	}

	@Override
	public int delete(String jwtToken) {
		String uuid = getUUIDFromJWT(jwtToken);
		if (uuid != null) {
			Token model = tokenMapper.get(uuid);
			UserDto loginUser = toLoginUser(model);
			if (loginUser != null) {
				return tokenMapper.delete(uuid);
			}
		}
		return 0;
	}

	@Override
	public void update(UserDto loginUser) {
		loginUser.setLoginTime(System.currentTimeMillis());
		loginUser.setExpireTime(loginUser.getLoginTime() + expireSeconds * 1000);
		Token model = tokenMapper.get(loginUser.getToken());
		model.setUpdateTime(java.lang.System.currentTimeMillis());
		model.setExpireTime(DateUtils.dateTime(new Date(loginUser.getExpireTime())));
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
	private String createJWTToken(UserDto loginUser) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(LOGIN_USER_KEY, loginUser.getToken());// 放入一个随机字符串，通过该串可找到登陆用户
		String jwtToken = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, getKeyInstance())
				.compact();
		return jwtToken;
	}

	private UserDto toLoginUser(Token model) {
		if (model == null) {
			return null;
		}
		// 校验是否已过期
		if (DateUtils.dateTime("yyyyMMddHHmmss", model.getExpireTime()).getTime() > System.currentTimeMillis()) {
			return JSONObject.parseObject(model.getVal(), UserDto.class);
		}
		return null;
	}

	private Key getKeyInstance() {
		if (KEY == null) {
			synchronized (TokenServiceImpl.class) {
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