import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author HANGKANG
 * @date 2018/6/14 下午1:47
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {

    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws Exception {
//        记录接受到的消息
        System.out.println("Client received:" + byteBuf.toString());
    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//        当被通知该channel是活动的时候就发送消息
        ctx.writeAndFlush(Unpooled.copiedBuffer("Hello World!", CharsetUtil.UTF_8));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        //打印错误堆栈
        cause.printStackTrace();
        ctx.close();
    }
}
