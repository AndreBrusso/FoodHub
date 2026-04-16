package br.com.foodhub.foodhub.exceptions;

public class SenhaInvalidaExpecption extends RuntimeException {
    public SenhaInvalidaExpecption(String message) {
        super(message);
    }
}
