package com.milak.repository;

import com.milak.model.Tecaj;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Mapper
@Repository
public interface TecajRepository {

    @Insert("insert into tecaj (\n" +
            "            broj_tecajnice,\n" +
            "            datum_primjene,\n" +
            "            drzava,\n" +
            "            sifra_valute,\n" +
            "            valuta,\n" +
            "            jedinica,\n" +
            "            kupovni,\n" +
            "            srednji,\n" +
            "            prodajni\n" +
            "        ) values (\n" +
            "            ${brojTecajnice},\n" +
            "            ${datumPrimjene},\n" +
            "            ${drzava},\n" +
            "            ${sifraValute},\n" +
            "            ${valuta},\n" +
            "            ${jedinica},\n" +
            "            ${kupovni},\n" +
            "            ${srednji},\n" +
            "            ${prodajni}\n" +
            "        )")
    void insertTecaj(Tecaj tecaji);

    @Select("select * from tecaj where datum_primjene = #{datum}")
    List<Tecaj> getTecajByDate(LocalDate date);

}
