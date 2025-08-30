package progDyC.pdyc_tp2.model;

import jakarta.persistence.*;

@Entity
@Table(name="admins")
public class Admin { 
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return username;
    }
    public void setNombre(String nombre) {
        this.username = nombre;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
