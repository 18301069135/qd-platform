package com.qd.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.DigestUtils;

import com.qd.server.filter.TokenFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationSuccessHandler authenticationSuccessHandler; // 认证成功
	@Autowired
	private AuthenticationFailureHandler authenticationFailureHandler; // 认证失败
	@Autowired
	private LogoutSuccessHandler logoutSuccessHandler; // 登出成功
	@Autowired
	private AuthenticationEntryPoint authenticationEntryPoint; // 认证异常
	@Autowired
	private AccessDeniedHandler accessDeniedHandler; // 授权失败
	@Autowired
	@Qualifier("userDetailsServiceImpl")
	private UserDetailsService userDetailsService; // 自己实现的UserDetailsService
	@Autowired
	private TokenFilter tokenFilter; // token过滤器，处理客户端的token参数
	@Autowired
	private FilterInvocationSecurityMetadataSource filterInvocationSecurityMetadataSource; // 授权拦截器，根据url获取所需权限
	@Autowired
	private AccessDecisionManager accessDecisionManager; // 授权决策

	// 密码加密方式
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// 定义认证用户信息获取来源，密码校验规则等
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	// 定义安全策略
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// 关闭csrf
		http.csrf().disable();
		// 基于token，所以不需要session
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests()
				// 放开所有路径
				.antMatchers("/**").permitAll()
				// 真正的授权决策
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O o) {
						// 授权拦截器，根据url获取所需权限
						o.setSecurityMetadataSource(filterInvocationSecurityMetadataSource);
						// 授权决策
						o.setAccessDecisionManager(accessDecisionManager);
						return o;
					}
				});
		http.formLogin().loginProcessingUrl("/login").successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler);
		http.exceptionHandling()
				// 授权异常
				.accessDeniedHandler(accessDeniedHandler)
				// 认证异常
				.authenticationEntryPoint(authenticationEntryPoint);
		http.logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler);

		// 解决不允许显示在iframe的问题
		http.headers().frameOptions().disable();
		http.headers().cacheControl();

		http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
	}

	public static void main(String[] args) {
		String password = DigestUtils.md5DigestAsHex("1".getBytes());
		System.out.println(new BCryptPasswordEncoder().encode(password));

	}
}
