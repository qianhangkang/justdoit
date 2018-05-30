package part5topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author HANGKANG
 * @date 2018/5/20 下午4:27
 */

public class Consumer2 {
    public static final String EXCHANGE_NAME = "topic_logs";


    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = channel.queueDeclare().getQueue();
        String routeKey = "#.lazy.#";
        channel.queueBind(queueName, EXCHANGE_NAME, routeKey);
        System.out.println("queue has bind routekey:#.lazy.#");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("[consumer2 received msg:]" + msg);
                System.out.println("[consumer2 done....]");
            }
        };

        channel.basicConsume(queueName, true, consumer);


    }



}
