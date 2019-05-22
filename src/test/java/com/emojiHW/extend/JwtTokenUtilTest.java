package com.emojiHW.extend;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.emojiHW.config.AppConfig;
import com.emojiHW.domain.User;
import com.emojiHW.extend.security.JwtTokenUtil;
import com.emojiHW.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;



@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class JwtTokenUtilTest {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailsService userDetailsService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Transactional
    @Test
    public void generateTokenTest(){
        User u = new User();
        String token = jwtTokenUtil.generateToken(u);
//        System.out.println(token);
        String [] parts = token.split("\\.") ;
        assertEquals(parts.length,3);
//        System.out.println(parts.length==3);
//        for(String p:parts){
//            System.out.println("========divider======");
//            System.out.println(p);
        }


    @Transactional
    @Test
    public void getUsernameFromTokenTest(){
        User u = new User();
        u.setUsername("SSmith");
        String token = jwtTokenUtil.generateToken(u);
        String username = jwtTokenUtil.getUsernameFromToken(token);
//        User testUser = userService.findByUsernameIgnoreCase(u.getUsername());
        assertEquals(username, u.getUsername());
    }

    @Test
    @Transactional
    public void isTokenExpiredTest(){
        User u = new User();
        String token = jwtTokenUtil.generateToken(u);
        Boolean validateToken = jwtTokenUtil.isTokenExpired(token);
//        System.out.println(validateToken);
        assertEquals(false,validateToken);

//
//        Map<String,Object> claims = new HashMap<>();
//        claims.put(JwtTokenUtil.CLAIM_KEY_USERNAME,"username");
//        claims.put(JwtTokenUtil.CLAIM_KEY_CREATED,new Date(System.currentTimeMillis()-10000));
//        String invalidToken = Jwts.builder()
//                .setClaims(claims)
//                .setExpiration(new Date(System.currentTimeMillis()+10000))
//                .signWith(SignatureAlgorithm.HS512, "emojiHW")
//                .compact();
//        Boolean invalidate = jwtTokenUtil.isTokenExpired(invalidToken);
//        assertEquals(true,invalidate);
    }

   }



