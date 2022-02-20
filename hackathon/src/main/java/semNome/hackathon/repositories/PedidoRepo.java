package semNome.hackathon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import semNome.hackathon.model.Aluno;
import semNome.hackathon.model.Pedido;

public interface PedidoRepo extends JpaRepository<Pedido, Integer> {
    @Query("FROM Pedido WHERE uc = ?1 and turno_orig = ?2 and turno_dest = ?3")
    Pedido encontraPorUcETurnos(String uc, String turno_orig, String turno_dest);
}
