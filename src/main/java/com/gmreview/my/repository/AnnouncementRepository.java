package com.gmreview.my.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gmreview.my.entity.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {
	
	Announcement findBySubject(String subject);
	
	Announcement findBySubjectAndContent(String subject,String content);
	
	List<Announcement> findBySubjectLike(String subject);
	
	Page<Announcement> findAll(Pageable pageable);
	
	Page<Announcement> findAll(Specification<Announcement> spec, Pageable pageable);
	

}
