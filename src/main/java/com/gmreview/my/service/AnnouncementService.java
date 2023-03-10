package com.gmreview.my.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gmreview.my.entity.Announcement;
import com.gmreview.my.entity.Members;
import com.gmreview.my.error.DataNotFoundException;
import com.gmreview.my.repository.AnnouncementRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnnouncementService {

	private final AnnouncementRepository announcementRepository;

	// 공지사항 게시판 리스트 불러오는 메소드
	public List<Announcement> getList() {

		return this.announcementRepository.findAll();

	}
	public Page<Announcement> getAnnouncementsList(int pageNum, int pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return announcementRepository.findAll(pageable);
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

	public void save(String subject, String content, Members member) {

		Announcement q = new Announcement();

		q.setSubject(subject);
		q.setContent(content);
		q.setCreateDate(LocalDateTime.now());
		q.setMembers(member);

		this.announcementRepository.save(q);
	}

}
