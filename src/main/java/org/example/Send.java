package org.example;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv){
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
        try(Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel()){

            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String number = "1";

            for(int i = 1; i <= 100; i++){
                number = String.valueOf(i);
                channel.basicPublish("", QUEUE_NAME, null, number.getBytes());
                System.out.println(" [x] Sent '" + number + "'");
            }



        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
