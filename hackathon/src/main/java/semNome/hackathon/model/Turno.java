package semNome.hackathon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;
import java.util.List;

@Entity
@Table(name = "turno")
public class Turno {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "uc")
    private String uc;
    @Column(name = "hora_inicio")
    private Time hora_inicio;
    @Column(name = "hora_fim")
    private Time hora_fim;
    @Column(name = "dia_semana")
    private String dia_semana;
    @Column(name = "num_turno")
    private int num_turno;
    @Column(name = "ano")
    private int ano;

    @ManyToMany(mappedBy = "turnos")
    @JsonIgnore
    private List<Aluno> alunos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public Time getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(Time hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public Time getHora_fim() {
        return hora_fim;
    }

    public void setHora_fim(Time hora_fim) {
        this.hora_fim = hora_fim;
    }

    public String getDia_semana() {
        return dia_semana;
    }

    public void setDia_semana(String dia_semana) {
        this.dia_semana = dia_semana;
    }

    public int getNum_turno() {
        return num_turno;
    }

    public void setNum_turno(int num_turno) {
        this.num_turno = num_turno;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
