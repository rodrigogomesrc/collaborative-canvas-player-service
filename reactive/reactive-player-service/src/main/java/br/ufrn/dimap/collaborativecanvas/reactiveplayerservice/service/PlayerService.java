package br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.model.JogadaPlayerDTO;
import br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.model.LoginDTO;
import br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.model.Player;
import br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.repository.PlayerRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;


@Service
public class PlayerService {
	
	@Autowired
	PlayerRepository playerRepository;
	
	private final ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
    private final Scheduler virtualScheduler = Schedulers.fromExecutorService(executorService);
    
	
	public Mono<Player> getPlayerById(Long id){
        return playerRepository.findById(id)
        		.subscribeOn(Schedulers.boundedElastic());
    }

    public Flux<Player> getAllPlayers(){
        return playerRepository.findAll()
        		.subscribeOn(Schedulers.boundedElastic());
    }
    public Mono<Player> addPlayer(Player player){
        return playerRepository.save(player)
        		.subscribeOn(Schedulers.boundedElastic());
    }
    public Mono<Player> updatePlayer(Long id, Player player){
    	return playerRepository.findById(id)
    			.subscribeOn(Schedulers.boundedElastic())
    			.map((p) -> {

        	p.setPassword(player.getPassword());
        	p.setName(player.getName());
        	p.setPaintedPixels(player.getPaintedPixels());
        	return p;
        }).flatMap(p -> 
        	playerRepository.save(p));
    }
    public Mono<Void> deletePlayer(Long id){
    	return playerRepository.deleteById(id)
    			.subscribeOn(Schedulers.boundedElastic());
    }
    public Mono<Player> updatePlayerMove(JogadaPlayerDTO jogada){
    	return playerRepository.findById(jogada.getId())
    			.subscribeOn(Schedulers.boundedElastic())
    			.map((p) -> {
        	p.setPaintedPixels(p.getPaintedPixels() + 1);
        	return p;
        }).flatMap(p -> 
        	playerRepository.save(p));
    }
    
    public Mono<Player> login(LoginDTO login) {
    	return playerRepository.findByNameAndPassword(login.getName(), login.getPassword())
    			.subscribeOn(Schedulers.boundedElastic());
    }
    public Flux<Player> getAllPlayersByRanking(){
    	return playerRepository.findAllByOrderByPaintedPixelsDesc()
    			.subscribeOn(Schedulers.boundedElastic());
    }
}
