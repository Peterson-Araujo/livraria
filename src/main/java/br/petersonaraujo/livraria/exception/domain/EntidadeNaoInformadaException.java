package br.petersonaraujo.livraria.exception.domain;

public class EntidadeNaoInformadaException extends RuntimeException {
    public EntidadeNaoInformadaException(String message) {
        super(message);
    }
}
