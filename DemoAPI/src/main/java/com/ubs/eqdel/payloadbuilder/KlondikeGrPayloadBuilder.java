package com.ubs.eqdel.payloadbuilder;

public interface KlondikeGrPayloadBuilder<T,V> {
    V buildPayload(T t);
}
