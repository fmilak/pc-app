package com.milak.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.milak.model.Tecaj;
import com.milak.repository.TecajRepository;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class TecajService {
    private static final Logger LOGGER = Logger.getLogger(TecajService.class);

    @Autowired
    private TecajRepository tecajRepository;

    public Tecaj getTecaj() {
        return null;
    }

    public void insertTecaj(Tecaj tecaj) {
        tecajRepository.insertTecaj(tecaj);
    }

    public List<Tecaj> getTecajByDate(LocalDate date) {
        List<Tecaj> tecajList = tecajRepository.getTecajByDate(date);
        if (tecajList == null || tecajList.isEmpty()) {
            tecajList = parseTecaj(getTecajFromApi(date));
//            tecajList.forEach(tecaj -> tecajRepository.insertTecaj(tecaj)); todo
        }

        return tecajList;
    }

    private String getTecajFromApi(LocalDate date) {
        String url = "http://api.hnb.hr/tecajn/v2?datum-primjene=" + date.toString();
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);

        String tecajJson = "";
        try {
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            tecajJson = EntityUtils.toString(entity);
            LOGGER.info(tecajJson);
        } catch (IOException e) {
            LOGGER.error(e);
        }

        return tecajJson;
    }

    private List<Tecaj> parseTecaj(String jsonTecaji) {

        ObjectMapper mapper = new ObjectMapper();
        List<Tecaj> tecajList = new ArrayList<>();
        try {
            tecajList = mapper.readValue(jsonTecaji, new TypeReference<ArrayList<Tecaj>>() {});
        } catch (IOException e) {
            LOGGER.error(e);
        }

        return tecajList;
    }

    public JSONArray fillJson(List<Tecaj> tecajList) {
        JSONArray jsonArray = new JSONArray();
        tecajList.forEach(tecaj -> {
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("value", tecaj.getValuta());
                jsonObject.put("label", tecaj.getValuta());
            } catch (JSONException e) {
                LOGGER.error(e);
            }
            jsonArray.put(jsonObject);
        });

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("value", "HRK");
            jsonObject.put("label", "HRK");
        } catch (JSONException e) {
            LOGGER.error(e);
        }
        jsonArray.put(jsonObject);
        return jsonArray;
    }

}
