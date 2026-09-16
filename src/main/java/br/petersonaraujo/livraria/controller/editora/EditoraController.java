package br.petersonaraujo.livraria.controller.editora;

import br.petersonaraujo.livraria.controller.editora.dto.EditoraRequest;
import br.petersonaraujo.livraria.controller.editora.dto.EditoraResponse;
import br.petersonaraujo.livraria.controller.editora.mapper.EditoraMapper;
import br.petersonaraujo.livraria.service.editora.EditoraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/editora")
@RequiredArgsConstructor
public class EditoraController {

    private final EditoraService editoraService;
    private final EditoraMapper mapper;

    @PostMapping
    public ResponseEntity<EditoraResponse> salvarEditora(EditoraRequest editoraRequest) {
        var editoraSalva = editoraService.salvarEditora(mapper.toModel(editoraRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(editoraSalva));
    }
}
