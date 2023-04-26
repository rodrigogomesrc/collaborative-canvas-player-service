package br.ufrn.dimap.collaborativecanvas.playerservice.models;

public class LoginDTO {
    private String name;
    private String password;

    public LoginDTO() {
    }

    public LoginDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return this.name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
