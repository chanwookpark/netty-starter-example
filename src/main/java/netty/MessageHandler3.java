package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 메시지 디코더와 함께 돌라가도록 테스트
 *
 * @author chanwook
 */
public class MessageHandler3 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println((String) msg);
    }
}
