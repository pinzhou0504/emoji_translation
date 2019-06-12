package com.emojiHW.service;

import com.emojiHW.config.AppConfig;
import com.emojiHW.domain.Image;
import com.emojiHW.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class ImageServiceTest {
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;

    @Test
    @Transactional
    public void findKeyByImageIdTest(){
        Image image = new Image();
        image.setKey("keyName");
        imageService.save(image);
        Image imageTest = imageService.findKeyByImageId(image.getId());
        assertNotNull(imageTest);
        assertEquals(image.getId(), imageTest.getId());
    }

    @Transactional
    @Test
    public void findImageKeyByUserIdTest(){
        //在image里找user的id，所以要设置user的instance
        User u = new User();
        u.setUsername("SSmith");
//        u.setFirstName("Sam");
        u.setLastName("Smith");
        u.setEmail("1234567@email.com");
        u.setPassword("123456");
        u.setPhoneNumber("1234567890");
//        u.setAccountNonExpired(true);
//        u.setAccountNonLocked(true);
//        u.setCredentialsNonExpired(true);
//        u.setEnabled(true);
        userService.save(u);
        Long userId = u.getId();
        Image image = new Image();
        image.setKey("keyName");
        image.setBucketName("bucketName");
        image.setUser(u);
        imageService.save(image);
        List<Image> testImageKey = imageService.findImageKeyByUserId(userId);
        assertNotNull(testImageKey);
        assertEquals(testImageKey.get(0).getKey(),"keyName");

    }
}
