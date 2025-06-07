package com.pets.bowwow.global.common.file.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.pets.bowwow.global.common.file.model.FileModel;
import com.pets.bowwow.global.common.file.model.FileSizeUnit;
import com.pets.bowwow.global.common.file.model.FileType;
import com.pets.bowwow.global.exception.AppException;
import com.pets.bowwow.global.exception.ExceptionCode;
import com.pets.bowwow.global.jpa.entity.FileEntity;
import com.pets.bowwow.global.jpa.repository.FileRepository;
import com.pets.bowwow.global.security.Principal;
import com.pets.bowwow.global.util.DateUtils;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileService {

    private String activeProfile;

    
    @Value("${app.file.path}")
    private String BASE_URL;
    
    private final FileRepository fileRepository;

    private final Environment environment;

    @PostConstruct
    public void init (){
        activeProfile = environment.getProperty("spring.profiles.active", "default");
    }

    /**
     * 신규 파일 저장 메소드
     * @param multipartFiles
     * @param desc
     * @return
     */
    @Transactional
    public Long saveFile(List<MultipartFile> multipartFiles, FileType fileType){
        return saveFile(this.lastFileGroupNo(), multipartFiles, fileType);
    }

    /**
     * 기존 파일 그룹번호에 파일 더 추가하는 메소드
     * @param fileGroupNo 파일 그룹번호
     * @param multipartFiles 파일데이터
     * @param desc 파일내용 [메모 작성시 사용된 파일인지, 스케줄 작성시 사용된 파일인지..]
     * @return
     */
    @Transactional
    public Long saveFile(Long fileGroupNo, List<MultipartFile> multipartFiles, FileType fileType){

        String fileUrl = new StringBuilder()
                .append(this.BASE_URL)
                .append(DateUtils.formatLocalDateTime(LocalDateTime.now(), "yyyymmdd"))
                .append("/")
                .toString();

        List<FileEntity> comFileBas = new ArrayList<>();

        for(MultipartFile file : multipartFiles){
            FileEntity node = this.fileUpload(file, fileUrl, fileGroupNo, fileType);
            comFileBas.add(node);
        }

        fileRepository.saveAll(comFileBas);

        return fileGroupNo;
    }

    /**
     * 파일 리스트 삭제
     * @param fileIds
     */
    public void deleteFile(List<Long> fileIds){

        List<FileEntity> files = fileRepository.findByFileIdInAndDelYn(fileIds, "N");

        for(FileEntity file : files){
            file.setDelYn("Y");
            if("prod".equals(this.activeProfile)){
                this.deleteFile(file.getUrl());
            }

            file.setUpdatedBy(Principal.getUser());
            file.setUpdatedAt(LocalDateTime.now());
            fileRepository.save(file);
        }
    }

    /**
     * 파일 데이터 반환
     * @return
     */
    public FileModel getFileByFileId(Long fileId){
        Optional<FileEntity> fileWrapper = fileRepository.findByFileIdAndDelYn(fileId, "N");

        if(fileWrapper.isEmpty()){
            return new FileModel();
        }
        FileEntity file = fileWrapper.get();
        
        FileModel result = new FileModel();
        result.setFileId(file.getFileId());
        result.setFileGroupNo(file.getFileGroupNo());
        result.setFileOriNm(file.getFileOriNm());
        result.setFileSysNm(file.getFileSysNm());
        result.setUrl(file.getUrl());

        return result;
    }

    /**
     * 파일 데이터 반환
     * @return
     */
    public List<FileModel> getFileByFileGroupNo(Long fileGroupNo){
        List<FileEntity> files = fileRepository.findByFileGroupNoAndDelYn(fileGroupNo, "N");

        if(files.isEmpty()){
            return new ArrayList<>();
        }

        return files.stream()
            .map(entity -> {
                FileModel model = new FileModel();
                model.setFileId(entity.getFileId());
                model.setFileGroupNo(entity.getFileGroupNo());
                model.setFileOriNm(entity.getFileOriNm());
                model.setFileSysNm(entity.getFileSysNm());
                model.setUrl(entity.getUrl());

                return model;
            })
            .toList();
    }

    /**
     * 파일 업로드
     * @param file
     * @param fileUrl
     * @param fileGroupNo
     * @return
     */
    private FileEntity fileUpload(MultipartFile file, String fileUrl, Long fileGroupNo, FileType fileType){

        if(file == null) throw new AppException(ExceptionCode.FILE_ERROR);

        String oriNm = file.getOriginalFilename();
        String extension = oriNm.substring(oriNm.lastIndexOf("."));
        
        String fileName;

        do{
            String etc = String.valueOf(System.currentTimeMillis()); // 동명이인 방지
            fileName = this.getFileName(extension, etc);
        } while(!this.fileValid(fileName, fileGroupNo));

        try {
            // 파일 저장 경로
            String filePath = fileUrl + fileName;

            File saveFile = new File(filePath);
            
            if(this.activeProfile.equals("prod")){
                Path directoryPath = Paths.get(fileUrl);
                if (!Files.exists(directoryPath)) {
                    Files.createDirectories(directoryPath);
                }

                file.transferTo(saveFile);
            }

            double fileSize = file.getSize() / 1024;

            FileEntity node = FileEntity.builder()
                .fileGroupNo(fileGroupNo)
                .url(filePath.replaceAll(this.BASE_URL, ""))
                .fileOriNm(oriNm)
                .fileSysNm(fileName)
                .extension(extension)
                .fileType(fileType)
                .fileSize(fileSize)
                .fileSizeUnit(FileSizeUnit.KB.name())
                .build();

            return node;
        } catch (IOException e) {
            log.error("",e);
            throw new AppException(ExceptionCode.FILE_ERROR);
        }
    }

    /**
     * 파일 이름 생성
     * @param extension 확장자
     * @param etcName 저장시킬 이름 배열
     * @return
     */
    private String getFileName(String extension, String... etcName){

        String dateTime = DateUtils.formatLocalDateTime(LocalDateTime.now(), "yyyymmddhhmiss");

        StringBuilder result = new StringBuilder();
        result.append(dateTime);

        for (String name : etcName) {
            result.append("-")
                    .append(name);
        }

        result.append(extension);

        return result.toString();
    }

     /**
     * 해당 그룹번호에 중복된 파일 Sys이름이 있는지 체크하는 기능 [중복 : false , 사용가능 : true]
     * @param fileName 확인할 파일이름
     * @param fileGroupNo 파일 그룹번호
     * @return
     */
    private Boolean fileValid(String fileName, Long fileGroupNo){
        return fileRepository.countByFileGroupNoAndFileSysNmAndDelYn(fileGroupNo, fileName, "N") < 1;
    }

     /**
     * 마지막 fileGroupNo 가져와 +1 후 반환
     *
     * @return
     */
    private Long lastFileGroupNo() {
        Optional<FileEntity> lastComFileBas = fileRepository.findFirstByOrderByFileGroupNoDesc();
        Long fileGroupNo = !lastComFileBas.isPresent() ? 1 : lastComFileBas.get().getFileGroupNo() + 1;

        return fileGroupNo;
    }

    /**
     * 물리 파일 삭제
     * @param url
     */
    private Boolean deleteFile(String url){
        return new File(this.BASE_URL + url).delete();
    }

}
