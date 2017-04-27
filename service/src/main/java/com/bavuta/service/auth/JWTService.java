package com.bavuta.service.auth;

import com.bavuta.model.Role;
import com.bavuta.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Calendar;

public class JWTService {
    private String secret;
    private Integer authTokenExp;
    private Integer refreshTokenExp;

    public JWTService(String secret, Integer authTokenExp, Integer refreshTokenExp) {
        this.secret = secret;
        this.authTokenExp = authTokenExp;
        this.refreshTokenExp = refreshTokenExp;
    }

    /**
     * Generates JWT auth token storing id, username, email, roles of the user and specifies expiration date.
     *
     * @param user that is used for token generation
     * @return generated JWT token
     */
    public String generateAuthToken(User user) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(user.getId()));
        claims.put("username", user.getUsername());
        claims.put("role", user.getRole().getRole().getValue());
        return buildToken(claims, authTokenExp);
    }

    /**
     * Parses user details from JWT token.
     *
     * @param JWT token to parse
     * @return retrieved user details
     */
    @SuppressWarnings("unchecked")
    public User parseAuthToken(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        User user = new User();
        user.setId(Long.valueOf(body.getSubject()));
        user.setUsername((String) body.get("username"));
        user.setRole(new Role(Role.ROLE.valueOf((String) body.get("role"))));

        return user;
    }

    /**
     * Verifies JWT refresh token.
     *
     * @param refresh token
     * @param user    to verify
     * @return verification status
     */
    public User parseRefreshToken(String token) {
        Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

        User user = new User();
        user.setId(Long.valueOf(body.getSubject()));
        user.setPassword((String) body.get("password"));

        return user;
    }

    /**
     * Generates JWT refresh token storing id, username, password of the user and specifies expiration date.
     *
     * @param user that is used for token generation
     * @return generated JWT token
     */
    public String generateRefreshToken(User user) {
        Claims claims = Jwts.claims().setSubject(String.valueOf(user.getId()));
        claims.put("password", user.getPassword());
        return buildToken(claims, refreshTokenExp);
    }

    private String buildToken(Claims claims, Integer exp) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, exp);
        return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secret).setExpiration(c.getTime()).compact();
    }

    public Integer getExpiration() {
        return authTokenExp;
    }
}