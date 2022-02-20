package semNome.hackathon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import semNome.hackathon.model.AnuncioGrupo;

import java.util.Collection;
import java.util.List;

public interface AnuncioGrupoRepo extends JpaRepository<AnuncioGrupo, Integer>, JpaSpecificationExecutor<AnuncioGrupo> {
}
