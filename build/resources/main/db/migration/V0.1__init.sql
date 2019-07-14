create table tecaj
(
	id int auto_increment,
	broj_tecajnice int null,
	datum_primjene date,
	drzava varchar2(255),
	sifra_valute varchar2(255),
	valuta varchar2(255),
	jedinica int,
	kupovni decimal,
	srednji decimal,
	prodajni decimal
);

create unique index tecaj_id_uindex
	on tecaj (id);

alter table tecaj
	add constraint tecaj_pk
		primary key (id);
