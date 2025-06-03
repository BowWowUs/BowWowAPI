package com.pets.bowwow.global.connect.model;

import java.util.List;

import lombok.Data;

@Data
public class PublicDataPortalRS<P> {

    private ApiResponse<P> response;

    @Data
    public static class ApiResponse<P>{

        private ApiHeader header;

        private ApiBody<P> body;

    }

    @Data
    public static class ApiBody<P>{
        
        private ApiItems<P> items;

        private Integer numOfRows;

        private Integer pageNo;

        private Integer totalCount;
    }

    @Data
    public static class ApiItems<P>{
        private List<P> item;
    }

    @Data
    public static class ApiHeader{

        private String resultCode;

        private String resultMsg;

    }


}
