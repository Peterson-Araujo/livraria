package br.petersonaraujo.livraria.repository;

import br.petersonaraujo.livraria.model.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
}
