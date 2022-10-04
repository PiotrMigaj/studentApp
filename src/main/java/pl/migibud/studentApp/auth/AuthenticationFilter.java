package pl.migibud.studentApp.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.stream.Collectors;

import static pl.migibud.studentApp.auth.SecurityConstants.*;

@Slf4j
@AllArgsConstructor
class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private ObjectMapper objectMapper;
    private AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try{
            AuthenticationRequest authRequest = objectMapper.readValue(request.getInputStream(), AuthenticationRequest.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    authRequest.getLogin(),
                    authRequest.getPass()
            ));
        }catch (IOException e){
            log.error(String.format("Error while authenticating: %s", e));
            throw new BadCredentialsException("Failed to authenticate", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info(String.format("Authentication success result: %s", authResult));

        if (!(authResult.getPrincipal() instanceof AppUser)) {
            throw new TypeMismatchException("Incorrect user type");
        }

        AppUser appUser = (AppUser) authResult.getPrincipal();

        String token = Jwts.builder()
                .setClaims(new HashMap<>() {
                    {
                        put(ROLE_CLAIM, appUser
                                .getRoles()
                                .stream()
                                .map(AppUserRole::getName)
                                .collect(Collectors.joining(","))); // ROLE_ADMIN,ROLE_USER
                    }
                })
                .setSubject(appUser.getUsername())
                .setExpiration(Timestamp.valueOf(LocalDateTime.now().plusDays(TOKEN_VALID_DURATION_IN_DAYS)))
                .signWith(SignatureAlgorithm.HS512, TOKEN_SIGNING_KEY)
                .compact();

        // HTTP Status
        response.setStatus(HttpStatus.ACCEPTED.value());

        // HTTP Headers
        response.addHeader(HEADER_EXPIRATION, String.valueOf(Timestamp.valueOf(LocalDateTime.now().plusDays(TOKEN_VALID_DURATION_IN_DAYS))));
        response.addHeader(HEADER_AUTH, HEADER_AUTH_BEARER_PREFIX + token);
        response.addHeader(HEADER_EXPOSED_HEADERS, HEADER_EXPIRATION + "," + HEADER_AUTH);

        // HTTP Body
        //TODO: add AppUserDto + AppUserMapper
        objectMapper.writeValue(response.getOutputStream(), appUser);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.error(String.format("Unsuccessful authentication : %s", failed));
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.getWriter().write(failed.getMessage());
        response.getWriter().flush();
    }
}
