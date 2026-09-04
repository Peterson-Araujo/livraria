package br.petersonaraujo.livraria.controller;

import br.petersonaraujo.livraria.controller.dto.AutorRequest;
import br.petersonaraujo.livraria.controller.dto.AutorResponse;
import br.petersonaraujo.livraria.controller.mapper.AutorMapper;
import br.petersonaraujo.livraria.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autor")
@RequiredArgsConstructor
public class AutorController {

    private final AutorService autorService;
    private final AutorMapper mapper;

    @PostMapping
    public ResponseEntity<AutorResponse> salvarAutor(@RequestBody @Valid AutorRequest autorRequest) {
        var autorSalvo = autorService.salvarAutor(mapper.toEntity(autorRequest));

        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(autorSalvo));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorResponse> buscarAutorPorId(@PathVariable Long id){
        return ResponseEntity.ok(mapper.toResponse(autorService.buscarAutorPorId(id)));
    }
}
