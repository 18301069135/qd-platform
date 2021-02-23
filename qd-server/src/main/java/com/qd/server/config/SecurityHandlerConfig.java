package com.qd.server.config;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.qd.common.utils.JsonResult;
import com.qd.common.utils.ResponseUtil;
import com.qd.server.dto.TokenDto;
import com.qd.server.dto.UserDto;
import com.qd.server.entity.Resource;
import com.qd.server.entity.User;
import com.qd.server.filter.TokenFilter;
import com.qd.server.service.IResourceService;
import com.qd.server.service.ITokenService;
import com.qd.server.service.IUserService;

import io.jsonwebtoken.lang.Collections;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
public class SecurityHandlerConfig {

	@Value("${resource.type.http}")
	private String http;

	@Autowired
	private ITokenService tokenService;

	@Autowired
	private IUserService userService;

	@Autowired
	private IResourceService resourceService;

	@Bean
	public AuthenticationSuccessHandler loginSuccessHandler() {
		return new AuthenticationSuccessHandler() {
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				UserDto loginUser = (UserDto) authentication.getPrincipal();
				int b = tokenService.delete(TokenFilter.getToken(request));
				if (b == 1) {
					log.info("用户：" + loginUser.getUsername() + "已经在其它地方登录过，踢除！");
				}
				User user = userService.getById(loginUser.getUser().getUserId());
				user.setErrorNum(0L);
				userService.edit(user);
				TokenDto token = tokenService.save(loginUser);
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
				JsonResult info = JsonResult.error(HttpStatus.UNAUTHORIZED, msg, null);
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
				JsonResult info = JsonResult.success("退出成功");
				String token = TokenFilter.getToken(request);
				tokenService.delete(token);
				ResponseUtil.responseJson(response, HttpStatus.OK.value(), info);
			}
		};
	}

	/***
	 * 认证异常
	 * 
	 * @return
	 */
	@Bean
	public AuthenticationEntryPoint authenticationEntryPoint() {
		return new AuthenticationEntryPoint() {
			@Override
			public void commence(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException authException) throws IOException, ServletException {
				JsonResult info = JsonResult.error(HttpStatus.UNAUTHORIZED, authException.getMessage(), null);
				ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), info);
			}
		};
	}

	/***
	 * 授权失败
	 * 
	 * @return
	 */
	@Bean
	public AccessDeniedHandler accessDeniedHandler() {
		return new AccessDeniedHandler() {
			@Override
			public void handle(HttpServletRequest request, HttpServletResponse response,
					AccessDeniedException accessDeniedException) throws IOException, ServletException {
				JsonResult info = new JsonResult(HttpStatus.UNAUTHORIZED.value(), accessDeniedException.getMessage(),
						null);
				ResponseUtil.responseJson(response, HttpStatus.UNAUTHORIZED.value(), info);
			}
		};
	}

	/***
	 * 授权决策
	 * 
	 * @return
	 */
	@Bean
	public AccessDecisionManager accessDecisionManager() {
		return new AccessDecisionManager() {
			@Override
			public void decide(Authentication authentication, Object object,
					Collection<ConfigAttribute> configAttributes)
					throws AccessDeniedException, InsufficientAuthenticationException {
				// 当前用户资源
				Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
				if (!Collections.isEmpty(configAttributes) && !Collections.isEmpty(authorities)) {
					// 需求资源id
					String resourceId = ((List<ConfigAttribute>) configAttributes).get(0).getAttribute();
					// 遍历当前角色资源
					for (GrantedAuthority authority : authorities) {
						if (authority.getAuthority().equals(resourceId)) {
							// 如果匹配到表示用户有权限
							return;
						}
					}
				}
				// 没有匹配到表示权限不足
				throw new AccessDeniedException("权限不足！");
			}

			@Override
			public boolean supports(ConfigAttribute attribute) {
				return true;
			}

			@Override
			public boolean supports(Class<?> clazz) {
				return true;
			}
		};
	}

	/**
	 * 授权拦截器，根据url获取所需权限
	 * 
	 * @return
	 */
	@Bean
	public FilterInvocationSecurityMetadataSource FilterInvocationSecurityMetadataSourceImpl() {
		return new FilterInvocationSecurityMetadataSource() {
			@Override
			public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
				HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
				// 查询所有http类型的资源
				List<Resource> resources = resourceService.list();
				Resource resource = null;
				// 按照url匹配
				if (resources != null && resources.size() > 0) {
					for (Resource r : resources) {
						RequestMatcher requestMatcher = new AntPathRequestMatcher(r.getResourceUrl());
						if (requestMatcher.matches(request)) {
							resource = r;
						}
					}
				}
				if (resource == null) {
					return null;
				}
				return SecurityConfig.createList(String.valueOf(resource.getResourceId()));
			}

			@Override
			public Collection<ConfigAttribute> getAllConfigAttributes() {
				return null;
			}

			@Override
			public boolean supports(Class<?> clazz) {
				return false;
			}
		};
	}

}
