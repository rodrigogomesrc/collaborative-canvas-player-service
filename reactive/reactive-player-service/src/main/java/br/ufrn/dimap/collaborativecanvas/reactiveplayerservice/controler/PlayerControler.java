package br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.controler;

import org.redisson.api.RMapCacheReactive;
import org.redisson.api.RedissonReactiveClient;
import org.redisson.codec.TypedJsonJacksonCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.service.PlayerService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/player")
public class PlayerControler {

	@Autowired
	private PlayerService playerService;

    private final RedissonReactiveClient redissonClient;

    private final RMapCacheReactive<String, Player> playerCache;

    public PlayerControler(@Autowired RedissonReactiveClient client) {
        this.redissonClient = client;
        this.playerCache = redissonClient.getMapCache("/player/", new TypedJsonJacksonCodec(String.class, Player.class));
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE,value="/{id}")
    public Mono<Player> getPlayerById(@PathVariable Long id) {
        System.out.println("antes de pegar o cache");
        return playerCache.get("player-by-id" + id).switchIfEmpty(
                this.playerService.getPlayerById(id)
                        .doOnNext(player -> System.out.println("vai salvar no cache"))
                        .flatMap(player -> this.playerCache.fastPut("player-by-id" + id, player).thenReturn(player))
        );
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Player> addPlayer(@RequestBody Player player) {
        if (player.getName() == null || player.getPassword() == null){
            return Mono.empty();
        } else {
            return playerService.addPlayer(player);
        }
        
    }

    @PutMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE,value="/{id}")
    public Mono<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        return playerService.updatePlayer(id, player)
                .publishOn(Schedulers.boundedElastic())
                .doOnNext(p -> playerCache.fastRemove("player-by-id" + id).subscribe()
        );
    }

    @DeleteMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/{id}")
    public Mono<Void> deletePlayer(@PathVariable Long id) {
        return playerService.deletePlayer(id)
                .publishOn(Schedulers.boundedElastic())
                .doOnSuccess(p -> playerCache.fastRemove("player-by-id" + id).subscribe());
    }


    @PostMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE,value="/play")
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
