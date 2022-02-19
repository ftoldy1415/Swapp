package semNome.hackathon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import semNome.hackathon.model.Aluno;
import semNome.hackathon.model.Turno;

public interface TurnoRepo extends JpaRepository<Turno, Integer> {
    @Query("FROM Turno WHERE uc = ?1 and num_turno = ?2")
    Turno encontraTurno(String uc, int num_turno);
}
