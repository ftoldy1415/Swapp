package semNome.hackathon.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import semNome.hackathon.model.Aluno;
import semNome.hackathon.model.Pedido;
import semNome.hackathon.model.Turno;
import semNome.hackathon.repositories.AlunoRepo;
import semNome.hackathon.repositories.PedidoRepo;
import semNome.hackathon.repositories.TurnoRepo;

import java.sql.Time;
import java.time.LocalTime;
import java.util.List;

@Service
public class AppService {

    @Autowired
    private AlunoRepo alunoRepo;

    @Autowired
    private TurnoRepo turnosRepo;

    @Autowired
    private PedidoRepo pedidoRepo;

    private String email;

    public boolean login(String email, String password){
        Aluno aux = alunoRepo.encontraPorEmail(email);
        if(aux == null) return false;
        else if(aux.getPassword().equals(password)) {
            this.email = email;
            return true;
        }
        return false;
    }

    public void criaTurno(Turno turno){
        this.turnosRepo.save(turno);
    }

    public void inscreveEmTurno(String uc, Integer num_turno){
        Turno t = this.turnosRepo.encontraTurno(uc, num_turno);
        Aluno a = this.alunoRepo.encontraPorEmail(this.email);
        a.addTurno(t);
        this.alunoRepo.save(a);
    }

    public List<Turno> obtemTodosTurnos(){
        Aluno a = this.alunoRepo.encontraPorEmail(this.email);
        return a.getTurnos();
    }

    public void anunciaTroca(String uc, String td){
        List<Turno> turnos = this.alunoRepo.encontraPorEmail(this.email).getTurnos();
        Turno t = null;
        for(Turno tur : turnos)
            if(tur.getUc().equals(uc)) t = tur;
        Pedido p = new Pedido(String.valueOf(t.getNum_turno()), td, Time.valueOf(LocalTime.now()), uc);
        p.setAluno(this.alunoRepo.encontraPorEmail(this.email));
        this.pedidoRepo.save(p);
    }

    public void criarAluno(Aluno aluno){
        this.alunoRepo.save(aluno);
    }
}
