package com.ubs.eqdel.rules.model;

import java.util.ArrayList;
import java.util.List;

public class ExplosionResult implements Result<String, String>{
    private String value;
    private List<Fact> factsList = new ArrayList<>();

    public ExplosionResult(String value){
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public List<Fact> getFacts() {
        return factsList;
    }

    @Override
    public void addFact(Fact fact) {
        factsList.add(fact);
    }

    @Override
    public void applyConsequence(String consequence) {
        this.value = value + consequence;
    }
}
