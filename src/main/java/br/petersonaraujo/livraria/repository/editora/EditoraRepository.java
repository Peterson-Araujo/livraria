package br.petersonaraujo.livraria.repository.editora;

import br.petersonaraujo.livraria.model.editora.EditoraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EditoraRepository extends JpaRepository<EditoraEntity, Long> {
}
