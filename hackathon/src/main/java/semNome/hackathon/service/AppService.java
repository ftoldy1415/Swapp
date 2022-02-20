package semNome.hackathon.service;


import com.github.javafaker.Faker;
import com.github.javafaker.LeagueOfLegends;
import com.github.javafaker.PhoneNumber;
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
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AppService {

    @Autowired
    private AlunoRepo alunoRepo;

    @Autowired
    private TurnoRepo turnosRepo;

    @Autowired
    private PedidoRepo pedidoRepo;

    private String email;
    private String uc_atual;

    public void popularDados(){
        Faker faker = new Faker(new Locale("pt"));
        for(int i = 0; i < 5; i++){
            String nome = faker.name().fullName();
            boolean existe = true;
            int rand_int1 = 0;
            while (existe) {
                rand_int1 = (int) Math.floor(Math.random()*(99999-10000+1)+10000);
                if(!alunoRepo.existsById(rand_int1)) existe = false;
            }
            int rand_int2 = (int) Math.floor(Math.random()*(969999999-910000000+1)+900000000);
            String password = faker.currency().name();
            if(password.length() > 8) password = password.substring(0, 8);
            existe = true;
            String email = null;
            while(existe){
                email = faker.pokemon().name() + "@gmail.com";
                if(this.alunoRepo.encontraPorEmail(email) == null) existe = false;
            }
            Aluno a = new Aluno(rand_int1, nome, password, email, String.valueOf(rand_int2));
            this.alunoRepo.save(a);
        }
    }


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


    public List<Map<String, String>> possiveisTrocas(Map<String, String> troca){

        // receber o utilizador, turno origem, uc
        // enviar para cima os turnos que ele pode escolher

        //uc que o utilizador pretende trocar
        String uc = troca.get("uc");

        uc_atual = uc;

        List<Pedido> pedidos = this.pedidoRepo.findAll();
        List<Map<String, String>> possiveis = new ArrayList<>(); //aqui ficarão todos os anuncios que faz sentido apresentar-lhe
        //e a eles ficará associada uma tag a dizer se a troca tem como destino o turno em que o utilizador está ou, caso contrário,
        //se a troca for da mesma cadeira mas para turnos diferentes.

        //eu estou no PL2. Quero ver todas as trocas que poderei efetuar. Assim, o sistema tem de me devolver todas os pedidos relativos a
        //essa UC com destino ao PL2. Assim, poderei escolher qual é que me interessa.

        int orig = 0;

        for(Turno t : this.alunoRepo.encontraPorEmail(this.email).getTurnos()){
            if(t.getUc().equals(uc)) orig = t.getNum_turno();
        }

        // uc igual

        for(Pedido p : pedidos){ //a minha origem como destino
            Map<String, String> turno = new HashMap<>();
            if(p.getUc().equals(uc) && p.getTurno_dest().equals(String.valueOf(orig))){
                turno.put("turno", p.getTurno_orig());
                Turno aux = turnosRepo.encontraTurno(uc, Integer.parseInt(p.getTurno_dest()));
                turno.put("horario", aux.getHora_inicio() + " - " + aux.getHora_fim());
                turno.put("dia_semana", aux.getDia_semana());
                possiveis.add(turno);
            }
        }
        return possiveis;
    }

    public boolean verificaSobreposicao(Map<String, String> turno){;
        String horario = turno.get("horario");
        Time horario_inicio = Time.valueOf(horario.split("-")[0]);
        Time horario_fim = Time.valueOf(horario.split("-")[1]);
        String dia_semana = turno.get("dia_semana");

        List<Turno> turnos = this.alunoRepo.encontraPorEmail(this.email).getTurnos();
        boolean sp = false;
        for(Turno t : turnos){
            Time hi = t.getHora_inicio();
            Time hf = t.getHora_fim();
            if(dia_semana.equals(t.getDia_semana())){
                if(!(horario_inicio.equals(hf) || horario_fim.equals(hi))){
                    if(hi.equals(horario_inicio) && hf.equals(horario_fim)) {
                        sp = true;
                        break;
                    }
                    System.out.println("Hora do proposto: " + horario_inicio + " - " + horario_fim);
                    System.out.println("Hora do meu: " + hi + " - " + hf);
                    System.out.println((horario_inicio.equals(hi) || horario_inicio.after(hi))
                            && (horario_inicio.equals(hf) || horario_inicio.before(hf)));
                    System.out.println();
                    if(((horario_inicio.equals(hi) || horario_inicio.after(hi))
                            && (horario_inicio.equals(hf) || horario_inicio.before(hf)))
                            || ((horario_fim.equals(hi) || horario_fim.after(hi))
                            && (horario_fim.equals(hf) || horario_fim.before(hf)))){
                        sp = true;
                        break;
                    }
                }
            }
        }
        return sp;
    }

    public void efetuaTroca(Map<String, String> troca){
        String turno = troca.get("turno");
        //Horario: inicio - fim
        String inicio = troca.get("horario").split("-")[0];
        String fim = troca.get("horario").split("-")[1];

        List<Pedido> pedidos = this.pedidoRepo.findAll();

        Comparator<Pedido> compareByTime = Comparator.comparing(Pedido::getData_pedido)
                .thenComparing(Pedido::getHora_pedido);

        List<Pedido> pedidosOrdenados = pedidos.stream()
                .sorted(compareByTime)
                .collect(Collectors.toList());

        System.out.println(pedidosOrdenados);

    }

}
