package part3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author HANGKANG
 * @date 2018/5/18 下午3:06
 */

public class Producer {

    private static final String EXCHANGE_NAME = "logs";


    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String msg = "helloworld!!";

        channel.basicPublish(EXCHANGE_NAME, "", null, msg.getBytes());
        System.out.println("send msg:" + msg + " success");

    }



}
