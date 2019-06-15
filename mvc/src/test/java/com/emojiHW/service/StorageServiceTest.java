package com.emojiHW.service;

import com.amazonaws.services.s3.AmazonS3;
import com.emojiHW.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;
import java.net.URL;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
public class StorageServiceTest extends UserServiceTest{
    @Autowired
    private StorageService storageService;

    @Autowired
    private AmazonS3 s3Fake;

    //因为不与数据库交互，所以不用transactional
    @Test
    public void uploadObjectTest(){
//        AmazonS3 s3Fake = mock(AmazonS3.class);
//        storageService.setS3Client(s3Fake);
        File fileTest=new File("123.png");
        storageService.uploadObject("123",fileTest);
        Mockito.verify(s3Fake,Mockito.times(1)).putObject(any());
    }

    @Test
    public void getObjectUrlTest(){
        String bucketName = "123";
        String key = "123.png";
//        AmazonS3 s3Fake = mock(AmazonS3.class);
        URL url = mock(URL.class);
        when(s3Fake.getUrl(anyString(),anyString())).thenReturn(url);
        //storageService.setS3Client(s3Fake);
//        File fileTest=new File("123.jpeg");
//        storageService.uploadObject(bucketName,fileTest);
        storageService.getObjectUrl(bucketName, key);
//        assertEquals("https://"+bucketName+".s3.amazonaws.com/"+key,url);
        Mockito.verify(s3Fake,times(1)).getUrl(anyString(),anyString());
    }
}
