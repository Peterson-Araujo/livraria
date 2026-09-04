package br.petersonaraujo.livraria.repository.autor;

import br.petersonaraujo.livraria.model.autor.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<AutorEntity, Long> {
    Optional<AutorEntity> findByNome(String nome);
    List<AutorEntity> findByNomeContainingIgnoreCase(String nome);
}
