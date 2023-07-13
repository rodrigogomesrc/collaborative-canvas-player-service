package br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.model;

public class UpdatePlayerDTO {

    private long id;

    private Player player;

    public UpdatePlayerDTO() {
    }

    public UpdatePlayerDTO(long id, Player player) {
        this.id = id;
        this.player = player;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}