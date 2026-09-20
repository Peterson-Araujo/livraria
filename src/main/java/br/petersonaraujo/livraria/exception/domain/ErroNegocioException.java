package br.petersonaraujo.livraria.exception.domain;

public class ErroNegocioException extends RuntimeException {
    public ErroNegocioException(String message) {
        super(message);
    }
}
