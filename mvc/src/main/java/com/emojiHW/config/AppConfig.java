package com.emojiHW.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.*;
import org.springframework.core.io.ClassPathResource;

@Configuration
@ComponentScan(basePackages = "com.emojiHW",
    excludeFilters = @ComponentScan.Filter(type = FilterType.REGEX,pattern = "com.emojiHW.api.*"))
public class AppConfig {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Bean(name="shareProperties")
    public PropertiesFactoryBean getShareProperties() {
        logger.debug("I am in the share properties");
        PropertiesFactoryBean bean = new PropertiesFactoryBean();
        bean.setLocation(new ClassPathResource("META-INF/share-runtime.properties"));
        return bean;
    }

    //下面的test是test environment，QA做的
    @Bean
    @Profile({"dev","test","stage","prod"})
    public AmazonS3 getAmazonS3bean(){
        String clientRegion = "us-east-1";
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withRegion(clientRegion)
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .build();
        return s3Client;
    }

    @Bean
    @Profile({"dev","test","stage","prod"})
    public AmazonSQS getAmazonSQS() {
        AmazonSQS client = AmazonSQSClientBuilder
                .standard()
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
        return client;
    }

//    @Value("${region}")
//    private String clientRegion;
}
