package ru.gb.coreservice.validators;

public interface Validator<E> {
    void validate(E e);
}
