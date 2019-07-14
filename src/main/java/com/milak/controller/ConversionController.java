package com.milak.controller;

import com.milak.model.Tecaj;
import com.milak.service.TecajService;
import jdk.nashorn.internal.objects.NativeJSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ConversionController {
    private static final Logger LOGGER = Logger.getLogger(ConversionController.class);

    @Autowired
    private TecajService tecajService;

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(value = "convert", consumes = "application/json")
    public String convert(@RequestHeader("conversionDate") LocalDate conversionDate,
                          @RequestHeader("firstCurr") String firstCurr,
                          @RequestHeader("secondCurr") String secondCurr,
                          @RequestHeader("value") String value) {


        List<Tecaj> tecajList = tecajService.getTecajByDate(conversionDate);

        Tecaj fromCurr = new Tecaj();
        Tecaj toCurr = new Tecaj();
        for (Tecaj tecaj :
                tecajList) {
            if (StringUtils.equals(tecaj.getSifraValute(), firstCurr)) {
                fromCurr = tecaj;
            } else if (StringUtils.equals(tecaj.getSifraValute(), secondCurr)) {
                toCurr = tecaj;
            }
        }
        int intValue = Integer.valueOf(value);
        BigDecimal sellValue = fromCurr.getProdajni().multiply(new BigDecimal(intValue));
        BigDecimal buyValue = toCurr.getKupovni().multiply(new BigDecimal(intValue));

        return String.valueOf(sellValue.subtract(buyValue));
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(value = "currencies")
    public String getCurrencies() {

        return tecajService.getTecajByDate(LocalDate.now()).toString();
    }
}
