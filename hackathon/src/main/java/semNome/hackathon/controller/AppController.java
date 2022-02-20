package semNome.hackathon.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import semNome.hackathon.EmailSenderService;
import semNome.hackathon.model.Aluno;
import semNome.hackathon.model.AnuncioGrupo;
import semNome.hackathon.model.Pedido;
import semNome.hackathon.model.Turno;
import semNome.hackathon.repositories.AlunoRepo;
import semNome.hackathon.service.AppService;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.List;
import java.util.Map;
@CrossOrigin
@RestController
@RequestMapping("")
public class AppController {

    @Autowired
    private AppService appService;

    @Autowired
    private EmailSenderService service;

    @GetMapping("/teste")
    public String hello(){
        return "olaaaaa";
    }

    @CrossOrigin
    @GetMapping("/popular_dados")
    public void popularDados(){
        this.appService.popularDados();
    }

    @CrossOrigin
    @GetMapping("/getAlunos")
    public List<Aluno> getAlunos(){
        //return this.alunoRepo.findAll();
        return null;
    }

    @CrossOrigin
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> cred){
        boolean res = appService.login(cred.get("email"), cred.get("password"));
        System.out.println("{\"login\" : \"" + res + "\"}");
        return "{\"login\" : \"" + res + "\"}";
    }

    @CrossOrigin
    @PostMapping("/criarAluno")
    public void criarAluno(@RequestBody Aluno aluno){
        this.appService.criarAluno(aluno);
    }

    @CrossOrigin
    @PostMapping("/criarTurno")
    public void criaTurno(@RequestBody Turno turno){
        appService.criaTurno(turno);
    }

    @CrossOrigin
    @PostMapping("/inscreveEmTurno")
    public void inscreveEmTurno(@RequestBody Map<String, String> turno){
        appService.inscreveEmTurno(turno.get("uc"), Integer.parseInt(turno.get("num_turno")));
    }

    @CrossOrigin
    @GetMapping("/getTurnos")
    public List<Turno> obtemTodosTurnos(){
        return appService.obtemTodosTurnos();
    }

    @CrossOrigin
    @PostMapping("/anunciaTroca")
    public void anunciaTroca(@RequestBody Map<String, String> troca){
        System.out.println("troca = " + troca);
        appService.anunciaTroca(troca.get("uc"), troca.get("turno_dest"));
    }

    @CrossOrigin
    @PostMapping("/possiveisTrocas")
    public List<Map<String, Object>> possiveisTrocas(@RequestBody Map<String, String> troca){
        System.out.println("troca = " + troca);
        List<Map<String, Object>> aux = this.appService.possiveisTrocas(troca);
        System.out.println(aux);
        return aux;
    }

    @CrossOrigin
    @PostMapping("/efetuaTroca")
    public void efetuaTroca(@RequestBody Map<String, String> troca){
        this.appService.efetuaTroca(troca);
    }

    @CrossOrigin
    @PostMapping("/sobreposicao")
    public String verificaSobreposicao(@RequestBody Map<String, Object> turno){
        return "\"sobreposicao\" : \"" + this.appService.verificaSobreposicao(turno) + "\"";
    }

    //todos os pedidos do utilizador: origem destino e UC
    @CrossOrigin
    @GetMapping("/pedidos_utilizador")
    public List<Pedido> pedidos_utilizador(){
        return this.appService.pedidos_utilizador();
    }

    //eliminar pedido de troca

    @CrossOrigin
    @PostMapping("/elimina_pedido")
    public void eliminar_pedido(@RequestBody Map<String, String> pedido){
        this.appService.eliminar_pedido(pedido);
    }

    @CrossOrigin
    @PostMapping("/anunciar_grupo")
    public void anunciar_grupo(@RequestBody Map<String, Object> grupo){
        this.appService.anunciar_grupo(grupo);
    }

    @CrossOrigin
    @PostMapping("/pedir_grupo")
    public List<AnuncioGrupo> pedir_grupo(@RequestBody Map<String, Object> info){
        return this.appService.pedir_grupo(info);
    }

    @CrossOrigin
    @PostMapping("/confirma_proposta")
    public void confirma_proposta(@RequestBody Map<String, Object> info){
        this.appService.confirma_proposta(info);
    }

    @CrossOrigin
    @GetMapping("/getUcs")
    public List<Turno> getUcs(){
        return this.appService.getUcs();
        //return this.appService.getUcs();
    }

}
