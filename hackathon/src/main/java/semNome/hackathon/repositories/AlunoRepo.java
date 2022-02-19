package semNome.hackathon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import semNome.hackathon.model.Aluno;

public interface AlunoRepo extends JpaRepository<Aluno, Integer> {

    @Query("FROM Aluno WHERE email = ?1")
    Aluno encontraPorEmail(String email);

}
