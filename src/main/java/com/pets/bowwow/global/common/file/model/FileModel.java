package com.pets.bowwow.global.common.file.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class FileModel {

    @Schema(description = "파일 IDX", example = "1", implementation = Long.class)
    private Long fileId;

    @Schema(description = "파일 그룹번호", example = "1", implementation = Long.class)
    private Long fileGroupNo;

    @Schema(description = "파일 원본 이름", example = "객체는 언제나 하나!.jpeg", implementation = String.class)
    private String fileOriNm = "-";

    @Schema(description = "파일 시스템 이름", example = "20250326135014-1742997014436.jpeg", implementation = String.class)
    private String fileSysNm = "-";

    @Schema(description = "파일 URL", example = "20250326/20250326135014-1742997014436.jpeg", implementation = String.class)
    private String url = "-";

}
