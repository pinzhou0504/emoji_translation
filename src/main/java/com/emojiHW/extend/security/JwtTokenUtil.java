package com.emojiHW.extend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    public static final String CLAIM_KEY_USERNAME = "sub";

    public static final String CLAIM_KEY_CREATED = "created";

    private String secret = "emojiHW";

    private long expiration = 86400l;

    private Date generateExpirationDate(){
        return new Date(System.currentTimeMillis()+ expiration *1000);
    }

    private Claims getClaimsFromToken (String token){
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            claims = null;
        }
        return claims;
    }

    public String getUsernameFromToken(String token){
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e){
            username = null;
        }
        return username;
    }





    public Boolean isTokenExpired(String token){
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e){
            expiration = null;
        }
        return expiration;
    }



    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }




    public Boolean validateToken(String token, UserDetails userDetails){
//        User user = (User) userDetails;
        final String username = getUsernameFromToken(token);
//        final Date created = getCreatedDateFromToken(token);
        return (
                username.equals(userDetails.getUsername())
                && !isTokenExpired(token)
        );
    }

//    public Map<String, String> mapToken(String token){
//        HashMap<String,String> map = new HashMap<>();
//        map.put("token:",token);
//        return map;
//    }

}
