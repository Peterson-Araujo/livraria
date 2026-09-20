package br.petersonaraujo.livraria.service.livro;

import br.petersonaraujo.livraria.controller.autor.mapper.AutorMapper;
import br.petersonaraujo.livraria.controller.editora.mapper.EditoraMapper;
import br.petersonaraujo.livraria.controller.livro.dto.LivroRequest;
import br.petersonaraujo.livraria.controller.livro.dto.LivroResponse;
import br.petersonaraujo.livraria.exception.domain.EntidadeNaoEncontradaException;
import br.petersonaraujo.livraria.exception.domain.EntidadeNaoInformadaException;
import br.petersonaraujo.livraria.model.autor.AutorEntity;
import br.petersonaraujo.livraria.model.livro.LivroEntity;
import br.petersonaraujo.livraria.repository.autor.AutorRepository;
import br.petersonaraujo.livraria.repository.editora.EditoraRepository;
import br.petersonaraujo.livraria.repository.livro.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LivroService {

    public static final String MSG_AUTOR_DEVE_SER_INFORMADO = "O autor deve ser informado";
    public static final String MSG_EDITORA_DEVE_SER_INFORMADA = "A editora deve ser informada";
    public static final String MSG_EDITORA_NAO_ENCONTRADA = "A editora informada não foi encontrada";
    public static final String MSG_LIVRO_NAO_ENCONTRADO = "O livro informado não foi encontrado";
    public static final String MSG_AUTOR_NAO_ENCONTRADO = "O autor informado não foi encontrado";

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final EditoraRepository editoraRepository;


    public LivroEntity salvarLivro(LivroEntity livroEntity) {

        verificarCamposNulos(livroEntity);

        var editoraEncontrada = editoraRepository.findById(livroEntity.getEditora().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_EDITORA_NAO_ENCONTRADA));

        var ids = livroEntity.getAutor().stream()
                .map(AutorEntity::getId)
                .toList();

        List<AutorEntity> autores = autorRepository.findAllById(ids);

        livroEntity.setAutor(autores);
        livroEntity.setEditora(editoraEncontrada);

        return livroRepository.save(livroEntity);
    }


    public List<LivroEntity> listarLivros() {
        return livroRepository.findAll();
    }

    private void verificarCamposNulos(LivroEntity livroEntity) {
        if (Objects.isNull(livroEntity.getAutor())) {
            throw new EntidadeNaoInformadaException(MSG_AUTOR_DEVE_SER_INFORMADO);
        }

        if (Objects.isNull(livroEntity.getEditora()) || livroEntity.getEditora().getId() == null) {
            throw new EntidadeNaoInformadaException(MSG_EDITORA_DEVE_SER_INFORMADA);
        }
    }

    public LivroEntity buscarLivroPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_LIVRO_NAO_ENCONTRADO));
    }

    public LivroEntity atualizarLivroPorId(Long id, LivroRequest livroRequest) {
        var livroEncontrado = livroRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_LIVRO_NAO_ENCONTRADO));

        livroEncontrado.setIsbn(livroRequest.isbn());
        livroEncontrado.setTitulo(livroRequest.titulo());
        livroEncontrado.setDescricao(livroRequest.descricao());
        livroEncontrado.setPreco(livroRequest.preco());
        livroEncontrado.setEstoque(livroRequest.estoque());
        livroEncontrado.setAnoPublicacao(livroRequest.anoPublicacao());
        livroEncontrado.setCategoria(livroRequest.categoria());

        var editoraEncontrada = editoraRepository.findById(livroRequest.editora().getId())
                        .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_EDITORA_NAO_ENCONTRADA));
        livroEncontrado.setEditora(editoraEncontrada);

        var autoresEncontrados = livroRequest.autor().stream()
                        .map(autor -> autorRepository.findById(autor.getId())
                                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_AUTOR_NAO_ENCONTRADO)))
                                .collect(Collectors.toList());
        livroEncontrado.setAutor(autoresEncontrados);

        return livroRepository.save(livroEncontrado);
    }

    public void deletarLivroPorId(Long id) {
        var livroEncontrado = livroRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_LIVRO_NAO_ENCONTRADO));

        livroRepository.delete(livroEncontrado);
    }
}
