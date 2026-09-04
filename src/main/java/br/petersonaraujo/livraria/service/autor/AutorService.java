package br.petersonaraujo.livraria.service.autor;

import br.petersonaraujo.livraria.exception.domain.EntidadeNaoEncontradaException;
import br.petersonaraujo.livraria.exception.domain.NegocioException;
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
                    throw new NegocioException(MSG_AUTOR_JA_CADASTRADO);
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

    public AutorEntity atualizarAutorPorId(Long id, AutorEntity entity) {
        return autorRepository.findById(id)
                .map(autor ->  {
                    autor.setNome(entity.getNome());
                    autor.setBiografia(entity.getBiografia());
                    autorRepository.save(autor);
                    return autor;
                })
                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_AUTOR_NAO_ENCONTRADO));
    }

    public void removerAutorPorId(Long id) {
        autorRepository.findById(id)
                .map(autorEntity ->  {
                    autorRepository.delete(autorEntity);
                    return autorEntity;
                })
                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_AUTOR_NAO_ENCONTRADO));
    }
}
