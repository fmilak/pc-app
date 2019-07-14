package com.milak.controller;

import com.milak.model.Tecaj;
import com.milak.service.TecajService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ConversionController {
    private static final Logger LOGGER = Logger.getLogger(ConversionController.class);

    @Autowired
    private TecajService tecajService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "convert", consumes = "application/json")
    public String convert(@RequestHeader("conversionDate") String conversionDate,
                          @RequestHeader("firstCurr") String firstCurr,
                          @RequestHeader("secondCurr") String secondCurr,
                          @RequestHeader("value") String value) {

        LocalDate date = LocalDate.parse(conversionDate);
        List<Tecaj> tecajList = tecajService.getTecajByDate(date);

        Tecaj fromCurr = null;
        Tecaj toCurr = null;
        for (Tecaj tecaj :
                tecajList) {
            if (StringUtils.equals(tecaj.getValuta(), firstCurr)) {
                fromCurr = tecaj;
            } else if (StringUtils.equals(tecaj.getValuta(), secondCurr)) {
                toCurr = tecaj;
            }
        }
        int intValue = 1;
        if (StringUtils.isNotEmpty(value)) {
            intValue = Integer.parseInt(value);
        }
        BigDecimal firstValue = new BigDecimal(1);
        BigDecimal secondValue = new BigDecimal(1);
        if (fromCurr == null && toCurr == null) {
            return String.valueOf(intValue);
        }
        if (fromCurr == null) {
            secondValue = new BigDecimal(intValue).divide(toCurr.getKupovni(), 5, RoundingMode.HALF_UP);
            return String.valueOf(secondValue);
        }
        if (toCurr == null) {
            firstValue = new BigDecimal(intValue).multiply(fromCurr.getKupovni());
            return String.valueOf(firstValue);
        }

        firstValue = new BigDecimal(intValue).multiply(fromCurr.getKupovni());
        secondValue = firstValue.divide(toCurr.getKupovni(), 5, RoundingMode.HALF_UP);

        return String.valueOf(secondValue);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "currencies")
    public String getCurrencies() {

        List<Tecaj> tecajList = tecajService.getTecajByDate(LocalDate.now());
        JSONArray jsonArray = tecajService.fillJson(tecajList);

        return jsonArray.toString();
    }
}
