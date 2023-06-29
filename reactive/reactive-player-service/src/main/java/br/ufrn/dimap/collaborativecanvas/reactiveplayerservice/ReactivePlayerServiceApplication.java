package br.ufrn.dimap.collaborativecanvas.reactiveplayerservice;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ReactivePlayerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactivePlayerServiceApplication.class, args);
	}

}
