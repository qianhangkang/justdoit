import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author HANGKANG
 * @date 2018/6/14 下午1:56
 */

public class EchoClient {
    private final String host;
    private final int port;

    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public static void main(String[] args) {
        if (2 != args.length) {
            System.err.println(
                    "Usage: " + EchoClient.class.getSimpleName() +
                            " <host> <port>");
            return;
        }
        final String host = args[0];
        final int port = Integer.parseInt(args[1]);

        new EchoClient(host, port).start();

    }

    private void start() {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
//            指定Bootstrap
            Bootstrap b = new Bootstrap();
//            指定 EventLoopGroup 来处理客户端事件。由于我们使用 NIO 传输，所以用到了 NioEventLoopGroup 的实现
            b.group(group)
//                    使用的channel类型是一个用于NIO传输
                    .channel(NioSocketChannel.class)
//                    设置服务器的InetSocketAddress
                    .remoteAddress(new InetSocketAddress(host, port))
//                  当建立一个连接和一个新的通道时，创建添加到 EchoClientHandler 实例 到 channel pipeline
                    .handler(new ChannelInitializer<SocketChannel>() {

                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoClientHandler());
                        }
                    });
//            连接到远程服务器；等待连接完成
            ChannelFuture f = b.connect().sync();
//            阻塞直到Channel完成
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
//                调用 shutdownGracefully() 来关闭线程池和释放所有资源
                group.shutdownGracefully().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
