package semNome.hackathon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "aluno")
public class Aluno {

    @Id
    @Column(name = "num")
    private int num;
    @Column(name = "nome")
    private String nome;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String email;
    @Column(name = "num_telemovel")
    private String num_telemovel;

    @JsonIgnore
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pedido> pedidos;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "inscrito",
            joinColumns = @JoinColumn(name = "aluno_num"),
            inverseJoinColumns = @JoinColumn(name = "turno_id"))
    private List<Turno> turnos;

    @JsonIgnore
    @OneToMany(mappedBy = "aluno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AnuncioGrupo> anunciosGrupo;

    public Aluno(){

    }

    public Aluno(int num, String nome, String password, String email, String num_telemovel){
        this.num = num;
        this.nome = nome;
        this.password = password;
        this.email = email;
        this.num_telemovel = num_telemovel;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
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

    public String getNum_telemovel() {
        return num_telemovel;
    }

    public void setNum_telemovel(String num_telemovel) {
        this.num_telemovel = num_telemovel;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Turno getTurno(String uc){
        for(Turno t : this.turnos){
            if(t.getUc().equals(uc)) return t;
        }
        return null;
    }

    public void removeTurno(String uc){
        this.turnos.removeIf(t -> t.getUc().equals(uc));
    }
}
