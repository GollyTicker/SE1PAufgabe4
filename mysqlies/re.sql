
-- Zum starten der Datenbank.
-- CMD mit Adminrechten ausführen und "startmsql" "tun".
-- beenden des Servers mit "stopmysql".

drop table if exists umfasst;
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
	Nr int(2) not null AUTO_INCREMENT,
	primary key(Nr),
	ZimmerNr int(2),
	gastID int(2),
	foreign key (gastID) references gast(Nr)
);

create table Zusatzleistung(
	Nr int(2) not null AUTO_INCREMENT,
	LeistungsArt varchar(30) unique,
	primary key (Nr)
);

create table umfasst(
	id int(2) not null AUTO_INCREMENT,
	rID int(2),
	foreign key (rID) references reservierung(Nr),
	zlID int(2),
	foreign key (zlID) references zusatzleistung(Nr),
	primary key(id)
)

