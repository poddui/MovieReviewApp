package hh.backend.MovieRatingApp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.backend.MovieRatingApp.domain.*;
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(ReviewRepository mrepository, CategoryRepository crepository, UserRepository urepository) 
	{return (args) -> {
		
		Category c1 = new Category("Action");
		Category c2 = new Category("Drama");
		Category c3 = new Category("Horror");
		Category c4 = new Category("Thriller");
		Category c5 = new Category("Scifi");
		Category c6 = new Category("Crime");
		Category c7 = new Category("Romance");
		Category c8 = new Category("Fantasy");
		Category c9 = new Category("Comedy");
		Category c10 = new Category("War");
		
		crepository.save(c1);
		crepository.save(c2);
		crepository.save(c3);
		crepository.save(c4);
		crepository.save(c5);
		crepository.save(c6);
		crepository.save(c7);
		crepository.save(c8);
		crepository.save(c9);
		crepository.save(c10);
		
		User u1 = new User("jimi", "$2a$12$wrQT802CTybBIBLl1ABzuu3Na6WR.owKc7gmJ2OEb5spbCvJN7hbO", "jimi@google.fi", "ADMIN");
		User u2 = new User("timo", "$2a$12$ta4Vqdvr49fhFj1EbZjeO.brdI0O82rDDAnHIMsQeJgmtYUxjCbGO", "timo@google.fi", "USER");
		User u3 = new User("olivia", "$2a$12$wrQT802CTybBIBLl1ABzuu3Na6WR.owKc7gmJ2OEb5spbCvJN7hbO", "olivia@google.fi", "USER");
		User u4 = new User("nelli", "$2a$12$wrQT802CTybBIBLl1ABzuu3Na6WR.owKc7gmJ2OEb5spbCvJN7hbO", "nelli@google.fi", "ADMIN");
		User u5 = new User("mikko", "$2a$12$ta4Vqdvr49fhFj1EbZjeO.brdI0O82rDDAnHIMsQeJgmtYUxjCbGO", "mikko@google.fi", "USER");
		User u6 = new User("esko", "$2a$12$ta4Vqdvr49fhFj1EbZjeO.brdI0O82rDDAnHIMsQeJgmtYUxjCbGO", "esko@google.fi", "USER");
		
		urepository.save(u1);
		urepository.save(u2);
		urepository.save(u3);
		urepository.save(u4);
		urepository.save(u5);
		urepository.save(u6);
		
		Review m1 = new Review("Pulp Fiction", 1994, "Quentin Tarantino", c6, u2, 8, "Yksi parhaimmista koskaan tehdyistä elokuvista! Pulp Fiction sisältää kolme toisiinsa omaperäisellä tavalla nivoutuvaa tarinaa jossa samat elokuvan päähenkilöt esiintyvät välillä suurissa, välillä pienissä rooleissa.");
		Review m2 = new Review("Intersterllar", 2014, "Christoper Nolan", c5, u3, 7, "Interstellar vaikuttaa aluksi varsin kiinnostavalta elokuvalta. Lohduttomaan, ilmastonmuutoksen, sotien ja kasvitautien raiskaamaan lähitulevaisuuteen sijoittuva elokuva maalaa melko realistista kuvaa siitä, mihin maailmamme on hyvää vauhtia matkalla.");
		Review m3 = new Review("Seven", 1995, "David Fincher", c4, u2, 9, "Kokonaisuutena Seitsemän on kylmän tehokas ja armottoman julma draama moraalittomasta maailmasta, minkä ainoa järkevä, harkittuja tekoja ja selkeästi motivoitu henkilö on murhaaja.");
		Review m4 = new Review("American Psycho", 2000, "Mary Harron", c4, u1, 10, "Tämä on yksi suosikkielokuvistani! Christian Bale esittää loistavasti psykopaattisen liikemiehen roolia ja elokuvan tunnelma on todella ahdistava ja jännittävä. Suosittelen kaikille psykologisista trillereistä pitäville!");
		Review m5 = new Review("The Godfather", 1972, "Francis Ford Coppola", c6, u5, 9, "Todellinen gangsteriklassikko, joka kertoo Corleonen perheen tarinan ja sen taistelusta vallan ja kunnian puolesta.");
		Review m6 = new Review("Forrest Gump", 1994, "Robert Zemeckis", c2, u6, 5, "Tämä elokuva kertoo Forrest Gumpin tarinan, joka on yksinkertainen mutta hyväsydäminen ihminen, joka elää kiehtovan elämän ja vaikuttaa suureen osaan Yhdysvaltain historian tapahtumia.");
		Review m7 = new Review("The Dark Knight", 2008, "Christopher Nolan", c1, u4, 9, "Tämä Batman-elokuva on kiehtova tarina siitä, miten Jokeri yrittää tuhota Gotham Cityn, ja Batmanin taistelu häntä vastaan, joka vaatii uusia uhrauksia ja henkilökohtaisia valintoja.");
		Review m8 = new Review("The Shining", 1980, "Stanley Kubrick", c3, u2, 6, "The Shining on Kubrickin kauhuklassikko, joka perustuu Stephen Kingin samannimiseen romaaniin, joka kertoo Jack Torrancen tarinan, joka saapuu talvella yksinäiselle hotellille vaimonsa ja poikansa kanssa.");
		Review m9 = new Review("Inception", 2010, "Christopher Nolan", c5, u3, 9, "Tämä elokuva on henkeäsalpaava sekoitus unen ja todellisuuden välillä, joka kertoo Dom Cobbista, varkaasta, joka pystyy tunkeutumaan ihmisten alitajuntaan varastamaan arvokkaita salaisuuksia.");
		Review m10 = new Review("The Shawshank Redemption", 1994, "Frank Darabont", c2, u3, 8, "Loistava elokuva, joka koskettaa katsojaa syvältä. Vankilassa elämän opettavainen tarina, joka saa varmasti kyyneliin.");
		Review m11 = new Review("The Matrix", 1999, "The Wachowskis", c5, u1, 10, "Tämä elokuva on kuin aikakapseli 90-luvulle. Hieno visuaalinen ilme ja hyvä juoni.");
		Review m12 = new Review("Fight Club", 1999, "David Fincher", c2, u4, 8, "Uskomaton elokuva, joka kertoo aivan erilaisesta klubista kuin mikä yleensä tulee ensimmäisenä mieleen.");
		
		mrepository.save(m1);
		mrepository.save(m2);
		mrepository.save(m3);
		mrepository.save(m4);
		mrepository.save(m5);
		mrepository.save(m6);
		mrepository.save(m7);
		mrepository.save(m8);
		mrepository.save(m9);
		mrepository.save(m10);
		mrepository.save(m11);
		mrepository.save(m12);
		
	};

}
	
}