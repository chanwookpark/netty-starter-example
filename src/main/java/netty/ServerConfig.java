package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

/**
 * @author chanwook
 */
@Configuration
public class ServerConfig {

    @Value("${tcp.boss.thread.count}")
    private int bossCount;

    @Value("${tcp.worker.thread.count}")
    private int workerCount;

    @Value("${tcp.port}")
    private int tcpPort;

    @Bean(name = "bossGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup bossGroup() {
        return new NioEventLoopGroup(bossCount);
    }

    @Bean(name = "workerGroup", destroyMethod = "shutdownGracefully")
    public NioEventLoopGroup workerGroup() {
        return new NioEventLoopGroup(workerCount);
    }

    @Bean
    public ServerBootstrap bootstrap() {
        ServerBootstrap b;
        b = new ServerBootstrap();
        b.group(bossGroup(), workerGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
//                        socketChannel.pipeline().addLast(new MessageHandler1());

//                        socketChannel.pipeline().addLast(new MessageDecoder(), new MessageHandler2());

                        socketChannel.pipeline().addLast("frameDecoder", new DelimiterBasedFrameDecoder(40, false, Delimiters.lineDelimiter()));
                        socketChannel.pipeline().addLast("stringDecoder", new StringDecoder(Charset.forName("utf-8")));
                        socketChannel.pipeline().addLast("bizHandler", new MessageHandler3());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        return b;
    }

    @Bean
    public InetSocketAddress tcpPort() {
        return new InetSocketAddress(tcpPort);
    }

}
