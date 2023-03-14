package com.gmreview.my.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gmreview.my.entity.FreeBoard;

public interface FreeBoardRepository extends JpaRepository<FreeBoard,Integer> {
	
	@Modifying
    @Query("UPDATE FreeBoard SET views = views+1 WHERE id = :id")
    void updateViews(@Param("id") Integer id);
	

}
