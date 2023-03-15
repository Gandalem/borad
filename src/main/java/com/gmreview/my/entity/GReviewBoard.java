package com.gmreview.my.entity;

import java.util.List;

import com.gmreview.my.dto.GReviewFormDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class GReviewBoard extends BaseEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String gname;
	
	private String gcontent;
	
	@Lob
	private Integer price;
	
	//평점을 담는 변수
	private double totalscore;
	
	@OneToMany(mappedBy = "gReviewBoard", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Ganswer> ganswer;
	
	// 평점 평균 계산 메소드
	public double getAverageRating() {
        int sum = 0;
        int count = 0;
        for (Ganswer ganswer : this.ganswer) {
            sum += ganswer.getScore();
            count++;
        }
        if (count == 0) {
        	return 0.0;
        }
        return (double) sum / count;
    }
    
    public static GReviewBoard creategReviewBoard(GReviewFormDto gReviewFormDto, double totalscore){
    	GReviewBoard gReviewBoard = new GReviewBoard();
    	gReviewBoard.setGname(gReviewFormDto.getGname());
    	gReviewBoard.setGcontent(gReviewFormDto.getGcontent());
    	gReviewBoard.setPrice(gReviewFormDto.getPrice());
    	gReviewBoard.setTotalscore(totalscore);
        return gReviewBoard;
	}
	
}
