package com.emojiHW.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import static org.mockito.Mockito.mock;

@Configuration
public class MockConfig {
    @Bean
    @Profile({"unit"})
    public AmazonS3 getAmazonS3bean(){
        AmazonS3 s3Fake = mock(AmazonS3.class);
        return s3Fake;
    }


}
