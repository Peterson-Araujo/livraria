package br.petersonaraujo.livraria.service.livro;

import br.petersonaraujo.livraria.exception.domain.AutorNaoInformadoException;
import br.petersonaraujo.livraria.exception.domain.EditoraNaoEncontradaException;
import br.petersonaraujo.livraria.exception.domain.EditoraNaoInformadaExecption;
import br.petersonaraujo.livraria.model.autor.AutorEntity;
import br.petersonaraujo.livraria.model.livro.LivroEntity;
import br.petersonaraujo.livraria.repository.autor.AutorRepository;
import br.petersonaraujo.livraria.repository.editora.EditoraRepository;
import br.petersonaraujo.livraria.repository.livro.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LivroService {

    public static final String MSG_AUTOR_DEVE_SER_INFORMADO = "O autor deve ser informado";
    public static final String MSG_EDITORA_DEVE_SER_INFORMADA = "A editora deve ser informada";
    public static final String MSG_EDITORA_NAO_ENCONTRADA = "A editora informada não foi encontrada";

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final EditoraRepository editoraRepository;

    public LivroEntity salvarLivro(LivroEntity livroEntity) {

        verificarCamposNulos(livroEntity);

        var editoraEncontrada = editoraRepository.findById(livroEntity.getEditora().getId())
                .orElseThrow(() -> new EditoraNaoEncontradaException(MSG_EDITORA_NAO_ENCONTRADA));

        var ids = livroEntity.getAutor().stream()
                .map(AutorEntity::getId)
                .toList();

        List<AutorEntity> autores = autorRepository.findAllById(ids);

        livroEntity.setAutor(autores);
        livroEntity.setEditora(editoraEncontrada);

        return livroRepository.save(livroEntity);
    }

    private void verificarCamposNulos(LivroEntity livroEntity) {
        if (Objects.isNull(livroEntity.getAutor())) {
            throw new AutorNaoInformadoException(MSG_AUTOR_DEVE_SER_INFORMADO);
        }

        if (Objects.isNull(livroEntity.getEditora()) || livroEntity.getEditora().getId() == null) {
            throw new EditoraNaoInformadaExecption(MSG_EDITORA_DEVE_SER_INFORMADA);
        }
    }


}
