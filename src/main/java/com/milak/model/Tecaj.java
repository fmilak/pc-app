package com.milak.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.milak.utils.FlexibleBigDecimalDeserializer;

import java.math.BigDecimal;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tecaj {

    @JsonProperty("broj_tecajnice")
    private int brojTecajnice;
    @JsonProperty("datum_primjene")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate datumPrimjene;
    @JsonProperty("drzava")
    private String drzava;
    @JsonProperty("sifra_valute")
    private String sifraValute;
    @JsonProperty("valuta")
    private String valuta;
    @JsonProperty("jedinica")
    private int jedinica;
    @JsonProperty("kupovni_tecaj")
    @JsonDeserialize(using = FlexibleBigDecimalDeserializer.class)
    private BigDecimal kupovni;
    @JsonProperty("srednji_tecaj")
    @JsonDeserialize(using = FlexibleBigDecimalDeserializer.class)
    private BigDecimal srednji;
    @JsonProperty("prodajni_tecaj")
    @JsonDeserialize(using = FlexibleBigDecimalDeserializer.class)
    private BigDecimal prodajni;

    public int getBrojTecajnice() {
        return brojTecajnice;
    }

    public void setBrojTecajnice(int brojTecajnice) {
        this.brojTecajnice = brojTecajnice;
    }

    public LocalDate getDatumPrimjene() {
        return datumPrimjene;
    }

    public void setDatumPrimjene(LocalDate datumPrimjene) {
        this.datumPrimjene = datumPrimjene;
    }

    public String getDrzava() {
        return drzava;
    }

    public void setDrzava(String drzava) {
        this.drzava = drzava;
    }

    public String getSifraValute() {
        return sifraValute;
    }

    public void setSifraValute(String sifraValute) {
        this.sifraValute = sifraValute;
    }

    public String getValuta() {
        return valuta;
    }

    public void setValuta(String valuta) {
        this.valuta = valuta;
    }

    public int getJedinica() {
        return jedinica;
    }

    public void setJedinica(int jedinica) {
        this.jedinica = jedinica;
    }

    public BigDecimal getKupovni() {
        return kupovni;
    }

    public void setKupovni(BigDecimal kupovni) {
        this.kupovni = kupovni;
    }

    public BigDecimal getSrednji() {
        return srednji;
    }

    public void setSrednji(BigDecimal srednji) {
        this.srednji = srednji;
    }

    public BigDecimal getProdajni() {
        return prodajni;
    }

    public void setProdajni(BigDecimal prodajni) {
        this.prodajni = prodajni;
    }
}
