package br.petersonaraujo.livraria.controller.editora;

import br.petersonaraujo.livraria.controller.editora.dto.EditoraRequest;
import br.petersonaraujo.livraria.controller.editora.dto.EditoraResponse;
import br.petersonaraujo.livraria.controller.editora.mapper.EditoraMapper;
import br.petersonaraujo.livraria.service.editora.EditoraService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editora")
@RequiredArgsConstructor
public class EditoraControllerImpl implements IEditoraController {

    private final EditoraService editoraService;
    private final EditoraMapper mapper;

    @Override
    @PostMapping
    public ResponseEntity<EditoraResponse> salvarEditora(@RequestBody @Valid EditoraRequest editoraRequest) {
        var editoraSalva = editoraService.salvarEditora(mapper.toModel(editoraRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(editoraSalva));
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<EditoraResponse> buscarEditoraPorId(@PathVariable Long id) {
        var editoraBuscar = editoraService.buscarEditoraPorId(id);
        return ResponseEntity.ok(mapper.toResponse(editoraBuscar));
    }

    @Override
    @GetMapping
    public ResponseEntity<List<EditoraResponse>> buscarEditoras() {
        return ResponseEntity.ok(editoraService.listarEditoras().stream().map(mapper::toResponse).toList());
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<EditoraResponse> atualizarEditoraPorId(@PathVariable Long id,
                                                                 @RequestBody @Valid EditoraRequest editoraRequest) {
        var editoraAtualizada = editoraService.atualizarEditoraPorId(id, mapper.toModel(editoraRequest));
        return ResponseEntity.ok(mapper.toResponse(editoraAtualizada));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEditoraPorId(@PathVariable Long id) {
        editoraService.removerEditoraPorId(id);
        return ResponseEntity.noContent().build();
    }
}