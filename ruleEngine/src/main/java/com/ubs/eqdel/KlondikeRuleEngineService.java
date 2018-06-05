package com.ubs.eqdel;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


import org.kie.api.runtime.KieSession;


import com.ubs.eqdel.rules.config.DroolsBeanFactory;
import com.ubs.eqdel.rules.model.Fact;
import com.ubs.eqdel.rules.model.Result;


public class KlondikeRuleEngineService implements RuleEngineService<File>
{
    private final Object domainObject;
    private final List<Fact> facts = new LinkedList<>();
    private final Result result;

    public KlondikeRuleEngineService(Object domainObject, List<Fact> facts, Result result){
        this.domainObject = domainObject;
        this.facts.addAll(facts);
        this.result = result;
    }


    @Override
    public Result buildPayload(Collection<File> rules) {

        KieSession ksession = new DroolsBeanFactory().getKieSession(rules);
        ksession.setGlobal("result", result);
        ksession.insert(domainObject);
        for(Fact fact: facts){
            ksession.insert(fact);
        }
        ksession.fireAllRules();

        return result;

    }
}
