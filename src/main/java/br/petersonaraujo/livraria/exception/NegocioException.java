package br.petersonaraujo.livraria.exception;

public class NegocioException extends RuntimeException {
    public NegocioException(String message) {
        super(message);
    }
}
