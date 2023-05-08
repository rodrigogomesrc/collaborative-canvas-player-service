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
import br.ufrn.dimap.collaborativecanvas.playerservice.repository.PlayerRepository;


@RestController
@RequestMapping("/player")
public class PlayerControler {

    @Autowired
    private PlayerRepository playerRepository;
    

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent()) {
            return ResponseEntity.ok(player.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Player>> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return ResponseEntity.ok(players);
    }


    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody LoginDTO player) {
        Player newPlayer = new Player();
        newPlayer.setName(player.getName());
        newPlayer.setPassword(player.getPassword());
        Player createdPlayer = playerRepository.save(newPlayer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPlayer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody Player player) {
        Optional<Player> existingPlayer = playerRepository.findById(id);
        if (existingPlayer.isPresent()) {
            player.setId(id);
            Player updatedPlayer = playerRepository.save(player);
            return ResponseEntity.ok(updatedPlayer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/play")
    public ResponseEntity<Player> updatePlayerMove(@RequestBody JogadaPlayerDTO jogada) {
        Optional<Player> existingPlayer = playerRepository.findById(jogada.getId());
        if (existingPlayer.isPresent()) {
            Player player = existingPlayer.get();
            player.setPaintedPixels(player.getPaintedPixels() + 1);
            Player updatedPlayer = playerRepository.save(player);
            return ResponseEntity.ok(updatedPlayer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        Optional<Player> existingPlayer = playerRepository.findById(id);
        if (existingPlayer.isPresent()) {
            playerRepository.delete(existingPlayer.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<HashMap<String, Long>> login(@RequestBody LoginDTO login) {
        String name = login.getName();
        String password = login.getPassword();
        Player player = playerRepository.findByNameAndPassword(name, password);
        if (player != null) {
            HashMap<String, Long> map = new HashMap<>();
            map.put("id", player.getId());
            return ResponseEntity.ok(map);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        
    }
    @GetMapping("/ranking")
    public ResponseEntity<List<Player>> getAllPlayersByRanking() {
        List<Player> players = playerRepository.findAllByOrderByPaintedPixelsDesc();
        return ResponseEntity.ok(players);
    }
    
}
