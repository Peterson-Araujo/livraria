package br.petersonaraujo.livraria.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 1. Captura erros do @Size, @NotBlank, etc. (Retorna 400)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> handleValidationExceptions(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        // Transforma a lista de erros do Spring na lista personalizada
        List<ErroResposta.CampoErro> camposComErro = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErroResposta.CampoErro(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        ErroResposta erroResposta = new ErroResposta(
                status.value(),
                "Requisição Inválida",
                "Um ou mais campos estão incorretos. Faça o preenchimento correto e tente novamente.",
                camposComErro
        );

        return ResponseEntity.status(status).body(erroResposta);
    }

    // 2. Captura erros de regras de negócio personalizados (Retorna 422 ou 400)
    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<ErroResposta> handleNegocioException(NegocioException ex) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; // Código 422 é ótimo para regras de negócio

        ErroResposta erroResposta = new ErroResposta(
                status.value(),
                "Regra de Negócio Violada",
                ex.getMessage(),
                null // Não há campos específicos falhando na validação sintática
        );

        return ResponseEntity.status(status).body(erroResposta);
    }
}
