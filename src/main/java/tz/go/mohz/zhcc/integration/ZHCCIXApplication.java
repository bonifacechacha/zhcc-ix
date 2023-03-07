package tz.go.mohz.zhcc.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class ZHCCIXApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZHCCIXApplication.class, args);
	}

}
