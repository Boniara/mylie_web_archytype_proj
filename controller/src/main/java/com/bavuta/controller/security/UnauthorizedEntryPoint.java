package com.bavuta.controller.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by bogdan on 4/27/2017.
 */
public class UnauthorizedEntryPoint extends LoginUrlAuthenticationEntryPoint
{
    private String error;

    public UnauthorizedEntryPoint(String loginUrl) throws JsonProcessingException
    {
        super(loginUrl);
        this.error = new ObjectMapper().writeValueAsString("Unauthorized");
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException
    {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getOutputStream().println(error);
    }
}