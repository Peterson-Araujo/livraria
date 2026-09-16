package br.petersonaraujo.livraria.service.editora;

import br.petersonaraujo.livraria.model.editora.EditoraEntity;
import br.petersonaraujo.livraria.repository.editora.EditoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EditoraService {

    private final EditoraRepository editoraRepository;

    public EditoraEntity salvarEditora(EditoraEntity editoraEntity) {
        return editoraRepository.save(editoraEntity);
    }
}
