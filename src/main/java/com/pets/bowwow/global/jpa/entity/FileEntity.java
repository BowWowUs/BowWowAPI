package com.pets.bowwow.global.jpa.entity;

import com.pets.bowwow.global.base.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FILE")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FILE_ID")
    private Long fileId;

    @Column(name = "FILE_GROUP_NO", nullable = false)
    private Long fileGroupNo;

    @Column(name = "URL", nullable = false)
    private String url;

    @Column(name = "FILE_ORI_NM", nullable = false)
    private String fileOriNm;

    @Column(name = "FILE_SYS_NM", nullable = false)
    private String fileSysNm;

    @Column(name = "EXTENSION", nullable = false)
    private String extension;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "FILE_SIZE", nullable = false)
    private Double fileSize;

    @Column(name = "FILE_SIZE_UNIT", nullable = false)
    private String fileSizeUnit;
}
