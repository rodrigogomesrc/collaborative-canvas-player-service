package br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.model.JogadaPlayerDTO;
import br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.model.LoginDTO;
import br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.model.Player;
import br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.repository.PlayerRepository;
import br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.service.PlayerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/player")
public class PlayerControler {
	@Autowired
	PlayerRepository repository;
	
	@Autowired
	PlayerService playerService;
	
	
	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE,value="/{id}")
    public Mono<Player> getPlayerById(@PathVariable Long id) {
		return playerService.getPlayerById(id);
    }
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }
    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Player> addPlayer(@RequestBody Player player) {
        if (player.getName() == null || player.getPassword() == null){
            return Mono.empty();
        } else{
            return playerService.addPlayer(player);
        }
        
    }
    @PutMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE,value="/{id}")
    public Mono<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        return playerService.updatePlayer(id, player);
    }
    
    @DeleteMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE,value="/{id}")
    public Mono<Void> deletePlayer(@PathVariable Long id) {
        return playerService.deletePlayer(id);
    }
    @PutMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE,value="/play")
    public Mono<Player> updatePlayerMove(@RequestBody JogadaPlayerDTO jogada) {
        return playerService.updatePlayerMove(jogada);
       
    }
    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE,value="/login")
    public Mono<Player> login(@RequestBody LoginDTO login) {
		return playerService.login(login);
    }
    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE,value="/ranking")
    public Flux<Player> getAllPlayersByRanking() {
        return playerService.getAllPlayersByRanking();
    }
    

}
