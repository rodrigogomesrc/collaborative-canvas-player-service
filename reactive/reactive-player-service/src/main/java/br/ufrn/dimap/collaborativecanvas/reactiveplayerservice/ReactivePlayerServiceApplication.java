package br.ufrn.dimap.collaborativecanvas.reactiveplayerservice;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class ReactivePlayerServiceApplication {

	@Bean
	public Consumer<String> printhelloworld() {
		return val -> System.out.println(val);
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactivePlayerServiceApplication.class, args);
	}

}
