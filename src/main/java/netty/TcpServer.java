package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author chanwook
 */
@Component
public class TcpServer {

    @Autowired
    private ServerBootstrap serverBootstrap;

    @Autowired
    private InetSocketAddress tcpPort;

    public void start() throws InterruptedException {
        final ChannelFuture future = serverBootstrap.bind(tcpPort).sync();

        //TODO 정상적인 종료를 위한 방법은 다시 확인
        future.channel().closeFuture().sync();
    }
}
