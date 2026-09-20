package br.petersonaraujo.livraria.exception;

import br.petersonaraujo.livraria.exception.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResposta> handleValidationExceptions(MethodArgumentNotValidException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

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

    @ExceptionHandler(ErroNegocioException.class)
    public ResponseEntity<ErroResposta> handleNegocioException(ErroNegocioException ex) {
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY; // Código 422 é ótimo para regras de negócio

        ErroResposta erroResposta = new ErroResposta(
                status.value(),
                "Regra de Negócio Violada",
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(status).body(erroResposta);
    }

    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<ErroResposta> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;

        ErroResposta erroResposta = new ErroResposta(
                status.value(),
                "Recurso Não Encontrado",
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(status).body(erroResposta);
    }

    @ExceptionHandler(EntidadeNaoInformadaException.class)
    public ResponseEntity<ErroResposta> handlerAutorNaoInformadoException(EntidadeNaoInformadaException ex) {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        ErroResposta erroResposta = new ErroResposta(
                status.value(),
                "O recurso deve ser informado.",
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(status).body(erroResposta);
    }
}
