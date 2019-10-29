package com.myclass.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private UserDetailsService userDetailsService;

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		final String JWT_SECRET = "aabbccdd";

		// Lấy value cuả header với key Authorization
		String tokenBearer = request.getHeader("Authorization");
		// Kiểm tra xem header Authorization có giá trị không
		if (tokenBearer != null && !tokenBearer.isEmpty()) {
			String token = tokenBearer.replace("Bearer ", "");

			// Giải mã token lấy email
			String email = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody().getSubject();

			UserDetails userDetails = userDetailsService.loadUserByUsername(email);
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userDetails, "", userDetails.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		chain.doFilter(request, response);
	}

}
