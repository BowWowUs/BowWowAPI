package com.pets.bowwow.global.jpa.entity;

import java.time.LocalDateTime;

import com.pets.bowwow.global.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TRAVEL_PLACE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelPlaceEntity extends BaseEntity{

    @Id
    @Column(name = "CONTENT_ID", nullable = false)
    private String contentId;       // 콘텐츠 ID

    @Column(name = "ADDR1", nullable = false)
    private String addr1;           // 기본 주소

    @Column(name = "ADDR2", nullable = false)
    private String addr2;           // 상세 주소

    @Column(name = "AREA_CODE", nullable = false)
    private String areaCode;        // 지역 코드

    @Column(name = "CAT1", nullable = false)
    private String cat1;            // 대분류 코드

    @Column(name = "CAT2", nullable = false)
    private String cat2;            // 중분류 코드

    @Column(name = "CAT3", nullable = false)
    private String cat3;            // 소분류 코드

    @Column(name = "CONTENT_TYPE_ID", nullable = false)
    private String contentTypeId;   // 콘텐츠 타입 ID

    @Column(name = "FIRST_IMAGE", nullable = false)
    private String firstImage;      // 대표 이미지

    @Column(name = "FIRST_IMAGE2", nullable = false)
    private String firstImage2;     // 보조 이미지

    @Column(name = "CPYRHT_DIV_CD", nullable = false)
    private String cpyrhtDivCd;     // 저작권 구분 코
    
    @Column(name = "LON", nullable = false)
    private Double lon;            // 경도

    @Column(name = "LAT", nullable = false)
    private Double lat;            // 위도

    @Column(name = "M_LEVEL", nullable = false)
    private String mLevel;          // 지도 레벨

    @Column(name = "MODIFIED_TIME", nullable = false)
    private LocalDateTime modifiedTime;    // 수정 시간

    @Column(name = "CREATED_TIME", nullable = false)
    private LocalDateTime createdTime;     // 생성 시간

    @Column(name = "SIGUNGU_CODE", nullable = false)
    private String sigunguCode;     // 시군구 코드

    @Column(name = "TEL", nullable = false)
    private String tel;             // 전화번호

    @Column(name = "TITLE", nullable = false)
    private String title;           // 장소명

    @Column(name = "ZIP_CODE", nullable = false)
    private String zipCode;         // 우편번호

}
