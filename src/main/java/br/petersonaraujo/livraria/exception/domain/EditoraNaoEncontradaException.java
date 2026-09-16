package br.petersonaraujo.livraria.exception.domain;

public class EditoraNaoEncontradaException extends RuntimeException {
    public EditoraNaoEncontradaException(String message) {
        super(message);
    }
}
