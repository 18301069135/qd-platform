package com.qd.server.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.qd.server.model.vo.LoginUser;
import com.qd.server.service.ITokenService;
import com.qd.server.service.impl.UserDetailsServiceImpl;

@Component
public class TokenFilter extends OncePerRequestFilter {

	public static final String TOKEN_KEY = "token";

	@Autowired
	private ITokenService tokenService;

	@Autowired
	private UserDetailsServiceImpl userLoginService;

	private static final Long MINUTES_10 = 10 * 60 * 1000L;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String token = getToken(request);
		if (!StringUtils.isEmpty(token)) {
			LoginUser loginUser = null;
			try {
				loginUser = tokenService.getUser(token);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (loginUser != null) {
				loginUser = checkLoginTime(loginUser);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(loginUser,
						null, loginUser.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		// 跨域设置
		if (response instanceof HttpServletResponse) {
			HttpServletResponse httpServletResponse = (HttpServletResponse) response;
			// 通过在响应 header 中设置 ‘*’ 来允许来自所有域的跨域请求访问。
			httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
			// 请求方式
			httpServletResponse.setHeader("Access-Control-Allow-Methods", "*");
			// （预检请求）的返回结果（即 Access-Control-Allow-Methods 和Access-Control-Allow-Headers
			// 提供的信息） 可以被缓存多久
			httpServletResponse.setHeader("Access-Control-Max-Age", "86400");
			// 首部字段用于预检请求的响应。其指明了实际请求中允许携带的首部字段
			httpServletResponse.setHeader("Access-Control-Allow-Headers", "*");
		}
		// sql,xss过滤
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		XssHttpServletRequestWrapper xssHttpServletRequestWrapper = new XssHttpServletRequestWrapper(
				httpServletRequest);
		filterChain.doFilter(xssHttpServletRequestWrapper, response);
	}

	private LoginUser checkLoginTime(LoginUser loginUser) {
		long expireTime = loginUser.getExpireTime();
		long currentTime = System.currentTimeMillis();
		if (expireTime - currentTime <= MINUTES_10) {
			String token = loginUser.getToken();
			loginUser = (LoginUser) userLoginService.loadUserByUsername(loginUser.getUsername());
			loginUser.setToken(token);
			tokenService.update(loginUser);
		}
		return loginUser;
	}

	public static String getToken(HttpServletRequest request) {
		String token = request.getParameter(TOKEN_KEY);
		if (StringUtils.isEmpty(token)) {
			token = request.getHeader(TOKEN_KEY);
		}
		return token;
	}

}
