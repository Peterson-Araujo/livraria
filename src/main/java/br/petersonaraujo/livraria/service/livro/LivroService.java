package br.petersonaraujo.livraria.service.livro;

import br.petersonaraujo.livraria.exception.domain.AutorNaoInformadoException;
import br.petersonaraujo.livraria.model.autor.AutorEntity;
import br.petersonaraujo.livraria.model.livro.LivroEntity;
import br.petersonaraujo.livraria.repository.autor.AutorRepository;
import br.petersonaraujo.livraria.repository.livro.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LivroService {

    public static final String MSG_AUTOR_DEVE_SER_INFORMADO = "O autor deve ser informado";

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    public LivroEntity salvarLivro(LivroEntity livroEntity) {

        if (Objects.isNull(livroEntity.getAutor())) {
            throw new AutorNaoInformadoException(MSG_AUTOR_DEVE_SER_INFORMADO);
        }

        var ids = livroEntity.getAutor().stream()
                .map(AutorEntity::getId)
                .toList();

        List<AutorEntity> autores = autorRepository.findAllById(ids);

        livroEntity.setAutor(autores);

        return livroRepository.save(livroEntity);
    }
}
