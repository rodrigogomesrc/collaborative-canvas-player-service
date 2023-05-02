package br.ufrn.dimap.collaborativecanvas.playerservice.models;

import jakarta.persistence.*;


@Entity
@Table
public class Player {
	
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String password;
	private int paintedPixels;

	public Player() {
		this.paintedPixels = 0;
	}
	
	public Player(long id, String name, String password, int paintedPixels) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.paintedPixels = paintedPixels;
	}
    public Player(long id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.paintedPixels = 0;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPaintedPixels() {
		return paintedPixels;
	}
	public void setPaintedPixels(int paintedPixels) {
		this.paintedPixels = paintedPixels;
	}
	
	
}

	
