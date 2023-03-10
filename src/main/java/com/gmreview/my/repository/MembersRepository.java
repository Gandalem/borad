package com.gmreview.my.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmreview.my.entity.Members;

public interface MembersRepository extends JpaRepository<Members, Long> {

	Members findByEmail(String email);
	
	Optional<Members> findByName(String name);

}
