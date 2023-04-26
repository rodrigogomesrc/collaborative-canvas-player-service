package br.ufrn.dimap.collaborativecanvas.playerservice.controlers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/player")
public class PlayerControler {

    @GetMapping
    public String buscas(){
        return "TODO";
    }
    
}
