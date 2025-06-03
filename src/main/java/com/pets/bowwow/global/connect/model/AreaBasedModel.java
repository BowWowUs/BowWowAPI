package com.pets.bowwow.global.connect.model;

import lombok.Data;

@Data
public class AreaBasedModel {

    private String contentid;       // 콘텐츠 ID
    private String addr1;           // 기본 주소
    private String addr2;           // 상세 주소
    private String areacode;        // 지역 코드
    private String cat1;            // 대분류 코드
    private String cat2;            // 중분류 코드
    private String cat3;            // 소분류 코드
    private String contenttypeid;   // 콘텐츠 타입 ID
    private String createdtime;     // 생성 시간
    private String firstimage;      // 대표 이미지
    private String firstimage2;     // 보조 이미지
    private String cpyrhtDivCd;     // 저작권 구분 코드
    private String mapx;            // 경도
    private String mapy;            // 위도
    private String mlevel;          // 지도 레벨
    private String modifiedtime;    // 수정 시간
    private String sigungucode;     // 시군구 코드
    private String tel;             // 전화번호
    private String title;           // 장소명
    private String zipcode;         // 우편번호
    
}
