package br.petersonaraujo.livraria.service.autor;

import br.petersonaraujo.livraria.exception.domain.EntidadeNaoEncontradaException;
import br.petersonaraujo.livraria.exception.domain.ErroNegocioException;
import br.petersonaraujo.livraria.model.autor.AutorEntity;
import br.petersonaraujo.livraria.repository.autor.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutorService {

    public static final String MSG_AUTOR_NAO_ENCONTRADO = "Autor não encontrado";
    public static final String MSG_AUTOR_JA_CADASTRADO = "Autor já cadastrado";

    private final AutorRepository autorRepository;

    public AutorEntity salvarAutor(AutorEntity autorEntity) {

        autorRepository.findByNome(autorEntity.getNome())
                .ifPresent(autor -> {
                    throw new ErroNegocioException(MSG_AUTOR_JA_CADASTRADO);
                });

        return autorRepository.save(autorEntity);
    }

    public AutorEntity buscarAutorPorId(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_AUTOR_NAO_ENCONTRADO));
    }

    public List<AutorEntity> listarAutoresPorNome(String nome) {
        return autorRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<AutorEntity> listarAutores() {
        return autorRepository.findAll();
    }

    public AutorEntity atualizarAutorPorId(Long id, AutorEntity entity) {

        var autorEncontrado = buscarAutorPorId(id);

        autorEncontrado.setNome(entity.getNome());
        autorEncontrado.setBiografia(entity.getBiografia());

        return autorRepository.save(autorEncontrado);
    }

    public void removerAutorPorId(Long id) {

        var autorEncontrado = buscarAutorPorId(id);

        autorRepository.delete(autorEncontrado);
    }
}
