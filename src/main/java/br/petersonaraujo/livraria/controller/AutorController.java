package br.petersonaraujo.livraria.controller;

import br.petersonaraujo.livraria.controller.dto.AutorRequest;
import br.petersonaraujo.livraria.controller.dto.AutorResponse;
import br.petersonaraujo.livraria.controller.mapper.AutorMapper;
import br.petersonaraujo.livraria.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autor")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;
    private final AutorMapper mapper;

    @PostMapping
    public ResponseEntity<AutorResponse> salvarAutor(@RequestBody @Valid AutorRequest autorRequest) {
        var autorSalvo = autorService.salvarAutor(mapper.toEntity(autorRequest));

        return ResponseEntity.ok(mapper.toResponse(autorSalvo));
    }
}
