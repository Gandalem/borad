package com.gmreview.my.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gmreview.my.entity.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement,Integer> {
	
	Announcement findBySubject(String subject);
	
	Announcement findBySubjectAndContent(String subject,String content);
	
	List<Announcement> findBySubjectLike(String subject);
	
	Page<Announcement> findAll(Pageable pageable);
	
	Page<Announcement> findAll(Specification<Announcement> spec, Pageable pageable);
	
	@Modifying
    @Query("UPDATE Announcement SET views = views+1 WHERE id = :id")
    void updateViews(@Param("id") Integer id);
	

}
