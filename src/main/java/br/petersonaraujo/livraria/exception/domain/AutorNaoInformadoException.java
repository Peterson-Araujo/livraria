package br.petersonaraujo.livraria.exception.domain;

public class AutorNaoInformadoException extends RuntimeException {
    public AutorNaoInformadoException(String message) {
        super(message);
    }
}
