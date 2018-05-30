package part2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author HANGKANG
 * @date 2018/5/18 下午2:23
 */

public class Producer {
    private static final String QUEUE = "task";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE, true, false, false, null);

        for (int i = 0; i < 5; i++) {
            String msg = "helloworld:" + i;
            channel.basicPublish("", QUEUE, MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
            System.out.println("[send msg]:" + msg);
        }

        channel.close();
        connection.close();

    }
}
