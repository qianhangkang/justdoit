package part4route;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author HANGKANG
 * @date 2018/5/18 下午4:57
 */

/**
 * 接受LEVEL所有级别的消息
 */
public class Consumer1 {
    private static final String EXCHANGE_NAME = "direct_logs";

    private static final String[] LEVEL = {"info", "debug", "error"};

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        String queueName = channel.queueDeclare().getQueue();
        for (String sevrity : LEVEL) {
            channel.queueBind(queueName, EXCHANGE_NAME, sevrity);
        }
        System.out.println("[consumer1 (error,info,debug)begin to receive...]");
        Consumer consumer = new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String msg = new String(body, "UTF-8");
                System.out.println("[consumer1 received msg:]" + msg);
                System.out.println("[consumer1 done....]");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}
