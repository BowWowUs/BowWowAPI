package com.pets.bowwow.global.security.custom;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pets.bowwow.global.base.BaseErrorModel;
import com.pets.bowwow.global.exception.AppException;
import com.pets.bowwow.global.exception.ExceptionCode;
import com.pets.bowwow.global.jpa.entity.UsersEntity;
import com.pets.bowwow.global.jpa.repository.UsersRepository;
import com.pets.bowwow.global.security.jwt.JwtTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final UsersRepository usersRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenProvider.getHeaderToken();
        log.debug("URI => {}", request.getRequestURI());
        log.debug("JwtAuthorizationFilter - TOKEN => {}", token);

        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                String userId = jwtTokenProvider.getSubject(token);
                UsersEntity users = usersRepository.findByUsernameAndDelYn(userId, "N")
                        .orElseThrow(() -> new AppException(ExceptionCode.NOT_FOUNT_USER));

                if ("Y".equals(users.getDelYn())) {
                    throw new AppException(ExceptionCode.USER_DELETE);
                }

                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                filterChain.doFilter(request, response);
            } else {
                filterChain.doFilter(request, response);
            }
        } catch (AppException e) {
            handleException(response, e);
        } catch (Exception e) {
            log.error("", e);
            handleException(response, new AppException(ExceptionCode.INTERNAL_SERVER_ERROR));
        }

    }

    private void handleException(HttpServletResponse response, AppException exception) throws IOException {
        BaseErrorModel baseBody = new BaseErrorModel();
        baseBody.setCode(exception.getCode());
        baseBody.setDesc(exception.getMessage());

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 상태 코드 401을 설정

        ObjectMapper objectMapper = new ObjectMapper();
        String responseBody = objectMapper.writeValueAsString(baseBody);

        response.getWriter().write(responseBody);
    }

}