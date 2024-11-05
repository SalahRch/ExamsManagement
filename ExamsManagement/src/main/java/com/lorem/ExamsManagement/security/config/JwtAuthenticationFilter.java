package com.lorem.ExamsManagement.security.config;

import com.lorem.ExamsManagement.DAO.UserDAO;
import com.lorem.ExamsManagement.model.User;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;


@Component
public class
JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private jwtService JwtService;

    @Autowired
    private UserDAO userDAO;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Obtain header that contains jwt
        String authHeader = request.getHeader("Authorization"); //Bearer Jwt
        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request , response);
            return;
        }
        // Obtain jwt token
        String token = authHeader.split(" ")[1];
        // Obtain subject/username in Jwt
        String username = JwtService.extractUsername(token);
        // Set Authenticate object inside our security context
        User user = userDAO.findByUsername(username).get();

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
        //execute rest of filters
        filterChain.doFilter(request, response);
    }
}
