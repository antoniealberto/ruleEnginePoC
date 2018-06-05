package com.ubs.eqdel.rules.model;

public interface Fact<T,U> {
    T getElement();
    U getPlace();
}
