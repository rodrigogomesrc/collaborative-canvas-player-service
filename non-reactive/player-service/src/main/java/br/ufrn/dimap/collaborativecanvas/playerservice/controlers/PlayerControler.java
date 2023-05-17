package br.ufrn.dimap.collaborativecanvas.playerservice.controlers;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufrn.dimap.collaborativecanvas.playerservice.models.JogadaPlayerDTO;
import br.ufrn.dimap.collaborativecanvas.playerservice.models.LoginDTO;
import br.ufrn.dimap.collaborativecanvas.playerservice.models.Player;
import br.ufrn.dimap.collaborativecanvas.playerservice.service.PlayerService;


@RestController
@RequestMapping("/player")
public class PlayerControler {

    @Autowired
    private PlayerService playerService;

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Player player = playerService.getPlayerById(id);
        if (player != null) {
            return ResponseEntity.ok(player);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerService.getAllPlayers();
        return ResponseEntity.ok(players);
    }


    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody LoginDTO player) {
        Player playerCreated = playerService.addPlayer(player);
        if (playerCreated != null){
            return ResponseEntity.status(HttpStatus.CREATED).body(playerCreated);
        } else{
            return ResponseEntity.badRequest().build();
        }
        
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        Player updatedPlayer = playerService.updatePlayer(id, player);
        if (updatedPlayer != null) {
            return ResponseEntity.ok(updatedPlayer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("/play")
    public ResponseEntity<Player> updatePlayerMove(@RequestBody JogadaPlayerDTO jogada) {
        Player updatedPlayer = playerService.updatePlayerMove(jogada);
        if (updatedPlayer != null) {
            return ResponseEntity.ok(updatedPlayer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        if (playerService.deletePlayer(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, Long>> login(@RequestBody LoginDTO login) {
        HashMap<String, Long>  playerLogin = playerService.logar(login);
        if (playerLogin != null) {
            return ResponseEntity.ok(playerLogin);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
    }
    @GetMapping("/ranking")
    public ResponseEntity<List<Player>> getAllPlayersByRanking() {
        List<Player> players = playerService.getAllPlayersByRanking();
        return ResponseEntity.ok(players);
    }
    
}
