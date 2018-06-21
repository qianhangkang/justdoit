import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author HANGKANG
 * @date 2018/6/14 上午11:44
 */

public class EchoServer {
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() +
                    " <port>");
            return;
        }
//        设置端口值
        int port = Integer.parseInt(args[0]);
//        启动服务器的start方法
        new EchoServer(port).start();
    }


    private void start() throws InterruptedException {
//        创建EventLoopGroup
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {

            ServerBootstrap b = new ServerBootstrap();
            //创建ServerBootstrap
            b.group(group)
                    //指定使用NIO的传输Channel
                    .channel(NioServerSocketChannel.class)
//                    设置socket地址使用所选端口
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new EchoServerHandler());
                        }
                    });
//            绑定服务器；sync等待服务器关闭
            ChannelFuture f = b.bind().sync();
            System.out.println(EchoServer.class.getName() + " started and listen on" + f.channel().localAddress());
//            关闭channel和块，直到它被关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully().sync();
        }
    }

}
