package br.ufrn.dimap.collaborativecanvas.playerservice.repository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.*;
import br.ufrn.dimap.collaborativecanvas.playerservice.models.*;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long>{
    
}
