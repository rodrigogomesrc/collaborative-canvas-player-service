package br.ufrn.dimap.collaborativecanvas.playerservice.repository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import br.ufrn.dimap.collaborativecanvas.playerservice.models.*;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{


    Optional<Player> findByName(String username);

    Player findByNameAndPassword(String username, String password);
    List<Player> findAllByOrderByPaintedPixelsDesc();
    
}
