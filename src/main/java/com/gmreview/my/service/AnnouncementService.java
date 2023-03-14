package com.gmreview.my.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gmreview.my.entity.Announcement;
import com.gmreview.my.entity.Members;
import com.gmreview.my.error.DataNotFoundException;
import com.gmreview.my.repository.AnnouncementRepository;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnnouncementService {

	private final AnnouncementRepository announcementRepository;

	// 공지사항 게시판 리스트 불러오는 메소드
	public List<Announcement> getList() {

		return this.announcementRepository.findAll();

	}
	
	public Page<Announcement> mainAnnouncementsList(int page, String kw) {
		// 최신글을 먼저 출력 하기, 날짜 컬럼 (createDate) 을 desc 해서 출력
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));

		// Pageable 객체에 두개의 값을 담아서 매개변수로 던짐 : 0,1,2,3
		Pageable pageable = PageRequest.of(page, 5, Sort.by(sorts));

		//Specification<Announcement> spec = search(kw);

		return this.announcementRepository.findAll(pageable);
	}
	
	public Page<Announcement> getAnnouncementsList(int page, String kw) {
		// 최신글을 먼저 출력 하기, 날짜 컬럼 (createDate) 을 desc 해서 출력
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));

		// Pageable 객체에 두개의 값을 담아서 매개변수로 던짐 : 0,1,2,3
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

		//Specification<Announcement> spec = search(kw);

		return this.announcementRepository.findAll(pageable);
	}

	// 공지사항의 글내용 접속을 위한 값을 출력
	public Announcement getannoboard(Integer id) {

		Optional<Announcement> announcement = this.announcementRepository.findById(id);

		if (announcement.isPresent()) {
			return announcement.get();
		} else {
			throw new DataNotFoundException("announcement not found");
		}

	}

	// 공지사항 저장
	public void save(String subject, String content, Members member) {

		Announcement q = new Announcement();

		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setMembers(member);

		this.announcementRepository.save(q);
	}

	// 공지사항 삭제
	public void deleteAnnouncement(Integer id) {
		Optional<Announcement> announcement = announcementRepository.findById(id);
		if (announcement.isPresent()) {
			announcementRepository.deleteById(id);
		} else {
			throw new DataNotFoundException("Announcement not found");
		}
	}

	// 공지사항 수정
	public void updateAnnouncement(Integer id, String subject, String content) {
		Optional<Announcement> announcement = announcementRepository.findById(id);
		if (announcement.isPresent()) {
			Announcement updatedAnnouncement = announcement.get();
			updatedAnnouncement.setSubject(subject);
			updatedAnnouncement.setContent(content);
			announcementRepository.save(updatedAnnouncement);
		} else {
			throw new DataNotFoundException("Announcement not found");
		}
	}
	
	@Transactional
    public void updateViews(Integer id) {
		announcementRepository.updateViews(id);
    }
	
//private Specification<Announcement> search(String kw){
//		
//		return new Specification<Announcement>() {
//		
//			private static final long serialVerstionUID = 1L;
//			
//			@Override
//			public Predicate toPredicate(Root<Announcement> q , CriteriaQuery<?> query,CriteriaBuilder cb) {
//				
//				query.distinct(true);
//				
//				Join<Announcement, Members> u1 = q.join("members",JoinType.LEFT);
//				
//				//Join<Announcement, Answer> a = q.join("answerList", JoinType.LEFT);
//				
//				//Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
//				 
//				return cb.or(cb.like(q.get("subject"), "%" + kw + "%"), // 제목
//						 
//				cb.like(q.get("content"), "%" + kw + "%"), // 내용
//				cb.like(u1.get("username"), "%" + kw + "%")); // 질문 작성자
//				//cb.like(a.get("content"), "%" + kw + "%"), // 답변 내용
//				//cb.like(u2.get("username"), "%" + kw + "%")); // 답변 작성자
//				
//			}
//			
//		};
//	}

}
