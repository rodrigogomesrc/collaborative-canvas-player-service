package br.ufrn.dimap.collaborativecanvas.playerservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

import br.ufrn.dimap.collaborativecanvas.playerservice.models.Player;
import br.ufrn.dimap.collaborativecanvas.playerservice.repository.PlayerRepository;

@Configuration
public class DataLoader implements ApplicationRunner{
    @Autowired
    PlayerRepository player;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        player.save(new Player(1, "joao", "joao123"));
        player.save(new Player(2, "jose", "jose123"));
        player.save(new Player(3, "maria", "maria123"));
        
    }
    
    
}
