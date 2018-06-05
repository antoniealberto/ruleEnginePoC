package com.ubs.eqdel.rules.model;

public class ExplosionFact implements Fact<String, String> {
    private final String element;
    private final String place;

    public ExplosionFact(String element, String place){
        this.element = element;
        this.place = place;
    }

    @Override
    public String getElement() {
        return element;
    }

    @Override
    public String getPlace() {
        return place;
    }
}
