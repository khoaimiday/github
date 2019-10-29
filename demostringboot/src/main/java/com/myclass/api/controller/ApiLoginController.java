//package com.myclass.api.controller;
//
//import java.util.Date;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.myclass.dto.UserLogin;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//
//@RestController
//public class ApiLoginController {
//
//	@Autowired
//	AuthenticationManager authenticationManager;
//
//	@PostMapping("api/login")
//	public ResponseEntity<String> login(@RequestBody UserLogin userLogin) {
//		Authentication authenticationToken;
//		try {
//			authenticationToken = new UsernamePasswordAuthenticationToken(userLogin.getEmail(),
//					userLogin.getPassword());
//			Authentication authentication = authenticationManager.authenticate(authenticationToken);
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//
//			String token = generalToken(authentication);
//			return new ResponseEntity<String>(token, HttpStatus.OK);
//		} catch (AuthenticationException e) {
//			e.printStackTrace();
//		}
//		return new ResponseEntity<String>("Sai ten dang nhap hoac mat khau", HttpStatus.BAD_REQUEST);
//	}
//
//	public String generalToken(Authentication authentication) {
//		// Chuỗi này là bí mật chỉ có phía Server biết
//		final String JWT_SECRET = "aabbccdd";
//		// Thời gian hiệu lực của chuỗi Token (10 ngày)
//		final long JWT_EXPIRATION = 864000000L;
//		Date now = new Date();
//		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//		String token = Jwts.builder()
//				.setSubject(userDetails.getUsername())
//				.setIssuedAt(now)
//				.setExpiration(expiryDate)
//				.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
//				.compact();
//		
//		return token;
//	}
//}
