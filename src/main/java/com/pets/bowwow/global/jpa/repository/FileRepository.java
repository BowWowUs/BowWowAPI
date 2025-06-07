package com.pets.bowwow.global.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import com.pets.bowwow.global.jpa.entity.FileEntity;

import jakarta.persistence.LockModeType;

public interface FileRepository extends JpaRepository<FileEntity, Long>{

    Optional<FileEntity> findByFileIdAndDelYn(Long fileId, String delYn);

    List<FileEntity> findByFileIdInAndDelYn(List<Long> ids, String delYn);

    List<FileEntity> findByFileGroupNoAndDelYn(Long fileGroupNo, String delYn);

    Long countByFileGroupNoAndFileSysNmAndDelYn(Long fileGroupNo, String fileSysNm, String delYn);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<FileEntity> findFirstByOrderByFileGroupNoDesc();

}
