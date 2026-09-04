package br.petersonaraujo.livraria.service;

import br.petersonaraujo.livraria.model.AutorEntity;
import br.petersonaraujo.livraria.repository.AutorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AutorService {

    private final AutorRepository autorRepository;

    public AutorEntity salvarAutor(AutorEntity autorEntity) {
        return autorRepository.save(autorEntity);
    }
}
