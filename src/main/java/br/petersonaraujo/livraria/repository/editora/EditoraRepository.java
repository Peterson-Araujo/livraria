package br.petersonaraujo.livraria.repository.editora;

import br.petersonaraujo.livraria.model.editora.EditoraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EditoraRepository extends JpaRepository<EditoraEntity, Long> {

    Optional<EditoraEntity> findByNomeAndCnpjAndEmail(String nome, String cnpj, String email);
    Optional<EditoraEntity> findByNome(String nome);
}
