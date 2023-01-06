//package com.ideationstorm.com.ideationstorm.config;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//import static org.springframework.http.HttpHeaders.AUTHORIZATION;
//
//public class JwtAuthFilter extends OncePerRequestFilter {
//
//
//    @Override
//    protected void doFilterInternal(
//            HttpServletRequest request,
//            HttpServletResponse response,
//            FilterChain filterChain) throws ServletException, IOException {
//        final String AUTH_HEADER = request.getHeader(AUTHORIZATION);
//        final String USER_EMAIL;
//        final String JWT;
//
//        if (AUTH_HEADER == null || !AUTH_HEADER.startsWith("BEARER")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
//        JWT = AUTH_HEADER.substring(7); // location of JWT in head
//        USER_EMAIL = "todo";
//        if (USER_EMAIL != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            UserDetails userDetails = userDetailsService.loadUserByUsername(USER_EMAIL);
//            final boolean isTokenValid; //TODO
//            if (isTokenValid) {
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);
//    }
//}
