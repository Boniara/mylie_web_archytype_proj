package com.bavuta.controller.security.jwt;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JWTProcessingFilter extends GenericFilterBean
{
	private static final Logger LOGGER = Logger.getLogger(JWTProcessingFilter.class);

	private static final String AUTHORIZATION = "Authorization";
	private static final String BEARER = "Bearer ";

	private AuthenticationManager authManager;

	public JWTProcessingFilter(AuthenticationManager authManager)
	{
		this.authManager = authManager;
	}

	@Override
	public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest request = (HttpServletRequest) rq;
		String auth = request.getHeader(AUTHORIZATION);
		if (!StringUtils.isEmpty(auth) && auth.startsWith(BEARER))
		{
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					StringUtils.removeStart(auth, BEARER), StringUtils.EMPTY);
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
			try
			{
				SecurityContextHolder.getContext().setAuthentication(authManager.authenticate(authentication));
			} catch (BadCredentialsException e)
			{
				LOGGER.error("User not authorized: " + e.getMessage());
			}
		}
		chain.doFilter(rq, rs);
	}
}