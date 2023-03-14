package com.gmreview.my.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gmreview.my.entity.Announcement;
import com.gmreview.my.entity.FreeBoard;
import com.gmreview.my.error.DataNotFoundException;
import com.gmreview.my.repository.FreeBoardRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FreeBoardService {

	private final FreeBoardRepository freeBoardRepository;

	public FreeBoard saveBoard(FreeBoard freeBoard) {
		return freeBoardRepository.save(freeBoard);
	}

	public Page<FreeBoard> getFreeBoardList(int page, String kw) {
		// 최신글을 먼저 출력 하기, 날짜 컬럼 (createDate) 을 desc 해서 출력
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("regTime"));

		// Pageable 객체에 두개의 값을 담아서 매개변수로 던짐 : 0,1,2,3
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));

		// Specification<Announcement> spec = search(kw);

		return this.freeBoardRepository.findAll(pageable);
	}

	public Page<FreeBoard> getMainFreeBoardList(int page, String kw) {
		// 최신글을 먼저 출력 하기, 날짜 컬럼 (createDate) 을 desc 해서 출력
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("regTime"));

		// Pageable 객체에 두개의 값을 담아서 매개변수로 던짐 : 0,1,2,3
		Pageable pageable = PageRequest.of(page, 5, Sort.by(sorts));

		// Specification<Announcement> spec = search(kw);

		return this.freeBoardRepository.findAll(pageable);
	}

	@Transactional
	public void updateViews(Integer id) {
		freeBoardRepository.updateViews(id);
	}

	public FreeBoard getFreeBoardById(Integer id) {

		Optional<FreeBoard> freeboard = this.freeBoardRepository.findById(id);

		if (freeboard.isPresent()) {
			return freeboard.get();
		} else {
			throw new DataNotFoundException("announcement not found");
		}
	}

	public void modify(FreeBoard freeBoard, String subject, String content) {
		freeBoard.setSubject(subject);
		freeBoard.setContent(content);
		this.freeBoardRepository.save(freeBoard);
	}

	public void delete(FreeBoard freeBoard) {
		this.freeBoardRepository.delete(freeBoard);
	}

}
