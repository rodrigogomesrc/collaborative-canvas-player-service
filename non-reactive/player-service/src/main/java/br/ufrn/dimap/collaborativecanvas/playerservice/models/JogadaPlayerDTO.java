package br.ufrn.dimap.collaborativecanvas.playerservice.models;

import java.io.Serializable;

public class JogadaPlayerDTO {
	private Long playerId;

    public Long getId() {
        return this.playerId;
    }

    public void setId(Long id) {
        this.playerId = id;
    }
}
