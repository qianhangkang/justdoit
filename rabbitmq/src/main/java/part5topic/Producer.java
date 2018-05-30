package part5topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author HANGKANG
 * @date 2018/5/18 下午8:08
 */


public class Producer {
    public static final String EXCHANGE_NAME = "topic_logs";

    public static final String[] MSG = {"hello.orange.world",
            "a.orange.b",
            "hello.world.orange.world",
            "isdsa.lazy.dsa",
            "fds.dfsa.lazy.fds"};

    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        //send msg
        for (String s : MSG) {
            String msg = "this is msg:" + s;
            channel.basicPublish(EXCHANGE_NAME, s, null, msg.getBytes());
            System.out.println("send msg:" + msg + " success");
        }

        channel.close();
        connection.close();
    }
}
