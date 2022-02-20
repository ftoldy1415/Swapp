package semNome.hackathon.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "anunciogrupo")
public class AnuncioGrupo{

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "num_pessoas")
    private int num_pessoas;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "uc")
    private String uc;


    @ManyToOne(fetch =FetchType.LAZY)
    @JoinColumn(name = "aluno_num", referencedColumnName = "num")
    @JsonIgnore
    private Aluno aluno;

    public AnuncioGrupo(int num_pessoas, String descricao, String uc){
        this.num_pessoas = num_pessoas;
        this.descricao = descricao;
        this.uc = uc;
    }

    public AnuncioGrupo(){

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum_pessoas() {
        return num_pessoas;
    }

    public void setNum_pessoas(int num_pessoas) {
        this.num_pessoas = num_pessoas;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
}
