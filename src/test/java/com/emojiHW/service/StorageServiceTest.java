package com.emojiHW.service;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.emojiHW.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.io.File;

import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class StorageServiceTest {
    private String clientRegion = "us-east-1";
    @Autowired
    private StorageService storageService;

    @Transactional
    @Test
    public void uploadObjectTest(){
        File fileTest=new File("/Users/Pinzhou/Desktop/WechatIMG6202.jpeg");
        storageService.uploadObject("emojihw-dev",fileTest);
    }

    @Transactional
    @Test
    public void getObjectUrlTest(){
        String bucketName = "emojihw-dev";
        String key = "WechatIMG6202.jpeg";
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new ProfileCredentialsProvider())
                .build();
        File fileTest=new File("/Users/Pinzhou/Desktop/WechatIMG6202.jpeg");
        storageService.uploadObject(bucketName,fileTest);
        String url = s3Client.getUrl("emojihw-dev", key).toString();
        assertEquals("https://"+bucketName+".s3.amazonaws.com/"+key,url);
    }
}
