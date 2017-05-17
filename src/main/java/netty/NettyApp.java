package netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author chanwook
 */
@SpringBootApplication
public class NettyApp {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext context = SpringApplication.run(NettyApp.class, args);

        TcpServer tcpServer = context.getBean(TcpServer.class);
        tcpServer.start();
    }

}
