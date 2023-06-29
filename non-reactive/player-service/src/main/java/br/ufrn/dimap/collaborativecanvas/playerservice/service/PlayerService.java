package br.ufrn.dimap.collaborativecanvas.playerservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

import br.ufrn.dimap.collaborativecanvas.playerservice.models.JogadaPlayerDTO;
import br.ufrn.dimap.collaborativecanvas.playerservice.models.LoginDTO;
import br.ufrn.dimap.collaborativecanvas.playerservice.models.Player;
import br.ufrn.dimap.collaborativecanvas.playerservice.repository.PlayerRepository;

@Service
public class PlayerService {
    
    @Autowired
    private PlayerRepository playerRepository;

    public Player getPlayerById(Long id){
        Optional<Player> player = playerRepository.findById(id);
        return player.orElse(null);
    }

    public List<Player> getAllPlayers(){
        List<Player> players = playerRepository.findAll();
        return players;
    }
    public Player addPlayer(LoginDTO player){
        Player newPlayer = new Player();
        newPlayer.setName(player.getName());
        newPlayer.setPassword(player.getPassword());
        Player createdPlayer = playerRepository.save(newPlayer);
        return createdPlayer;
    }
    public Player updatePlayer(Long id, Player player){
        Optional<Player> existingPlayer = playerRepository.findById(id);
        if (existingPlayer.isPresent()) {
            player.setId(id);
            Player updatedPlayer = playerRepository.save(player);
            return updatedPlayer;
        }else{
            return null;
        }
    }
    @CachePut(value = "players", key = "#jogada.id")
    public Player updatePlayerMove(JogadaPlayerDTO jogada){
        Optional<Player> existingPlayer = playerRepository.findById(jogada.getId());
        if (existingPlayer.isPresent()) {
            Player player = existingPlayer.get();
            player.setPaintedPixels(player.getPaintedPixels() + 1);
            Player updatedPlayer = playerRepository.save(player);
            return updatedPlayer;
        } else {
            return null;
        }
    }
    public boolean deletePlayer(Long id){
        Optional<Player> existingPlayer = playerRepository.findById(id);
        if (existingPlayer.isPresent()) {
            playerRepository.delete(existingPlayer.get());
            return true;
        }
        return false;
    }
    public HashMap<String, Long> logar(LoginDTO login){
        String name = login.getName();
        String password = login.getPassword();
        Player player = playerRepository.findByNameAndPassword(name, password);
        if (player != null) {
            HashMap<String, Long> map = new HashMap<>();
            map.put("id", player.getId());
            return map;
        } else {
            return null;
        }
    }
    public List<Player> getAllPlayersByRanking() {
        List<Player> players = playerRepository.findAllByOrderByPaintedPixelsDesc();
        return players;
    }
    
}
