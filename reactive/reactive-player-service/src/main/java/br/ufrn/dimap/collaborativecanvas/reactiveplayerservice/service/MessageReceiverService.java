package br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.service;

import br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.model.JogadaPlayerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class MessageReceiverService {

    @Autowired
    private PlayerService playerService;

    @Bean
    public Consumer<JogadaPlayerDTO> play() {
        return jogadaPlayerDTO -> playerService.updatePlayerMove(jogadaPlayerDTO).subscribe();
    }
}
