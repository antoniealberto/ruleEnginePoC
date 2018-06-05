package com.ubs.eqdel.rules.model;

import java.util.List;

public interface Action<T> {

    ActionOutcome apply(Result result, List<Fact> facts, T domainObject);
}
