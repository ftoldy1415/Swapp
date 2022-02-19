package semNome.hackathon.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import semNome.hackathon.EmailSenderService;
import semNome.hackathon.model.Aluno;
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
        appService.anunciaTroca(troca.get("uc"), troca.get("turno_dest"));
    }

    @CrossOrigin
    @PostMapping("/efetuaTroca")
    public void efetuaTroca(@RequestBody Map<String, String> troca){
        this.appService.possiveisTroca(troca);
    }


}
