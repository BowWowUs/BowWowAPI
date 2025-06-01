package com.pets.bowwow.global.security.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pets.bowwow.global.base.BaseErrorModel;
import com.pets.bowwow.global.exception.ExceptionCode;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler{
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
//        log.error("", accessDeniedException);
        BaseErrorModel baseBody = new BaseErrorModel();
        baseBody.setCode(ExceptionCode.NOT_PERMISSION.code());
        baseBody.setDesc(ExceptionCode.NOT_PERMISSION.message());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = objectMapper.writeValueAsString(baseBody);

        response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 상태 코드 403을 설정
        response.getWriter().write(responseBody);
    }
    
}