package br.petersonaraujo.livraria.repository.cliente;

import br.petersonaraujo.livraria.model.cliente.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Long> {
    Optional<ClienteEntity> findByEmail(String email);
    Optional<ClienteEntity> findByNome(String nome);
}
