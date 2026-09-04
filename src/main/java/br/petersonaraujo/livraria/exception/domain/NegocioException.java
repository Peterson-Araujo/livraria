package br.petersonaraujo.livraria.exception.domain;

public class NegocioException extends RuntimeException {
    public NegocioException(String message) {
        super(message);
    }
}
