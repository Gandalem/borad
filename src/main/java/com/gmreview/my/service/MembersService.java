package com.gmreview.my.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gmreview.my.DateNotFoundExceptoin;
import com.gmreview.my.entity.Members;
import com.gmreview.my.repository.MembersRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class MembersService implements UserDetailsService{
	
	private final MembersRepository membersRepository;
	
	public Members saveMember(Members member){
        validateDuplicateMember(member);
        return membersRepository.save(member);
    }

    private void validateDuplicateMember(Members member){
        Members findMember = membersRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Members member = membersRepository.findByEmail(email);
        
        //Log.info("====> : "+ email);
        //Log.info("====> : "+ member.getEmail());
        //Log.info("====> : "+ member.getRole());
        //Log.info("====> : "+ member.getRole().toString());
        
        //넘겨 받은 ID (email)이 DB에 존재하지 않는 경우
        if(member == null){
            throw new UsernameNotFoundException(email); //예외(오류)를 강제로 발생 시킴
        }
        
        //user 객체에는 3가지 값이 반드시 적용 : 1 id 2 pass 3 authorization(role)
        //Spring Security 에서 인증이 완료 되면 ROLE_USER, ROLE_ADMIN
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
    
public Members getName(String member) {
		
		Members members = this.membersRepository.findByEmail(member);
		
		if(members!=null) {
			return members;
		}else {
			throw new DateNotFoundExceptoin("member not found");
		}
	}
	
}
