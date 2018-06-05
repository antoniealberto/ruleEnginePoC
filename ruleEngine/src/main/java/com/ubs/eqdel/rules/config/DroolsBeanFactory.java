package com.ubs.eqdel.rules.config;

import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

import java.io.File;
import java.util.Collection;
import java.util.List;

public class DroolsBeanFactory {


    private KieServices kieServices=KieServices.Factory.get();


    private KieFileSystem getKieFileSystem(Collection<File> rules){
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        for(File rule:rules){
            kieFileSystem.write(ResourceFactory.newFileResource(rule));
        }
        return kieFileSystem;

    }

    public KieContainer getKieContainer(List<File> rules) {
        getKieRepository();

        KieBuilder kb = kieServices.newKieBuilder(getKieFileSystem(rules));
        kb.buildAll();

        KieModule kieModule = kb.getKieModule();
        KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        return kContainer;

    }

    private void getKieRepository() {
        final KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(new KieModule() {
                        public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
    }

    public KieSession getKieSession(Collection<File> rules){
        getKieRepository();
        KieFileSystem kieFileSystem = getKieFileSystem(rules);

        KieBuilder kb = kieServices.newKieBuilder(kieFileSystem);
        kb.buildAll();
        KieModule kieModule = kb.getKieModule();

        KieContainer kContainer = kieServices.newKieContainer(kieModule.getReleaseId());

        return kContainer.newKieSession();

    }




}
