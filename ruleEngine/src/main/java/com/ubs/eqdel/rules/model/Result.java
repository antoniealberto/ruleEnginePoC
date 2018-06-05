package com.ubs.eqdel.rules.model;

import java.util.List;

public interface Result<T, U> {
    T getValue();
    List<Fact> getFacts();
    void addFact(Fact fact);
    void applyConsequence(U consequence);
}
