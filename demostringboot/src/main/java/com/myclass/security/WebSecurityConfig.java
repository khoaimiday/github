package com.myclass.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@ComponentScan("com.myclass")
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	// Khai báo Service lấy thông tin user từ db và khai báo phương thức mã hoá password
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Cấu hình phân quyền
		http.csrf().disable()
		.antMatcher("/admin/**")		// Gặp link này thìk kiểm tra quyền
		.authorizeRequests()
		.antMatchers("/admin/login**")
		.permitAll()
		.antMatchers("/admin/**")		// Gặp link này thì chỉ cho quyền ADMIN và MANAGER truy cập
		.hasAnyRole("ADMIN","MANAGER")
		.anyRequest()					// Những link khác truy cập thoải mái
		.permitAll();
		
		// Cấu hình form đăng nhập
		http.formLogin()
		.loginPage("/admin/login")				// Link mặc định là /login để tới trang đăng nhập (GET)
		.loginProcessingUrl("/admin/login")		// Link submit thông tin đăng nhập (POST)
		.usernameParameter("email")
		.passwordParameter("password")
		.defaultSuccessUrl("/admin/category")
		.failureUrl("/admin/login?error=true");
		
		// Cấu hình phần logout
		http.logout()
		.logoutUrl("/admin/logout")
		.logoutSuccessUrl("/admin/login")
		.deleteCookies("JSESSIONID");
		
		http.exceptionHandling()
		.accessDeniedPage("/error/403");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/static/*");
	}
}

