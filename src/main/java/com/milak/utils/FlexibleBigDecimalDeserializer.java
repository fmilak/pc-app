package com.milak.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;

import java.io.IOException;
import java.math.BigDecimal;

public class FlexibleBigDecimalDeserializer extends NumberDeserializers.BigDecimalDeserializer {

    @Override
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        String decimalString = p.getText();
        if (decimalString.contains(",")) {
            decimalString = decimalString.replace(",", ".");
        }

        return new BigDecimal(decimalString);
    }
}
