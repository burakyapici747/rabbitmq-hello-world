package org.example.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLogDirect {
    private static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] argv) throws Exception{
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");

        try(Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel()
        ){
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");

            String severity = "red";
            String message = "Helloooo Worlddddd!";

            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");
        }
    }
}
