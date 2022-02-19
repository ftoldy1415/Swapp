package semNome.hackathon.model;

import javax.persistence.*;
import javax.websocket.ClientEndpoint;
import java.sql.Time;

@Entity
@Table(name = "pedido")
public class Pedido {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name  = "turno_orig")
    private String turno_orig;

    @Column(name = "turno_dest")
    private String turno_dest;

    @Column(name = "hora_pedido")
    private Time hora_pedido;

    @Column(name = "uc")
    private String uc;

    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "aluno_num", referencedColumnName = "num")
    private Aluno aluno;

    public Pedido(){

    }

    public Pedido(String turno_orig, String turno_dest, Time hora_pedido, String uc){
        this.turno_orig = turno_orig;
        this.turno_dest = turno_dest;
        this.hora_pedido = hora_pedido;
        this.uc = uc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTurno_orig() {
        return turno_orig;
    }

    public void setTurno_orig(String turno_orig) {
        this.turno_orig = turno_orig;
    }

    public String getTurno_dest() {
        return turno_dest;
    }

    public void setTurno_dest(String turno_dest) {
        this.turno_dest = turno_dest;
    }

    public Time getHora_pedido() {
        return hora_pedido;
    }

    public void setHora_pedido(Time hora_pedido) {
        this.hora_pedido = hora_pedido;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }
}
