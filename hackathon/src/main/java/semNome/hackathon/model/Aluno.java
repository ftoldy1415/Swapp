package semNome.hackathon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @Column(name = "num")
    private String num;
    @Column(name = "nome")
    private String nome;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "inscrito",
            joinColumns = @JoinColumn(name = "aluno_num"),
            inverseJoinColumns = @JoinColumn(name = "turno_id"))
    private List<Turno> turnos;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addTurno(Turno turno){
        this.turnos.add(turno);
    }

    public List<Turno> getTurnos(){
        return this.turnos;
    }
}
