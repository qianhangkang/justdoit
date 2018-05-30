package client;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author HANGKANG
 * @date 2018/5/18 上午11:49
 */

public class Recv {
    private static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("waiting for listen...");

        Consumer consumer = new Consumer() {
            public void handleConsumeOk(String s) {

            }

            public void handleCancelOk(String s) {

            }

            public void handleCancel(String s) throws IOException {

            }

            public void handleShutdownSignal(String s, ShutdownSignalException e) {

            }

            public void handleRecoverOk(String s) {

            }

            public void handleDelivery(String s, Envelope envelope, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
                String msg = new String(bytes, "UTF-8");
                System.out.println("recv msg:" + msg);

            }
        };


        channel.basicConsume(QUEUE_NAME, true, consumer);


    }
}
