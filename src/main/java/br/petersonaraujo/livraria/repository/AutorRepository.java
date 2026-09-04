package br.petersonaraujo.livraria.repository;

import br.petersonaraujo.livraria.model.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
    Optional<AutorEntity> findByNome(String nome);
}
