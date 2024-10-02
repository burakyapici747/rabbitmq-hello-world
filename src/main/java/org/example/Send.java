package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private static final String QUEUE_NAME_1 = "hello";
    private static final String QUEUE_NAME_2 = "world";

    public static void main(String[] argv){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try(Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel()){

            channel.queueDeclare(QUEUE_NAME_1, false, false, false, null);
            channel.queueDeclare(QUEUE_NAME_2, false, false, false, null);


            channel.basicPublish("", QUEUE_NAME_1, null, "Hello".getBytes());
            channel.basicPublish("", QUEUE_NAME_2, null, "World".getBytes());
            System.out.println(" [x] Sent '" + "Hello" + "'");
            System.out.println(" [x] Sent '" + "World" + "'");

        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
