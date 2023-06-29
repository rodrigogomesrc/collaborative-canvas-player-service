package br.ufrn.dimap.collaborativecanvas.playerservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

//@EntityScan("br.ufrn.dimap.collaborativecanvas.playerservice.models")
//@EnableJpaRepositories(basePackages = "br.ufrn.dimap.collaborativecanvas.playerservice.repository")
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
public class PlayerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlayerServiceApplication.class, args);
	}

}
