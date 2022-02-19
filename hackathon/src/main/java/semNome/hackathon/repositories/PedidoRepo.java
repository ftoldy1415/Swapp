package semNome.hackathon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import semNome.hackathon.model.Pedido;

public interface PedidoRepo extends JpaRepository<Pedido, Integer> {
}
