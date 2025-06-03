package com.pets.bowwow.global.jpa.entity;

import org.hibernate.annotations.Comment;

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
@Table(name = "CODE")
@Comment("코드 테이블")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CodeEntity extends BaseEntity{

    @Id
    @Column(name = "CD", nullable = false)
    @Comment("코드")
    private String cd;

    @Column(name = "NAME", nullable = false)
    @Comment("코드 명")
    private String name;

}
