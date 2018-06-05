package com.ubs.eqdel.payloadbuilder;

import org.junit.Test;


import static org.junit.Assert.*;

public class TradeExplosionPayloadBuilderTest {
    @Test
    public void buildPayload() throws Exception {
        KlondikeGrPayloadBuilder<String, String> tradeExplosionPayloadBuilder = new TradeExplosionPayloadBuilder();

        String grPayload = tradeExplosionPayloadBuilder.buildPayload("initial product payload");
        System.out.println( "Explosion outcome: \n"+ grPayload);
        assertNotNull(grPayload);
    }

}