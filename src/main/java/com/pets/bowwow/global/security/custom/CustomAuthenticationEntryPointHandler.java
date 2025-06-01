package com.pets.bowwow.global.security.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pets.bowwow.global.base.BaseErrorModel;
import com.pets.bowwow.global.exception.ExceptionCode;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@Component
public class CustomAuthenticationEntryPointHandler implements AuthenticationEntryPoint{

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        log.error("Error", authException);
        BaseErrorModel baseBody = new BaseErrorModel();
        baseBody.setCode(ExceptionCode.NOT_AUTHORIZED_USER.code());
        baseBody.setDesc(ExceptionCode.NOT_AUTHORIZED_USER.message());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = objectMapper.writeValueAsString(baseBody);

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 상태 코드 401을 설정
        response.getWriter().write(responseBody);
    }

}