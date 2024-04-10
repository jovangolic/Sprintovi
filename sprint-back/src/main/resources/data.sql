INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (1,'miroslav@maildrop.cc','miroslav','$2y$12$NH2KM2BJaBl.ik90Z1YqAOjoPgSd0ns/bF.7WedMxZ54OhWQNNnh6','Miroslav','Simic','ADMIN');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (2,'tamara@maildrop.cc','tamara','$2y$12$DRhCpltZygkA7EZ2WeWIbewWBjLE0KYiUO.tHDUaJNMpsHxXEw9Ky','Tamara','Milosavljevic','KORISNIK');
INSERT INTO korisnik (id, e_mail, korisnicko_ime, lozinka, ime, prezime, uloga)
              VALUES (3,'petar@maildrop.cc','petar','$2y$12$i6/mU4w0HhG8RQRXHjNCa.tG2OwGSVXb0GYUnf8MZUdeadE4voHbC','Petar','Jovic','KORISNIK');

			
INSERT INTO stanje (id, ime) VALUES(1, 'nov');
INSERT INTO stanje (id, ime) VALUES(2, 'u toku');
INSERT INTO stanje (id, ime) VALUES(3, 'zavrsen');
			
INSERT INTO sprint (id, ime, ukupno_bodova) VALUES(1, 'Sprint1', '13');
INSERT INTO sprint (id, ime, ukupno_bodova) VALUES(2, 'Sprint2', '8');


INSERT INTO zadatak (id, ime, zaduzeni, bodovi, sprint_id, stanje_id)
			VALUES(1, 'Kreirati rest servis', 'Nikola', 8, 1, 3);
INSERT INTO zadatak (id, ime, zaduzeni, bodovi, sprint_id, stanje_id)
			VALUES(2, 'Kreirati pocetnu stranicu', 'Bane', 5, 1, 2);
INSERT INTO zadatak (id, ime, zaduzeni, bodovi, sprint_id, stanje_id)
			VALUES(3, 'Kreirati logo', 'Ana', 8, 2, 1);

