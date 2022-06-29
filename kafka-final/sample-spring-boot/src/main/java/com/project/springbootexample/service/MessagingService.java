package com.project.springbootexample.service;

import com.project.springbootexample.model.UserMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class MessagingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagingService.class);

    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, UserMessage> userMessageTemplate;

    public void produce(String message) throws ExecutionException, InterruptedException {
            UserMessage userMessage = new UserMessage(message, new Date());
            userMessageTemplate.send(this.topic, userMessage);
            //sends the message to topic
           // SendResult<String, UserMessage> result = userMessageTemplate.send(this.topic, userMessage).get();

            //can get data sent, topic's offset, partition where the data stored
            //String data = result.getRecordMetadata().toString();
            //CAN STORE/UPDATE THIS IN DB TO PASS PARTITION DURING MESSAGE FETCH  recordMetadata=java_topic_3-0@0
          //  LOGGER.info("OFFSET: "+ (Integer.parseInt(data.substring(data.length() - 1)) + 1));
            //LOGGER.info("PARTITION: "+ Integer.parseInt(data.substring(data.length() - 3, data.length() -2)));

    }
}
