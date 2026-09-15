package br.petersonaraujo.livraria.repository.livro;

import br.petersonaraujo.livraria.model.livro.LivroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<LivroEntity, Long> {
}
