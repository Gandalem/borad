package com.gmreview.my.dto;

import lombok.Getter;
import lombok.Setter;

import org.modelmapper.ModelMapper;

import com.gmreview.my.entity.GReviewBoard;
import com.gmreview.my.entity.GameImg;

@Getter @Setter
public class GameImgDto{

    private Long id;

    private String imgName; //이미지 파일명

    private String oriImgName; //원본 이미지 파일명

    private String imgUrl; //이미지 조회 경로

    private String repimgYn; //대표 이미지 여부

    private GReviewBoard gReviewBoard;
    
    private static ModelMapper modelMapper = new ModelMapper();

    public static GameImgDto of(GameImg gameImg) {
        return modelMapper.map(gameImg,GameImgDto.class);
    }


}