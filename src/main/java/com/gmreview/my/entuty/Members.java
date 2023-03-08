package com.gmreview.my.entuty;


import org.springframework.security.crypto.password.PasswordEncoder;

import com.gmreview.my.constant.Role;
import com.gmreview.my.dto.MemberFormDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
@Table(name="member")
public class Members extends BaseEntity  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String address;
	
	@Enumerated(EnumType.STRING) //DB 에저장될떼 숫자로 저장되지 않고 String 형식으로저장
	private Role role;
	
	public static Members createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Members member = new Members();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.User);
        return member;
	}
	
	
}
