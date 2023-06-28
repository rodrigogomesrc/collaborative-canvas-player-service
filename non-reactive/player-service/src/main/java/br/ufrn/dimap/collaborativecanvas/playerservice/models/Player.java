package br.ufrn.dimap.collaborativecanvas.playerservice.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table
public class Player implements Serializable {
	
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@JsonIgnore
	private String password;
	private int paintedPixels;

	public Player() {
		this.paintedPixels = 0;
	}
	
	public Player(Long id, String name, String password, int paintedPixels) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.paintedPixels = paintedPixels;
	}
    public Player(Long id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.paintedPixels = 0;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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

	
