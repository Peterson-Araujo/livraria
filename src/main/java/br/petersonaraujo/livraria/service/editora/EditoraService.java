package br.petersonaraujo.livraria.service.editora;

import br.petersonaraujo.livraria.exception.domain.EntidadeNaoEncontradaException;
import br.petersonaraujo.livraria.exception.domain.ErroNegocioException;
import br.petersonaraujo.livraria.model.editora.EditoraEntity;
import br.petersonaraujo.livraria.repository.editora.EditoraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EditoraService {

    public static final String MSG_EDITORA_NAO_ENCONTRADA = "Editora não encontrada";
    public static final String MSG_EDITORA_JA_CADASTRADA = "Editora já cadastrada";

    private final EditoraRepository editoraRepository;

    public EditoraEntity salvarEditora(EditoraEntity editoraEntity) {

        editoraRepository.findByNomeAndCnpjAndEmail(editoraEntity.getNome(),  editoraEntity.getCnpj(), editoraEntity.getEmail())
                .ifPresent(editora -> {
                    throw new ErroNegocioException(MSG_EDITORA_JA_CADASTRADA);
                });

        return editoraRepository.save(editoraEntity);
    }

    public EditoraEntity buscarEditoraPorId(Long id) {
        return editoraRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(MSG_EDITORA_NAO_ENCONTRADA));
    }

    public List<EditoraEntity> listarEditoras() {
        return editoraRepository.findAll();
    }

    public EditoraEntity atualizarEditoraPorId(Long id, EditoraEntity editoraEntity) {

        var editoraEncontrada = buscarEditoraPorId(id);

        editoraEncontrada.setNome(editoraEntity.getNome());
        editoraEncontrada.setCnpj(editoraEntity.getCnpj());
        editoraEncontrada.setEmail(editoraEntity.getEmail());

        return editoraRepository.save(editoraEncontrada);
    }

    public void removerEditoraPorId(Long id) {
        var editoraEncontrada = buscarEditoraPorId(id);

        editoraRepository.delete(editoraEncontrada);
    }
}
