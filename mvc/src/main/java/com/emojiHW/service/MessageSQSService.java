package com.emojiHW.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MessageSQSService {
    private AmazonSQS sqs;
    public MessageSQSService(@Autowired AmazonSQS sqs, @Value("${jms.queue.name}")String sqsName) {
        this.sqs=sqs;
        this.queueUrl = getQueueUrl(sqsName);
    }

    public String getQueueUrl(String queueName){
        String queueUrl = sqs.getQueueUrl(queueName).getQueueUrl();
        return queueUrl;
    }

    private String queueUrl = "https://sqs.us-east-1.amazonaws.com/156305995669/car_dealer_queue_dev";

    public void sendMessage(String message){
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queueUrl)
                .withMessageBody(message);
        sqs.sendMessage(send_msg_request);
    }
}
