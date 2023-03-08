package com.gmreview.my.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmreview.my.entuty.Members;

public interface MembersRepository extends JpaRepository<Members, Long> {

	Members findByEmail(String email);

}
