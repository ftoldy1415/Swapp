package semNome.hackathon.service;


import com.github.javafaker.Faker;
import com.github.javafaker.LeagueOfLegends;
import com.github.javafaker.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import semNome.hackathon.EmailSenderService;
import semNome.hackathon.model.Aluno;
import semNome.hackathon.model.AnuncioGrupo;
import semNome.hackathon.model.Pedido;
import semNome.hackathon.model.Turno;
import semNome.hackathon.repositories.AlunoRepo;
import semNome.hackathon.repositories.AnuncioGrupoRepo;
import semNome.hackathon.repositories.PedidoRepo;
import semNome.hackathon.repositories.TurnoRepo;

import javax.crypto.spec.RC2ParameterSpec;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
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

    @Autowired
    private AnuncioGrupoRepo anuncioGrupoRepo;

    @Autowired
    private EmailSenderService service;

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
        if(this.pedidoRepo.encontraPorUcETurnos(uc, String.valueOf(t.getNum_turno()), td) == null){
            Pedido p = new Pedido(String.valueOf(t.getNum_turno()), td, Time.valueOf(LocalTime.now()), uc, Date.valueOf(LocalDate.now()));
            System.out.println("Data " + Date.valueOf(LocalDate.now()));
            p.setAluno(this.alunoRepo.encontraPorEmail(this.email));
            this.pedidoRepo.save(p);
        }
    }

    public void criarAluno(Aluno aluno){
        this.alunoRepo.save(aluno);
    }


    public List<Map<String, Object>> possiveisTrocas(Map<String, String> troca){

        // receber o utilizador, turno origem, uc
        // enviar para cima os turnos que ele pode escolher

        //uc que o utilizador pretende trocar
        String uc = troca.get("uc");

        uc_atual = uc;

        List<Pedido> pedidos = this.pedidoRepo.findAll();
        List<Map<String, Object>> possiveis = new ArrayList<>(); //aqui ficarão todos os anuncios que faz sentido apresentar-lhe
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
            Map<String, Object> turno = new HashMap<>();
            if(p.getUc().equals(uc) && p.getTurno_dest().equals(String.valueOf(orig))){
                turno.put("turno", p.getTurno_orig());
                Turno aux = turnosRepo.encontraTurno(uc, Integer.parseInt(p.getTurno_dest()));
                turno.put("horario", aux.getHora_inicio() + "-" + aux.getHora_fim());
                turno.put("dia_semana", aux.getDia_semana());
                String s = null;
                if (verificaSobreposicao(turno)) s = "Yes";
                else s = "No";
                turno.put("sobreposicao",s );
                System.out.println("sobreposicao = " + s);
                possiveis.add(turno);
            }
        }
        return possiveis;
    }

    public boolean verificaSobreposicao(Map<String, Object> turno){;
        String horario = (String) turno.get("horario");
        Time horario_inicio = Time.valueOf(horario.split("-")[0]);
        Time horario_fim = Time.valueOf(horario.split("-")[1]);
        String dia_semana = (String)turno.get("dia_semana");

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
        String turno_dest = troca.get("turno");
        //Horario: inicio - fim

        List<Turno> turnos = this.alunoRepo.encontraPorEmail(this.email).getTurnos();
        String turno_orig = "";
        for(Turno t : turnos){
            if(t.getUc().equals(this.uc_atual)) turno_orig = String.valueOf(t.getNum_turno());
        }

        String orig = turno_orig;

        List<Pedido> pedidos = this.pedidoRepo.findAll();

        pedidos = pedidos.stream().filter(p -> p.getTurno_dest().equals(orig)).collect(Collectors.toList());

        Comparator<Pedido> compareByTime = Comparator.comparing(Pedido::getData_pedido)
                .thenComparing(Pedido::getHora_pedido);

        List<Pedido> pedidosOrdenados = pedidos.stream()
                .sorted(compareByTime)
                .collect(Collectors.toList());

        System.out.println(pedidosOrdenados);

        //PL5 -> PL2
        //nos somos PL2
        //queremos um pedido que venha para o PL2

        Pedido p = pedidosOrdenados.get(0);

        Aluno aluno1 = this.alunoRepo.encontraPorEmail(this.email);
        Aluno aluno2 = p.getAluno();

        Turno t1 = aluno1.getTurno(this.uc_atual);
        Turno t2 = aluno2.getTurno(this.uc_atual);

        aluno1.removeTurno(this.uc_atual);
        aluno1.addTurno(t2);

        aluno2.removeTurno(this.uc_atual);
        aluno2.addTurno(t1);

        this.alunoRepo.save(aluno1);
        this.alunoRepo.save(aluno2);

        service.sendEmail(aluno1.getEmail(), "A sua troca foi efetuada!",
                "[" + uc_atual + "] Troca turno " + orig + "com turno " + turno_dest);
        service.sendEmail(aluno2.getEmail(), "A sua troca foi efetuada!",
                "[" + uc_atual + "] Troca turno " + orig + "com turno " + turno_dest);
        this.pedidoRepo.deleteById(p.getId());
    }

    public List<Pedido> pedidos_utilizador(){
        Aluno a = this.alunoRepo.encontraPorEmail(this.email);
        return a.getPedidos();
    }

    public void eliminar_pedido(Map<String, String> pedido){
        String uc = pedido.get("uc");
        String origem = pedido.get("origem");
        String dest = pedido.get("dest");

        Pedido p = this.pedidoRepo.encontraPorUcETurnos(uc, origem, dest);
        System.out.println("Pedido: " + p.getId());
        this.pedidoRepo.deleteById(p.getId());
    }


    // Lógica para grupos

    // Proposta de grupo
    // argumentos (número de pessoas, descrição, uc)
    public void anunciar_grupo(Map<String, Object> grupo){
        int n_pessoas = Integer.parseInt((String) grupo.get("num_pessoas"));
        String descricao = (String) grupo.get("descricao");
        String uc = (String) grupo.get("uc");

        //criar objeto anuncio
        AnuncioGrupo ag = new AnuncioGrupo(n_pessoas, descricao, uc);
        ag.setAluno(this.alunoRepo.encontraPorEmail(this.email));
        this.anuncioGrupoRepo.save(ag);
    }

    //Procura de grupo
    //retornar lista de anuncios disponiveis para a cadeira desejada
    public List<AnuncioGrupo> pedir_grupo(Map<String, Object> info){ //fornecer cadeira desejada
        String uc = (String) info.get("uc");
        List<AnuncioGrupo> anuncioGrupos = new ArrayList<>();
        List<AnuncioGrupo> lista = this.anuncioGrupoRepo.findAll();

        for(AnuncioGrupo ag : lista){
            if(ag.getUc().equals(uc)) anuncioGrupos.add(ag);
        }

        return anuncioGrupos;
        //ir buscar todos os anuncios de grupos de uma determinada cadeira
        //e enviar para a frontend
    }

    //Eliminar proposta quando for aceite (método de confirmação)

    public void confirma_proposta(Map<String, Object> info){
        int id = (Integer) info.get("id");
        Optional<AnuncioGrupo> ag = this.anuncioGrupoRepo.findById(id);
        if(ag.isPresent()){
            AnuncioGrupo a = ag.get();
            String email = a.getAluno().getEmail();
            service.sendEmail("brunofilipe377@gmail.com", this.email + " wants to join your group! Get in touch!",
                    "Group proposal for " + a.getUc());
            service.sendEmail("jpdelgado2001@gmail.com", "You have chosen group " + a.getId() +
                            "! Try to get in touch! Email: " + email,
                    "Group proposal for " + ag.get().getUc());
        }
    }

    public List<Turno> getUcs(){
        List<Turno> turnos = this.alunoRepo.encontraPorEmail(this.email).getTurnos();
        List<Turno> ucs = new ArrayList<>();

        for(Turno t : turnos){
            if(!ucs.contains(t.getUc())) ucs.add(t);
        }

        return ucs;
    }



}
