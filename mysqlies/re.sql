
-- Zum starten der Datenbank.
-- CMD mit Adminrechten ausführen und "startmsql" "tun".
-- beenden des Servers mit "stopmysql".

drop table if exists z2r;
drop table if exists Reservierung;
drop table if exists Zusatzleistung;
drop table if exists Gast;


create table Gast(
	Nr int(2) not null AUTO_INCREMENT,
	Name varchar(20),
	Email varchar(30),
	IstStammKunde boolean,
	primary key(Nr)
);

create table Reservierung(
	nr int(2) not null AUTO_INCREMENT,
	primary key(nr),
	ZimmerNr int(2),
	gast_id int(2),
	foreign key (gast_id) references gast(nr)
);

create table Zusatzleistung(
	Nr int(2) not null AUTO_INCREMENT,
	LeistungsArt varchar(30) unique,
	primary key (Nr)
);

create table umfasst(
	id int(2) not null AUTO_INCREMENT,
	r_id int(2),
	foreign key (r_id) references reservierung(nr),
	z_id int(2),
	foreign key (z_id) references zusatzleistung(nr),
	primary key(id)
)

