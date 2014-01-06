
DROP TABLE IF EXISTS z2r;
DROP TABLE IF EXISTS Reservierung;
DROP TABLE IF EXISTS Zusatzleistung;
DROP TABLE IF EXISTS Gast;

CREATE TABLE Gast(
	Nr INT(2) NOT NULL AUTO_INCREMENT,
	Name VARCHAR(20),
	Email VARCHAR(30),
	IstStammKunde BOOLEAN,
	PRIMARY KEY(Nr)
);

CREATE TABLE Reservierung(
	nr INT(2) NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(nr),
	ZimmerNr INT(2),
	gast_id int(2),
	FOREIGN KEY (gast_id) REFERENCES gast(nr)
);

CREATE TABLE Zusatzleistung(
	Nr INT(2) NOT NULL AUTO_INCREMENT,
	LeistungsArt VARCHAR(30) UNIQUE,
	PRIMARY KEY (Nr)
);


CREATE TABLE z2r(
	id int(2) NOT NULL AUTO_INCREMENT,
	r_id int(2),
	FOREIGN KEY (r_id) REFERENCES reservierung(nr),
	z_id int(2),
	FOREIGN KEY (z_id) REFERENCES zusatzleistung(nr),
	PRIMARY KEY(id)
)

