
package io.linfeng;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.core.env.Environment;
import java.net.InetAddress;
import java.net.UnknownHostException;


@Slf4j
@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
public class Applications {

	public static void main(String[] args) throws UnknownHostException {
		ConfigurableApplicationContext application = SpringApplication.run(Applications.class, args);
		Environment env = application.getEnvironment();
				env.getProperty("spring.application.name"),
				InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"),
				"127.0.0.1",
				env.getProperty("server.port"));
	}

}
