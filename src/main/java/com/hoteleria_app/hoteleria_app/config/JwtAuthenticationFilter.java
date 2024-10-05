package com.hoteleria_app.hoteleria_app.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.hoteleria_app.hoteleria_app.model.Permission.PermissionModel;
import com.hoteleria_app.hoteleria_app.service.User.UserService;
import com.hoteleria_app.hoteleria_app.utils.JwtService;
import java.util.*;
import java.util.stream.Collectors;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final HandlerExceptionResolver handlerExceptionResolver;

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;

    private final Map<String, Authentication> authenticationCache = new HashMap<>();
    private static final long CACHE_EXPIRATION_MS = 0; // 5 minutes

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService,
            HandlerExceptionResolver handlerExceptionResolver, UserService userService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.handlerExceptionResolver = handlerExceptionResolver;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            final String jwt = authHeader.substring(7);

            // Check if authentication is cached
            Authentication cachedAuth = authenticationCache.get(jwt);
            if (cachedAuth != null) {
                SecurityContextHolder.getContext().setAuthentication(cachedAuth);
                filterChain.doFilter(request, response);
                return;
            }

            final String userEmail = jwtService.extractUsername(jwt);
            if (userEmail != null
                    && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(userEmail);
                if (jwtService.isTokenValid(jwt, userDetails)) {
                    final Long id_user = jwtService.extractUserId(jwt);

                    Set<PermissionModel> permissions = userService.findByIdWithPermissions(id_user);
                    List<GrantedAuthority> authorities = permissions.stream()
                            .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                            .collect(Collectors.toList());

                    UsernamePasswordAuthenticationToken authToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, authorities);
                    authToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Cache the authentication
                    authenticationCache.put(jwt, authToken);

                    // Schedule cache cleanup
                    scheduleCacheCleanup(jwt);

                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            filterChain.doFilter(request, response);
        } catch (Exception exception) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        }
    }

    private void scheduleCacheCleanup(String jwt) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                authenticationCache.remove(jwt);
            }
        }, CACHE_EXPIRATION_MS);
    }
}
