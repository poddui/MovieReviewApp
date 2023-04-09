package hh.backend.MovieRatingApp.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long category_id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
    @JsonIgnore
    @JsonIgnoreProperties ("jsoncategories") 
	private List<Review> movies;
	

	public Category(String name) {
		super();
		this.name = name;
	}
	
	public Category() {
		super();
		this.name = null;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Category [category_id=" + category_id + ", name=" + name + ", movies=" + movies + "]";
	}


	
	
	

}
