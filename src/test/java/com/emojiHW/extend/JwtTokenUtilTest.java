package com.emojiHW.extend;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.emojiHW.config.AppConfig;
import com.emojiHW.domain.User;
import com.emojiHW.extend.security.JwtTokenUtil;
import com.emojiHW.service.UserService;
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
        u.setUsername("SSmith");
        u.setFirstName("Sam");
        u.setLastName("Smith");
        u.setEmail("1234567@email.com");
        u.setPassword("123456");
        u.setAccountNonExpired(true);
        u.setAccountNonLocked(true);
        u.setCredentialsNonExpired(true);
        u.setEnabled(true);
//        userService.save(u);
        String token = jwtTokenUtil.generateToken(u);
//        System.out.println(token);
        String [] parts = token.split("\\.") ;
        assertEquals(parts.length,3);
//        System.out.println(parts.length==3);
//        for(String p:parts){
//            System.out.println("========divider======");
//            System.out.println(p);
        }




//        UserDetails testUser = userDetailsService.loadUserByUsername(u.getUsername());



   }



