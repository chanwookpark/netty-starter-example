package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.Charset;

/**
 * 메시지 디코더와 함께 돌라가도록 테스트
 *
 * @author chanwook
 */
public class MessageHandler2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(((ByteBuf) msg).toString(Charset.defaultCharset()));
    }
}
