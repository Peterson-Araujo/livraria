package br.petersonaraujo.livraria.service;

import br.petersonaraujo.livraria.exception.NegocioException;
import br.petersonaraujo.livraria.model.AutorEntity;
import br.petersonaraujo.livraria.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorEntity salvarAutor(AutorEntity autorEntity) {

        autorRepository.findByNome(autorEntity.getNome())
                .ifPresent(autor -> {
                    throw new NegocioException("Autor já cadastrado");
                });

        return autorRepository.save(autorEntity);
    }
}
