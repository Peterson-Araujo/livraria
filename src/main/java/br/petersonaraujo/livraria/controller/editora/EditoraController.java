package br.petersonaraujo.livraria.controller.editora;

import br.petersonaraujo.livraria.controller.autor.dto.AutorResponse;
import br.petersonaraujo.livraria.controller.editora.dto.EditoraRequest;
import br.petersonaraujo.livraria.controller.editora.dto.EditoraResponse;
import br.petersonaraujo.livraria.controller.editora.mapper.EditoraMapper;
import br.petersonaraujo.livraria.service.editora.EditoraService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editora")
@RequiredArgsConstructor
public class EditoraController {

    private final EditoraService editoraService;
    private final EditoraMapper mapper;

    @PostMapping
    public ResponseEntity<EditoraResponse> salvarEditora(@RequestBody EditoraRequest editoraRequest) {
        var editoraSalva = editoraService.salvarEditora(mapper.toModel(editoraRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(editoraSalva));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EditoraResponse> buscarEditoraPorId(@PathVariable Long id) {
        var editoraBuscar = editoraService.buscarEditoraPorId(id);
        return ResponseEntity.ok(mapper.toResponse(editoraBuscar));
    }

    @GetMapping
    public ResponseEntity<List<EditoraResponse>> buscarEditoras() {
        return ResponseEntity.ok(editoraService.listarEditoras().stream().map(mapper::toResponse).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditoraResponse> atualizarEditoraPorId(@PathVariable Long id, @RequestBody EditoraRequest editoraRequest) {
        var editoraAtualizada = editoraService.atualizarEditoraPorId(id, mapper.toModel(editoraRequest));
        return ResponseEntity.ok(mapper.toResponse(editoraAtualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EditoraResponse> deletarEditoraPorId(@PathVariable Long id) {
        editoraService.removerEditoraPorId(id);
        return ResponseEntity.noContent().build();
    }
}
