package part4route;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author HANGKANG
 * @date 2018/5/18 下午4:50
 */

public class Producer {
    private static final String EXCHANGE_NAME = "direct_logs";

    private static final String[] LEVEL = {"info", "debug", "error"};


    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        for (String severity : LEVEL) {
            String msg = "this is a msg level " + severity;
            channel.basicPublish(EXCHANGE_NAME, severity, null, msg.getBytes());
            System.out.println("send msg[ " + msg + " ] success");
        }

        channel.close();
        connection.close();

    }
}
