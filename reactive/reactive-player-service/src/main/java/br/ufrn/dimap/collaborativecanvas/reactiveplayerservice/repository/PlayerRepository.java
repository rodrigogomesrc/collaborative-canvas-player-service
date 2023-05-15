package br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.model.Player;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerRepository extends ReactiveCrudRepository<Player, Long>{
	Mono<Player> findByName(String username);
    Mono<Player> findByNameAndPassword(String username, String password);
    Flux<Player> findAllByOrderByPaintedPixelsDesc();
}
