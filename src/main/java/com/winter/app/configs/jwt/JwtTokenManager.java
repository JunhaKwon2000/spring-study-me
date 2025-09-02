package com.winter.app.configs.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.winter.app.member.MemberDAO;
import com.winter.app.member.MemberVO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

// JWT 토큰을 생성하고 검증하는 매니저 클래스
@Component
public class JwtTokenManager {
	
	// 비밀 키 (노출 금지, 서버에서 안전하게 관리) - 모든 서버가 같은 값을 가져야함
	@Value("${jwt.secretKey}")
	private String secretKey;
	
	// 토큰 유효 시간
	@Value("${jwt.tokenValidTime}")
	private Long tokenValidTime;
	
	// 토큰 발급자
	@Value("${jwt.issuer}")
	private String issuer;
	
	// 암호화된 키
	private SecretKey key;
	
	// DB 접속
	@Autowired
	private MemberDAO memberDAO;
	
	// 생성자에서도 해도 됨 -> 초기화하는 거니깐
	@PostConstruct // 생성자 호출 전에 실행(생성자 호출 전에 초기화하고 싶은 것이 있을 경우 해주세용)
	public void init() {
		String k = Base64.getEncoder().encodeToString(this.secretKey.getBytes());
		key = Keys.hmacShaKeyFor(k.getBytes());
	}
	
	// Token 발급 메서드
	public String createToken(Authentication authentication) {
		Map<String, Object> claims = new java.util.HashMap<>();
		claims.put("roles", authentication.getAuthorities().toString());
		claims.put("pw", "123123123123123");
		return Jwts.builder()
					.subject(authentication.getName()) // subject: 사용자의 ID(username)
					//.claim("roles", authentication.getAuthorities().toString()) // claim: 사용자 정보(권한 + @)
					//.claims(claims)
					.issuedAt(new Date()) // 토큰 발급 시간			.
					.expiration(new Date(System.currentTimeMillis() + tokenValidTime)) // 토큰 만료 시간
					.issuer(issuer) // 토큰 발급자
					.signWith(key) // 서명 알고리즘 및 키
					.compact(); // 토큰 생성
	}
	
	// Token 검증 메서드
	public Authentication getAuthenticationByToken(String token) throws Exception {
		Claims claims = Jwts.parser()
			.verifyWith(key)
			.build()
			.parseSignedClaims(token)
			.getPayload();
		
		// 검증 통과 - DB에서 사용자 정보 조회
		MemberVO memberVO = new MemberVO();
		memberVO.setUsername(claims.getSubject());
		memberVO = memberDAO.getMemberByPassword(memberVO);
		
		// MemberVO(userDetail)를 이용해서 Authentication 객체 생성
		Authentication authentication = new UsernamePasswordAuthenticationToken(memberVO, null, memberVO.getAuthorities());
		return authentication;
		
		// 검증 실패는 예외 발생
	}
	
}
