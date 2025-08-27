package com.winter.app.member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import com.winter.app.member.validation.AddGroup;
import com.winter.app.member.validation.UpdateGroup;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MemberVO implements UserDetails, OAuth2User {
	
	@NotBlank(message = "username is required", groups = {AddGroup.class})
	private String username;
	@Size(min = 6, max = 12, groups = {AddGroup.class})
	private String password;
	private String passwordCheck;
	@NotBlank(groups = {AddGroup.class, UpdateGroup.class})
	private String name;
	@NotBlank(groups = {AddGroup.class, UpdateGroup.class})
	@Email(groups = {AddGroup.class, UpdateGroup.class})
	private String email;
	// @Pattern(regexp = "/^0\\d{1,2}-\\d{3,4}-\\d{4}$/", message = "Phone number must be in 000-0000-0000")
	private String phone;
	@NotNull(groups = {AddGroup.class, UpdateGroup.class})
	@Past(groups = {AddGroup.class, UpdateGroup.class})
	private LocalDate birth;
	/* ----------- 스프링 시큐리티 -------------------*/
	private Long accountNonExpired;
	private Long accountNonLocked;
	private Long credentialsNonExpired;
	private Long enabled;
	/* ----------- 스프링 시큐리티 -------------------*/
	
	private ProfileVO profileVO;
	private List<RoleVO> roleVO;
	/* ----------- 소셜 -------------------*/
	private Map<String, Object> attributes;
	private String accessToken;
	private String sns;
	/* ----------- 소셜 -------------------*/
	
	/* ---------스프링 시큐리티----------- */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();
		for(RoleVO role : roleVO) {
			list.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		return list;
	}
	/* ---------스프링 시큐리티----------- */
	
}
