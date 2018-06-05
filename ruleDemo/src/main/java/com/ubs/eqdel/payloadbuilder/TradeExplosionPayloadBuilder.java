package com.ubs.eqdel.payloadbuilder;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;


import com.ubs.eqdel.KlondikeRuleEngineService;
import com.ubs.eqdel.RuleEngineService;
import com.ubs.eqdel.rules.model.ExplosionFact;
import com.ubs.eqdel.rules.model.ExplosionResult;
import com.ubs.eqdel.rules.model.Fact;
import com.ubs.eqdel.rules.model.Product;
import com.ubs.eqdel.rules.model.Result;
import com.ubs.eqdel.rules.model.explosion.EqiProduct;

public class TradeExplosionPayloadBuilder implements KlondikeGrPayloadBuilder<String, String>{
    private static final String DSL_RULE_PATH = "/com/ubs/eqdel/rulesrepository/rulebook.dsl";
    private static final Collection<File> ruleFiles = getRulesAsInputStreams();

    @Override
    public String buildPayload(String payload){
        Product product = new EqiProduct("EQI", payload);
        Result<String, String> result = fireExplosion(product);
        return result.getValue();
    }

    private static Collection<File> getRulesAsInputStreams() {
        File rulesDir;
        try {
            rulesDir = new File(TradeExplosionPayloadBuilder.class.getResource(DSL_RULE_PATH).toURI()).getParentFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        Collection<File> ruleFiles = FileUtils.listFiles(
            rulesDir,
            new RegexFileFilter("^(.*?)"),
            DirectoryFileFilter.DIRECTORY
        );
        return ruleFiles;
    }

    private Result fireExplosion(Product product){
        Result<String, String> result = new ExplosionResult(product.getProductAsString());
        Fact fact = new ExplosionFact("EQI", "Product");

        RuleEngineService ruleEngineService = new KlondikeRuleEngineService(product, Arrays.asList(fact), result);
        result = ruleEngineService.buildPayload(ruleFiles);
        return  result;
    }

}
