package br.ufrn.dimap.collaborativecanvas.reactiveplayerservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Table
@Data
public class Player {
	@Id
	private Long id;
	@Column
	private String name;
	
	
	@Column
	private String password;
	@Column("painted_pixels")
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