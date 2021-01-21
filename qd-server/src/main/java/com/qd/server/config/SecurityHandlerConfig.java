package com.qd.server.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.qd.core.util.ResponseInfo;
import com.qd.core.util.ResponseUtil;
import com.qd.server.filter.TokenFilter;
import com.qd.server.model.po.QdUser;
import com.qd.server.model.vo.LoginUser;
import com.qd.server.model.vo.Token;
import com.qd.server.service.ITokenService;
import com.qd.server.service.IUserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class SecurityHandlerConfig {

	@Autowired
	private ITokenService tokenService;

	@Autowired
	private IUserService userService;

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				LoginUser loginUser = (LoginUser) authentication.getPrincipal();
				int b = tokenService.delete(TokenFilter.getToken(request));
				if (b == 1) {
					log.info("用户：" + loginUser.getUsername() + "已经在其它地方登录过，踢除！");
				}
				QdUser user = userService.getByCode(loginUser.getUser().getCode());
				user.setError_num(0);
				userService.edit(user, false);
				Token token = tokenService.save(loginUser);
				ResponseUtil.responseJson(response, HttpStatus.OK.value(), token);
			}
		};
	}

	/**
	 * 登陆失败
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationFailureHandler loginFailureHandler() {
		return new AuthenticationFailureHandler() {
			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				String msg = null;
				if (exception instanceof BadCredentialsException) {
					msg = "密码错误";
				} else {
					msg = exception.getMessage();
				}
				ResponseInfo info = new ResponseInfo(HttpStatus.UNAUTHORIZED.value() + "", msg);
				ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), info);
			}
		};
	}

	/**
	 * 退出处理
	 * 
	 * @return
	 */
	@Bean
	public LogoutSuccessHandler logoutSussHandler() {
		return new LogoutSuccessHandler() {
			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				ResponseInfo info = new ResponseInfo(HttpStatus.OK.value() + "", "退出成功");
				String token = TokenFilter.getToken(request);
				tokenService.delete(token);
				ResponseUtil.responseJson(response, HttpStatus.OK.value(), info);
			}
		};
	}

}
