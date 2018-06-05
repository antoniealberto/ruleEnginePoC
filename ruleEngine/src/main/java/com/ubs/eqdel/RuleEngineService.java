package com.ubs.eqdel;

import java.util.Collection;


import com.ubs.eqdel.rules.model.Result;

public interface RuleEngineService<T> {

    Result buildPayload(Collection<T> rules);

}
