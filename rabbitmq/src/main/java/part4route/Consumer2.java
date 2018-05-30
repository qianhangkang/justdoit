package part4route;

/**
 * @author HANGKANG
 * @date 2018/5/18 下午5:42
 */

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 只接受error级别的消息
 */
public class Consumer2 {
    private static final String EXCHANGE_NAME = "direct_logs";

    private static final String[] LEVEL = {"info", "debug", "error"};

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, LEVEL[2]);

        System.out.println("[consumer2 (just error) begin to receive...]");
        Consumer consumer = new DefaultConsumer(channel){
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
