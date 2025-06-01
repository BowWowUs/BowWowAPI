package com.pets.bowwow.global.base;

import java.time.LocalDateTime;

import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Column(name = "DEL_YN", length = 1, nullable = false)
    @Comment("삭제 여부")
    private String delYn = "N";

    @Column(name = "CREATED_AT", nullable = false)
    @Comment("생성일시")
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = "CREATED_BY", nullable = false)
    @Comment("생성자")
    private String createdBy;

    @Column(name = "UPDATED_AT")
    @Comment("수정일시")
    @LastModifiedBy
    private LocalDateTime updatedAt;

    @Column(name = "UPDATED_BY")
    @Comment("수정자")
    private String updatedBy;
}
