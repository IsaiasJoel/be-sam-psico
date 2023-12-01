package org.nicmaish.besampsico.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.io.Serializable;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

//Clase S1
@Component
public class JwtTokenUtil implements Serializable {

    //milisegundos
	public final long JWT_TOKEN_VALIDITY = 5 * 60 * 60 * 1000; //5 horas
//	public final long JWT_TOKEN_VALIDITY = 1000; //(para pruebas)

    @Value("${jwt.secret}") //EL Expression Language
    private String secret;

    //Payload //valores internos del token //claims
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("role", userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining()));
//        claims.put("test", "value-test");

        return doGenerateToken(claims, userDetails.getUsername());
    }

    private String doGenerateToken(Map<String, Object> claims, String username) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY))
                .signWith(getSigningKey())
                .compact();
    }

    private Key getSigningKey() {
        return new SecretKeySpec(Base64.getDecoder().decode(secret), SignatureAlgorithm.HS512.getJcaName());
    }

    /////////////////////////validaciones///////////////////
    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token).getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token));
    }

//    //    //

    public String obtenerTokenDesdeCabeceraHttp(HttpServletRequest request) {
        String cabeceraHttp = request.getHeader("Authorization"); //contiene el token pero con el "Barear ..."
        String token = null;

        if (cabeceraHttp == null) {
            throw new NullPointerException("No existe cabecera de seguridad para la acción solicitada");
        }

        if (cabeceraHttp.startsWith("Bearer ") || cabeceraHttp.startsWith("bearer ")) {
            final int TOKEN_POSITION = 7;
            token = cabeceraHttp.substring(TOKEN_POSITION);
        }
        return token;
    }

    public String obtenerIpDesdeCabeceraHttp(HttpServletRequest request) {
        String ip = "";

        if (request == null) {
            throw new NullPointerException("Los datos enviados no son correctos.");
        }

        ip = request.getRemoteAddr(); //getLocalAddr

        return ip;
    }
}
