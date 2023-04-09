package hh.backend.MovieRatingApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private int release_year;
	private String director;
	
	@ManyToOne
    @JoinColumn ( name = "categoryid")
	private Category category;
	@JsonIgnoreProperties ("jsonmovies")
	
	@ManyToOne
	@JoinColumn ( name = "username")
	private User user;
	private int rating;
	private String user_review;
	
	public Review(String name, int release_year, String director, Category category, User user,
			int rating, String user_review) {
		super();
		this.name = name;
		this.release_year = release_year;
		this.director = director;
		this.category = category;
		this.user = user;
		this.rating = rating;
		this.user_review = user_review;
	}
	
	public Review() {
		super();
		this.name = null;
		this.release_year = 0;
		this.director = null;
		this.category = null;
		this.user = null;
		this.rating = 0;
		this.user_review = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRelease_year() {
		return release_year;
	}

	public void setRelease_year(int release_year) {
		this.release_year = release_year;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
	
	public String getUser_review() {
		return user_review;
	}

	public void setUser_review(String user_review) {
		this.user_review = user_review;
	}

	@Override
	public String toString() {
	    if (this.category != null)
	        if (this.user != null) {
	            return "Review [id=" + id + ", name=" + name + ", release_year=" + release_year + ", director=" + director + ", category=" + this.getCategory() + ", user=" + this.getUser() + ", rating=" + rating + ", user_review=" + user_review + "]";
	        } else {
	            return "Review [id=" + id + ", name=" + name + ", release_year=" + release_year + ", director=" + director + ", category=" + this.getCategory() + ", rating=" + rating + ", user_review=" + user_review + "]";
	        }
	    else {
	        return "Review [id=" + id + ", name=" + name + ", release_year=" + release_year + ", director=" + director + ", rating=" + rating + ", user_review=" + user_review + "]";
	    }
	}
}
