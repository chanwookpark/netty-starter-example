package netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.Charset;

/**
 * 테스트를 위해서 짧게짧게...
 * 회원번호, 숫자, 3자리
 * 주문번호, 문자, 5자리
 * 주문명, 문자, 10자리
 * 주문일자, 숫자, 14자리 (yyyyMMddHHmmss)
 * <p>
 * 숫자만 : 12312345123456789920170510112233 (34byte)
 * 영문 : 123abc12aaaaaaaaaa20170510112233
 * 한글 : 123H1234한글로가자20170510112233
 * 개행 : 123\nabc12\naaaaaaaaaa\n20170510112233\n
 *
 * @author chanwook
 */
public class MessageHandler1 extends ChannelInboundHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(MessageHandler1.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        ByteBuf in = (ByteBuf) msg;

        logger.info("Request Start. length: {}", in.readableBytes());

//        if (in.readableBytes() != 32) {
//            //예외를 잡아서 에러 처리하는 걸 추가해야겠지..
//            throw new RuntimeException("request bytes " + in.readableBytes());
//        }

        final ByteBuf memberNumber = in.readBytes(3);
        logger.info("[IN] Member Number : " + memberNumber.toString(Charset.forName("utf-8")));

        final ByteBuf orderNumber = in.readBytes(5);
        logger.info("[IN] Order Number : " + orderNumber.toString(Charset.forName("utf-8")));

        final ByteBuf orderName = in.readBytes(10);
        logger.info("[IN] Order Name : " + orderName.toString(Charset.forName("utf-8")));

        final ByteBuf orderDate = in.readBytes(14);
        logger.info("[IN] Order Date : " + orderDate.toString(Charset.forName("utf-8")));

    }
}
