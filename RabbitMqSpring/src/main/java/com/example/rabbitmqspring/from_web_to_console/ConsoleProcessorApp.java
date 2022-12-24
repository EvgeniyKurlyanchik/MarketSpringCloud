package com.example.rabbitmqspring.from_web_to_console;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

import static java.lang.System.*;

public class ConsoleProcessorApp {
    // На сервере создаете webToConsoleExchanger и webToConsoleQueue

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Channel channel;
        try (Connection connection = factory.newConnection()) {
            channel = connection.createChannel();
        }
        DeliverCallback deliverCallback = (consumerTag, delivery) -> out.println("Received Message '" + new String(delivery.getBody(), StandardCharsets.UTF_8));
        channel.basicConsume("webToConsoleQueue", true, deliverCallback, consumerTag -> {
        });
    }
}