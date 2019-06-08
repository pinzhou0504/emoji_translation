package com.emojiHW.service;

import java.io.File;
import java.net.URL;
import java.security.Key;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {
    private AmazonS3 s3Client;

    //            = AmazonS3ClientBuilder.standard()
//            .withRegion(clientRegion)
//            .withCredentials(new DefaultAWSCredentialsProviderChain())
//            .build();
    public StorageService(@Autowired AmazonS3 s3) {
            this.s3Client=s3;}

    public void uploadObject(String bucketName, File file) {

//        try {


        // Upload a file as a new object with ContentType and title specified.
        PutObjectRequest request = new PutObjectRequest(bucketName, file.getName(), file);
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

//        fullObject = s3Client.getObject(new GetObjectRequest(bucketName, key));
        URL url = s3Client.getUrl(bucketName, key);
        String urlStr = url.toString();
        return urlStr;


//        S3Object fullObject = null;
//        // Get an object and print its contents.
//        System.out.println("Downloading an object");
//        fullObject = s3Client.getObject(new GetObjectRequest(bucketName, key));
//        System.out.println("Content-Type: " + fullObject.getObjectMetadata().getContentType());
//        System.out.println("Content: ");
//        displayTextInputStream(fullObject.getObjectContent());


    }

    public void setS3Client(AmazonS3 s3Client){
        this.s3Client=s3Client;
    }

    public AmazonS3 getS3Client(){
        return s3Client;
    }
}


