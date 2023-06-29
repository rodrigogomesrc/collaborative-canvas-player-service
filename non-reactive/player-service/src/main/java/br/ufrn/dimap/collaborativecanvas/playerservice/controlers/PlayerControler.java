package br.ufrn.dimap.collaborativecanvas.playerservice.controlers;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufrn.dimap.collaborativecanvas.playerservice.models.JogadaPlayerDTO;
import br.ufrn.dimap.collaborativecanvas.playerservice.models.LoginDTO;
import br.ufrn.dimap.collaborativecanvas.playerservice.models.Player;
import br.ufrn.dimap.collaborativecanvas.playerservice.service.PlayerService;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/player")
public class PlayerControler {

    @Autowired
    private PlayerService playerService;

    @Cacheable(value = "players", key = "#id")
    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            return player;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
        }
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }


    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody LoginDTO player) {
        Player playerCreated = playerService.addPlayer(player);
        if (playerCreated != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(playerCreated);
        } else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        
    }

    @CachePut(value = "players", key = "#id")
    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        Player updatedPlayer = playerService.updatePlayer(id, player);
        if (updatedPlayer != null) {
            return player;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
        }
    }

    
    @PostMapping("/play")
    public ResponseEntity<Player> updatePlayerMove(@RequestBody JogadaPlayerDTO jogada) {
        Player updatedPlayer = playerService.updatePlayerMove(jogada);
        if (updatedPlayer != null) {
            return ResponseEntity.ok(updatedPlayer);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
        }
    }

    @CacheEvict(value = "players", key = "#id")
    @DeleteMapping("/{id}")
    public String deletePlayer(@PathVariable Long id) {
        if (playerService.deletePlayer(id)) {
            return "player deleted";
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, Long>> login(@RequestBody LoginDTO login) {
        HashMap<String, Long>  playerLogin = playerService.logar(login);
        if (playerLogin != null) {
            return ResponseEntity.ok(playerLogin);
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        
    }
    @GetMapping("/ranking")
    public List<Player> getAllPlayersByRanking() {
        return playerService.getAllPlayersByRanking();
    }
    
}
