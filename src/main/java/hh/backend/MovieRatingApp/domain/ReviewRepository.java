package hh.backend.MovieRatingApp.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long>{

	List<Review> findByName(String name);
	
	Optional<Review> findById(Long id);
}
