package com.emojiHW.service;
import java.io.File;
import java.security.Key;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.stereotype.Component;

@Component
public class StorageService {
    private String clientRegion = "us-east-1";
    public void uploadObject (String bucketName,File file){

//        try {
            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                    .withRegion(clientRegion)
                    .withCredentials(new DefaultAWSCredentialsProviderChain())
                    .build();

            // Upload a file as a new object with ContentType and title specified.
            PutObjectRequest request = new PutObjectRequest(bucketName, file.getName(),file);
            s3Client.putObject(request);
//        }
//        catch(AmazonServiceException e) {
//            // The call was transmitted successfully, but Amazon S3 couldn't process
//            // it, so it returned an error response.
//            e.printStackTrace();
//        }
//        catch(SdkClientException e) {
//            // Amazon S3 couldn't be contacted for a response, or the client
//            // couldn't parse the response from Amazon S3.
//            e.printStackTrace();
//        }
    }
    public String getObjectUrl(String bucketName, String key) {
//        S3Object fullObject = null;
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(clientRegion)
                .withCredentials(new ProfileCredentialsProvider())
                .build();
//        fullObject = s3Client.getObject(new GetObjectRequest(bucketName, key));
        String getUrl = s3Client.getUrl(bucketName, key).toString();
        return getUrl;

//        S3Object fullObject = null;
//        // Get an object and print its contents.
//        System.out.println("Downloading an object");
//        fullObject = s3Client.getObject(new GetObjectRequest(bucketName, key));
//        System.out.println("Content-Type: " + fullObject.getObjectMetadata().getContentType());
//        System.out.println("Content: ");
//        displayTextInputStream(fullObject.getObjectContent());


    }
}


