package com.emojiHW.worker.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProcessService {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @JmsListener(destination = "car_dealer_queue_dev")
    public void processMessage(String msg)throws IOException {
        System.out.println(msg);
    }
}
