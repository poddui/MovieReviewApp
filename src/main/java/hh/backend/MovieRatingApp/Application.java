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
		
		Category c1 = new Category("Scifi");
		Category c2 = new Category("Toiminta");
		Category c3 = new Category("Rikos");
		
		crepository.save(c1);
		crepository.save(c2);
		crepository.save(c3);
		
		User u1 = new User("admin", "$2a$12$wrQT802CTybBIBLl1ABzuu3Na6WR.owKc7gmJ2OEb5spbCvJN7hbO", "admin@google.fi", "ADMIN");
		User u2 = new User("guest", "$2a$12$lZy2kikIAMYQyeSb4erAX.hdMZM0SfTb8WaqkmWDgL1x.pySNjpAW", "guest@google.fi", "GUEST");
		User u3 = new User("user", "$2a$12$ta4Vqdvr49fhFj1EbZjeO.brdI0O82rDDAnHIMsQeJgmtYUxjCbGO", "user@google.fi", "USER");
		
		urepository.save(u1);
		urepository.save(u2);
		urepository.save(u3);
		
		Review m1 = new Review("Pulp Fiction", 1994, "Quentin Tarantino", c3, u2, 5, "Hyvä");
		Review m2 = new Review("Intersterllar", 2014, "Christoper Nolan", c1, u1, 7, "Mainio");
		Review m3 = new Review("John Wick: Chapter 3", 2019, "Chad Stahelski", c2, u3, 3, "Menevä");
		
		mrepository.save(m1);
		mrepository.save(m2);
		mrepository.save(m3);
		
	};

}
	
}