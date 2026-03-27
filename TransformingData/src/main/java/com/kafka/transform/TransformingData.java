package com.kafka.transform;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransformingData {

    private final RabbitTemplate rabbitTemplate;

    public TransformingData(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queuesToDeclare = @Queue(name = "source_topic"))
    public void handleMessage(String value) {
        String result = process(value);
        rabbitTemplate.convertAndSend("target_topic", result);
    }

    private String process(String value) {
        int res = 0;
        String[] fileData = value.split(",");
        String operator = fileData[2];

        switch (operator) {
            case "+":
                res = Integer.parseInt(fileData[0]) + Integer.parseInt(fileData[1]);
                break;
            case "-":
                res = Integer.parseInt(fileData[0]) - Integer.parseInt(fileData[1]);
                break;
            case "*":
                res = Integer.parseInt(fileData[0]) * Integer.parseInt(fileData[1]);
                break;
            case "/":
                res = Integer.parseInt(fileData[0]) / Integer.parseInt(fileData[1]);
                break;
        }
        return value + "," + res;
    }

    public static void main(String[] args) {
        SpringApplication.run(TransformingData.class, args);
    }
}