package com.emojiHW.extend.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("#{shareProperties['jwt.header']}")
    private String tokenHeader;
    private String bear="Bearer ";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = httpServletRequest.getHeader(this.tokenHeader);
        if(tokenHeader!=null && tokenHeader.startsWith(bear)) {
            String authToken = tokenHeader.substring(bear.length());
            String username = jwtTokenUtil.getUsernameFromToken(authToken);

            logger.info("checking authentication for user " + username);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                } else {
                    logger.warn("token is no longer valid");
                }
            }
        }else{
            logger.info("token doesn't container jwt bearer header");
        }
        filterChain.doFilter(httpServletRequest,httpServletResponse);
        }
    
}
